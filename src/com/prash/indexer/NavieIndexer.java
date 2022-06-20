package com.prash.indexer;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayDeque;
import java.util.Deque;

public class NavieIndexer {
	
private Deque<Weblink> queue = new ArrayDeque<>();
	
	private static class Weblink {
		private long id;
	    private String title;
		private String url;
	    private String host;
	    
	    private volatile String htmlPage;
	    
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getHost() {
			return host;
		}
		public void setHost(String host) {
			this.host = host;
		}
		public String getHtmlPage() {
			return htmlPage;
		}
		public void setHtmlPage(String htmlPage) {
			this.htmlPage = htmlPage;
		}
	}
	
	private static class Downloader implements Runnable {
		private Weblink weblink;
		public Downloader(Weblink weblink) {
			this.weblink = weblink;
		}
		public void run() {
			try {
				String htmlPage = HttpConnect.download(weblink.getUrl());
				weblink.setHtmlPage(htmlPage);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static class Indexer implements Runnable {
		private Weblink weblink;
		private Indexer(Weblink weblink) {
			this.weblink = weblink;
		}
		public void run() {			
			while (true) {
				String htmlPage = weblink.getHtmlPage();
				if (htmlPage != null) {
					index(htmlPage);
					
					break;
				} else {
					//System.out.println(weblink.getId() + " not yet downloaded!");
				}
			}			
		}
		private void index(String text) {
			if (text != null) {
				System.out.println("\nIndexed: " + weblink.getId() + "\n");
			}
		}
	}
	
	public void go() {
		while (queue.size() > 0) {
			Weblink weblink = queue.remove();
			Thread downloaderThread = new Thread(new Downloader(weblink));
			Thread indexerThread = new Thread(new Indexer(weblink));
			
			downloaderThread.start();
			indexerThread.start();
		}
	}
	public void add(Weblink link) {
		queue.add(link);
	}
	public Weblink createWeblink(long id, String title, String url, String host) {
		Weblink weblink = new Weblink();
    	weblink.setId(id);
    	weblink.setTitle(title);
    	weblink.setUrl(url);
    	weblink.setHost(host);
    	return weblink;
    }

	public static void main(String[] args) {
		NavieIndexer naiveIndexer = new NavieIndexer();
		naiveIndexer.add(naiveIndexer.createWeblink(2000, "Taming Tiger, Part 2", "https://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html", "https://www.javaworld.com"));
		naiveIndexer.add(naiveIndexer.createWeblink(2001, "How do I import a pre-existing Java project into Eclipse and get up and running?", "https://stackoverflow.com/questions/142863/how-do-i-import-a-pre-existing-java-project-into-eclipse-and-get-up-and-running", "https://www.stackoverflow.com"));
		naiveIndexer.add(naiveIndexer.createWeblink(2002, "Interface vs Abstract Class", "https://mindprod.com/jgloss/interfacevsabstract.html", "https://mindprod.com"));
		naiveIndexer.add(naiveIndexer.createWeblink(2004, "Virtual Hosting and Tomcat", "https://tomcat.apache.org/tomcat-6.0-doc/virtual-hosting-howto.html", "https://tomcat.apache.org"));
		naiveIndexer.go();

	}

}

package my.first.threads;

public class Mythread extends Thread {

	public void run() {
		System.out.println("inside run..");
		go();
	}

	private void go() {
		System.out.println("inside go..");
		more();
	}

	private void more() {
		System.out.println("inside more..");
		
	}
	
	public static void main(String[] args) {
		Thread mt = new Mythread();
		mt.start();
	}
}

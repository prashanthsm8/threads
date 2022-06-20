package my.first.threads;

import java.util.concurrent.TimeUnit;

public class StopThread {

	private static volatile boolean stop;
	
	public static void main(String[] args) throws InterruptedException {
		 new Thread (new Runnable() {
			
			@Override
			public void run() {
				
				while(!stop) {
					System.out.println("In While");
				}
			}
		}).start();
		 
		 Thread.currentThread().sleep(20);
		 TimeUnit.SECONDS.sleep(1);
		 stop = true;

	}

}

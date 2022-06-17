package my.first.threads;

public class ThreadPriorityDemo {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName());
		
		Thread t1 = new Thread(new EmailCamp());
		Thread t2 = new Thread(new DataAgg());
		
		t1.setName("EmailCamp thread");
		t2.setName("DataAgg thread");
		
		t1.setPriority(Thread.MAX_PRIORITY);
		t2.setPriority(Thread.MAX_PRIORITY);
		
		t1.start();
		t2.start();
		
		try {
			t2.join(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Inside main");

	}

}

class EmailCamp implements Runnable {

	@Override
	public void run() {
		
		for(int i=0;i<=10;i++)
		{
			System.out.println(Thread.currentThread().getName());
			if(i==5)
			{
				Thread.currentThread().yield();
			}
		}
	}
	
}

class DataAgg implements Runnable {

	@Override
	public void run() {
		
		for(int i=0;i<=10;i++)
		{
			System.out.println(Thread.currentThread().getName());
			/*
			 * if(i==5) { Thread.currentThread().yield(); }
			 */
		}
	}
	
}
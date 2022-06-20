package my.first.threads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MeetupsSimulator {
	
	public static class MeetupEvent{
		
		private String name;
		private AtomicInteger count = new AtomicInteger(1);
		
		public AtomicInteger getCount() {
			return count;
		}

		public MeetupEvent(String name)
		{
			this.name = name;
		}
		
		public void attending(int guestCount)
		{
			if(guestCount==1)
			{
				count.incrementAndGet();
			}
			else
			{
				count.addAndGet(guestCount);
			}
		}
		
		public void notattending(int guestCount)
		{
			if(guestCount==1)
			{
				count.decrementAndGet();
			}
			else
			{
				boolean updated = false;
				while(!updated)
				{
					int currentcount = count.get();
					int newcount = currentcount - guestCount;
					updated = count.compareAndSet(currentcount, newcount);
				}
			}
		}
	}

	public static void main(String[] args) {
		
		MeetupEvent js = new MeetupEvent("Java metts");
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				js.attending(4);
				System.out.println(Thread.currentThread().getName()+":"+js.getCount());
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				js.attending(3);
				System.out.println(Thread.currentThread().getName()+":"+js.getCount());
				js.notattending(3);
				System.out.println(Thread.currentThread().getName()+":"+js.getCount());
			}
		});
		
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				js.attending(1);
				System.out.println(Thread.currentThread().getName()+":"+js.getCount());
			}
		});
		
		t1.start();
		sleep(1);
		t2.start();
		sleep(2);
		t3.start();
		sleep(2);
	}

	private static void sleep(int t2)
	{
		try {
			TimeUnit.SECONDS.sleep(t2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

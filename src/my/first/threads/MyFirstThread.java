package my.first.threads;

public class MyFirstThread {

	public static void main(String[] args) {
		
		Task task = new Task();
		
		Thread t1 = new Thread(task);
		t1.start();
		
		try {
			t1.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Inside main..");
	}

}

class Task implements Runnable{

	@Override
	public void run() {
		System.out.println("Inside run.. actual logic");
		go();
	}
	
	private void go()
	{
		System.out.println("inside go...");
		more();
	}
	
	private void more()
	{
		System.out.println("Inside more...");
	}
	
	
	
}

package my.first.threads;

public class RaceCondition {

	public static void main(String[] args) {
	  BankAccount bk = new BankAccount();
	  bk.setBalance(100);
	  Thread jon = new Thread(bk);
	  Thread anita = new Thread(bk);
	  
	  jon.setName("jon");
	  anita.setName("anita");
	  
	  jon.start();
	  anita.start();
	  
	}

}

class BankAccount implements Runnable{

	private int balance;
	public void setBalance(int balance) {
		this.balance = balance;
	}
	@Override
	public void run() {
		makeWithdraw(75);
		if(balance < 0)
		{
			System.out.println("money ovedrwan!!");
		}
		
	}
	
	private synchronized void makeWithdraw(int amount) {
		if(balance>amount)
		{
			System.out.println(Thread.currentThread().getName()+"is about to withdraw");
			balance-=amount;
			System.out.println(Thread.currentThread().getName()+"has withdrawn: " + amount);
		}
		else
		{
			System.out.println("no balance");
		}
	}
	
}
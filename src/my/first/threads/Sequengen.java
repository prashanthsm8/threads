package my.first.threads;

public class Sequengen {

	public static void main(String[] args) {
		
		String tt ="";
		for(int i=41300;i<=41356;i++)
		{
			tt=tt+Integer.valueOf(i)+",";
		}

		System.out.println(tt);
	}

}

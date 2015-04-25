package Test;

public class Testor {
	
	public static void main(String[] args){
				
		Thread[] t = new Thread[3];
		
		t[0] = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Client c1 = new Client("4 5 * 2 + = ");
				c1.start();
			}
		});
		t[1] = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Client c2 = new Client("6 1 + = q");
				c2.start();
			}
		});
		t[2] = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Client c3 = new Client("4 0 / = ");
//				Client c3 = new Client("4 =");
				c3.start();
			}
		});
		
		for (Thread thread : t) {
			thread.start();
		}
		
		Server so = new Server();
		so.start();

	}
}

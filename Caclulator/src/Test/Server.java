package Test;

class MyRunnable implements Runnable{
	private int threadId;
	
	MyRunnable(int id){
		threadId = id;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(threadId);
	}
	
}

public class Server {
	
	public static void spawnWorker(int clientId){
		MyRunnable t1 = new MyRunnable(0);
	}
	
	public static void main(String[] args){
		
		MyRunnable t2 = new MyRunnable(1);
		
	}	 
}

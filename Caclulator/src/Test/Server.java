package Test;

import java.util.HashSet;
import java.util.Hashtable;
//
class MyRunnable implements Runnable{
	private String threadId;
	
	MyRunnable(){
		threadId = Thread.currentThread().getName();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		//while(sharedQueue.top.client == threadId)
		
		//		threadStack().push()
		//		sharedQueue.pop()
	}
	
}

public class Server {
	//The pool of worker threads
	HashSet<String> threadPool = new HashSet<String>();
			
	Server(){
		//while sharedQueue not empty
		//   if(threadPool does not contains the thread with name as ClienId)
		//		 spawnWorker(clientId)
		//		 push to the shared Queue
	}
	
	public static void spawnWorker(String clientId){
		//Create a new runnable
		MyRunnable r = new MyRunnable();
		new Thread(r, clientId).start();
	}
			
	public static void main(String[] args){
		//	Client client = new Client();
		//System.out.println(client.clientId.toString());
		//MyRunnable r1 = new MyRunnable();
		//new Thread(r1, "Rohit").start();
		//new Thread(r1, "Rohit").start();
	}	 
}

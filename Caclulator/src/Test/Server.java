package Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Stack;
//
class MyRunnable implements Runnable{
	private String threadId;
	Stack<String> st = new Stack<String>();
	MyRunnable(){
		threadId = Thread.currentThread().getName();
	}
	
	private void pushInStack(Task theTop){
		String[] syms = {"*", "+", "-", "/"};
		if(Arrays.asList(syms).contains(theTop.getToken())){
			
			String operator = theTop.getToken();
			int rightOperand = Integer.parseInt(st.pop());
			int leftOperand = Integer.parseInt(st.pop());
			int result = computeBinary(operator, leftOperand, rightOperand);
			st.push(Integer.toString(result));
		}
		else if(theTop.getToken() == "="){
			System.out.println(st.pop());
		}
		else{
			st.push(theTop.getToken());
		}
	}
	
	private int computeBinary(String operator, int leftOperand,	int rightOperand) {
		// TODO Auto-generated method stub
		switch (operator) {
		case "+":
			return leftOperand + rightOperand;
		case "-":
			return leftOperand - rightOperand;
		case "*":
			return leftOperand * rightOperand;
		case "/":
			return leftOperand / rightOperand;
		default:
			System.out.println("The Impossible");
			break;
		}
		return 0;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Task theTop = Constants.sharedQueue.element();		//	Make sync
		while(true)//while(sharedQueue.top.client == threadId)
		{	
			if(theTop.getToken() == "q"){
				break;
			}
			else if(theTop.getClientId().toString() == threadId){
				if((theTop.getToken() == ".")){
					Constants.sharedQueue.remove();		//	Make sync
					break;
				}	
				pushInStack(theTop);
				Constants.sharedQueue.remove();
			}
			theTop = Constants.sharedQueue.element();		//	Make sync	
		}
		Server.threadPool.remove(threadId);
	}
	
}

public class Server {
	//The pool of worker threads
	static HashSet<String> threadPool = new HashSet<String>();
	static Task theTop = new Task();
	Server(){
		
			while(true){
				if(! Constants.sharedQueue.isEmpty()){
					theTop = Constants.sharedQueue.element();			//Make sync
					if((theTop.getToken() == "q")){
						Constants.sharedQueue.remove();
						break;
					}
					else if(! threadPool.contains(theTop.getClientId().toString())){
						spawnWorker(theTop.getClientId().toString());
						threadPool.add(theTop.getClientId().toString());	
					}
				}
			}
		
	}
	
	public void spawnWorker(String clientId){
		//Create a new runnable
		MyRunnable r = new MyRunnable();
		new Thread(r, clientId).start();
	}
			
	public static void main(String[] args){
		
		//Server s = new Server();
		//	Client client = new Client();
		//System.out.println(client.clientId.toString());
		//MyRunnable r1 = new MyRunnable();
		//new Thread(r1, "Rohit").start();
		//new Thread(r1, "Rohit").start();
	}	 
}

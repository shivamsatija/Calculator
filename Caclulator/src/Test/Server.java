package Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Stack;
//
class MyRunnable implements Runnable{
	private String threadId;
	Stack<String> st = new Stack<String>();
		
	private void pushInStack(Task theTop){
		String[] syms = {"*", "+", "-", "/"};
		if(Arrays.asList(syms).contains(theTop.getToken())){
			
			String operator = theTop.getToken();
			int rightOperand = Integer.parseInt(st.pop());
			int leftOperand = Integer.parseInt(st.pop());
			int result = computeBinary(operator, leftOperand, rightOperand);
			st.push(Integer.toString(result));
		}
		else if(theTop.getToken().equals("=")){
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
		threadId = Thread.currentThread().getName();
		
		while(true){
			
			if(SharedQueue.hasElem()){
			
				synchronized (Server.class) {
					//System.out.println(threadId + " " + SharedQueue.theSharedQueue.element().getClientId().toString());
					if(SharedQueue.hasElem()){
						if(SharedQueue.theSharedQueue.element().getToken().equals("q")){
							break;
						}
						else if(SharedQueue.theSharedQueue.element().getClientId().toString().equals(threadId)){
							pushInStack(SharedQueue.theSharedQueue.element());
							SharedQueue.theSharedQueue.remove();
						}
					}
				}
			}
		}
	}	
	
}

public class Server {
	//The pool of worker threads
	static HashSet<String> threadPool = new HashSet<String>();
	
	
	public void start(){
		while(true){
			if(SharedQueue.hasElem()){
				synchronized (Server.class) {				
					if(SharedQueue.hasElem()){
						if(SharedQueue.theSharedQueue.element().getToken().equals("q")){
							break;
						}
						if(! threadPool.contains(SharedQueue.theSharedQueue.element().getClientId().toString())){
							//System.out.println("Does not contain");
							//System.out.println(threadPool.size());
							threadPool.add(SharedQueue.theSharedQueue.element().getClientId().toString());
							//System.out.println(threadPool.size());
							spawnWorker(SharedQueue.theSharedQueue.element().getClientId().toString());
						}
					}
				//	break;
//					for (String s : threadPool) {
//						System.out.println(s +" " + SharedQueue.theSharedQueue.element().getClientId().toString());
//					}
				}
			}
		}
	}
			
	static void spawnWorker(String clientId){
		//Create a new runnable
		MyRunnable r = new MyRunnable();
		new Thread(r, clientId).start();
	}
			
 
}

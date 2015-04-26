package Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Stack;

class MyRunnable implements Runnable{
/*This class is the runnable implementation for the task done by each worker thread of the server*/
	
	private String threadId;
	private boolean isExceptionRaised=false;
	Stack<String> st = new Stack<String>();
		 
	/*
	  The function pushes a token to the thread stack
	  and does the logic to evaluate the existing elements
	 */
	private void pushInStack(Task theTop){
		String[] syms = {"*", "+", "-", "/"};
		if(Arrays.asList(syms).contains(theTop.getToken())){
			
			String operator = theTop.getToken();
			double rightOperand = Double.parseDouble(st.pop());
			double leftOperand = Double.parseDouble(st.pop());
			if(rightOperand==0 && operator.equals("/")){
				this.isExceptionRaised=true;
				st.push("0");
			}
			else{
				double result = computeBinary(operator, leftOperand, rightOperand);
				st.push(Double.toString(result));
			}
		}
		else if(theTop.getToken().equals("=")){
			Result result=new Result();
			result.setValue(Double.parseDouble(st.firstElement()));
			result.setClientRead(false);
			if(this.isExceptionRaised==true)
				result.setExceptionRaised(true);
			Shared.result.put(threadId, result);
			st.pop();
		}
		else{
			st.push(theTop.getToken());
		}
	}
	
	/*
	 The function evaluate left operand "operator" right operator
	 */
	
	private  double computeBinary(String operator, double leftOperand,	double rightOperand) {
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
	
	/*
	 	This function checks elements from the shared queue and does the popping only if its client id is same as the threads id
	 	Each server thread runs the same logic.
	 */
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		threadId = Thread.currentThread().getName();
		
		while(true){
			
			if(Shared.hasElem()){
			
				synchronized (Server.class) {
				
					if(Shared.hasElem()){
						if(Shared.theSharedQueue.element().getToken().equals("q")){
							break;
						}
						
						else if(Shared.theSharedQueue.element().getClientId().toString().equals(threadId)){
							pushInStack(Shared.theSharedQueue.element());
							
							if(Shared.theSharedQueue.element().getToken().equals("=")){
								Shared.theSharedQueue.remove();
								Server.threadPool.remove(threadId);
								break;
							}
							else
								Shared.theSharedQueue.remove();
							
						}
					}
				}
			}
		}
	}	
	
}

public class Server {
	/*This class corresponds to a server object and is the main thread 
	which spawns new worker threads only when a new client sends an expression token
	 */
	
	
	//The pool of worker threads
	static HashSet<String> threadPool = new HashSet<String>();
	
	
	public void start(){
		while(true){
			if(Shared.hasElem()){
				synchronized (Server.class) {				
					if(Shared.hasElem()){
						if(Shared.theSharedQueue.element().getToken().equals("q")){
							Shared.serverQuit=true;
							break;
						}
						if(! threadPool.contains(Shared.theSharedQueue.element().getClientId().toString())){
							threadPool.add(Shared.theSharedQueue.element().getClientId().toString());
							spawnWorker(Shared.theSharedQueue.element().getClientId().toString());
						}
					}
				}
			}
		}
	}
			
	/*Starts a worker thread which handles the client with the name passed as the parameter*/
	static void spawnWorker(String clientId){
		//Create a new runnable
		MyRunnable r = new MyRunnable();
		new Thread(r, clientId).start();
	}
			
 
}

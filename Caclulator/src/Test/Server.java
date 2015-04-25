package Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Stack;
//
class MyRunnable implements Runnable{
	private String threadId;
	private boolean isExceptionRaised=false;
	Stack<String> st = new Stack<String>();
		
	private void pushInStack(Task theTop){
		String[] syms = {"*", "+", "-", "/"};
		if(Arrays.asList(syms).contains(theTop.getToken())){
			
			String operator = theTop.getToken();
			double rightOperand = Double.parseDouble(st.pop());
			double leftOperand = Double.parseDouble(st.pop());
			if(rightOperand==0 && operator.equals("/")){
//				System.out.println("Divide by zero is not possible");
				this.isExceptionRaised=true;
				st.push("0");
			}
			else{
				double result = computeBinary(operator, leftOperand, rightOperand);
				st.push(Double.toString(result));
			}
		}
		else if(theTop.getToken().equals("=")){
//			System.out.println(st.pop());
			Result result=new Result();
			result.setValue(Double.parseDouble(st.firstElement()));
			result.setClientRead(false);
			if(this.isExceptionRaised==true)
				result.setExceptionRaised(true);
			Shared.result.put(threadId, result);
//			System.out.println(result.getValue()+threadId);
			st.pop();
		}
		else{
			st.push(theTop.getToken());
		}
	}
	
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
	
		
	@Override
	public void run() {
		// TODO Auto-generated method stub
		threadId = Thread.currentThread().getName();
		
		while(true){
			
			if(Shared.hasElem()){
			
				synchronized (Server.class) {
					//System.out.println(threadId + " " + SharedQueue.theSharedQueue.element().getClientId().toString());
					if(Shared.hasElem()){
						if(Shared.theSharedQueue.element().getToken().equals("q")){
//							Shared.serverQuit=true;
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
							//System.out.println("Does not contain");
							//System.out.println(threadPool.size());
							threadPool.add(Shared.theSharedQueue.element().getClientId().toString());
							//System.out.println(threadPool.size());
							spawnWorker(Shared.theSharedQueue.element().getClientId().toString());
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

package Test;

import java.util.ArrayDeque;
import java.util.Queue;

public class CalculatorMain {
	
	Queue <Task> sharedQueue= new ArrayDeque<Task>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Queue <Task> sharedQueue= new ArrayDeque<Task>();
		Client c1=new Client(sharedQueue);
		c1.setClientId(c1);
		c1.setExpression("7 365 * 43 +");
//		for(Task task : c1.getTokens()){
//			System.out.println(task.clientId + " " + task.token);
////			System.out.println("\n");
//		}
//		System.out.println("\n");
		Client c2=new Client(sharedQueue);
		c2.setClientId(c2);
		c2.setExpression("93 365 * 43 +");
//		for(Task task : c2.getTokens()){
//			System.out.println(task.clientId + " " + task.token);
////			System.out.println("\n");
//		}	
//		Queue <Task> sharedQueue= new ArrayDeque<Task>();
//		sharedQueue.addAll(c1.getTokens());
//		sharedQueue.addAll(c2.getTokens());
		for(Task task1: sharedQueue){
			System.out.println(task1.clientId + " " + task1.token);
		}

	}

}

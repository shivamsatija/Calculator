package Test;

import java.util.Arrays;
import java.util.Stack;

public class TestEvalExp {
	
	public static void main(String[] args){
		
		Task[] tasks = new Task[6];
		for (int i = 0; i < tasks.length; i++) {
			tasks[i] = new Task();
//			tasks[i].setClientId(i);
		}
		tasks[0].setToken("7");
		tasks[1].setToken("365");
		tasks[2].setToken("+");
		tasks[3].setToken("27");
		tasks[4].setToken("*");
		tasks[5].setToken("=");
		
		Stack<String> st = new Stack<String>();
		String[] syms = {"*", "+", "-", "/"};
		
		for (Task task : tasks) {
			if(Arrays.asList(syms).contains(task.getToken())){
				
				String operator = task.getToken();
				int rightOperand = Integer.parseInt(st.pop());
				int leftOperand = Integer.parseInt(st.pop());
				int result = computeBinary(operator, leftOperand, rightOperand);
				st.push(Integer.toString(result));
			}
			else if(task.getToken() == "="){
				System.out.println(st.pop());
			}
			else{
				st.push(task.getToken());
			}
		}
	}

	private static int computeBinary(String operator, int leftOperand,
			int rightOperand) {
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
	
}


package Test;

public class CalculatorMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client c1=new Client();
		c1.setClientId(1);
		c1.setExpression("7 365 * 43 +");
		for(Task task : c1.getTokens()){
			System.out.println(task.clientId + " " + task.token);
			System.out.println("\n");
		}

	}

}

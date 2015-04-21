package Test;


public class CalculatorMain {
//	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client c1=new Client();
		c1.setClientId(c1);
		c1.setExpression("7 365 * 43 +");

		Client c2=new Client();
		c2.setClientId(c2);
		c2.setExpression("93 365 * 43 +");

		for(Task task1: Constants.sharedQueue){
			System.out.println(task1.clientId + " " + task1.token);
		}

	}

}

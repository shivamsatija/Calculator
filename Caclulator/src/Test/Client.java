package Test;


public class Client {

	Client clientId;
	String expression;
	
	Client(String s){
		clientId = this;
		expression = s;
	}
	
	public void start(){
		String[] exps = expression.split(" ");
		for (String exp : exps) {
			SharedQueue.theSharedQueue.add(new Task(this, exp));
		//	System.out.println(this + " : " + exp);
		}
	}
}

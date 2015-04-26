package Test;


public class Client {
	
	public Client getClientId() {
		return clientId;
	}

	public void setClientId(Client clientId) {
		this.clientId = clientId;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	Client clientId;
	String expression;
	
	public Client(String s){
		clientId = this;
		expression = s;
	}
	
	public void start(){
		String[] exps = expression.split(" ");
		for (String exp : exps) {
			Shared.theSharedQueue.add(new Task(this, exp));
		//	System.out.println(this + " : " + exp);
		}
		System.out.println("before while");
		boolean resultCalculated=true;
		while(!Shared.result.containsKey(clientId.toString())){
			if(Shared.serverQuit==true){
				System.out.println("Server has shut down");
				resultCalculated=false;
				break;
			}
//			System.out.println(Shared.result.size());
		}
//		System.out.println("after while");
		if(resultCalculated==true){
			if(Shared.result.get(clientId.toString()).isExceptionRaised()==false)
				System.out.println(Shared.result.get(clientId.toString()).getValue());
			else
				System.out.println(this.expression+"can't be evaluated");
		}
			
		Shared.result.remove(clientId);
	}
}

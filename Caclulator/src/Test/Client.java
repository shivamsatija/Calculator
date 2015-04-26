package Test;


public class Client {
/*	
	This is Client class which contains two member variables, representing
	a real client wishing to get the expression evaluated
	
*/
	Client clientId;		//the id of the client
	String expression;		//the expression to be evaluated
	
	public Client(String s){
		clientId = this;
		expression = s;
	}
	
	/* Basic setter and getter funcitons*/
	
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
	
	/*
		The start function does the client logic including
			- Tokenizing
			- Updating the shared queue
			- Waiting for the result
	*/
	
	public void start(){
		String[] exps = expression.split(" ");
		for (String exp : exps) {
			Shared.theSharedQueue.add(new Task(this, exp));
		}
		
		boolean resultCalculated=true;
		while(!Shared.result.containsKey(clientId.toString())){
			if(Shared.serverQuit==true){
				System.out.println("Server has shut down");
				resultCalculated=false;
				break;
			}

		}

		if(resultCalculated==true){
			if(Shared.result.get(clientId.toString()).isExceptionRaised()==false)
				System.out.println(Shared.result.get(clientId.toString()).getValue());
			else
				System.out.println(this.expression+"can't be evaluated");
		}
			
		Shared.result.remove(clientId);
	}
}

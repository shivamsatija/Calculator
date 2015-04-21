package Test;
import java.util.ArrayDeque;
import java.util.Queue;


public class Client {
	
	int clientId;
	String expression;
	Queue <Task> tokens=new ArrayDeque<Task>();
	
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public Queue<Task> getTokens() {
		if(this.expression!=null){
			for(int i=0;i<this.expression.length();i++){
				
//				Initializing a new temporary String
				String tokenInsert=new String();
				tokenInsert="";

//				Checking if the string do not contains any spaces, 
//				then whole string will be taken as one token
				if(this.expression.substring(i).contains(" ")==false){
					tokenInsert+=this.expression.substring(i);
					i=this.expression.length();
				}
				
//				If string contains empty spaces
				else{
					tokenInsert+=this.expression.substring(i,i+this.expression.substring(i).indexOf(" "));
					i=i+this.expression.substring(i).indexOf(" ");
				}
					
//				Initializing new task object to be inserted into the queue 
				Task temp=new Task();
				temp.setClientId(this.clientId);
				temp.setToken(tokenInsert);
				
//				Inserting the token objects into the queue
				this.tokens.add(temp);
			}
		}
		return this.tokens;
	}
	public void setTokens(Queue<Task> tokens) {
		this.tokens = tokens;
	}
	
	
}

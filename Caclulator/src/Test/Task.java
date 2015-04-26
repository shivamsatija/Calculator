package Test;

// Task Class
public class Task {

	/*
	 * Class representing a task which has two member 
	 * One is identify its sender
	 * The other contains the value sent
	 */
	Client clientId;
	String token;
	
	Task(Client c, String t){
		clientId = c;
		token = t;
	}
	
	//Getters and setters for the client
	public Client getClientId() {
		return clientId;
	}
	public void setClientId(Client clientId2) {
		this.clientId = clientId2;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}

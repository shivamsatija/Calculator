package Test;

// Task Class
public class Task {

	Client clientId;
	String token;
	
	Task(Client c, String t){
		clientId = c;
		token = t;
	}
	
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

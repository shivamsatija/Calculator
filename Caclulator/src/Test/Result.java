package Test;

public class Result {
	/*
	 	This class represents the data structure 
	 	for a result of an expresion evaluation
	 	 
	 */
	
	private double value;			//Value of the expression evaluation
	private boolean clientRead;		//This value represents whether the client has read the result or not
	private boolean isExceptionRaised;  //This represents whether any exception was raised while evaluation
	
	public boolean isExceptionRaised() {
		return isExceptionRaised;
	}
	public void setExceptionRaised(boolean isExceptionRaised) {
		this.isExceptionRaised = isExceptionRaised;
	}
	
	
	
	/*Basic setters and getters*/
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public boolean isClientRead() {
		return clientRead;
	}
	public void setClientRead(boolean clientRead) {
		this.clientRead = clientRead;
	}

}

package Test;

public class Result {
	private double value;
	private boolean clientRead;
	private boolean isExceptionRaised;
	
	public boolean isExceptionRaised() {
		return isExceptionRaised;
	}
	public void setExceptionRaised(boolean isExceptionRaised) {
		this.isExceptionRaised = isExceptionRaised;
	}
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

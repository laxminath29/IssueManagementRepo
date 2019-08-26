package com.nxtlife.efkon.msil.issueManagement.utility;

@SuppressWarnings("serial")
public class CustomException extends Exception {
	String message;
	
	public CustomException(String message) {
		super(message);
		this.message=message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
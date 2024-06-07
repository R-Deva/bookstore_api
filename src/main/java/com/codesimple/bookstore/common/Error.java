package com.codesimple.bookstore.common;

public class Error {

	private String targate;
	private String message;
	
	public Error(String targate, String message) {
		super();
		this.targate = targate;
		this.message = message;
	}
	public String getTargate() {
		return targate;
	}
	public void setTargate(String targate) {
		this.targate = targate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}

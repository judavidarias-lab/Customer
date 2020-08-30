package com.customer.controller.vo;

public class ResponseVo {
	
	private String message;
	private int status;
	private String messageError;
	
	public ResponseVo(String message, int status, String messageError) {
		super();
		this.message = message;
		this.status = status;
		this.messageError = messageError;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessageError() {
		return messageError;
	}
	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}
	
	
}

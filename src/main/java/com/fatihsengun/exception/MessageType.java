package com.fatihsengun.exception;

import lombok.Getter;

@Getter
public enum MessageType {
	
	NO_RECORD_EXIST("1001", "No Record Exist!"),
	GENERAL_EXCEPTION("9999","A General Error Occured"),
	INVALID_USERNAME_OR_PASSWORD("1002","Invalid Username or Password!"),
	USER_NOT_FOUND("1003","User not Found!"),
	TOKEN_EXPIRED("1004","Token Expired!");
	
	private String code;
	
	private String message;
	
	private MessageType(String code, String message) {
		
		this.code=code;
		
		this.message=message;
	}
	

}

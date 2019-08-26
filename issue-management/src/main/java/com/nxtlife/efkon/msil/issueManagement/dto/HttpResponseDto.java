package com.nxtlife.efkon.msil.issueManagement.dto;

import org.springframework.http.HttpStatus;

public class HttpResponseDto {
	String message;
	HttpStatus status;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public HttpResponseDto(String message, HttpStatus status) {
		super();
		this.message = message;
		this.status = status;
	}

}
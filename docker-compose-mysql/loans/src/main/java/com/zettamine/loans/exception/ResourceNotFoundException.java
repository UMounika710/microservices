package com.zettamine.loans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String Customer, String fieldName, String fieldValue) {
		super(String.format("%s not found with %s : %s", Customer, fieldName, fieldValue));
	}
	
	
	

}

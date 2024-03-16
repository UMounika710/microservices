package com.zettamine.boot.mi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationErrorHandlerController {
	
 @ExceptionHandler(value = MethodArgumentNotValidException.class)	
 public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex){
	
	 List<String> errors = ex.getBindingResult().getFieldErrors()
			 					.stream()
			 					.map(except -> except.getField()+": "+except.getDefaultMessage())
			 					.collect(Collectors.toList());
	 return new ResponseEntity<List<String>>(errors, HttpStatus.BAD_REQUEST);	 
 }

}

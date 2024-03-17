package com.zettamine.accounts.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.zettamine.accounts.constants.AccountsConstants;
import com.zettamine.accounts.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value = CustomerAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(Exception ex, WebRequest webRequest){
		ErrorResponseDto error = new ErrorResponseDto();
	
		error.setApiPath(webRequest.getDescription(false));
		error.setErrorCode(HttpStatus.BAD_REQUEST);
		error.setErrorMessage(ex.getMessage());
		error.setErrorTime(LocalDateTime.now());

		return new ResponseEntity<ErrorResponseDto>(error, HttpStatus.BAD_REQUEST);
	}	
	
	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(Exception ex, WebRequest webRequest){
		ErrorResponseDto error = new ErrorResponseDto();
	
		error.setApiPath(webRequest.getDescription(false));
		error.setErrorCode(HttpStatus.NOT_FOUND);
		error.setErrorMessage(ex.getMessage());
		error.setErrorTime(LocalDateTime.now());

		return new ResponseEntity<ErrorResponseDto>(error, HttpStatus.BAD_REQUEST);
	}	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception, WebRequest webRequest) {
		ErrorResponseDto errorResponseDTO = new ErrorResponseDto(webRequest.getDescription(false),
				HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		Map<String, String> validationErrors = new HashMap<>();
		List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

		validationErrorList.forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String validationMsg = error.getDefaultMessage();
			validationErrors.put(fieldName, validationMsg);
		});
		
		return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);

	}

}

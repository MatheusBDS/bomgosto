package com.bomgosto.resources.exceptions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bomgosto.services.exceptions.DataIntegrityException;
import com.bomgosto.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResouceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(StandardError.builder()
						.status(HttpStatus.NOT_FOUND.value())
						.msg(e.getMessage())
						.timeStamp(System.currentTimeMillis())
						.build());
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request){
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(StandardError.builder()
						.status(HttpStatus.BAD_REQUEST.value())
						.msg(e.getMessage())
						.timeStamp(System.currentTimeMillis())
						.build());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
		
		ValidationError err = ValidationError.builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.msg("Erro de validação")
				.timeStamp(System.currentTimeMillis())
				.erros(new ArrayList<>())
				.build();
		
		e.getBindingResult().getFieldErrors().forEach( (fe) -> err.addError(fe.getField(), fe.getDefaultMessage()) );
		
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(err);
	}
	
}

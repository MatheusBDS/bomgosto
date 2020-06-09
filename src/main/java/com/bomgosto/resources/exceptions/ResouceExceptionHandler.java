package com.bomgosto.resources.exceptions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.bomgosto.services.exceptions.AuthorizationException;
import com.bomgosto.services.exceptions.FileException;
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

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request){
		return ResponseEntity
				.status(HttpStatus.FORBIDDEN)
				.body(StandardError.builder()
						.status(HttpStatus.FORBIDDEN.value())
						.msg(e.getMessage())
						.timeStamp(System.currentTimeMillis())
						.build());
	}

	@ExceptionHandler(FileException.class)
	public ResponseEntity<StandardError> file(FileException e, HttpServletRequest request){
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(StandardError.builder()
						.status(HttpStatus.BAD_REQUEST.value())
						.msg(e.getMessage())
						.timeStamp(System.currentTimeMillis())
						.build());
	}

	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<StandardError> amazonService(AmazonServiceException e, HttpServletRequest request){
		HttpStatus code = HttpStatus.valueOf(e.getErrorCode());
		return ResponseEntity
				.status(code)
				.body(StandardError.builder()
						.status(code.value())
						.msg(e.getMessage())
						.timeStamp(System.currentTimeMillis())
						.build());
	}

	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<StandardError> amazonClient(AmazonClientException e, HttpServletRequest request){
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(StandardError.builder()
						.status(HttpStatus.BAD_REQUEST.value())
						.msg(e.getMessage())
						.timeStamp(System.currentTimeMillis())
						.build());
	}

	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<StandardError> amazonS3(AmazonS3Exception e, HttpServletRequest request){
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(StandardError.builder()
						.status(HttpStatus.BAD_REQUEST.value())
						.msg(e.getMessage())
						.timeStamp(System.currentTimeMillis())
						.build());
	}
}

package com.bomgosto.resources.exceptions;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ValidationError extends StandardError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private List<FieldMessage> erros;
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}
	
	public void addError(String fieldName, String message) {
		erros.add(FieldMessage.builder()
				.fieldName(fieldName)
				.message(message)
				.build());
	}
	
}
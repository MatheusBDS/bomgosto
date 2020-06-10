package com.bomgosto.resources.exceptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class StandardError implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long timeStamp;

	private Integer status;

	private String error;

	private String message;

	private String path;

}

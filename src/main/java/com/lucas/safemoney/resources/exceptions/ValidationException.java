package com.lucas.safemoney.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class ValidationException extends StandardError{

	private static final long serialVersionUID = 1L;
	
	// Atributos
	@Getter
	private List<FieldMessage> error = new ArrayList<>();
	
	public ValidationException(Integer code, String message, Long instant) {
		super(code, message, instant);
	}
	
	// Getters e Setters
	public void addError(FieldMessage error) {
		this.error.add(error);
	}
}

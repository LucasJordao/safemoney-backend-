package com.lucas.safemoney.resources.exceptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class FieldMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// Atributos
	private String field;
	private String message;

}

package com.lucas.safemoney.resources.exceptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class StandardError implements Serializable{

	private static final long serialVersionUID = 1L;

	// Atributos
	@Getter @Setter
	private Integer status;
	@Getter @Setter
	private String msg;
	@Getter @Setter
	private Long timestamp;
	
}

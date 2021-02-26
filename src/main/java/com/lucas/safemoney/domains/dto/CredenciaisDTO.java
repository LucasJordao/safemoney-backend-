package com.lucas.safemoney.domains.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CredenciaisDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// Atributos
	private String email;
	private String senha;

}

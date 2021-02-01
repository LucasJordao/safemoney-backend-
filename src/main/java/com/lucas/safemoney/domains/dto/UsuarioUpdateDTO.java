package com.lucas.safemoney.domains.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;

import com.lucas.safemoney.services.validation.UpdateUsuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@UpdateUsuario
public class UsuarioUpdateDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// Atributos
	private String nome;
	@Email(message = "Digite um email válido")
	private String email;
	private String senha;
	private String perfil;

}

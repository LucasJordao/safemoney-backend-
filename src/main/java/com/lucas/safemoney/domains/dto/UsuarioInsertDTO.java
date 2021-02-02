package com.lucas.safemoney.domains.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.lucas.safemoney.services.validation.InsertUsuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@InsertUsuario
public class UsuarioInsertDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// Atributos
	@NotEmpty(message = "O campo nome não pode ser vazio")
	@Size(min = 5, max = 500, message = "O tamanho do nome tem que ser entre 5 e 500")
	private String nome;
	@NotEmpty(message = "O campo email não pode ser vazio")
	@Size(min = 5, max = 500, message = "O tamanho do email tem que ser entre 5 e 500")
	@Email(message = "O email informado é inválido")
	private String email;
	@NotEmpty(message = "O campo senha não pode ser vazio")
	@Size(min = 5, max = 50, message = "O tamanho da senha tem que ser entre 5 e 50")
	private String senha;
	private String perfil;
	
}

package com.lucas.safemoney.domains.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.lucas.safemoney.domains.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CarteiraInsertDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// Atributo
	@NotEmpty(message = "O titulo não pode ser vazio")
	private String titulo;
	@Size(max = 500, min = 1)
	private String descricao;
	@NotNull(message = "O valor não pode ser vazio")
	private Double valor;
	@NotNull(message = "O usuário não pode ser vazio")
	private Usuario usuario;
}

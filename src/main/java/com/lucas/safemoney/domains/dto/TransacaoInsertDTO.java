package com.lucas.safemoney.domains.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.lucas.safemoney.domains.Carteira;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class TransacaoInsertDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// Atributos
	@NotNull(message = "O titulo não pode ser vazio")
	@Size(max = 150, min = 5, message = "O titulo tem que ter entre 5 e 150 caracteres")
	private String titulo;
	@NotNull(message = "O valor não pode ser vazio")
	private Double valor;
	@Size(max = 500, min = 5, message = "O titulo tem que ter entre 5 e 500 caracteres")
	private String descricao;
	@NotNull(message = "A carteira não pode ser vazia")
	private Carteira carteira;
}

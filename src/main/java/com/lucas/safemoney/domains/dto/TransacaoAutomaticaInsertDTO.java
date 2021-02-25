package com.lucas.safemoney.domains.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucas.safemoney.domains.Carteira;
import com.lucas.safemoney.domains.enums.TipoPeriodo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class TransacaoAutomaticaInsertDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	// Atributos
	@NotNull(message =  "O titulo não pode ser vazio")
	@Size(min = 5, max = 150, message = "O titulo tem que ter entre 5 e 150 caracteres")
	private String titulo;
	@NotNull(message = "O valor não pode ser vazio")
	private Double valor;
	@NotNull(message = "O periodo não pode ser vazio")
	private TipoPeriodo periodo;
	@Size(min = 5, max = 500, message = "A descrição tem que ter entre 5 e 500 caracteres")
	private String descricao;
	@NotNull(message = "A carteira não pode ser vazia")
	private Carteira carteira;
	@NotNull(message = "Data da transação não pode ser vazia")
	@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING, timezone = "GMT")
	private String dataTransacao;
	private Boolean status;
}

package com.lucas.safemoney.domains.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucas.safemoney.domains.enums.TipoPeriodo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class GastoAutomaticoUpdateDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// Atributos
	@Size(min = 5, max = 150, message = "O titulo tem que ter entre 5 e 150 caracteres")
	private String titulo;
	private Double valor;
	private TipoPeriodo periodo;
	@Size(min = 5, max = 500, message = "A descrição tem que ter entre 5 e 500 caracteres")
	private String descricao;
	@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING, timezone = "GMT")
	private String dataGasto;
}

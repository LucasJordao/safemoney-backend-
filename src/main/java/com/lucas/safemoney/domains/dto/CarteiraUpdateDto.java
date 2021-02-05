package com.lucas.safemoney.domains.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CarteiraUpdateDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// Atributo
	private String titulo;
	@Size(max = 500, min = 1)
	private String descricao;
	@Min(value = 0, message = "O valor tem que ser maior que 0")
	private Double valor;

}

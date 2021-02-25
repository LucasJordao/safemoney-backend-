package com.lucas.safemoney.domains.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TipoPeriodo {

	// atributos
	MENSAL(1, "mensal"),
	ANUAL(2, "anual"),
	DIARIO(3, "diario");

	@Getter
	private int code;
	@Getter
	private String descricao;
	
	// Méotodos
	public static TipoPeriodo toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		
		for(TipoPeriodo x: TipoPeriodo.values()) {
			if(code.equals(x.getCode())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Código inválido. Código: " + code);
	}
}

package com.lucas.safemoney.domains.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Perfil {
	// Atributos
	ADMIN(1, "ROLE_ADMIN"),
	USER(2, "ROLE_USER");
	
	private int code;
	private String descricao;
	
	// Metodos
	public static Perfil toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Perfil x: Perfil.values()) {
			if(cod.equals(x.getCode())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Código inválido: " + cod);
	}
}

package com.bomgosto.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"), //
	CLIENTE(2, "ROLE_CLIENTE");

	private int cod;

	private String descricao;

	public static Perfil toEnum(Integer cod) {
		for (Perfil x : Perfil.values()) {
			return cod.equals(x.getCod()) ? x : null;
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}

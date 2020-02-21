package com.bomgosto.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoCliente {

	PESSOA_FISICA(1, "Pessoa Física"), //
	PESSOA_JURIDICA(2, "Pessoa Jurídica");

	private int cod;
	private String descricao;

	public static TipoCliente toEnum(Integer cod) {
		for (TipoCliente x : TipoCliente.values()) {
			return cod.equals(x.getCod()) ? x : null;
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}

package com.bomgosto.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadoPagamento {

	PENDENTE(1, "Pendente"), //
	QUITADO(2, "Quitado"), //
	CANCELADO(3, "Cancelado");

	private int cod;

	private String descricao;

	public static EstadoPagamento toEnum(Integer cod) {
		for (EstadoPagamento x : EstadoPagamento.values()) {
			return cod.equals(x.getCod()) ? x : null;
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}

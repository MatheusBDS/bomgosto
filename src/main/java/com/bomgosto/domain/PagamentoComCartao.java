package com.bomgosto.domain;

import java.io.Serializable;

import javax.persistence.Entity;

import com.bomgosto.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;
	
	private Integer numeroParcelas;

	@Builder
	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroParcelas) {
		super(id, estado, pedido);
		
		this.numeroParcelas = numeroParcelas;
	}
	
	
}

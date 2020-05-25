package com.bomgosto.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import com.bomgosto.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class PagamentoComDinheiro extends Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;
	
    @JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamento;

	@Builder
	public PagamentoComDinheiro(Integer id, EstadoPagamento estado, Pedido pedido, Date dataPagamento) {
		super(id, estado, pedido);
		
		this.dataPagamento = dataPagamento;
	}
	
	
	
}

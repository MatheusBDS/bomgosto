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
public class PagamentoComBoleto extends Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataVencimento;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamento;

	@Builder
	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

}

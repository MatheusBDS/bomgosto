package com.bomgosto.domain;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PagamentoComCartao extends Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;
	
	private Integer numeroParcelas;
}

package com.bomgosto.domain;

import java.io.Serializable;

import javax.persistence.Entity;

import com.bomgosto.domain.enums.EstadoPagamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
public class Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;
	
	private Integer id;
	private EstadoPagamento estado;
}

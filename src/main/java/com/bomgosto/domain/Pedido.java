package com.bomgosto.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;

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
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

	private Integer id;
	private Date instante;
	private String observacoes;

}

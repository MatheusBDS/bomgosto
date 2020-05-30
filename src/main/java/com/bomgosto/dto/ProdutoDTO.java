package com.bomgosto.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.bomgosto.domain.Produto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;

	private String tamanho;

	private String unidadeMedida;

	private BigDecimal precoUnitario;

	private String imagem;
	
	@Builder
	public ProdutoDTO(Produto obj) {
		id = obj.getId();
		nome = obj.getNome();
		tamanho = obj.getTamanho();
		unidadeMedida = obj.getUnidadeMedida();
		precoUnitario = obj.getPrecoUnitario();
		imagem = obj.getImagem();
	}

}

package com.bomgosto.dto;

import com.bomgosto.domain.Cidade;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class CidadeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String nome;

    @Builder
    public CidadeDTO(Cidade obj) {
        id = obj.getId();
        nome = obj.getNome();
    }
}

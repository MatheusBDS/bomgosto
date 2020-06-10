package com.bomgosto.dto;

import com.bomgosto.domain.Estado;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class EstadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String nome;

    @Builder
    public EstadoDTO(Estado obj){
        id = obj.getId();
        nome = obj.getNome();
    }
}

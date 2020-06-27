package com.bomgosto.dto;

import com.bomgosto.domain.Endereco;
import com.bomgosto.services.validation.EnderecoInsert;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@EnderecoInsert
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class EnderecoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String logradouro;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String numero;

    private String complemento;

    private String bairro;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String cep;

    private Integer cidade;

    private Integer cliente;

    @Builder
    public EnderecoDTO (Endereco obj){
        logradouro = obj.getLogradouro();
        numero = obj.getNumero();
        complemento = obj.getComplemento();
        bairro = obj.getBairro();
        cep = obj.getCep();
        cidade = obj.getCidade().getId();
        cliente = obj.getCliente().getId();
    }
}

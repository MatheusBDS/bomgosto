package com.bomgosto.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.bomgosto.domain.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode(exclude = {"nome", "email", "cpfOuCnpj", "tipo"})
@Entity
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Getter
    @Setter
    private String nome;
    
    @Column(unique = true)
    @Getter
    @Setter
    private String email;
    
    @Getter
    @Setter
    private String cpfOuCnpj;
    
    private Integer tipo;
    
    @Getter
    @Setter
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

    @Getter
    @Setter
    @ElementCollection
    @CollectionTable(name = "TELEFONE")
    private Set<String> telefones = new HashSet<>();

    @JsonIgnore
    @Getter
    @Setter
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();
    
    @Builder
    public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo) {
        super();
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = (tipo == null) ? null : tipo.getCod();
    }

    public void setTipo(TipoCliente tipo) {
    	this.tipo = tipo.getCod();
    }
    
    public TipoCliente getTipo() {
        return TipoCliente.toEnum(tipo);
    }

}

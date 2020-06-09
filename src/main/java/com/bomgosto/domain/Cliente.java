package com.bomgosto.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.bomgosto.domain.enums.Perfil;
import com.bomgosto.domain.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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
    private String imageUrl;

    @JsonIgnore
    @Getter
    @Setter
    private String senha;
    
    @Getter
    @Setter
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

    @Getter
    @Setter
    @ElementCollection
    @CollectionTable(name = "TELEFONE")
    private Set<String> telefones = new HashSet<>();
    
    @ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<>();

    @JsonIgnore
    @Getter
    @Setter
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();
    
    public Cliente() {
    	addPerfil(Perfil.CLIENTE);
    }
    
    public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo, String senha) {
        super();
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = (tipo == null) ? null : tipo.getCod();
        this.senha = senha;
        addPerfil(Perfil.CLIENTE);
    }

    public void setTipo(TipoCliente tipo) {
    	this.tipo = tipo.getCod();
    }
    
    public TipoCliente getTipo() {
        return TipoCliente.toEnum(tipo);
    }
    
    public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
    
    public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}
}

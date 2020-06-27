package com.bomgosto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bomgosto.domain.Endereco;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    @Transactional(readOnly = true)
    Endereco findByCep(String cep);

}

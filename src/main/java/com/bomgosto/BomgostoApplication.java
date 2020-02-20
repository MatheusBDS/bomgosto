package com.bomgosto;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bomgosto.domain.Categoria;
import com.bomgosto.repositories.CategoriaRepository;

@SpringBootApplication
public class BomgostoApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BomgostoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		categoriaRepository.saveAll(Arrays.asList(
				Categoria.builder()
				.id(null)
				.nome("Informática").build(),
				Categoria.builder()
				.id(null)
				.nome("Escritório").build()));
	}
	
}

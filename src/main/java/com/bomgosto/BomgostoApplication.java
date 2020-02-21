package com.bomgosto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bomgosto.domain.Categoria;
import com.bomgosto.domain.Cidade;
import com.bomgosto.domain.Estado;
import com.bomgosto.domain.Produto;
import com.bomgosto.repositories.CategoriaRepository;
import com.bomgosto.repositories.CidadeRepository;
import com.bomgosto.repositories.EstadoRepository;
import com.bomgosto.repositories.ProdutoRepository;

@SpringBootApplication
public class BomgostoApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;

	public static void main(String[] args) {
		SpringApplication.run(BomgostoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// PRODUTOS
		Produto p1 = Produto.builder().id(null).nome("Pizza de Calabresa").tamanho("Grande").unidadeMedida("Gramas")
				.precoUnitario(new BigDecimal(30)).categorias(new ArrayList<>()).build();
		Produto p2 = Produto.builder().id(null).nome("Pizza de Frango").tamanho("Grande").unidadeMedida("Gramas")
				.precoUnitario(new BigDecimal(30)).categorias(new ArrayList<>()).build();
		Produto p3 = Produto.builder().id(null).nome("Suco de Laranja").tamanho("500").unidadeMedida("Milimetros")
				.precoUnitario(new BigDecimal(3)).categorias(new ArrayList<>()).build();

		// CATEGORIAS
		Categoria cat1 = Categoria.builder().id(null).nome("Pizza").produtos(new ArrayList<>()).build();
		Categoria cat2 = Categoria.builder().id(null).nome("Bebida").produtos(new ArrayList<>()).build();

		cat1.getProdutos().addAll(Arrays.asList(p1, p2));
		cat2.getProdutos().addAll(Arrays.asList(p3));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1));
		p3.getCategorias().addAll(Arrays.asList(cat2));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		// ESTADOS
		Estado est1 = Estado.builder().nome("Distrito Federal").cidades(new ArrayList<>()).build();

		Cidade c1 = Cidade.builder().nome("Ceilândia").estado(est1).build();

		est1.getCidades().addAll(Arrays.asList(c1));

		estadoRepository.saveAll(Arrays.asList(est1));
		cidadeRepository.saveAll(Arrays.asList(c1));
	}

}

package com.bomgosto.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bomgosto.domain.Categoria;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {

		List<Categoria> lista = new ArrayList<>();
		lista.add(Categoria.builder()
				.id(1)
				.nome("Informática")
				.build());
		lista.add(Categoria.builder()
				.id(2)
				.nome("Escritório")
				.build());
		
		return lista;
	}

}

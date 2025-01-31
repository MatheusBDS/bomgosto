package com.bomgosto.resources;

import java.util.List;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bomgosto.domain.Produto;
import com.bomgosto.dto.ProdutoDTO;
import com.bomgosto.resources.utils.URL;
import com.bomgosto.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

    @Autowired
    ProdutoService service;

	@ApiOperation(value = "Busca produto por id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> find(@PathVariable Integer id) {
        Produto obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

	@ApiOperation(value = "Retorna produtos por categoria com paginação")
    @GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome", defaultValue="") String nome, 
			@RequestParam(value="categorias", defaultValue="") String categorias, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDTO = list.map(obj -> ProdutoDTO.builder().obj(obj).build());  
		return ResponseEntity.ok().body(listDTO);
	}

}

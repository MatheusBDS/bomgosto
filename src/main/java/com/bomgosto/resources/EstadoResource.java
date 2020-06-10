package com.bomgosto.resources;

import com.bomgosto.dto.CidadeDTO;
import com.bomgosto.dto.EstadoDTO;
import com.bomgosto.services.CidadeService;
import com.bomgosto.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

    @Autowired
    private EstadoService service;

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<List<EstadoDTO>> findAll(){
        List<EstadoDTO> listDTO = service.findAll().stream().map(EstadoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping("/{estadoId}/cidades")
    public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId){
        List<CidadeDTO> listDTO = cidadeService.findByEstado(estadoId).stream().map(CidadeDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

}

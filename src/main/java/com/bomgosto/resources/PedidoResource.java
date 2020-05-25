package com.bomgosto.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bomgosto.domain.Pedido;
import com.bomgosto.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

    @Autowired
    PedidoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Pedido obj = service.buscar(id);
        return ResponseEntity.ok().body(obj);
    }

}

package com.bomgosto.resources;

import com.bomgosto.domain.Cliente;
import com.bomgosto.domain.Endereco;
import com.bomgosto.dto.ClienteDTO;
import com.bomgosto.dto.ClienteNewDTO;
import com.bomgosto.dto.EnderecoDTO;
import com.bomgosto.services.ClienteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    @Autowired
    ClienteService service;

	@ApiOperation(value = "Busca cliente por id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> find(@PathVariable Integer id) {
        Cliente obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

	@ApiOperation(value = "Busca cliente por email")
	@GetMapping(value = "/email")
	public ResponseEntity<Cliente> find(@RequestParam(value = "value") String email) {
		Cliente obj = service.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}

	@ApiOperation(value = "Insere cliente")
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto) {
		Cliente obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Atualiza dados do cliente")
    @PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id) {
		objDTO.setId(id);
		service.update(objDTO);

		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation(value = "Remove cliente")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation(value = "Retorna todos clientes")
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<ClienteDTO> listDTO = service.findAll().stream().map(obj -> ClienteDTO.builder().obj(obj).build())
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(listDTO);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation(value = "Retorna todos clientes com paginação")
	@GetMapping(value = "/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
			Page<ClienteDTO> listDTO = service.findPage(page, linesPerPage, orderBy, direction)
				.map(obj -> ClienteDTO.builder().obj(obj).build());

		return ResponseEntity.ok().body(listDTO);
	}

	@ApiOperation(value = "Salva imagem do cliente")
	@PostMapping(value = "/picture")
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name = "file") MultipartFile file){

		URI uri = service.uploadProfilePicture(file);

		return ResponseEntity.created(uri).build();

	}

	@ApiOperation(value = "Adiciona endereço ao cliente")
	@PutMapping(value = "/{id}/adicionarEndereco")
	public ResponseEntity<Void> addEndereco(@Valid @RequestBody EnderecoDTO objDTO, @PathVariable(value = "id") Integer clienteId) {
		Endereco obj = service.addEndereco(clienteId, objDTO);

		return ResponseEntity.noContent().build();
	}
    
}

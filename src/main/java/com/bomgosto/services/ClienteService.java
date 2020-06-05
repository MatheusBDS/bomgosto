package com.bomgosto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bomgosto.domain.Cidade;
import com.bomgosto.domain.Cliente;
import com.bomgosto.domain.Endereco;
import com.bomgosto.domain.enums.TipoCliente;
import com.bomgosto.dto.ClienteDTO;
import com.bomgosto.dto.ClienteNewDTO;
import com.bomgosto.repositories.ClienteRepository;
import com.bomgosto.repositories.EnderecoRepository;
import com.bomgosto.services.exceptions.DataIntegrityException;
import com.bomgosto.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private BCryptPasswordEncoder pe;
	
    @Autowired
    private ClienteRepository repo;

    @Autowired
	private EnderecoRepository enderecoRepository;
    
    public Cliente find(Integer id) {
        Optional<Cliente> obj = repo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id " + id + ", Tipo: " + Cliente.class.getName()));
    }

    @Transactional
    public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		
		return obj;
	}
    
    public Cliente update(Cliente obj) {
    	Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas"); 
		}
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		return Cliente.builder()
				.id(objDTO.getId())
				.nome(objDTO.getNome())
				.email(objDTO.getEmail())
				.cpfOuCnpj(null)
				.tipo(null)
				.senha(null)
				.build();
	}
    
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cli = Cliente.builder()
				.id(null)
				.nome(objDTO.getNome())
				.email(objDTO.getEmail())
				.cpfOuCnpj(objDTO.getCpfOuCnpj())
				.tipo(TipoCliente.toEnum(objDTO.getTipo()))
				.senha(pe.encode(objDTO.getSenha()))
				.build();
		
		Cidade cid = Cidade.builder()
				.id(objDTO.getCidadeId())
				.nome(null)
				.estado(null)
				.build();
		
		Endereco end = Endereco.builder()
				.id(null)
				.logradouro(objDTO.getLogradouro())
				.numero(objDTO.getNumero())
				.complemento(objDTO.getComplemento())
				.bairro(objDTO.getBairro())
				.cep(objDTO.getCep())
				.cliente(cli)
				.cidade(cid)				
				.build();
	
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		
		if (objDTO.getTelefone2() != null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		if (objDTO.getTelefone3() != null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}

package com.bomgosto.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.bomgosto.domain.Cliente;
import com.bomgosto.domain.enums.TipoCliente;
import com.bomgosto.dto.ClienteNewDTO;
import com.bomgosto.repositories.ClienteRepository;
import com.bomgosto.resources.exceptions.FieldMessage;
import com.bomgosto.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}
	
	@Override
	public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();


		if(objDTO.getTipo().equals(TipoCliente.PESSOA_FISICA.getCod()) && !BR.isValidCPF(objDTO.getCpfOuCnpj())) {
			list.add(FieldMessage.builder()
					.fieldName("cpfOuCnpj")
					.message("CPF inválido!")
					.build());
		}
		
		if(objDTO.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCod()) && !BR.isValidCNPJ(objDTO.getCpfOuCnpj())) {
			list.add(FieldMessage.builder()
					.fieldName("cpfOuCnpj")
					.message("CNPJ inválido!")
					.build());
		}
		
		Cliente aux = clienteRepository.findByEmail(objDTO.getEmail());
		if(aux != null) {
			list.add(FieldMessage.builder()
					.fieldName("email")
					.message("Email já existente!")
					.build());
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
	
	

}

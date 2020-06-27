package com.bomgosto.services.validation;

import com.bomgosto.domain.Endereco;
import com.bomgosto.dto.EnderecoDTO;
import com.bomgosto.repositories.EnderecoRepository;
import com.bomgosto.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class EnderecoInsertValidator implements ConstraintValidator<EnderecoInsert, EnderecoDTO> {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Override
	public void initialize(EnderecoInsert ann) {
	}
	
	@Override
	public boolean isValid(EnderecoDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		Endereco aux = enderecoRepository.findByCep(objDTO.getCep());
		if(aux != null) {
			list.add(FieldMessage.builder()
					.fieldName("endereco")
					.message("Endereço já existente!")
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

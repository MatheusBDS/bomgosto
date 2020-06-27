package com.bomgosto.dto;

import com.bomgosto.domain.Cliente;
import com.bomgosto.services.validation.ClienteUpdate;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import java.io.Serializable;

@ClienteUpdate
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

//	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;

//	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;

	private String senhaAtual;

	private String senhaNova;
	
	@Builder
	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
	}

}

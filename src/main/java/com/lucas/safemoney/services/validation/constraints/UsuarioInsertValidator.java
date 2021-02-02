package com.lucas.safemoney.services.validation.constraints;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.lucas.safemoney.domains.Usuario;
import com.lucas.safemoney.domains.dto.UsuarioInsertDTO;
import com.lucas.safemoney.repositories.UsuarioRepository;
import com.lucas.safemoney.resources.exceptions.FieldMessage;
import com.lucas.safemoney.services.validation.InsertUsuario;

public class UsuarioInsertValidator implements ConstraintValidator<InsertUsuario, UsuarioInsertDTO>{
	
	// Injeção de dependencias
	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public void initialize(InsertUsuario ann) {
	}
	
	@Override
	public boolean isValid(UsuarioInsertDTO value, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();
		
		Usuario user = repository.findByEmail(value.getEmail());
		
		if(user != null && user.getEmail().toUpperCase().equals(value.getEmail().toUpperCase())) {
			list.add(new FieldMessage("email", "Email já em uso"));
		}
		
		for(FieldMessage e: list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getField())
														.addConstraintViolation();
		}
		
		return list.isEmpty();
	}
}

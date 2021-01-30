package com.lucas.safemoney.services.instantiates;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.safemoney.domains.Usuario;
import com.lucas.safemoney.repositories.UsuarioRepository;

@Service
public class DevInstantiate {
	
	// Repositories
	@Autowired
	private UsuarioRepository repo;
	
	
	public void instantiate() {
		Usuario user1 = new Usuario(null, "Lucas William Silva Jordão", "lucas@hotmail.com", "Senha12345", "https://imagem-perfil.com");
		
		repo.saveAll(Arrays.asList(user1));
	}
	
}

package com.lucas.safemoney.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.safemoney.domains.Usuario;
import com.lucas.safemoney.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	// Repositories
	@Autowired
	private UsuarioRepository repo;
	
	
	/* 
	 * Método responsável por retornar todos os usuários do banco de dados 
	 */
	public List<Usuario> list(){
		return repo.findAll();
	}
	
}

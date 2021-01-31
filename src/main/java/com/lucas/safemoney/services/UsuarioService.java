package com.lucas.safemoney.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.safemoney.domains.Usuario;
import com.lucas.safemoney.repositories.UsuarioRepository;
import com.lucas.safemoney.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	// Repositories
	@Autowired
	private UsuarioRepository repo;
	
	/*
	 * Método responsável por retornar todos os usuários do banco de dados 
	 * @return esse método retorna uma lista de usuários
	 */
	public List<Usuario> list(){
		return repo.findAll();
	}
	
	/*
	 * Método responsável por retornar um usuário pelo id
	 * @param id 
	 * @return esse método retorna um usuário 
	 */
	public Usuario findById(int id) {
		Optional<Usuario> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id 
																+ ", Tipo: " + Usuario.class.getName()));
	}
	
}

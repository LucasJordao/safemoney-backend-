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
	
	/**
	 * Método responsável por retornar todos os usuários do banco de dados 
	 * @return esse método retorna uma lista de usuários
	 */
	public List<Usuario> list(){
		return repo.findAll();
	}
	
	/**
	 * Método responsável por retornar um usuário pelo id
	 * @param id 
	 * @return esse método retorna um usuário 
	 */
	public Usuario findById(int id) {
		Optional<Usuario> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id 
																+ ", Tipo: " + Usuario.class.getName()));
	}
	
	/**
	 * Método responsável por adicionar um novo Usuario no banco de dados
	 * @param obj do tipo Usuario
	 * @return esse método retorna um usuário 
	 */
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	/**
	 * Método responsável por atualizar um Usuario do banco de dados
	 * @param obj do tipo Usuario
	 * @return esse método retorna um usuario
	 */
	public Usuario update(Usuario obj) {
		Usuario newObj = this.findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	/**
	 * Método responsável por deletar um usuário no banco de dados
	 * @param id do tipo Integer
	 */
	public void delete(Integer id) {
		Usuario user = this.findById(id);
		repo.delete(user);
	}
	
	// Métodos Auxiliares
	
	private void updateData(Usuario newObj, Usuario obj) {
		if(obj.getNome() != null) {
			newObj.setNome(obj.getNome());
		}
		if(obj.getEmail() != null) {
			newObj.setEmail(obj.getEmail());
		}
		if(obj.getSenha() != null) {
			newObj.setSenha(obj.getSenha());
		}
		if(obj.getPerfil() != null) {
			newObj.setPerfil(obj.getPerfil());
		}
	}
	
}

package com.lucas.safemoney.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucas.safemoney.domains.Carteira;
import com.lucas.safemoney.domains.Usuario;
import com.lucas.safemoney.domains.dto.CarteiraInsertDTO;
import com.lucas.safemoney.domains.dto.CarteiraUpdateDto;
import com.lucas.safemoney.repositories.CarteiraRepository;
import com.lucas.safemoney.repositories.UsuarioRepository;
import com.lucas.safemoney.services.exceptions.ObjectNotFoundException;

@Service
public class CarteiraService {

	// Repositories
	@Autowired
	private CarteiraRepository repo;
	
	@Autowired
	private UsuarioRepository userRepo;
	
	// Services auxiliares
	@Autowired
	private UsuarioService userService;

	/**
	 * Método responsável por retornar todas as Carteiras do banco de dados
	 * 
	 * @return esse método retorna uma lista de carteiras
	 */
	public List<Carteira> list() {
		return repo.findAll();
	}

	/**
	 * Método responsável por retornar uma Carteira pelo id
	 * 
	 * @param id
	 * 
	 * @return esse método retorna uma Carteira
	 */
	public Carteira findById(int id) {
		Optional<Carteira> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Carteira.class.getName()));
	}
	
	/**
	 * Método responsável por retornar uma página com carteiras 
	 * @param page do tipo Integer
	 * @param linePerPage do tipo Integer
	 * @param direction do tipo String
	 * @param orderBy do tipo String
	 * 
	 * @return Page<Carteira>
	 */
	public Page<Carteira> findPage(Integer page, Integer linePerPage, String direction, String orderBy){
		PageRequest obj = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(obj);
	}
	
	
	/**
	 * Método responsável por criar uma nova Carteira para o Usuário
	 * @param objDTO do tipo CarteiraInsertDTO
	 * @return Carteira
	 */
	public Carteira insert(CarteiraInsertDTO objDTO) {
		Carteira newObj = new Carteira();
		
		this.fromDto(newObj, objDTO);
		
		Usuario user = newObj.getUsuario();
		user.getCarteiras().add(newObj);
		
		Carteira cart = this.repo.save(newObj);
		this.userRepo.save(user);
		
		return cart;
	}
	
	/**
	 * Método responsável por deletar uma carteira do banco de dados
	 * @param id do tipo Integer
	 */
	public void delete(Integer id) {
		Carteira cart = this.findById(id);
		this.repo.delete(cart);
	}
	
	/**
	 * Método responsável por atualizar uma Carteira do banco de dados
	 * @param obj do tipo CarteiraUpdateDto
	 * @param id do tipo Integer
	 * @return newObj do tipo Carteira
	 */
	public Carteira update(CarteiraUpdateDto obj, Integer id) {
		Carteira newObj = this.findById(id);
		this.saveData(newObj, obj);
		
		return this.repo.save(newObj);
	}
	
	// Métodos auxiliares
	private void fromDto(Carteira newObj, CarteiraInsertDTO objDTO) {
		Usuario user = this.userService.findById(objDTO.getUsuario().getId());
		newObj.setId(null);
		newObj.setTitulo(objDTO.getTitulo());
		newObj.setDescricao(objDTO.getDescricao());
		newObj.setValor(objDTO.getValor());
		newObj.setUsuario(user);
	}
	
	
	/**
	 * Método responsável por atualizar dados de uma CarteiraUpdateDto para uma Carteira genérica
	 * @param newObj do tipo Carteira
	 * @param obj do tipo CarteiraUpdateDto
	 */
	private void saveData(Carteira newObj, CarteiraUpdateDto obj) {
		if(obj.getTitulo() != null) {
			newObj.setTitulo(obj.getTitulo());
		}
		if(obj.getDescricao() != null) {
			newObj.setDescricao(obj.getDescricao());
		}
		if(obj.getValor() != null) {
			newObj.setValor(obj.getValor());
		}
	}
}

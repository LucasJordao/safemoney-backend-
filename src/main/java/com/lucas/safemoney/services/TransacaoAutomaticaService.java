package com.lucas.safemoney.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucas.safemoney.config.security.UserSS;
import com.lucas.safemoney.domains.Carteira;
import com.lucas.safemoney.domains.TransacaoAutomatica;
import com.lucas.safemoney.domains.dto.TransacaoAutomaticaInsertDTO;
import com.lucas.safemoney.domains.dto.TransacaoAutomaticaUpdateDTO;
import com.lucas.safemoney.domains.enums.Perfil;
import com.lucas.safemoney.repositories.CarteiraRepository;
import com.lucas.safemoney.repositories.TransacaoAutomaticaRepository;
import com.lucas.safemoney.services.exceptions.AuthorizationException;
import com.lucas.safemoney.services.exceptions.ObjectNotFoundException;

@Service
public class TransacaoAutomaticaService {

	// Repositories
	@Autowired
	private TransacaoAutomaticaRepository repo;
	@Autowired
	private CarteiraRepository cartRepo;
	
	
	// Services
	@Autowired
	private CarteiraService cartService;
	
	/**
	 * Método responsável por retornar todos as Transações automaticas do banco de dados
	 * 
	 * @return esse método retorna uma lista de transações automaticas
	 */
	public List<TransacaoAutomatica> list() {
		return repo.findAll();
	}

	/**
	 * Método responsável por retornar um TransacaoAutomatica pelo id
	 * 
	 * @param id
	 * 
	 * @return esse método retorna um TransacaoAutomatica
	 */
	public TransacaoAutomatica findById(int id) {
		Optional<TransacaoAutomatica> obj = repo.findById(id);

		TransacaoAutomatica trans = obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + TransacaoAutomatica.class.getName()));
		
		UserSS user = UsuarioService.authenticated();
		
		if(user == null || !user.hasRole(Perfil.ADMIN) && !trans.getCarteira().getUsuario().getId().equals(user.getId())) {
			throw new AuthorizationException("Acesso Negado");
		}
		
		
		return trans;
	}	
	
	/**
	 * Método responsável por retornar uma página com transções automáticas 
	 * @param page do tipo Integer
	 * @param linePerPage do tipo Integer
	 * @param direction do tipo String
	 * @param orderBy do tipo String
	 * 
	 * @return Page<TransacaoAutomatica>
	 */
	public Page<TransacaoAutomatica> findPage(Integer page, Integer linePerPage, String direction, String orderBy){
		PageRequest obj = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(obj);
	}
	
	
	/**
	 * Método responsável por deletar uma transação automática do banco de dados
	 * @param id do tipo TransacaoAutomatica
	 */
	public void delete(Integer id) {
		TransacaoAutomatica trans = this.findById(id);
		
		UserSS user = UsuarioService.authenticated();
		
		if(user == null || !user.hasRole(Perfil.ADMIN) && !trans.getCarteira().getUsuario().getId().equals(user.getId())) {
			throw new AuthorizationException("Acesso Negado");
		}
		
		this.repo.delete(trans);
	}
	
	/**
	 * Método responsável por criar uma transação automática
	 * @param obj do tipo TransacaoAutomatica
	 * @return gast do tipo TransacaoAutomatica
	 */
	public TransacaoAutomatica insert(TransacaoAutomatica obj) {
		Carteira cart = this.cartService.findById(obj.getCarteira().getId());
		
		UserSS user = UsuarioService.authenticated();
		
		if(user == null || !user.hasRole(Perfil.ADMIN) && !cart.getUsuario().getId().equals(user.getId())) {
			throw new AuthorizationException("Acesso Negado");
		}
		
		obj.setId(null);
		obj.setCarteira(cart);
		if(obj.getStatus() == null) {
			obj.setStatus(true);
		}
		cart.getTransacoesAutomaticas().add(obj);

		TransacaoAutomatica gast = this.repo.save(obj);
		this.cartRepo.save(cart);
		return gast;
	}
	
	/**
	 * Método responsável por atualizar uma transação automática
	 * @param obj do tipo TransacaoAutomatica
	 * @return newObj do tipo TransacaoAutomatica
	 */
	public TransacaoAutomatica update(TransacaoAutomatica obj) {
		TransacaoAutomatica newObj = this.findById(obj.getId());
		
		UserSS user = UsuarioService.authenticated();
		
		if(user == null || !user.hasRole(Perfil.ADMIN) && !newObj.getCarteira().getUsuario().getId().equals(user.getId())) {
			throw new AuthorizationException("Acesso Negado");
		}
			
		this.saveData(newObj, obj);
		
		return this.repo.save(newObj);
	}
	
	// Métodos auxiliares
	public TransacaoAutomatica fromInsertDTO(TransacaoAutomaticaInsertDTO objDTO) {
		Date data = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		TransacaoAutomatica transAutomatica;
		try {
			transAutomatica = new TransacaoAutomatica(null, objDTO.getTitulo(), objDTO.getValor(), data, objDTO.getPeriodo(), objDTO.getDescricao(), objDTO.getCarteira(), sdf.parse(objDTO.getDataTransacao()), objDTO.getStatus());
		} catch (ParseException e) {
			throw new IllegalArgumentException("Erro ao converter a data");
		}
		
		return transAutomatica;
	}
	
	
	public TransacaoAutomatica fromUpdateDTO(TransacaoAutomaticaUpdateDTO objDTO, Integer id) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataGasto = null;
		if(objDTO.getDataTransacao() != null) {
			dataGasto = sdf.parse(objDTO.getDataTransacao());
		}
		
		TransacaoAutomatica transAutomatica = new TransacaoAutomatica(id, objDTO.getTitulo(), objDTO.getValor(), 
						null, objDTO.getPeriodo(), objDTO.getDescricao() , null, dataGasto, objDTO.getStatus());
		
		return transAutomatica;
	}
	
	private void saveData(TransacaoAutomatica newObj, TransacaoAutomatica obj) {

		if(obj.getTitulo() != null) {
			newObj.setTitulo(obj.getTitulo());
		}
		if(obj.getValor() != null) {
			newObj.setValor(obj.getValor());
		}
		if(obj.getDataTransacao() != null) {
			newObj.setDataTransacao(obj.getDataTransacao());
		}
		if(obj.getPeriodo() != null) {
			newObj.setPeriodo(obj.getPeriodo());
		}
		if(obj.getDescricao() != null) {
			newObj.setDescricao(obj.getDescricao());
		}
		if(obj.getStatus() != null) {
			newObj.setStatus(obj.getStatus());
		}
	}
}

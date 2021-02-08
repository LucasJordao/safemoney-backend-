package com.lucas.safemoney.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucas.safemoney.domains.Carteira;
import com.lucas.safemoney.domains.Transacao;
import com.lucas.safemoney.domains.dto.TransacaoInsertDTO;
import com.lucas.safemoney.repositories.CarteiraRepository;
import com.lucas.safemoney.repositories.TransacaoRepository;
import com.lucas.safemoney.services.exceptions.ObjectNotFoundException;

@Service
public class TransacaoService {

	// Repositories
	@Autowired
	private TransacaoRepository repo;
	@Autowired
	private CarteiraRepository cartRepo;
	
	// Services
	@Autowired
	private CarteiraService cartService;

	/**
	 * Método responsável por retornar todas as Transacoes do banco de dados
	 * 
	 * @return esse método retorna uma lista de transacoes
	 */
	public List<Transacao> list() {
		return repo.findAll();
	}

	/**
	 * Método responsável por retornar uma Transacao pelo id
	 * 
	 * @param id
	 * 
	 * @return esse método retorna uma Transacao
	 */
	public Transacao findById(int id) {
		Optional<Transacao> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Transacao.class.getName()));
	}
	
	/**
	 * Método responsável por retornar uma página com transações 
	 * @param page do tipo Integer
	 * @param linePerPage do tipo Integer
	 * @param direction do tipo String
	 * @param orderBy do tipo String
	 * 
	 * @return Page<Transacao>
	 */
	public Page<Transacao> findPage(Integer page, Integer linePerPage, String direction, String orderBy){
		PageRequest obj = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(obj);
	}
	
	
	/**
	 * Método responsável por deletar uma Transacao no banco de dados
	 * @param id do tipo Integer
	 */
	public void delete(Integer id) {
		Transacao trans = this.findById(id);
		this.repo.delete(trans);
	}
	
	
	public Transacao insert(Transacao obj) {
		Carteira cart = obj.getCarteira();
		Transacao objSave = repo.save(obj);
		cartRepo.save(cart);
		
		return objSave;
	}
	
	// Métodos auxiliares
	public Transacao fromInsertDTO(TransacaoInsertDTO objDTO) {
		Date data = new Date();
		Transacao obj = new Transacao(null, objDTO.getTitulo(), objDTO.getValor(), data, objDTO.getDescricao(), null);
		Carteira carteira = cartService.findById(objDTO.getCarteira().getId());
		obj.setCarteira(carteira);
		carteira.getTransacoes().add(obj);
		return obj;
	}
}

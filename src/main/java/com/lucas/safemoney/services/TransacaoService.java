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
import com.lucas.safemoney.domains.dto.TransacaoUpdateDTO;
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
		Carteira cart = trans.getCarteira();
		
		cart.setValor(cart.getValor() - trans.getValor());
		
		this.repo.delete(trans);
		this.cartRepo.save(cart);
	}
	
	
	/**
	 * Método responsável por inserir uma nova Transacao no banco de dados
	 * @param obj do tipo Transacao
	 * @return objSave do tipo Transacao
	 */
	public Transacao insert(Transacao obj) {
		Carteira cart = obj.getCarteira();
		
		// Atualizando o valor da carteira apartir da transacao
		if(obj.getValor() != null) {
			cart.setValor(cart.getValor() + obj.getValor());
		}
		
		Transacao objSave = repo.save(obj);
		cartRepo.save(cart);
		
		return objSave;
	}
	
	
	/**
	 * Método responsável por atualizar uma Transacao do banco de dados
	 * @param obj do tipo Transacao
	 * @return newObj do tipo Transacao
	 */
	public Transacao update(Transacao obj) {
		Transacao newObj = this.findById(obj.getId());
		Carteira cart = newObj.getCarteira();
		Double valorAtualizado = (cart.getValor() - newObj.getValor()) + obj.getValor();
		
		this.saveData(newObj, obj);
		cart.setValor(valorAtualizado);
		
		newObj = this.repo.save(newObj);
		this.cartRepo.save(cart);
		
		return newObj;
	}
	
	
	// Métodos auxiliares
	
	/**
	 * Método responsável por converter um objeto do tipo TransacaoInsertDTO para Transacao
	 * @param objDTO do tipo TransacaoInsertDTO
	 * @return obj do tipo Transacao
	 */
	public Transacao fromInsertDTO(TransacaoInsertDTO objDTO) {
		Date data = new Date();
		Transacao obj = new Transacao(null, objDTO.getTitulo(), objDTO.getValor(), data, objDTO.getDescricao(), null);
		Carteira carteira = cartService.findById(objDTO.getCarteira().getId());
		obj.setCarteira(carteira);
		carteira.getTransacoes().add(obj);
		return obj;
	}
	
	
	/**
	 * Método responsável por converter um objeto do tipo TransacaoUpdateDTO para Transacao
	 * @param objDTO do tipo TransacaoUpdateDTO
	 * @return obj do tipo Transacao
	 */
	public Transacao fromUpdateDTO(TransacaoUpdateDTO objDTO) {
		Transacao obj = new Transacao(null, objDTO.getTitulo(), objDTO.getValor(), null, objDTO.getDescricao(), null);
		
		return obj;
	}
	
	/**
	 * Método responsável por atualizar os dados de uma Transacao para Outra
	 * @param newObj do tipo Transacao
	 * @param obj do tipo Transacao
	 */
	private void saveData(Transacao newObj, Transacao obj) {
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

package com.lucas.safemoney.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucas.safemoney.domains.Transacao;
import com.lucas.safemoney.repositories.TransacaoRepository;
import com.lucas.safemoney.services.exceptions.ObjectNotFoundException;

@Service
public class TransacaoService {

	// Repositories
	@Autowired
	private TransacaoRepository repo;

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
}

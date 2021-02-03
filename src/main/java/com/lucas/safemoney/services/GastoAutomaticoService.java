package com.lucas.safemoney.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucas.safemoney.domains.GastoAutomatico;
import com.lucas.safemoney.repositories.GastoAutomaticoRepository;
import com.lucas.safemoney.services.exceptions.ObjectNotFoundException;

@Service
public class GastoAutomaticoService {

	// Repositories
	@Autowired
	private GastoAutomaticoRepository repo;

	/**
	 * Método responsável por retornar todos os Gastos automaticos do banco de dados
	 * 
	 * @return esse método retorna uma lista de gastos automaticos
	 */
	public List<GastoAutomatico> list() {
		return repo.findAll();
	}

	/**
	 * Método responsável por retornar um GastoAutomatico pelo id
	 * 
	 * @param id
	 * 
	 * @return esse método retorna um GastoAutomatico
	 */
	public GastoAutomatico findById(int id) {
		Optional<GastoAutomatico> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + GastoAutomatico.class.getName()));
	}	
	
	/**
	 * Método responsável por retornar uma página com gastos automáticos 
	 * @param page do tipo Integer
	 * @param linePerPage do tipo Integer
	 * @param direction do tipo String
	 * @param orderBy do tipo String
	 * 
	 * @return Page<GastoAutomatico>
	 */
	public Page<GastoAutomatico> findPage(Integer page, Integer linePerPage, String direction, String orderBy){
		PageRequest obj = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(obj);
	}
}

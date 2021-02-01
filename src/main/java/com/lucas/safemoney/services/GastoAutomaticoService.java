package com.lucas.safemoney.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.safemoney.domains.GastoAutomatico;
import com.lucas.safemoney.repositories.GastoAutomaticoRepository;
import com.lucas.safemoney.services.exceptions.ObjectNotFoundException;

@Service
public class GastoAutomaticoService {

	// Repositories
	@Autowired
	private GastoAutomaticoRepository repo;

	/*
	 * Método responsável por retornar todos os Gastos automaticos do banco de dados
	 * 
	 * @return esse método retorna uma lista de gastos automaticos
	 */
	public List<GastoAutomatico> list() {
		return repo.findAll();
	}

	/*
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
}

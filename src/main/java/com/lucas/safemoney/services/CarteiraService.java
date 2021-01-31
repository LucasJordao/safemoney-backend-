package com.lucas.safemoney.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.safemoney.domains.Carteira;
import com.lucas.safemoney.repositories.CarteiraRepository;
import com.lucas.safemoney.services.exceptions.ObjectNotFoundException;

@Service
public class CarteiraService {

	// Repositories
	@Autowired
	private CarteiraRepository repo;

	/*
	 * Método responsável por retornar todas as Carteiras do banco de dados
	 * 
	 * @return esse método retorna uma lista de carteiras
	 */
	public List<Carteira> list() {
		return repo.findAll();
	}

	/*
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
}

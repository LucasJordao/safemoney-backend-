package com.lucas.safemoney.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.lucas.safemoney.domains.TransacaoAutomatica;

public interface TransacaoAutomaticaRepository extends JpaRepository<TransacaoAutomatica, Integer>{
	
	@Transactional(readOnly = true)
	List<TransacaoAutomatica> findByPeriodoAndStatus(Integer value, Boolean status);

}

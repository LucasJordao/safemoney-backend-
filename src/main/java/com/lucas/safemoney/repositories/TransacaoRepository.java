package com.lucas.safemoney.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.lucas.safemoney.domains.Carteira;
import com.lucas.safemoney.domains.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer>{

	@Transactional(readOnly = true)
	List<Transacao> findByCarteira(Carteira carteira);
}

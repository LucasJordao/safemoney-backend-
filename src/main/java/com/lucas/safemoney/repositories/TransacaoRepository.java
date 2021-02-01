package com.lucas.safemoney.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.safemoney.domains.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer>{

}

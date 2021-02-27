package com.lucas.safemoney.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.lucas.safemoney.domains.Carteira;
import com.lucas.safemoney.domains.Usuario;

public interface CarteiraRepository extends JpaRepository<Carteira, Integer>{
	
	@Transactional(readOnly=true)
	Page<Carteira> findByUsuario(Usuario user, Pageable pageRequest);
	
}

package com.lucas.safemoney.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.safemoney.domains.Carteira;

public interface CarteiraRepository extends JpaRepository<Carteira, Integer>{

}

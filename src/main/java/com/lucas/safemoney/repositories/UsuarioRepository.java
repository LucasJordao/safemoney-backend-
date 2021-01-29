package com.lucas.safemoney.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.safemoney.domains.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}

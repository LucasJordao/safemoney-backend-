package com.lucas.safemoney.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.safemoney.domains.Usuario;
import com.lucas.safemoney.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
	
	// Services
	@Autowired
	private UsuarioService service;
	
	// EndPoints
	@GetMapping
	public ResponseEntity<List<Usuario>> listar(){
		List<Usuario> usuarios = service.list();
		
		return ResponseEntity.ok().body(usuarios);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> listarPorId(@PathVariable Integer id){
		Usuario obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
}

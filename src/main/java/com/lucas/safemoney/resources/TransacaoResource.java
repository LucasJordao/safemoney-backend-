package com.lucas.safemoney.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.safemoney.domains.Transacao;
import com.lucas.safemoney.services.TransacaoService;

@RestController
@RequestMapping(value = "/transacoes")
public class TransacaoResource {
	
	// Services
	@Autowired
	private TransacaoService service;
	
	// EndPoints
	@GetMapping
	public ResponseEntity<List<Transacao>> listar(){
		List<Transacao> transacoes = service.list();
		
		return ResponseEntity.ok().body(transacoes);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> listarPorId(@PathVariable Integer id){
		Transacao obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
}

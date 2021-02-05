package com.lucas.safemoney.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<Transacao>> findPage(
				@RequestParam(value="page", defaultValue="0") Integer page, 
				@RequestParam(value="linesPerPage", defaultValue="24") Integer linePerPage, 
				@RequestParam(value="direction", defaultValue="DESC") String direction,
				@RequestParam(value="orderBy", defaultValue="titulo") String orderBy){
		Page<Transacao> list = service.findPage(page, linePerPage, direction, orderBy);
		
		return ResponseEntity.ok().body(list);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id){
		this.service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}

package com.lucas.safemoney.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucas.safemoney.domains.Transacao;
import com.lucas.safemoney.domains.dto.TransacaoInsertDTO;
import com.lucas.safemoney.domains.dto.TransacaoUpdateDTO;
import com.lucas.safemoney.services.TransacaoService;

@RestController
@RequestMapping(value = "/transacoes")
public class TransacaoResource {
	
	// Services
	@Autowired
	private TransacaoService service;
	
	// EndPoints
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
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
	@PreAuthorize("hasAnyRole('ADMIN')")
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
	
	@PostMapping
	public ResponseEntity<Void> inserir(@Valid @RequestBody TransacaoInsertDTO objDTO){
		Transacao obj = this.service.fromInsertDTO(objDTO);
		obj = this.service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizar(@RequestBody TransacaoUpdateDTO objDTO, @PathVariable Integer id){
		Transacao obj = this.service.fromUpdateDTO(objDTO);
		obj.setId(id);
		
		this.service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
}

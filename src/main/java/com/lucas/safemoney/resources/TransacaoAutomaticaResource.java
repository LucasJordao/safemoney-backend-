package com.lucas.safemoney.resources;

import java.net.URI;
import java.text.ParseException;
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

import com.lucas.safemoney.domains.TransacaoAutomatica;
import com.lucas.safemoney.domains.dto.TransacaoAutomaticaInsertDTO;
import com.lucas.safemoney.domains.dto.TransacaoAutomaticaUpdateDTO;
import com.lucas.safemoney.services.TransacaoAutomaticaService;

@RestController
@RequestMapping(value = "/transacoes-automaticas")
public class TransacaoAutomaticaResource {
	
	// Services
	@Autowired
	private TransacaoAutomaticaService service;
	
	// EndPoints
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<TransacaoAutomatica>> listar(){
		List<TransacaoAutomatica> transacao = service.list();
		
		return ResponseEntity.ok().body(transacao);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> listarPorId(@PathVariable Integer id){
		TransacaoAutomatica obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/page")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Page<TransacaoAutomatica>> findPage(
				@RequestParam(value="page", defaultValue="0") Integer page, 
				@RequestParam(value="linesPerPage", defaultValue="24") Integer linePerPage, 
				@RequestParam(value="direction", defaultValue="DESC") String direction,
				@RequestParam(value="orderBy", defaultValue="titulo") String orderBy){
		Page<TransacaoAutomatica> list = service.findPage(page, linePerPage, direction, orderBy);
		
		return ResponseEntity.ok().body(list);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id){
		this.service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	
	@PostMapping
	public ResponseEntity<Void> inserir(@Valid @RequestBody TransacaoAutomaticaInsertDTO objDTO){
		TransacaoAutomatica obj = this.service.fromInsertDTO(objDTO);
		
		obj = this.service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizar(@Valid @RequestBody TransacaoAutomaticaUpdateDTO objDTO, @PathVariable Integer id){
		
		try {
			TransacaoAutomatica obj = this.service.fromUpdateDTO(objDTO, id);
			obj = this.service.update(obj);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Erro ao converter a data");
		}
		
		return ResponseEntity.noContent().build();
	}
}

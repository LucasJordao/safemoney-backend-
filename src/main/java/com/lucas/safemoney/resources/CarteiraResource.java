package com.lucas.safemoney.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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

import com.lucas.safemoney.domains.Carteira;
import com.lucas.safemoney.domains.dto.CarteiraInsertDTO;
import com.lucas.safemoney.domains.dto.CarteiraUpdateDTO;
import com.lucas.safemoney.services.CarteiraService;

@RestController
@RequestMapping(value = "/carteiras")
public class CarteiraResource {
	
	// Services
	@Autowired
	private CarteiraService service;
	
	// EndPoints
	@GetMapping
	public ResponseEntity<List<Carteira>> listar(){
		List<Carteira> carteiras = service.list();
		
		return ResponseEntity.ok().body(carteiras);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> listarPorId(@PathVariable Integer id){
		Carteira obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<Carteira>> findPage(
					@RequestParam(value="page", defaultValue="0") Integer page, 
					@RequestParam(value="linesPerPage", defaultValue="6") Integer linePerPage, 
					@RequestParam(value="direction", defaultValue="DESC") String direction,
					@RequestParam(value="orderBy", defaultValue="titulo") String orderBy){
		Page<Carteira> list = service.findPage(page, linePerPage, direction, orderBy);
		
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Void> inserir(@Valid @RequestBody CarteiraInsertDTO objDTO){
		
		Carteira cart = this.service.insert(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cart.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		
		this.service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizar(@Valid @RequestBody CarteiraUpdateDTO obj, @PathVariable Integer id){
		this.service.update(obj, id);
		
		return ResponseEntity.noContent().build();
	}
}

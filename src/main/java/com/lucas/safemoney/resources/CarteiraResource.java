package com.lucas.safemoney.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.safemoney.domains.Carteira;
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
}

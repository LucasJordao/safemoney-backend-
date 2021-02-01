package com.lucas.safemoney.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.safemoney.domains.GastoAutomatico;
import com.lucas.safemoney.services.GastoAutomaticoService;

@RestController
@RequestMapping(value = "/gastos-automaticos")
public class GastoAutomaticoResource {
	
	// Services
	@Autowired
	private GastoAutomaticoService service;
	
	// EndPoints
	@GetMapping
	public ResponseEntity<List<GastoAutomatico>> listar(){
		List<GastoAutomatico> gasto = service.list();
		
		return ResponseEntity.ok().body(gasto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> listarPorId(@PathVariable Integer id){
		GastoAutomatico obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
}

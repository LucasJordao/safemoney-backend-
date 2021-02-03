package com.lucas.safemoney.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<GastoAutomatico>> findPage(
				@RequestParam(value="page", defaultValue="0") Integer page, 
				@RequestParam(value="linesPerPage", defaultValue="24") Integer linePerPage, 
				@RequestParam(value="direction", defaultValue="DESC") String direction,
				@RequestParam(value="orderBy", defaultValue="titulo") String orderBy){
		Page<GastoAutomatico> list = service.findPage(page, linePerPage, direction, orderBy);
		
		return ResponseEntity.ok().body(list);
	}
}

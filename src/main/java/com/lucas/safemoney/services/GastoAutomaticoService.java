package com.lucas.safemoney.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucas.safemoney.domains.Carteira;
import com.lucas.safemoney.domains.GastoAutomatico;
import com.lucas.safemoney.domains.Transacao;
import com.lucas.safemoney.domains.dto.GastoAutomaticoInsertDTO;
import com.lucas.safemoney.repositories.CarteiraRepository;
import com.lucas.safemoney.repositories.GastoAutomaticoRepository;
import com.lucas.safemoney.services.exceptions.ObjectNotFoundException;

@Service
public class GastoAutomaticoService {

	// Repositories
	@Autowired
	private GastoAutomaticoRepository repo;
	@Autowired
	private CarteiraRepository cartRepo;
	
	
	// Services
	@Autowired
	private CarteiraService cartService;
	@Autowired 
	private TransacaoService transService;
	
	/**
	 * Método responsável por retornar todos os Gastos automaticos do banco de dados
	 * 
	 * @return esse método retorna uma lista de gastos automaticos
	 */
	public List<GastoAutomatico> list() {
		return repo.findAll();
	}

	/**
	 * Método responsável por retornar um GastoAutomatico pelo id
	 * 
	 * @param id
	 * 
	 * @return esse método retorna um GastoAutomatico
	 */
	public GastoAutomatico findById(int id) {
		Optional<GastoAutomatico> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + GastoAutomatico.class.getName()));
	}	
	
	/**
	 * Método responsável por retornar uma página com gastos automáticos 
	 * @param page do tipo Integer
	 * @param linePerPage do tipo Integer
	 * @param direction do tipo String
	 * @param orderBy do tipo String
	 * 
	 * @return Page<GastoAutomatico>
	 */
	public Page<GastoAutomatico> findPage(Integer page, Integer linePerPage, String direction, String orderBy){
		PageRequest obj = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(obj);
	}
	
	
	/**
	 * Método responsável por deletar um Gasto Automatico do banco de dados
	 * @param id do tipo GastoAutomatico
	 */
	public void delete(Integer id) {
		GastoAutomatico gasto = this.findById(id);
		
		this.repo.delete(gasto);
	}
	
	
	public GastoAutomatico insert(GastoAutomatico obj) {
		Transacao trans = new Transacao();
		Carteira cart = this.cartService.findById(obj.getCarteira().getId());
		obj.setId(null);
		obj.setCarteira(cart);
		cart.getGastosAutomaticos().add(obj);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dataAtual = sdf.format(new Date());
		
		
		if(dataAtual.equals(sdf.format(obj.getDataGasto()))) {
			trans.setCarteira(cart);
			trans.setData(new Date());
			trans.setDescricao("Transação feita a partir do gasto automático: " + obj.getTitulo());
			trans.setTitulo("Transação feita a partir do gasto automático");
			trans.setValor(obj.getValor());
			
			cart.getTransacoes().add(trans);
			this.transService.insert(trans);
			
		}
			
		GastoAutomatico gast = this.repo.save(obj);
		this.cartRepo.save(cart);
		return gast;
	}
	
	// Métodos auxiliares
	public GastoAutomatico fromInsertDTO(GastoAutomaticoInsertDTO objDTO) {
		Date data = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		GastoAutomatico gastAutomatico;
		try {
			gastAutomatico = new GastoAutomatico(null, objDTO.getTitulo(), objDTO.getValor(), data, objDTO.getPeriodo(), objDTO.getDescricao(), objDTO.getCarteira(), sdf.parse(objDTO.getDataGasto()));
		} catch (ParseException e) {
			throw new IllegalArgumentException("Erro ao converter a data");
		}
		
		return gastAutomatico;
	}
}

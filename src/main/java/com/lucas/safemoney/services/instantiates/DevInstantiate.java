package com.lucas.safemoney.services.instantiates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lucas.safemoney.domains.Carteira;
import com.lucas.safemoney.domains.TransacaoAutomatica;
import com.lucas.safemoney.domains.Transacao;
import com.lucas.safemoney.domains.Usuario;
import com.lucas.safemoney.domains.enums.Perfil;
import com.lucas.safemoney.domains.enums.TipoPeriodo;
import com.lucas.safemoney.repositories.CarteiraRepository;
import com.lucas.safemoney.repositories.TransacaoAutomaticaRepository;
import com.lucas.safemoney.repositories.TransacaoRepository;
import com.lucas.safemoney.repositories.UsuarioRepository;

@Service
public class DevInstantiate {
	
	// Repositories
	@Autowired
	private UsuarioRepository repo;
	@Autowired
	private CarteiraRepository cartRepo;
	@Autowired
	private TransacaoRepository transRepo;
	@Autowired
	private TransacaoAutomaticaRepository gastoRepo;
	
	// Classes auxiliares
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public void instantiate() throws ParseException {
		// Usuarios
		Usuario user1 = new Usuario(null, "Lucas William Silva Jordão", "lucas@hotmail.com", pe.encode("Senha12345"), "perfil.png");
		user1.addPerfil(Perfil.ADMIN);
		
		// Carteiras
		Carteira cart1 = new Carteira(null, "Carteira para comprar uma casa", "Dinheiro acumulado para comprar uma casa", 150.00, user1);
		Carteira cart2 = new Carteira(null, "Carteira para comprar um carro", "Dinheiro acumulado para comprar o carro", 500.00, user1);
		
		// Transacoes
		Transacao tran1 = new Transacao(null, "Adicionando 10 reais", 10.00, sdf.parse("12/10/2020 12:03"), "Adicionando 10 reais", cart1);
		Transacao tran2 = new Transacao(null, "Adicionando 140 reais", 140.00, sdf.parse("12/11/2020 13:00"), "Adicionando 140 reais", cart1);
		Transacao tran3 = new Transacao(null, "Adicionando 250 reais", 250.00, sdf.parse("12/10/2020 14:02"), "Adicionando 250 reais", cart2);
		Transacao tran4 = new Transacao(null, "Adicionando 250 reais", 250.00, sdf.parse("12/11/2020 15:20"), "Adicionando 250 reais", cart2);
		
		// Gastos Automaticos
		TransacaoAutomatica gast1 = new TransacaoAutomatica(null, "Gasto referente ao seguro", 10.00, new Date(), TipoPeriodo.MENSAL, "Gasto referente ao seguro futuro do carro", cart2, sdf.parse("12/11/2001 12:30"), true);
		
		// Fazendo relacionamento
		user1.getCarteiras().addAll(Arrays.asList(cart1, cart2));
		cart1.getTransacoes().addAll(Arrays.asList(tran1, tran2));
		cart2.getTransacoes().addAll(Arrays.asList(tran3, tran4));
		cart2.getTransacoesAutomaticas().addAll(Arrays.asList(gast1));
		
		// Fazendo persistencia
		repo.saveAll(Arrays.asList(user1));
		cartRepo.saveAll(Arrays.asList(cart1, cart2));
		transRepo.saveAll(Arrays.asList(tran1, tran2, tran3, tran4));
		gastoRepo.saveAll(Arrays.asList(gast1));
		cartRepo.saveAll(Arrays.asList(cart1, cart2));
	}
	
}

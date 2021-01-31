package com.lucas.safemoney.services.instantiates;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.safemoney.domains.Carteira;
import com.lucas.safemoney.domains.Usuario;
import com.lucas.safemoney.repositories.CarteiraRepository;
import com.lucas.safemoney.repositories.UsuarioRepository;

@Service
public class DevInstantiate {
	
	// Repositories
	@Autowired
	private UsuarioRepository repo;
	@Autowired
	private CarteiraRepository cartRepo;
	
	public void instantiate() {
		// Usuarios
		Usuario user1 = new Usuario(null, "Lucas William Silva Jordão", "lucas@hotmail.com", "Senha12345", "https://imagem-perfil.com");
		
		// Carteiras
		Carteira cart1 = new Carteira(null, "Carteira para comprar uma casa", "Dinheiro acumulado para comprar uma casa", 150.00, user1);
		Carteira cart2 = new Carteira(null, "Carteira para comprar um carro", "Dinheiro acumulado para comprar o carro", 500.00, user1);
		
		user1.getCarteiras().addAll(Arrays.asList(cart1, cart2));
		
		repo.saveAll(Arrays.asList(user1));
		cartRepo.saveAll(Arrays.asList(cart1, cart2));
	}
	
}

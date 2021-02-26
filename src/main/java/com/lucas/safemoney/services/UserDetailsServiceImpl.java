package com.lucas.safemoney.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lucas.safemoney.config.security.UserSS;
import com.lucas.safemoney.domains.Usuario;
import com.lucas.safemoney.repositories.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	// Repositories
	@Autowired
	private UsuarioRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario user = userRepo.findByEmail(email);
		
		if(user == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new UserSS(user.getId(), user.getEmail(), user.getSenha(), user.getPerfis());
	}

}

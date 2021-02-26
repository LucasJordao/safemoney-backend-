package com.lucas.safemoney.config.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lucas.safemoney.domains.enums.Perfil;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserSS implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	// Atributos
	private Integer id;
	private String email;
	private String senha;
	private  Collection<? extends GrantedAuthority> authorities;
	
	// Construturoes personalizados
	public UserSS(Integer id, String email, String senha, Set<Perfil> authorities) {
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.authorities = authorities.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}
	
	// Getters
	public Integer getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public boolean hasRole(Perfil perfil) {
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
	}

}

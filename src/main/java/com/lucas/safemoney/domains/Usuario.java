package com.lucas.safemoney.domains;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucas.safemoney.domains.enums.Perfil;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode(exclude = {"nome", "email", "senha", "perfil", "carteiras"})
@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// Atributos
	@Getter @Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Getter @Setter
	private String nome;
	@Getter @Setter
	@Column(unique = true)
	private String email;
	@Getter @Setter
	@JsonIgnore
	private String senha;
	@Getter @Setter
	private String perfil;
	
	// Relacionamentos
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@Getter @Setter
	private List<Carteira> carteiras = new ArrayList<>();
	
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<Integer> perfis = new HashSet<>();
	
	// Construtores
	public Usuario(Integer id, String nome, String email, String senha, String perfil) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.perfil = perfil;
	}
	
	// Getters e Setters personalizados
	public Set<Perfil> getPerfis(){
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCode());
	}
}

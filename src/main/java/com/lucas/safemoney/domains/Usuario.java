package com.lucas.safemoney.domains;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"nome", "email", "senha", "perfil"})
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
	private String email;
	@Getter @Setter
	private String senha;
	@Getter @Setter
	private String perfil;
}

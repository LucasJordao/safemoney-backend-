package com.lucas.safemoney.domains;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
	@NotEmpty(message = "O campo nome não pode ser vazio")
	@Size(min = 5, max = 500, message = "O tamanho do nome tem que ser entre 5 e 500")
	@Getter @Setter
	private String nome;
	@NotEmpty(message = "O campo email não pode ser vazio")
	@Size(min = 5, max = 500, message = "O tamanho do email tem que ser entre 5 e 500")
	@Email(message = "O email informado é inválido")
	@Getter @Setter
	private String email;
	@NotEmpty(message = "O campo senha não pode ser vazio")
	@Size(min = 5, max = 50, message = "O tamanho da senha tem que ser entre 5 e 50")
	@Getter @Setter
	private String senha;
	@Getter @Setter
	private String perfil;
	
	// Relacionamentos
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@Getter @Setter
	private List<Carteira> carteiras = new ArrayList<>();
	
	// Construtores
	public Usuario(Integer id, String nome, String email, String senha, String perfil) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.perfil = perfil;
	}
}

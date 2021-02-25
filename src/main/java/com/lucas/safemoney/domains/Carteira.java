package com.lucas.safemoney.domains;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode(exclude = {"titulo", "descricao", "valor"})
@Entity
@Table(name = "CARTEIRA")
public class Carteira implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Integer id;
	@Getter @Setter
	private String titulo;
	@Getter @Setter
	private String descricao;
	@Getter @Setter
	private Double valor;
	
	// Relacionamentos
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	@Getter @Setter
	private Usuario usuario;
	
	@OneToMany(mappedBy = "carteira", cascade = CascadeType.ALL)
	@Getter @Setter
	private List<Transacao> transacoes = new ArrayList<>();
	
	@OneToMany(mappedBy = "carteira", cascade = CascadeType.ALL)
	@Getter @Setter
	private List<TransacaoAutomatica> transacoesAutomaticas = new ArrayList<>();

	// Construtores
	public Carteira(Integer id, String titulo, String descricao, Double valor, Usuario usuario) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.valor = valor;
		this.usuario = usuario;
	}
	
	
}

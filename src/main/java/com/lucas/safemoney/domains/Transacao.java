package com.lucas.safemoney.domains;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"titulo", "valor", "data", "descricao"})
@Entity
@Table(name = "TRANSACAO")
public class Transacao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Integer id;
	@Getter @Setter
	private String titulo;
	@Getter @Setter
	private Double valor;
	@Getter @Setter
	private Date data;
	@Getter @Setter
	private String descricao;
	
	// Relacionamentos
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "carteira_id")
	@Getter @Setter
	private Carteira carteira;
}

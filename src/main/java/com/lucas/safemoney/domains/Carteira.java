package com.lucas.safemoney.domains;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
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
	private Float valor;
	
	// Relacionamentos
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	@Getter @Setter
	private Usuario usuario;
}

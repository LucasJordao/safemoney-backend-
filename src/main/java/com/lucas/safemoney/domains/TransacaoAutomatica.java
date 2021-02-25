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
import com.lucas.safemoney.domains.enums.TipoPeriodo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode(exclude = {"titulo", "valor", "dataInicial", "periodo", "descricao", "dataTransacao"})
@Entity
@Table(name = "TRANSACAO_AUTOMATICA")
public class TransacaoAutomatica implements Serializable{

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
	private Date dataInicial;
	private Integer periodo;
	@Getter @Setter
	private String descricao;
	@Getter @Setter
	private Date dataTransacao;
	@Getter @Setter
	private Boolean status;
	
	// Relacionamentos
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "carteira_id")
	@Getter @Setter
	private Carteira carteira;
	
	// Construtores
	public TransacaoAutomatica(Integer id, String titulo, Double valor, Date dataInicial, TipoPeriodo periodo,
			String descricao, Carteira carteira, Date dataTransacao, Boolean status) {
		this.id = id;
		this.titulo = titulo;
		this.valor = valor;
		this.dataInicial = dataInicial;
		this.periodo = periodo == null ? null : periodo.getCode();
		this.descricao = descricao;
		this.carteira = carteira;
		this.dataTransacao = dataTransacao;
		this.status = status;
	}
	
	// Getters and Setters
	public TipoPeriodo getPeriodo() {
		return TipoPeriodo.toEnum(periodo);
	}
	
	public void setPeriodo(TipoPeriodo periodo) {
		this.periodo = periodo.getCode();
	}
}

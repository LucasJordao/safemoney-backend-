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
@EqualsAndHashCode(exclude = {"titulo", "valor", "dataInicial", "periodo", "descricao"})
@Entity
@Table(name = "GASTO_AUTOMATICO")
public class GastoAutomatico implements Serializable{

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
	private Date dataGasto;
	
	// Relacionamentos
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "carteira_id")
	@Getter @Setter
	private Carteira carteira;
	
	// Construtores
	public GastoAutomatico(Integer id, String titulo, Double valor, Date dataInicial, TipoPeriodo periodo,
			String descricao, Carteira carteira, Date dataGasto) {
		this.id = id;
		this.titulo = titulo;
		this.valor = valor;
		this.dataInicial = dataInicial;
		this.periodo = periodo.getCode();
		this.descricao = descricao;
		this.carteira = carteira;
		this.dataGasto = dataGasto;
	}
	
	// Getters and Setters
	public TipoPeriodo getPeriodo() {
		return TipoPeriodo.toEnum(periodo);
	}
	
	public void setPeriodo(TipoPeriodo periodo) {
		this.periodo = periodo.getCode();
	}
}

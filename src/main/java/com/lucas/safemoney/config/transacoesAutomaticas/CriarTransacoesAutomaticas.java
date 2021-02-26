package com.lucas.safemoney.config.transacoesAutomaticas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lucas.safemoney.domains.Transacao;
import com.lucas.safemoney.domains.TransacaoAutomatica;
import com.lucas.safemoney.domains.enums.TipoPeriodo;
import com.lucas.safemoney.repositories.TransacaoAutomaticaRepository;
import com.lucas.safemoney.services.TransacaoService;

@EnableAsync
@Component @EnableScheduling
public class CriarTransacoesAutomaticas {
	// Repositories
	@Autowired
	private TransacaoAutomaticaRepository repo;
	
	// Services
	@Autowired
	private TransacaoService transService;

	// Objetos auxiliares
	SimpleDateFormat diaMesAno = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat dia = new SimpleDateFormat("dd");
	Date agora = new Date(System.currentTimeMillis());
	String dataAtual = diaMesAno.format(agora);
	String diaAtual = dia.format(agora);
	
	private static final String TIME_ZONE = "America/Sao_Paulo";
	
	@Async
	@Scheduled(cron = "0 0 1 * * *", zone = TIME_ZONE)
	public void criarTransacao() {
		try {
			
			this.criarTransacaoMensal();
			this.criarTransacaoDiaria();
			this.criarTransacaoAnual();
			
		} catch (ParseException e) {
			e.printStackTrace();
		}		
	}
	
	
	/**
	 * Método responsável por verificar e criar transação mensal automática
	 * @throws ParseException 
	 */
	private void criarTransacaoMensal() throws ParseException {
		System.out.println("Checando transações automaticas mensais");
		
		List<TransacaoAutomatica> listaTransAutomatica = repo.findByPeriodoAndStatus(TipoPeriodo.MENSAL.getCode(), true);
		
		for(TransacaoAutomatica x: listaTransAutomatica) {
		
			if(!(diaMesAno.parse(dataAtual).before(diaMesAno.parse(diaMesAno.format(x.getDataTransacao())))) && diaAtual.equals(dia.format(x.getDataTransacao()))) {
				
				Transacao newTras = new Transacao(null, x.getTitulo() + " (TA)", x.getValor(), diaMesAno.parse(dataAtual), x.getDescricao(), x.getCarteira());
	
				transService.insert(newTras);
			}
			
		}
	}
	
	
	/**
	 * Método responsável por verificar e criar transação diaria automática
	 * @throws ParseException 
	 */
	private void criarTransacaoDiaria() throws ParseException {
		System.out.println("Checando transações automaticas diárias");
		
		List<TransacaoAutomatica> listaTransAutomatica = repo.findByPeriodoAndStatus(TipoPeriodo.DIARIO.getCode(), true);
		String dataAtual = diaMesAno.format(agora);
		
		for(TransacaoAutomatica x: listaTransAutomatica) {
			if(!(diaMesAno.parse(dataAtual).before(diaMesAno.parse(diaMesAno.format(x.getDataTransacao()))))) {

				Transacao newTras = new Transacao(null, x.getTitulo() + " (TA)", x.getValor(), diaMesAno.parse(dataAtual), x.getDescricao(), x.getCarteira());

				transService.insert(newTras);
			}
		}
	}
	
	/**
	 * Método responsável por criar transações anuais
	 * @throws ParseException
	 */
	private void criarTransacaoAnual() throws ParseException {
		System.out.println("Checando transação automatica anual");
		Calendar dataAgora = Calendar.getInstance();
		Calendar dataTransacao = Calendar.getInstance();
		
		dataAgora.setTime(diaMesAno.parse(dataAtual));
		int anoAtual = dataAgora.get(Calendar.YEAR);
		int diaAtual = dataAgora.get(Calendar.DAY_OF_MONTH);
		int mesAtual = dataAgora.get(Calendar.MONTH);
		
		List<TransacaoAutomatica> listaTransAutomatica = repo.findByPeriodoAndStatus(TipoPeriodo.ANUAL.getCode(), true);
		
		for(TransacaoAutomatica x: listaTransAutomatica) {
			System.out.println(x.getStatus());
			dataTransacao.setTime(x.getDataTransacao());
			int anoTrans = dataTransacao.get(Calendar.YEAR);
			int mesTrans = dataTransacao.get(Calendar.MONTH);
			int diaTrans = dataTransacao.get(Calendar.DAY_OF_MONTH);
			
			if(!(diaMesAno.parse(dataAtual).before(diaMesAno.parse(diaMesAno.format(x.getDataTransacao()))))) {
				if(diaTrans == diaAtual && mesTrans == mesAtual && anoAtual >= anoTrans) {
					Transacao newTras = new Transacao(null, x.getTitulo() + " (TA)", x.getValor(), diaMesAno.parse(dataAtual), x.getDescricao(), x.getCarteira());

					transService.insert(newTras);
				}
			}
		}
	}
	
	
}

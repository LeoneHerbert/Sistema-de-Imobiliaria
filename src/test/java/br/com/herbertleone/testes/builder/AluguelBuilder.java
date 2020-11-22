package br.com.herbertleone.testes.builder;
import br.com.herbertleone.api.model.Aluguel;
import br.com.herbertleone.api.model.Locacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AluguelBuilder {

	private Aluguel aluguel;

	private AluguelBuilder() {}
	
	public static AluguelBuilder umAluguel() {

		AluguelBuilder builder = new AluguelBuilder();

		builder.aluguel = new Aluguel();

		Locacao locacao = LocacaoBuilder.umaLocacao().paraUmImovel("Casa").paraUmCliente("Herbert").constroi();

		builder.aluguel.setLocacao(locacao);

		builder.aluguel.setDataVencimento(LocalDate.now().plusDays(30));

		return builder;
	}

	public AluguelBuilder comDataDeVencimento(LocalDate dataVencimento) {
		aluguel.setDataVencimento(dataVencimento);
		return this;
	}

	public AluguelBuilder comDataDePagamento(LocalDate dataPagamento) {
		aluguel.setDataPagamento(dataPagamento);
		return this;
	}

	public AluguelBuilder comLocacao(Locacao locacao){
		aluguel.setLocacao(locacao);
		return this;
	}

	public AluguelBuilder comValorpago(BigDecimal valorPago) {
		aluguel.setValorPago(valorPago);
		return this;
	}

	public Aluguel constroi(){
		return aluguel;
	}
}
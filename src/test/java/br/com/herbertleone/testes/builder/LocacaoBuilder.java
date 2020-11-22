package br.com.herbertleone.testes.builder;
import br.com.herbertleone.api.model.Cliente;
import br.com.herbertleone.api.model.Imovel;
import br.com.herbertleone.api.model.Locacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LocacaoBuilder {

	private Locacao locacao;
	private static Long contadorID = new Long(1);

	private LocacaoBuilder() {}
	
	public static LocacaoBuilder umaLocacao() {

		LocacaoBuilder builder = new LocacaoBuilder();

		builder.locacao = new Locacao();

		builder.locacao.setAtivo(true);
		builder.locacao.setDataInicio(LocalDate.now());
		builder.locacao.setValorAluguel(new BigDecimal(5000));

		return builder;
	}

	public LocacaoBuilder paraUmImovel(String tipoImovel) {
		locacao.setImovel(new Imovel(tipoImovel));
		return this;
	}

	public LocacaoBuilder paraUmCliente(String nomeCliente) {
		locacao.setCliente(new Cliente(nomeCliente));
		return this;
	}

	public LocacaoBuilder paraUmCliente(Cliente cliente) {
		locacao.setCliente(cliente);
		return this;
	}

	public LocacaoBuilder ativo(boolean ativo) {
		locacao.setAtivo(ativo);
		return this;
	}

	public LocacaoBuilder noBairro(String bairro) {
		locacao.getImovel().setBairro(bairro);
		return this;
	}

	public LocacaoBuilder comValorSugerido(BigDecimal valorSugerido) {
		locacao.getImovel().setValorDoAluguelSugerido(valorSugerido);
		return this;
	}
	public LocacaoBuilder comValorDeAluguel(BigDecimal valorAluguel) {
		locacao.setValorAluguel(valorAluguel);
		return this;
	}

	public Locacao constroi(){
		return locacao;
	}
}
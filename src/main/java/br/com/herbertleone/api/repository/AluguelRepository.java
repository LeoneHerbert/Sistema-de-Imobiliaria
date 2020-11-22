package br.com.herbertleone.api.repository;
import br.com.herbertleone.api.model.Aluguel;

import java.time.LocalDate;
import java.util.List;

public interface AluguelRepository {

	void salva(Aluguel aluguel);

	void exclui(Aluguel aluguel);

	void atualiza(Aluguel aluguel);

	Aluguel buscaPorDataVencimento(LocalDate dataVencimento);

	List<Aluguel> recuperarAlugueisPagosPor(String nomeCliente);

	List<Aluguel> recuperarAlugueisPagosEmAtrasoNaDataDeVencimentoPor(String nomeCliente);

	List<Aluguel> recuperarAlugueisPagosEmAtraso();
}

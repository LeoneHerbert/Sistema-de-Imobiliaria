package br.com.herbertleone.api.repository;
import br.com.herbertleone.api.model.Locacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface LocacaolRepository {

	Locacao buscaPorDataInicio(LocalDate dataInicio);

	List<Locacao> recuperarLocacoesPor(String bairro, String tipoImovel, boolean ativo);

	List<Locacao> recuperarLocacoesPor(BigDecimal limitePreco, boolean ativo);

	void salva(Locacao locacao);

	void exclui(Locacao locacao);

	void atualiza(Locacao locacao);
}

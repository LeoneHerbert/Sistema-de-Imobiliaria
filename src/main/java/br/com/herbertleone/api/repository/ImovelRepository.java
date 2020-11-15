package br.com.herbertleone.api.repository;
import br.com.herbertleone.api.model.Imovel;

public interface ImovelRepository {

	Imovel buscaPorId(Long id);

	Imovel buscaPorTipo(String tipo);

	void salva(Imovel imovel);

	void exclui(Imovel imovel);

	void atualiza(Imovel imovel);

}

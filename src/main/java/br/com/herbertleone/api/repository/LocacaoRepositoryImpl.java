package br.com.herbertleone.api.repository;

import br.com.herbertleone.api.model.Locacao;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class LocacaoRepositoryImpl implements LocacaolRepository {

	private EntityManager manager;

	public LocacaoRepositoryImpl(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public Locacao buscaPorDataInicio(LocalDate dataInicio) {
		return manager.createQuery("SELECT locacao FROM Locacao locacao WHERE locacao.dataInicio = :pDataInicio", Locacao.class)
				.setParameter("pDataInicio", dataInicio)
				.getSingleResult();
	}

	@Override
	public List<Locacao> recuperarLocacoesPor(String bairro, String tipoImovel, boolean ativo) {
		return manager.createQuery("SELECT locacao FROM Locacao locacao WHERE locacao.ativo = :pAtivo AND locacao.imovel.bairro = :pBairro AND locacao.imovel.tipoDeImovel = :pTipoImovel" , Locacao.class)
				.setParameter("pAtivo", ativo)
				.setParameter("pBairro", bairro)
				.setParameter("pTipoImovel", tipoImovel)
				.getResultList();
	}

	@Override
	public List<Locacao> recuperarLocacoesPor(BigDecimal limitePreco, boolean ativo) {
		return manager.createQuery("SELECT locacao FROM Locacao locacao WHERE locacao.ativo = :pAtivo AND locacao.imovel.valorDoAluguelSugerido <= :pLimitePreco " , Locacao.class)
				.setParameter("pAtivo", ativo)
				.setParameter("pLimitePreco", limitePreco)
				.getResultList();
	}

	@Override
	public void salva(Locacao locacao) {
		manager.persist(locacao);
	}

	@Override
	public void exclui(Locacao locacao) {
		manager.remove(locacao);
	}

	@Override
	public void atualiza(Locacao locacao) {
		manager.merge(locacao);
	}
}

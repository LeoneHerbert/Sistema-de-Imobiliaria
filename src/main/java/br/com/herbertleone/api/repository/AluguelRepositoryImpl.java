package br.com.herbertleone.api.repository;

import br.com.herbertleone.api.model.Aluguel;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class AluguelRepositoryImpl implements AluguelRepository {

	private EntityManager manager;

	public AluguelRepositoryImpl(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void salva(Aluguel aluguel) {
		manager.persist(aluguel);
	}

	@Override
	public void exclui(Aluguel aluguel) {
		manager.remove(aluguel);
	}

	@Override
	public void atualiza(Aluguel aluguel) {
		manager.merge(aluguel);
	}

	@Override
	public Aluguel buscaPorDataVencimento(LocalDate dataVencimento) {
		return manager.createQuery("SELECT aluguel FROM Aluguel aluguel WHERE aluguel.dataVencimento = :pDataVencimento", Aluguel.class)
				.setParameter("pDataVencimento", dataVencimento)
				.getSingleResult();
	}

	@Override
	public List<Aluguel> recuperarAlugueisPagosPor(String nomeCliente) {
		return manager.createQuery("SELECT aluguel FROM Aluguel aluguel WHERE aluguel.locacao.cliente.nome = :pNomeCliente" , Aluguel.class)
				.setParameter("pNomeCliente", nomeCliente)
				.getResultList();
	}

	@Override
	public List<Aluguel> recuperarAlugueisPagosEmAtrasoNaDataDeVencimentoPor(String nomeCliente) {
		return manager.createQuery("SELECT aluguel FROM Aluguel aluguel WHERE aluguel.locacao.cliente.nome = :pNomeCliente AND data_pagamento > data_vencimento", Aluguel.class)
				.setParameter("pNomeCliente", nomeCliente)
				.getResultList();
	}
}

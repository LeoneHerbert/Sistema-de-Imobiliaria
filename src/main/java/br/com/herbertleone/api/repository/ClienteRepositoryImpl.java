package br.com.herbertleone.api.repository;

import br.com.herbertleone.api.model.Cliente;

import javax.persistence.EntityManager;

public class ClienteRepositoryImpl implements ClienteRepository {

	private EntityManager manager;

	public ClienteRepositoryImpl(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public Cliente buscaPorNome(String nome) {
		return manager.createQuery("SELECT cliente FROM Cliente cliente WHERE cliente.nome = :pNome", Cliente.class)
				.setParameter("pNome", nome)
				.getSingleResult();
	}

	@Override
	public void salva(Cliente cliente) {
		manager.persist(cliente);
	}

	@Override
	public void exclui(Cliente cliente) {
		manager.remove(cliente);
	}

	@Override
	public void atualiza(Cliente cliente) {
		manager.merge(cliente);
	}
}

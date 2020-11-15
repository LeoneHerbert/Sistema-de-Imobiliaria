package br.com.herbertleone.api.repository;
import br.com.herbertleone.api.model.Cliente;

public interface ClienteRepository {

	Cliente buscaPorNome(String nome);

	void salva(Cliente cliente);

	void exclui(Cliente cliente);

	void atualiza(Cliente cliente);
}

package br.com.herbertleone.testes.builder;
import br.com.herbertleone.api.model.Cliente;

public class ClienteBuilder {

	private Cliente cliente;
	private static Long contadorID = new Long(1);

	private ClienteBuilder() {}
	
	public static ClienteBuilder umCliente() {
		
		ClienteBuilder builder = new ClienteBuilder();
		builder.cliente = new Cliente();
		builder.cliente.setId(contadorID + new Long(1));
		builder.cliente.setNome("Cliente 1");
		return builder;
	}

	public ClienteBuilder comNome(String nome) {
		cliente.setNome(nome);
		return this;
	}

	public Cliente constroi(){
		return cliente;
	}
}
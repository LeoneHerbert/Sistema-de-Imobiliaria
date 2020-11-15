package br.com.herbertleone.testes.repository;

import br.com.herbertleone.api.model.Cliente;
import br.com.herbertleone.api.repository.ClienteRepository;
import br.com.herbertleone.api.repository.ClienteRepositoryImpl;
import org.junit.jupiter.api.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class ClienteRepositoryTest {

	private EntityManager manager;
	private static EntityManagerFactory emf;
	private ClienteRepository clientes;

	@BeforeAll
	public static void inicio() {
		emf = Persistence.createEntityManagerFactory("sistemaDeImobiliaria_test");
	}

	@BeforeEach
	public void antes() {
		manager = emf.createEntityManager();
		manager.getTransaction().begin();
		clientes = new ClienteRepositoryImpl(manager);
	}

	@AfterEach
	public void depois() {
		manager.getTransaction().rollback();
	}

	@AfterAll
	public static void fim() {
		emf.close();
	}

	@Test
	public void deveSalvarUmCliente() {

		Cliente cliente = new Cliente("Herbert");

		clientes.salva(cliente);
		manager.flush();
		manager.clear();

		Cliente novoCliente = clientes.buscaPorNome("Herbert");
		Assertions.assertNotNull(novoCliente);
	}

	@Test
	public void deveAtualizarUmCliente() {

		Cliente cliente = new Cliente("Herbert");

		clientes.salva(cliente);
		cliente.setNome(("Eduardo"));

		clientes.atualiza(cliente);

		manager.flush();
		manager.clear();

		Cliente novoCliente = clientes.buscaPorNome("Eduardo");
		Assertions.assertNotNull(novoCliente);

		Assertions.assertThrows(NoResultException.class,
				() -> clientes.buscaPorNome("Herbert"),
				"Deveria ter lançado a exceção NoResultException");
	}

	@Test
	public void deveExcluirUmCliente() {

		Cliente cliente = new Cliente("Herbert");

		clientes.salva(cliente);
		clientes.exclui(cliente);

		manager.flush();
		manager.clear();

		Assertions.assertThrows(NoResultException.class,
				() -> clientes.buscaPorNome("Herbert"),
				"Deveria ter lançado a exceção NoResultException");
	}


	@Test
	public void deveEncontrarClientePeloNome() {

		Assertions.assertThrows(NoResultException.class,
				() -> clientes.buscaPorNome("Herbert"),
				"Deveria lançar a exceção NoResultException");

		clientes.salva(new Cliente("Herbert") );
		manager.flush();
		manager.clear();

		Cliente clienteDoBanco = clientes.buscaPorNome("Herbert");

		Assertions.assertEquals("Herbert", clienteDoBanco.getNome());
	}

}

package br.com.herbertleone.testes.repository;

import br.com.herbertleone.api.repository.AluguelRepository;
import br.com.herbertleone.api.repository.AluguelRepositoryImpl;
import br.com.herbertleone.testes.builder.AluguelBuilder;
import br.com.herbertleone.testes.builder.LocacaoBuilder;
import br.com.herbertleone.api.model.Aluguel;
import br.com.herbertleone.api.model.Locacao;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public class AluguelRepositoryTest {

    private static EntityManagerFactory emf;
    private EntityManager manager;
    private AluguelRepository alugueis;

    @BeforeAll
    public static void inicio() {
        emf = Persistence.createEntityManagerFactory("sistemaDeImobiliaria_test");
    }

    @AfterAll
    public static void fim() {
        emf.close();
    }

    @BeforeEach
    public void antes() {
        manager = emf.createEntityManager();
        manager.getTransaction().begin();
        alugueis = new AluguelRepositoryImpl(manager);
    }

    @AfterEach
    public void depois() {
        manager.getTransaction().rollback();
    }

    @Test
    public void deveSalvarUmAluguel() {

        Aluguel aluguel = AluguelBuilder.umAluguel().constroi();

        alugueis.salva(aluguel);
        manager.flush();
        manager.clear();

        Aluguel novoAluguel = alugueis.buscaPorDataVencimento(LocalDate.now().plusDays(30));

        Assertions.assertNotNull(novoAluguel);

    }

    @Test
    public void deveAtualizarUmAluguel() {

        Aluguel aluguel = AluguelBuilder.umAluguel().constroi();
        aluguel.setObs("Teste");
        alugueis.salva(aluguel);

        aluguel.setObs("MUDOU");
        alugueis.atualiza(aluguel);

        manager.flush();
        manager.clear();

        Assertions.assertEquals("MUDOU", aluguel.getObs());

    }

    @Test
    public void deveExcluirUmAluguel() {
        Aluguel aluguel = AluguelBuilder.umAluguel().constroi();

        alugueis.salva(aluguel);
        alugueis.exclui(aluguel);

        manager.flush();
        manager.clear();

        Assertions.assertThrows(NoResultException.class,
                () -> alugueis.buscaPorDataVencimento(LocalDate.now().plusDays(30)),
                "Deveria ter lançado a exceção NoResultException");
    }

    @Test
    public void deveRecuperarUmaListaComTodosOsAlugueisPagosDeUmCliente() {


        Aluguel aluguel01 = AluguelBuilder.umAluguel().comDataDeVencimento(LocalDate.of(2020, 11, 30)).comValorpago(new BigDecimal(5000)).constroi();

        Aluguel aluguel02 = AluguelBuilder.umAluguel().comDataDeVencimento(LocalDate.of(2020, 11, 25)).comValorpago(new BigDecimal(5000)).constroi();

        Aluguel aluguel03 = AluguelBuilder.umAluguel().comDataDeVencimento(LocalDate.of(2020, 11, 20)).constroi();

        alugueis.salva(aluguel01);
        alugueis.salva(aluguel02);
        alugueis.salva(aluguel03);

        manager.flush();
        manager.clear();

        List<Aluguel> listaDeAlugueis = alugueis.recuperarAlugueisPagosPor("Herbert");

        Stream<Aluguel> listaDeAlugueisPagos = listaDeAlugueis.
                stream().filter(aluguel -> aluguel.getValorPago() != null);

        Assertions.assertEquals(2, listaDeAlugueisPagos.count());
    }

    @Test
    public void deveRecuperarUmaListaComTodosOsAlugueisPagosComAtrasoNaDataDeVencimentoDeUmCliente() {


        Aluguel aluguel01 = AluguelBuilder.umAluguel().comDataDeVencimento(LocalDate.of(2020, 11, 30)).comDataDePagamento(LocalDate.of(2020, 12, 25)).comValorpago(new BigDecimal(5000)).constroi();

        Aluguel aluguel02 = AluguelBuilder.umAluguel().comDataDeVencimento(LocalDate.of(2020, 11, 25)).comDataDePagamento(LocalDate.of(2020, 11, 25)).comValorpago(new BigDecimal(5000)).constroi();

        Aluguel aluguel03 = AluguelBuilder.umAluguel().comDataDeVencimento(LocalDate.of(2020, 11, 20)).comDataDePagamento(LocalDate.of(2020, 10, 25)).constroi();

        alugueis.salva(aluguel01);
        alugueis.salva(aluguel02);
        alugueis.salva(aluguel03);

        manager.flush();
        manager.clear();

        List<Aluguel> listaDeAlugueis = alugueis.recuperarAlugueisPagosEmAtrasoNaDataDeVencimentoPor("Herbert");

        Stream<Aluguel> listaDeAlugueisPagos = listaDeAlugueis.
                stream().filter(aluguel -> aluguel.getValorPago() != null);

        Assertions.assertEquals(1, listaDeAlugueisPagos.count());
    }

    @Test
    public void deveLancarUmaExcecaoSeOValorPagoForMenorQueOValorDoAluguel(){
        Locacao locacao = LocacaoBuilder.umaLocacao().comValorDeAluguel(new BigDecimal(2000)).constroi();
        Aluguel aluguel = new Aluguel();
        aluguel.setLocacao(locacao);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> aluguel.setValorPago(new BigDecimal(1800)),
                "O valor pago deve ser igual ao valor do aluguel" );

    }

    @Test
    public void deveRetornarValorSemAcrescimoDeMultas(){
        Locacao locacao = LocacaoBuilder.umaLocacao().comValorDeAluguel(new BigDecimal(2000)).constroi();

        Aluguel aluguel = AluguelBuilder.umAluguel().comDataDeVencimento(LocalDate.of(2020, 11, 30)).comDataDePagamento(LocalDate.of(2020, 11, 30)).constroi();

        Assertions.assertDoesNotThrow(() -> aluguel.setValorPago(new BigDecimal(2000)));
    }
}

package br.com.herbertleone.testes.service;

import br.com.herbertleone.api.model.Aluguel;
import br.com.herbertleone.api.model.Cliente;
import br.com.herbertleone.api.model.Locacao;
import br.com.herbertleone.api.repository.AluguelRepository;
import br.com.herbertleone.api.repository.ClienteRepository;
import br.com.herbertleone.api.service.AluguelService;
import br.com.herbertleone.api.service.EmailService;
import br.com.herbertleone.testes.builder.AluguelBuilder;
import br.com.herbertleone.testes.builder.ClienteBuilder;
import br.com.herbertleone.testes.builder.LocacaoBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class AluguelServiceTest {

    private EmailService emailService;
    private AluguelService aluguelService;
    private AluguelRepository aluguelRepository;
    private Cliente cliente;

    @BeforeEach
    public void setup() {
        aluguelService = new AluguelService();

        cliente = ClienteBuilder.umCliente().constroi();

        Cliente cliente = mock(Cliente.class);

        aluguelRepository =  Mockito.mock(AluguelRepository.class);

        emailService = Mockito.mock(EmailService.class );

        aluguelService.setAluguelRepository(aluguelRepository );

        aluguelService.setEmailService(emailService );
    }

    @Test
    public void deveEnviarEmailParaUsuariosAtrasados() {

        Cliente cliente1 = mock(Cliente.class);
        Cliente cliente2 = mock(Cliente.class);
        Cliente cliente3 = mock(Cliente.class);
        Cliente cliente4 = mock(Cliente.class);

        Locacao locacao1 = LocacaoBuilder.umaLocacao().paraUmCliente(cliente1).constroi();
        Locacao locacao2 = LocacaoBuilder.umaLocacao().paraUmCliente(cliente2).constroi();
        Locacao locacao3 = LocacaoBuilder.umaLocacao().paraUmCliente(cliente3).constroi();

        List<Aluguel> alugueisEmAtraso = Arrays.asList(
                AluguelBuilder.umAluguel().comLocacao(locacao1).comDataDeVencimento(LocalDate.of(2020, 11, 30)).comDataDePagamento(LocalDate.of(2020, 12, 25)).comValorpago(new BigDecimal(5008.25)).constroi(),
                AluguelBuilder.umAluguel().comLocacao(locacao2).comDataDeVencimento(LocalDate.of(2020, 11, 30)).comDataDePagamento(LocalDate.of(2020, 12, 25)).comValorpago(new BigDecimal(5008.25)).constroi(),
                AluguelBuilder.umAluguel().comLocacao(locacao3).comDataDeVencimento(LocalDate.of(2020, 11, 30)).comDataDePagamento(LocalDate.of(2020, 12, 25)).comValorpago(new BigDecimal(5008.25)).constroi()
        );

        Mockito.when(aluguelRepository.recuperarAlugueisPagosEmAtraso()).thenReturn(alugueisEmAtraso);

        aluguelService.enviaEmailParaClientesQueNaoPagaramNaDataPrevista();

        Mockito.verify(emailService, times(1)).notifica(cliente1);
        Mockito.verify(emailService, times(1)).notifica(cliente2);
        Mockito.verify(emailService, times(1)).notifica(cliente3);
        Mockito.verify(emailService, Mockito.never()).notifica(cliente4);

        verifyNoMoreInteractions(emailService);
    }

    @Test
    public void verificaSeUmaExcecaoFoiLancada() {

        Cliente cliente1 = mock(Cliente.class);
        Cliente cliente2 = mock(Cliente.class);
        Cliente cliente3 = mock(Cliente.class);
        Cliente cliente4 = mock(Cliente.class);

        Locacao locacao1 = LocacaoBuilder.umaLocacao().paraUmCliente(cliente1).constroi();
        Locacao locacao2 = LocacaoBuilder.umaLocacao().paraUmCliente(cliente2).constroi();
        Locacao locacao3 = LocacaoBuilder.umaLocacao().paraUmCliente(cliente3).constroi();

        List<Aluguel> alugueisEmAtraso = Arrays.asList(
                AluguelBuilder.umAluguel().comLocacao(locacao1).comDataDeVencimento(LocalDate.of(2020, 11, 30)).comDataDePagamento(LocalDate.of(2020, 12, 25)).comValorpago(new BigDecimal(5008.25)).constroi(),
                AluguelBuilder.umAluguel().comLocacao(locacao2).comDataDeVencimento(LocalDate.of(2020, 11, 30)).comDataDePagamento(LocalDate.of(2020, 12, 25)).comValorpago(new BigDecimal(5008.25)).constroi(),
                AluguelBuilder.umAluguel().comLocacao(locacao3).comDataDeVencimento(LocalDate.of(2020, 11, 30)).comDataDePagamento(LocalDate.of(2020, 12, 25)).comValorpago(new BigDecimal(5008.25)).constroi()
        );

        Mockito.when(aluguelRepository.recuperarAlugueisPagosEmAtraso()).thenReturn(alugueisEmAtraso);

        aluguelService.enviaEmailParaClientesQueNaoPagaramNaDataPrevista();

        Mockito.when(emailService.notifica(cliente4)).thenThrow(new RuntimeException("Não foi possível enviar email") );
        Mockito.verify(emailService, times(1)).notifica(cliente1);
        Mockito.verify(emailService, times(1)).notifica(cliente2);
        Mockito.verify(emailService, times(1)).notifica(cliente3);

        verifyNoMoreInteractions(emailService);
    }
}

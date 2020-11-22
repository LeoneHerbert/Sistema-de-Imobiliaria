package br.com.herbertleone.api.service;

import br.com.herbertleone.api.model.Aluguel;
import br.com.herbertleone.api.repository.AluguelRepository;

import java.util.List;

public class AluguelService {

    private AluguelRepository aluguelRepository;
    private EmailService emailService;

    public void enviaEmailParaClientesQueNaoPagaramNaDataPrevista() {
        List<Aluguel> alugueisAtrasados = aluguelRepository.recuperarAlugueisPagosEmAtraso();

        alugueisAtrasados.forEach(aluguel ->
                emailService.notifica(aluguel.getLocacao().getCliente()
                ));
    }

    public void setAluguelRepository(AluguelRepository aluguelRepository) {
        this.aluguelRepository = aluguelRepository;
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
}

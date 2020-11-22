package br.com.herbertleone.api.service;

import br.com.herbertleone.api.model.Cliente;

public interface EmailService {
    public boolean notifica(Cliente usuario);

}

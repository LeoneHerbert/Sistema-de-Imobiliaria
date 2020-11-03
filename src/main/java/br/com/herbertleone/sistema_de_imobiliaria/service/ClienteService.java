package br.com.herbertleone.sistema_de_imobiliaria.service;

import br.com.herbertleone.sistema_de_imobiliaria.model.Cliente;
import br.com.herbertleone.sistema_de_imobiliaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    private final GenericService<Cliente> genericoService;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
        this.genericoService = new GenericService<Cliente>(clienteRepository);
    }

    @Transactional
    public Cliente salva(Cliente cliente ) {
        return this.genericoService.salva(cliente);
    }

    @Transactional(readOnly = true)
    public Cliente buscaPor(Integer id) {
        return this.genericoService.buscaPor(id );
    }

    @Transactional(readOnly = true)
    public List<Cliente> todos() {
        return genericoService.todos();
    }

    @Transactional
    public Cliente atualiza(Cliente cliente, Integer id) {
        return this.genericoService.atualiza(cliente, id);
    }

    @Transactional
    public void excluir(Integer id) {
        this.genericoService.excluirPor(id );
    }
}

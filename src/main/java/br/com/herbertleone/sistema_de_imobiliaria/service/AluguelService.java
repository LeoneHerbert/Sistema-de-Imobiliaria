package br.com.herbertleone.sistema_de_imobiliaria.service;

import br.com.herbertleone.sistema_de_imobiliaria.model.Aluguel;
import br.com.herbertleone.sistema_de_imobiliaria.repository.AluguelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AluguelService {
    private final AluguelRepository aluguelRepository;

    private final GenericService<Aluguel> genericoService;

    @Autowired
    public AluguelService(AluguelRepository aluguelRepository) {
        this.aluguelRepository = aluguelRepository;
        this.genericoService = new GenericService<Aluguel>(aluguelRepository);
    }

    @Transactional
    public Aluguel salva(Aluguel aluguel ) {
        return this.genericoService.salva(aluguel);
    }

    @Transactional(readOnly = true)
    public Aluguel buscaPor(Integer id) {
        return this.genericoService.buscaPor(id );
    }

    @Transactional(readOnly = true)
    public List<Aluguel> todos() {
        return genericoService.todos();
    }

    @Transactional
    public Aluguel atualiza(Aluguel aluguel, Integer id) {
        return this.genericoService.atualiza(aluguel, id);
    }

    @Transactional
    public void excluir(Integer id) {
        this.genericoService.excluirPor(id );
    }
}

package br.com.herbertleone.sistema_de_imobiliaria.service;

import br.com.herbertleone.sistema_de_imobiliaria.model.Locacao;
import br.com.herbertleone.sistema_de_imobiliaria.repository.LocacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class LocacaoService {
    private final LocacaoRepository locacaoRepository;

    private final GenericService<Locacao> genericoService;

    @Autowired
    public LocacaoService(LocacaoRepository locacaoRepository) {
        this.locacaoRepository = locacaoRepository;
        this.genericoService = new GenericService<Locacao>(locacaoRepository);
    }

    @Transactional
    public Locacao salva(Locacao locacao ) {
        return this.genericoService.salva(locacao);
    }

    @Transactional(readOnly = true)
    public Locacao buscaPor(Integer id) {
        return this.genericoService.buscaPor(id );
    }

    @Transactional(readOnly = true)
    public List<Locacao> todos() {
        return genericoService.todos();
    }

    @Transactional
    public Locacao atualiza(Locacao locacao, Integer id) {
        return this.genericoService.atualiza(locacao, id);
    }

    @Transactional
    public void excluir(Integer id) {
        this.genericoService.excluirPor(id );
    }
}

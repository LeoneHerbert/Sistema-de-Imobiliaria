package br.com.herbertleone.sistema_de_imobiliaria.service;

import br.com.herbertleone.sistema_de_imobiliaria.model.Imovel;
import br.com.herbertleone.sistema_de_imobiliaria.repository.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ImovelService {
    private final ImovelRepository imovelRepository;

    private final GenericService<Imovel> genericoService;

    @Autowired
    public ImovelService(ImovelRepository imovelRepository) {
        this.imovelRepository = imovelRepository;
        this.genericoService = new GenericService<Imovel>(imovelRepository);
    }

    @Transactional
    public Imovel salva(Imovel imovel ) {
        return this.genericoService.salva(imovel);
    }

    @Transactional(readOnly = true)
    public Imovel buscaPor(Integer id) {
        return this.genericoService.buscaPor(id );
    }

    @Transactional(readOnly = true)
    public List<Imovel> todos() {
        return genericoService.todos();
    }

    @Transactional
    public Imovel atualiza(Imovel imovel, Integer id) {
        return this.genericoService.atualiza(imovel, id);
    }

    @Transactional
    public void excluir(Integer id) {
        this.genericoService.excluirPor(id );
    }
}

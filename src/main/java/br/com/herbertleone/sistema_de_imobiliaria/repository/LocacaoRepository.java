package br.com.herbertleone.sistema_de_imobiliaria.repository;

import br.com.herbertleone.sistema_de_imobiliaria.model.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Integer> {
}

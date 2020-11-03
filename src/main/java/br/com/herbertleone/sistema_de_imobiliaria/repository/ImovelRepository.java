package br.com.herbertleone.sistema_de_imobiliaria.repository;

import br.com.herbertleone.sistema_de_imobiliaria.model.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Integer> {
}

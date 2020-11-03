package br.com.herbertleone.sistema_de_imobiliaria.repository;

import br.com.herbertleone.sistema_de_imobiliaria.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}

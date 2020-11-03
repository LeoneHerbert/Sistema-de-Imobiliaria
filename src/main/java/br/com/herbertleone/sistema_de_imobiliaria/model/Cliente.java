package br.com.herbertleone.sistema_de_imobiliaria.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column
    private String cpf;

    @Column(nullable = false)
    private String telefonePrincipal;

    @Column
    private String telefoneSecundario;

    @Column
    private String email;

    @Column
    private LocalDate dataDeNascimento;

    @OneToMany(mappedBy = "id.locacao", cascade = CascadeType.PERSIST)
    private Set<Locacao> locacoes = new LinkedHashSet<>();
}

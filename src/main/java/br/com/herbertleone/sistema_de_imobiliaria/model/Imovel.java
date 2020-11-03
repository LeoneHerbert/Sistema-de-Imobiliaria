package br.com.herbertleone.sistema_de_imobiliaria.model;

import br.com.herbertleone.sistema_de_imobiliaria.model.enums.TipoImovel;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Imovel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoImovel tipoDoImovel;

    @Column
    private double metragem;

    @Column
    private Integer quantidadeDeDormitorios;

    @Column
    private Integer quantidadeDeBanheiros;

    @Column
    private Integer quantidadeDeSuites;

    @Column
    private Integer vagasNaGaragem;

    @Column
    private BigDecimal valorDoAluguelSugerido;

    @Column
    private String observacao;
}

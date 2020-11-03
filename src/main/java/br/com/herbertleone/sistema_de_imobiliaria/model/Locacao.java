package br.com.herbertleone.sistema_de_imobiliaria.model;

import javax.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Locacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "imovel_id")
    private Imovel imovel;

    @ManyToOne
    @JoinColumn(name = "inquilino_id")
    private Cliente inquilino;

    @Column
    private BigDecimal valorDoAlguel;

    @Column
    private double percentualDaMulta;

    @Column
    private LocalDate dataDeInicio;

    @Column
    private LocalDate dataDeFim;

    @Column
    private boolean ativo;
}

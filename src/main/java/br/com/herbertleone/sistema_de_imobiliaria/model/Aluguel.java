package br.com.herbertleone.sistema_de_imobiliaria.model;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDate dataDeVencimento;

    @Column
    private BigDecimal valorPago;

    @Column
    private LocalDate dataDePagamento;

    @Column
    private String observacao;
}

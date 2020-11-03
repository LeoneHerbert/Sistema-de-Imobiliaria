package br.com.herbertleone.sistema_de_imobiliaria.model;

import javax.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Endereco {

    private String cep;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
}
package br.com.herbertleone.api.model;
import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Entity
@Table(name = "aluguel")
public class Aluguel {

	@ManyToOne(cascade= {CascadeType.MERGE, CascadeType.ALL})
	private Locacao locacao;

	@Id
	@Column(name = "data_vencimento")
	private LocalDate dataVencimento;

	@Column(name = "valor_pago")
	private BigDecimal valorPago;

	@Column(name = "data_pagamento")
	private LocalDate dataPagamento;

	@Column(name = "obs")
	private String obs;

	public Aluguel(){}

	public Locacao getLocacao() {
		return locacao;
	}

	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		System.out.println(valorPago);
		if(valorPago.doubleValue() != this.valorASerPago().doubleValue()){
			throw new IllegalArgumentException("O valor pago deve ser calculado a partir do valor do aluguel e da multa");
		}
		this.valorPago = valorPago;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public BigDecimal valorASerPago(){
		Period periodo = Period.between(this.getDataVencimento(), this.getDataPagamento());
		if(periodo.getDays() > 0){
			BigDecimal valorAPagar = this.locacao.getPercentualMulta().multiply(new BigDecimal(periodo.getDays()));
			valorAPagar = this.locacao.getValorAluguel().add(valorAPagar);
			return valorAPagar.setScale(2, RoundingMode.HALF_EVEN);
		}else if(periodo.getDays() > 242){
			BigDecimal valorAPagar = this.locacao.getPercentualMulta().multiply(new BigDecimal(242));
			valorAPagar = this.locacao.getValorAluguel().add(valorAPagar);
			return valorAPagar.setScale(2, RoundingMode.HALF_EVEN);
		}else{
			return this.locacao.getValorAluguel().setScale(2, RoundingMode.HALF_EVEN);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Aluguel aluguel = (Aluguel) o;
		return locacao.equals(aluguel.locacao);
	}

	@Override
	public int hashCode() {
		return Objects.hash(locacao);
	}

	@Override
	public String toString() {
		return "Aluguel{" +
				", locacao=" + locacao +
				", dataVencimento=" + dataVencimento +
				", valorPago=" + valorPago +
				", dataPagamento=" + dataPagamento +
				", obs='" + obs + '\'' +
				'}';
	}
}
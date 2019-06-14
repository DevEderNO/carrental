package br.com.devederno.carrental.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Locacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "O campo valor total é obrigatório")
	@NumberFormat(pattern = "#,##0.00")
	@Column(precision = 10, scale = 2)
	private BigDecimal valorTotal;

	@NotNull(message = "A data de locação é obrigatório")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataDeLocacao;

	@NotNull(message = "A data de devolução é obrigatório")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataDeDevolucao;

	@ManyToOne
	@JoinColumn(name = "motorista_id")
	@NotNull(message = "O motorista é obrigatório")
	private Motorista motorista;

	@ManyToOne
	@JoinColumn(name = "carro_id")
	@NotNull(message = "O carro é obrigatório")
	private Carro carro;

	public Locacao() {
		super();
	}

	public Locacao(
			Long id,
			@NotNull(message = "O campo valor total é obrigatório") BigDecimal valorTotal,
			@NotNull(message = "A data de locação é obrigatório") LocalDate dataDeLocacao,
			@NotNull(message = "A data de devolução é obrigatório") LocalDate dataDeDevolucao,
			@NotNull(message = "O motorista é obrigatório") Motorista motorista,
			@NotNull(message = "O carro é obrigatório") Carro carro) {
		super();
		this.id = id;
		this.valorTotal = valorTotal;
		this.dataDeLocacao = dataDeLocacao;
		this.dataDeDevolucao = dataDeDevolucao;
		this.motorista = motorista;
		this.carro = carro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDate getDataDeLocacao() {
		return dataDeLocacao;
	}

	public void setDataDeLocacao(LocalDate dataDeLocacao) {
		this.dataDeLocacao = dataDeLocacao;
	}

	public LocalDate getDataDeDevolucao() {
		return dataDeDevolucao;
	}

	public void setDataDeDevolucao(LocalDate dataDeDevolucao) {
		this.dataDeDevolucao = dataDeDevolucao;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carro == null) ? 0 : carro.hashCode());
		result = prime * result + ((dataDeDevolucao == null) ? 0 : dataDeDevolucao.hashCode());
		result = prime * result + ((dataDeLocacao == null) ? 0 : dataDeLocacao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result	+ ((motorista == null) ? 0 : motorista.hashCode());
		result = prime * result + ((valorTotal == null) ? 0 : valorTotal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Locacao other = (Locacao) obj;
		if (carro == null) {
			if (other.carro != null)
				return false;
		} else if (!carro.equals(other.carro))
			return false;
		if (dataDeDevolucao == null) {
			if (other.dataDeDevolucao != null)
				return false;
		} else if (!dataDeDevolucao.equals(other.dataDeDevolucao))
			return false;
		if (dataDeLocacao == null) {
			if (other.dataDeLocacao != null)
				return false;
		} else if (!dataDeLocacao.equals(other.dataDeLocacao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (motorista == null) {
			if (other.motorista != null)
				return false;
		} else if (!motorista.equals(other.motorista))
			return false;
		if (valorTotal == null) {
			if (other.valorTotal != null)
				return false;
		} else if (!valorTotal.equals(other.valorTotal))
			return false;
		return true;
	}

}

package br.com.devederno.carrental.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

@Entity
public class Carro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O campo placa é obrigatório")
	private String placa;

	@NotBlank(message = "O campo chassi é obrigatório")
	private String chassi;

	@NotNull(message = "O campo valor da diaria é obrigatório")
	@Column(precision = 10, scale = 2)
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorDaDiaria;

	@ManyToOne
	@JoinColumn(name = "modelo_id")
	@NotNull(message = "O modelo é obrigatório")
	private Modelo modelo;

	public Carro() {
	}

	public Carro(
			Long id,
			@NotBlank(message = "O campo placa é obrigatório") String placa,
			@NotBlank(message = "O campo chassi é obrigatório") String chassi,
			@NotNull(message = "O campo valor da diaria é obrigatório") BigDecimal valorDaDiaria,
			@NotNull(message = "O modelo é obrigatório") Modelo modelo) {
		super();
		this.id = id;
		this.placa = placa;
		this.chassi = chassi;
		this.valorDaDiaria = valorDaDiaria;
		this.modelo = modelo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public BigDecimal getValorDaDiaria() {
		return valorDaDiaria;
	}

	public void setValorDaDiaria(BigDecimal valorDaDiaria) {
		this.valorDaDiaria = valorDaDiaria;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chassi == null) ? 0 : chassi.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
		result = prime * result
				+ ((valorDaDiaria == null) ? 0 : valorDaDiaria.hashCode());
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
		Carro other = (Carro) obj;
		if (chassi == null) {
			if (other.chassi != null)
				return false;
		} else if (!chassi.equals(other.chassi))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		if (valorDaDiaria == null) {
			if (other.valorDaDiaria != null)
				return false;
		} else if (!valorDaDiaria.equals(other.valorDaDiaria))
			return false;
		return true;
	}
	
}

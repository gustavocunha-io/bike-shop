package br.gasc.model;

import java.time.Year;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Bicicleta {
	@Id
	@SequenceGenerator(name = "id_bicicleta", sequenceName = "seq_bicicleta", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_bicicleta")
	private Long id;
	
	@Column(nullable = false, length = 30)
	private String marca;
	
	@Column(nullable = false)
	private String model;
	
	// Não deve ser possível que o cliente cadastre uma bicicleta sem o seu tipo.
	@ManyToOne
	@JoinColumn(nullable = false)
	private TipoBicicleta tipoBicicleta;
	
	@Column(nullable = false)
	private Integer tamanhoQuadro;
	
	// Optei por String pois podem haver números de série contendo letras ou até zeros no início.
	@Column(nullable = false)
	private String numeroSerie;
	
	@Column(nullable = false)
	private Year anoFabricacao;
	
	@ManyToOne
	private Cliente proprietario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public TipoBicicleta getTipoBicicleta() {
		return tipoBicicleta;
	}

	public void setTipoBicicleta(TipoBicicleta tipoBicicleta) {
		this.tipoBicicleta = tipoBicicleta;
	}

	public Integer getTamanhoQuadro() {
		return tamanhoQuadro;
	}

	public void setTamanhoQuadro(Integer tamanhoQuadro) {
		this.tamanhoQuadro = tamanhoQuadro;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public Year getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(Year anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public Cliente getProprietario() {
		return proprietario;
	}

	public void setProprietario(Cliente proprietario) {
		this.proprietario = proprietario;
	}
	
}
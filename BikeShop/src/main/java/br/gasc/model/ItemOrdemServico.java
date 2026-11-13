package br.gasc.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class ItemOrdemServico {
	@Id
	@SequenceGenerator(name = "id_item_ordem_servico", sequenceName = "seq_item_ordem_servico", allocationSize = 1)
	@GeneratedValue(generator = "id_item_ordem_servico", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ordem_servico_id", nullable = false)
	private OrdemServico ordemServico;
	
	@ManyToOne
	@JoinColumn(name = "servico_id", nullable = false)
	private Servico servico;
	
	@Column(precision = 10, scale = 2)
	private BigDecimal valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}
		
}
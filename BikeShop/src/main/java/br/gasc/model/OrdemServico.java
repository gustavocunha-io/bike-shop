package br.gasc.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;

@Entity
public class OrdemServico {
	@Id
	@SequenceGenerator(name = "id_ordem_servico", sequenceName = "seq_ordem_servico", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_ordem_servico")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "bicicleta_id", nullable = false)
	private Bicicleta bicicleta;
	
	@OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL)
	private List<ItemOrdemServico> servicosList = new ArrayList<ItemOrdemServico>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Bicicleta getBicicleta() {
		return bicicleta;
	}

	public void setBicicleta(Bicicleta bicicleta) {
		this.bicicleta = bicicleta;
	}

	public List<ItemOrdemServico> getServicosList() {
		return servicosList;
	}

	public void setServicosList(List<ItemOrdemServico> servicosList) {
		this.servicosList = servicosList;
	}
	
}
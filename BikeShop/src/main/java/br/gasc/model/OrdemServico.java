package br.gasc.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import br.gasc.enums.StatusOrdemServico;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Pattern;

@Entity
public class OrdemServico {
	@Id
	@SequenceGenerator(name = "id_ordem_servico", sequenceName = "seq_ordem_servico", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_ordem_servico")
	private Long id;
	
	@Column(nullable = false)
	private Date dataAgendamento;
	
	/** Defini que sempre ao criar uma ordem de serviço ela já vem com status {@code AGENDADA}*/
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusOrdemServico status = StatusOrdemServico.AGENDADA;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "bicicleta_id", nullable = false)
	private Bicicleta bicicleta;
	
	@OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL)
	private List<ItemOrdemServico> servicosList = new ArrayList<ItemOrdemServico>();
	
	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal valorTotal = BigDecimal.ZERO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}
	
	public StatusOrdemServico getStatus() {
		return status;
	}

	public void setStatus(StatusOrdemServico status) {
		this.status = status;
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

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
}
package br.gasc.model;

import java.util.List;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Size;

@Entity
public class OrdemServico {
	@Id
	@SequenceGenerator(name = "id_ordem_servico", sequenceName = "seq_ordem_servico", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_ordem_servico")
	private Long id;
	
	@Size(min = 1, message = "A OS deve possuir pelo menos 1 serviço")
	@OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL)
	private List<ItemServicoOS> itensServicosList;
	
	@ManyToOne
	@JoinColumn(name = "bicicleta_id", nullable = false)
	private Bicicleta bicicleta;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;
	
}

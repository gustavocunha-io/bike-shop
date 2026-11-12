package br.gasc.model;

import java.util.List;

import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
	
	@Size(min = 1)
	@ManyToMany
	@JoinTable(name = "ordem_servico")
	private List<Servico> servicosList;
}

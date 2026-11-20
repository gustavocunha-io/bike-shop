package br.gasc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity(name = "tipo_bicicleta")
public class TipoBicicleta {
	@Id
	@SequenceGenerator(name = "id_tipo_bicicleta", sequenceName = "seq_tipo_bicicleta", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tipo_bicicleta")
	private Long id;
	
	// O nome é único, pois, não pode haver categorias com o mesmo nome.
	@Column(unique = true, nullable = false, length = 30)
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
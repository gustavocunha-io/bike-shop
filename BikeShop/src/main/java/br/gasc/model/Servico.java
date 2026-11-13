package br.gasc.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

/*
 * CORRIGIR A CONSTRUÇÃO LÓGICA DA TABELA DE MODO QUE SERVICO SEJA INDEPENDENTE DE
 * ORDEM DE SERVIÇO 
 */
@Entity
public class Servico {
	@Id
	@SequenceGenerator(name = "id_servico", sequenceName = "seq_servico", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_servico")
	private Long id;
	
	@Column(nullable = false, length = 50)
	private String tipo;
	
	/** Foi definido apenas para explicar o que é realizado em cada serviço, podendo o mesmo ser nulo. */
	@Column(length = 255, nullable = true)
	private String descricao;
	
	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
}
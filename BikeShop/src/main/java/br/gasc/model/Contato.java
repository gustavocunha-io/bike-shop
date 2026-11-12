package br.gasc.model;

import br.gasc.enums.TipoContato;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class Contato {
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private TipoContato tipoContato;
	
	@Column(nullable = false, length = 11)
	private String valor;

	public TipoContato getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(TipoContato tipoContato) {
		this.tipoContato = tipoContato;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
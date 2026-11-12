package br.gasc.enums;

import java.util.ArrayList;
import java.util.List;

public enum TipoContato {
	CELULAR("Celular"),
	FIXO("Fixo"),
	EMPRESARIAL("Empresarial"),
	WHATSAPP("Whatsapp");
	
	private final String descricao;

	private TipoContato(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static List<TipoContato> obterDescricao() {
		List<TipoContato> tipoContatoList = new ArrayList<TipoContato>();
		
		for(TipoContato tipoContato : values())
			tipoContatoList.add(tipoContato);
		return tipoContatoList;
	}
	
}
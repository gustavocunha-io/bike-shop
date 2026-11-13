package br.gasc.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Enumeração criada pada representar o statu de uma ordem de serviço solocitada pelo cliente da loja,
 * consiste em quatro estágios: {@code AGENDADA,} {@code EXECUTADA,}  {@code PAGA,} e {@code CANCELADA,}
 */
public enum StatusOrdemServico {
	AGENDADA("Agendada"),
	EXECUTADA("Executada"),
	PAGA("Paga"),
	CANCELADA("Cancelada");
	
	private final String descricao;

	private StatusOrdemServico(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static List<StatusOrdemServico> obterStatusOrdemServico() {
		List<StatusOrdemServico> statusOrdemServicosList = new ArrayList<StatusOrdemServico>();
		
		for(StatusOrdemServico status : values())
			statusOrdemServicosList.add(status);
		return statusOrdemServicosList;
	}
	
}
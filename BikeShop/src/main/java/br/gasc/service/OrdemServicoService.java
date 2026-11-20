package br.gasc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.gasc.dao.OrdemServicoDAO;
import br.gasc.exception.RegraNegocioException;
import br.gasc.model.OrdemServico;

@Service
public class OrdemServicoService {
	private OrdemServicoDAO ordemServicoDAO;
	
	public OrdemServicoService() {
		this.ordemServicoDAO = new OrdemServicoDAO();
	}
	
	public void cadastrarOrdemServico(OrdemServico ordemServico) {
		if(ordemServico == null)
			throw new RegraNegocioException(null);
		
		ordemServicoDAO.alterar(ordemServico);
	}
	
	public List<OrdemServico> listarOrdemServico() {
		return ordemServicoDAO.listar();
	}
}

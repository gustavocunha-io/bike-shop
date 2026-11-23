package br.gasc.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import br.gasc.dao.OrdemServicoDAO;
import br.gasc.enums.StatusOrdemServico;
import br.gasc.exception.RegraNegocioException;
import br.gasc.model.OrdemServico;

import static br.gasc.constantes.Constantes.*;

@Service
public class OrdemServicoService {
	private OrdemServicoDAO ordemServicoDAO;
	
	public OrdemServicoService() {
		this.ordemServicoDAO = new OrdemServicoDAO();
	}
	
	public OrdemServico pesquisarPorId(Long id) {
		if(id == null)
			throw new RegraNegocioException(MSG_ERRO_ORDEM_SERVICO_NULA);
		
		return ordemServicoDAO.pesquisar(id);
	}
	
	public void cadastrarOrdemServico(OrdemServico ordemServico) {
		if(ordemServico == null)
			throw new RegraNegocioException(MSG_ERRO_ORDEM_SERVICO_NULA);
		
		if(ordemServico.getDataAgendamento().isBefore(LocalDate.now()))
			throw new RegraNegocioException(MSG_ERRO_DATA_AGENDAMENTO_PASSADA);
		
		if(ordemServico.getServicosList().size() < 1)
			throw new RegraNegocioException(MSG_ERRO_SERVICO_MENOR_QUE_1);
		
		ordemServicoDAO.alterar(ordemServico);
	}
	
	public void alterarOrdemServico(OrdemServico ordemServico) { 
		if(ordemServico == null)
			throw new RegraNegocioException(MSG_ERRO_ORDEM_SERVICO_NULA);
		
		ordemServicoDAO.alterar(ordemServico);
	}
	
	public List<OrdemServico> filtrarOrdemServico(String statusFiltro, LocalDate dataInicio, LocalDate dataExata) {
	    StatusOrdemServico status = null;

	    if (statusFiltro != null && !statusFiltro.isEmpty() && !"TODOS".equalsIgnoreCase(statusFiltro)) {
	        try {
	            status = StatusOrdemServico.valueOf(statusFiltro.toUpperCase());
	        } catch (IllegalArgumentException e) {
	            status = StatusOrdemServico.AGENDADA;
	        }
	    }

	    return ordemServicoDAO.filtrar(status, dataInicio, dataExata);
	}
	
	
	public List<OrdemServico> listarHistoricoPorCliente(Long idCliente) {
		return ordemServicoDAO.listarHistoricoPorCliente(idCliente);
	}

	public List<OrdemServico> listarHistoricoPorBicicleta(Long idBicicleta) {
		return ordemServicoDAO.listarHistoricoPorBicicleta(idBicicleta);
	}
	
	public List<OrdemServico> listarOrdemServicoPorStatus(StatusOrdemServico statusOrdemServico) {
		return ordemServicoDAO.listarPorStatusComServicos(statusOrdemServico);
	}
	
	public List<OrdemServico> listarHistoricoPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
		return ordemServicoDAO.listarHistoricoPorPeriodo(dataInicio, dataFim);
	}

	public OrdemServico pesquisarPorIdComServicos(Long id) {
		if (id == null)
			throw new RegraNegocioException(MSG_ERRO_ORDEM_SERVICO_NULA);

		return ordemServicoDAO.pesquisarComServicos(id);
	}
}

package br.gasc.service;

import org.springframework.stereotype.Service;

import br.gasc.dao.ServicoDAO;
import br.gasc.exception.RegraNegocioException;
import br.gasc.model.Servico;

import static br.gasc.constantes.Constantes.*;

import java.util.List;

@Service
public class ServicoService {
	private ServicoDAO servicoDAO;
	
	public ServicoService() {
		this.servicoDAO = new ServicoDAO();
	}
	
	public void cadastrarServico(Servico servico) {
		if(servico == null)
			throw new RegraNegocioException(MSG_ERRO_SERVICO_NULO);
		
		if(servico.getTipo() == null || servico.getTipo().isBlank())
			throw new RegraNegocioException(MSG_ERRO_TIPO_SERVICO_NULO);
		
		if(servico.getId() != null && servicoDAO.pesquisar(servico.getId()) != null)
			throw new RegraNegocioException(MSG_ERRO_SERVICO_DUPLICADO);
		
		servicoDAO.inserir(servico);
	}
	
	public Servico pesquisarServicoId(Long id) {
		return servicoDAO.pesquisar(id);
	}
	
	public List<Servico> listarServicos() {
		return servicoDAO.listar();
	}
	
}

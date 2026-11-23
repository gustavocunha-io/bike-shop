package br.gasc.service;

import java.time.Year;
import java.util.List;

import org.springframework.stereotype.Service;

import br.gasc.dao.BicicletaDAO;
import br.gasc.exception.RegraNegocioException;
import br.gasc.model.Bicicleta;

import static br.gasc.constantes.Constantes.*;

@Service
public class BicicletaService {
	private BicicletaDAO bicicletaDAO;

	public BicicletaService() {
		this.bicicletaDAO = new BicicletaDAO();
	}
	
	public void cadastrarBicicleta(Bicicleta bicicleta) {
		if(bicicleta == null)
			throw new RegraNegocioException(MSG_ERRO_BICICLETA_NULA);
		
		if(bicicleta.getMarca() == null)
			throw new RegraNegocioException(MSG_ERRO_MARCA_BICICLETA_NULA);
		
		if(bicicleta.getModelo() == null)
			throw new RegraNegocioException(MSG_ERRO_MODELO_BICICLETA_NULO);
		
		if(bicicleta.getTipoBicicleta() == null)
			throw new RegraNegocioException(MSG_ERRO_TIPO_BICICLETA_NULO);
		
		if(bicicleta.getAnoFabricacao() != null && bicicleta.getAnoFabricacao().getValue() > Year.now().getValue())
			throw new RegraNegocioException(MSG_ERRO_ANO_FABRICACAO_BICICLETA_FUTURO);

		bicicletaDAO.inserir(bicicleta);
	}
	
	public Bicicleta pesquisarPorId(Long id) {
		return bicicletaDAO.pesquisar(id);
	}
	
	public List<Bicicleta> obterBicicletasCliente(Long id) {
		if(id == null)
			throw new RegraNegocioException(MSG_ERRO_ID_BICICLETA_NULO);
		
		return bicicletaDAO.obterBicicletasCliente(id);
	}
	
	public List<Bicicleta> listaBicicleta() {
		return bicicletaDAO.listar();
	}
	
}
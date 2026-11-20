package br.gasc.service;

import java.time.Year;
import java.util.List;

import org.springframework.stereotype.Service;

import br.gasc.dao.BicicletaDAO;
import br.gasc.exception.RegraNegocioException;
import br.gasc.model.Bicicleta;

@Service
public class BicicletaService {
	private BicicletaDAO bicicletaDAO;

	public BicicletaService() {
		this.bicicletaDAO = new BicicletaDAO();
	}
	
	public void cadastrarBicicleta(Bicicleta bicicleta) {
		if(bicicleta == null)
			throw new RegraNegocioException(null);
		
		if(bicicleta.getAnoFabricacao() != null && bicicleta.getAnoFabricacao().getValue() >= Year.now().getValue())
			throw new RegraNegocioException(null);

		bicicletaDAO.inserir(bicicleta);
	}
	
	public List<Bicicleta> obterBicicletasCliente(Long id) {
		if(id == null)
			throw new RegraNegocioException("");
		
		return bicicletaDAO.obterBicicletasCliente(id);
	}
	
	public List<Bicicleta> listaBicicleta() {
		return bicicletaDAO.listar();
	}
	
}
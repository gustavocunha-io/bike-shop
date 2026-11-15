package br.gasc.service;

import java.util.List;

import br.gasc.dao.TipoBicicletaDAO;
import br.gasc.model.TipoBicicleta;

public class TipoBicicletaService {
	private TipoBicicletaDAO tipoBicicletaDAO;

	public TipoBicicletaService() {
		this.tipoBicicletaDAO = new TipoBicicletaDAO();
	}

	public List<TipoBicicleta> listarTipoBicicleta() {
		return tipoBicicletaDAO.listar();
	}
	
}

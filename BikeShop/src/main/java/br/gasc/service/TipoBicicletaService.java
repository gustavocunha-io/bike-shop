package br.gasc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.gasc.dao.TipoBicicletaDAO;
import br.gasc.model.TipoBicicleta;

@Service
public class TipoBicicletaService {
	private TipoBicicletaDAO tipoBicicletaDAO;

	public TipoBicicletaService() {
		this.tipoBicicletaDAO = new TipoBicicletaDAO();
	}

	public List<TipoBicicleta> listarTipoBicicleta() {
		return tipoBicicletaDAO.listar();
	}
	
}

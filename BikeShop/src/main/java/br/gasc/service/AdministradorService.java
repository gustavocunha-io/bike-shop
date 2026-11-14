package br.gasc.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.gasc.dao.AdministradorDAO;
import br.gasc.model.Administrador;
import br.gasc.utils.HashSenhaUtil;

@Service
public class AdministradorService {
	
	public boolean autenticar(Administrador administrador) {
		if(administrador == null || administrador.getLogin() == null)
			return false;
		
		AdministradorDAO administradorDao = new AdministradorDAO();
		Optional<Administrador> administradorBancoOpt = administradorDao.buscarPorLogin(administrador.getLogin());
		
		if(administradorBancoOpt.isPresent())
			return HashSenhaUtil.verificarSenha(administrador.getSenha(), administradorBancoOpt.get().getSenha());
		return false;
	}
	
}

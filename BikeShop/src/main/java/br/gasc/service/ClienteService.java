package br.gasc.service;

import org.springframework.stereotype.Service;

import br.gasc.dao.ClienteDao;

@Service
public class ClienteService {

	/**
	 * Verifica se o cpf inserido ja está cadastrado no sistema. Se sim, retorna {@code true} caso o contrário, {@code false}
	 * 
	 * @param cpf - numero de identificação recebido.
	 * @return
	 */
	public boolean validarCpf(String cpf) {
		return new ClienteDao().buscarPorCpf(cpf).isPresent();
	}
	
}

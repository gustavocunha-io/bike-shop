package br.gasc.service;

import java.util.List; 

import org.springframework.stereotype.Service;

import br.gasc.dao.ClienteDao;
import br.gasc.exception.RegraNegocioException;
import br.gasc.model.Cliente;

import static br.gasc.constantes.Constantes.*;

@Service
public class ClienteService {
	private ClienteDao clienteDao;
	
	public ClienteService() {
		this.clienteDao = new ClienteDao();
	}

	/**
	 * Realiza a inserção de um novo cliente no banco validando se o cpf do mesmo já se encontra cadastrado.
	 * 
	 * @param cliente pessoa ao qual solicitou um serviço a loja
	 * @return 
	 */
	public void cadastrarCliente(Cliente cliente) {
		if(cliente == null)
			throw new RegraNegocioException(MSG_ERRO_CLIENTE_NULO);
		
		if(validarCpf(cliente.getCpf()))
			throw new RegraNegocioException(MSG_ERRO_CPF_DUPLICADO);
		
		clienteDao.inserir(cliente);
	}
	
	/**
	 * Verifica se o cpf inserido ja está cadastrado no sistema.
	 * 
	 * @param cpf número de identificação recebido.
	 * @return {@code true} se já estiver cadastrado, {@code false} caso contrário.
	 */
	public boolean validarCpf(String cpf) {
		return clienteDao.buscarPorCpf(cpf).isPresent();
	}
	
	public List<Cliente> listarClientes() {
		return clienteDao.listar();
	}
	
}

package br.gasc.service;

import java.time.LocalDate;
import java.util.List; 

import org.springframework.stereotype.Service;

import br.gasc.dao.ClienteDAO;
import br.gasc.exception.RegraNegocioException;
import br.gasc.model.Cliente;

import static br.gasc.constantes.Constantes.*;

@Service
public class ClienteService {
	private ClienteDAO clienteDao;
	
	public ClienteService() {
		this.clienteDao = new ClienteDAO();
	}

	/**
	 * Realiza a inserção de um novo cliente no banco validando se o cpf do mesmo já se encontra cadastrado.
	 * 
	 * @param cliente pessoa ao qual solicitou um serviço a loja
	 */
	@SuppressWarnings("deprecation")
	public void cadastrarCliente(Cliente cliente) {
		if(cliente == null)
			throw new RegraNegocioException(MSG_ERRO_CLIENTE_NULO);
		
		if(cliente.getDataNascimento().getYear() >= LocalDate.now().getYear())
			throw new RegraNegocioException(MSG_ERRO_DATA_NASCIMENTO_INVALIDA);
		
		if(validarCpf(cliente.getCpf()))
			throw new RegraNegocioException(MSG_ERRO_CPF_DUPLICADO);
		
		clienteDao.inserir(cliente);
	}
	
	public Cliente pesquisarPorCpf(String cpf) {
		if(cpf == null || cpf.isBlank())
			throw new RegraNegocioException("okclkd");
		return clienteDao.buscarPorCpf(cpf).get();
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
	
	
	/**
	 * Lista todos os clientes que estão cadastrados no sistema 
	 * 
	 * @return retorna uma lista de clientes ou nulo se não existir nenhum.
	 */
	public List<Cliente> listarClientes() {
		return clienteDao.listar();
	}
	
}

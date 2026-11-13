package br.gasc.dao;

import java.util.Optional;

import br.gasc.model.Cliente;
import jakarta.persistence.EntityManager;

public class ClienteDAO extends DAO<Cliente> {
	public ClienteDAO() {
		super(Cliente.class);
	}

	public Optional<Cliente> buscarPorCpf(String cpf) {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		
		try {
			return entityManager.createQuery("SELECT c FROM Cliente c WHERE c.cpf = :cpf", Cliente.class)
					.setParameter("cpf", cpf)
					.getResultStream()
					.findFirst();
		} finally {
			if(entityManager != null && entityManager.isOpen())
				entityManager.close();
		}
	}
	
}
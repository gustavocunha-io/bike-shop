package br.gasc.dao;

import java.util.Optional;

import br.gasc.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class ClienteDao {
	private EntityManager entityManager;
	
	public ClienteDao() {
		this.entityManager = new JPAUtil().getEntityManager();
	}

	public Optional<Cliente> buscarPorCpf(String cpf) {
		try {
			TypedQuery<Cliente> query = entityManager.createQuery("SELECT c FROM Cliente c WHERE c.cpf = :cpf", Cliente.class);
			query.setParameter("cpf", cpf);
			return Optional.of(query.getSingleResult());
		} catch (NoResultException noResultException) {
			return Optional.empty();
		} finally {
			if(entityManager != null && entityManager.isOpen())
				entityManager.close();
		}
	}
}

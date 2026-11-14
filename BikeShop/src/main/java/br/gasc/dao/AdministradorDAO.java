package br.gasc.dao;

import java.util.Optional;

import br.gasc.model.Administrador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;


public class AdministradorDAO {
	private EntityManager entityManager;
	
	
	public AdministradorDAO() {
		this.entityManager = new JPAUtil().getEntityManager();
	}
	
	public Optional<Administrador> buscarPorLogin(String login) {
		try {
			TypedQuery<Administrador> query = entityManager.createQuery("SELECT a FROM Administrador a WHERE a.login = :login", Administrador.class); 		
			query.setParameter("login", login);
			Administrador administradorBanco = query.getSingleResult();
			return Optional.of(administradorBanco);
			
		} catch (NoResultException noResultException) {
			return Optional.empty();
			
		} finally {
			if(entityManager != null && entityManager.isOpen())
				entityManager.close();
		}
	}
}
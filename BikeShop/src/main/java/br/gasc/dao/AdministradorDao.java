package br.gasc.dao;

import br.gasc.model.Administrador;
import br.gasc.utils.HashSenhaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;


public class AdministradorDao {
	private EntityManager entityManager;
	
	
	public AdministradorDao() {
		this.entityManager = new JPAUtil().getEntityManager();
	}
	
	public boolean validarLogin(Administrador administrador) {
		if(administrador == null)
			throw new IllegalArgumentException("O administrador não deve ser nulo");
		
		try {
			TypedQuery<Administrador> query = entityManager.createQuery("SELECT a FROM Administrador a WHERE a.login = :login", Administrador.class); 		
			query.setParameter("login", administrador.getLogin());
			
			Administrador administradorBanco = query.getSingleResult();
			
			return HashSenhaUtil.verificarSenha(administrador.getSenha(), administradorBanco.getSenha());			
		} catch (NoResultException noResultException) {
			noResultException.printStackTrace();
			return false;
		} finally {
			if(entityManager != null && entityManager.isOpen())
				entityManager.close();
		}
	}

}
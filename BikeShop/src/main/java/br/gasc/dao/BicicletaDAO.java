package br.gasc.dao;

import java.util.List;

import br.gasc.model.Bicicleta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class BicicletaDAO extends DAO<Bicicleta> {

	public BicicletaDAO() {
		super(Bicicleta.class);
	}
	
	public List<Bicicleta> obterBicicletasCliente(Long id) {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		
		try {
			TypedQuery<Bicicleta> query = entityManager.createQuery("SELECT b FROM Bicicleta b WHERE b.proprietario.id = :id", Bicicleta.class);
			query.setParameter("id", id);
			List<Bicicleta> bicicletasList = query.getResultList();
			
			return bicicletasList;
		} catch (NoResultException noResultException) {
			return null;
		}
	}
	
}

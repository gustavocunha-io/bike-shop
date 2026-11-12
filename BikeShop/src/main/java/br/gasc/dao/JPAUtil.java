package br.gasc.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bike-shop");
	
	public EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
}
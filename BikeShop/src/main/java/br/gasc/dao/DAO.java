package br.gasc.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;

public class DAO<T> implements CRUD<T> {
	private Class<T> classe;
	
	public DAO(Class<T> classe) {
		this.classe = classe;
	}
	
	@Override
	public void inserir(T t) {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(t);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public void alterar(T t) {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.merge(t);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public void remover(T t) {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		
		entityManager.getTransaction().begin();
		T entity = entityManager.merge(t);
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public T pesquisar(Long id) {
		return new JPAUtil().getEntityManager().find(classe, id);
	}

	@Override
	public List<T> listar() {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		CriteriaQuery<T> query = entityManager.getCriteriaBuilder().createQuery(classe);
		
		query.select(query.from(classe));
		
		return entityManager.createQuery(query).getResultList();
	}
	
}
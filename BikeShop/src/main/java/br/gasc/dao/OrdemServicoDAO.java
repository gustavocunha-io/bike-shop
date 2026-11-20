package br.gasc.dao;

import java.util.List;

import br.gasc.enums.StatusOrdemServico;
import br.gasc.model.OrdemServico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class OrdemServicoDAO extends DAO<OrdemServico> {
	
	public OrdemServicoDAO() {
		super(OrdemServico.class);
	}

	public List<OrdemServico> listarPorStatus(StatusOrdemServico statusOrdemServico){
		EntityManager entityManager = new JPAUtil().getEntityManager();
		
		try {
		
			TypedQuery<OrdemServico> query = entityManager.createQuery("SELECT os FROM ordem_servico os WHERE os.status = :status", OrdemServico.class);
			query.setParameter("status", statusOrdemServico);
			return query.getResultList();
		} catch (NoResultException noResultException) {
			return null;
		}
	}
	
}

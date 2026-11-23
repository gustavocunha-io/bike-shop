package br.gasc.dao;

import java.time.LocalDate;
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

	
	public List<OrdemServico> filtrar(StatusOrdemServico status, LocalDate dataInicio, LocalDate dataExata) {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		
		try {
			String queryStr = "SELECT DISTINCT os FROM ordem_servico os LEFT JOIN FETCH os.servicosList WHERE 1 = 1 ";

			if (status != null)
				queryStr += "AND os.status = :status ";

			if (dataExata != null)
				queryStr += "AND os.dataAgendamento = :dataExata ";
			
			else if (dataInicio != null)
				queryStr += "AND os.dataAgendamento >= :dataInicio ";

			queryStr += "ORDER BY os.dataAgendamento ASC";

			TypedQuery<OrdemServico> query = entityManager.createQuery(queryStr, OrdemServico.class);

			if (status != null)
				query.setParameter("status", status);

			if (dataExata != null)
				query.setParameter("dataExata", dataExata);
			
			else if (dataInicio != null)
				query.setParameter("dataInicio", dataInicio);

			return query.getResultList();
		} catch (NoResultException noResultException) {
			return null;
		} finally {
			entityManager.close();
		}
	}
	
	public List<OrdemServico> listarHistoricoPorCliente(Long idCliente) {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		try {
			TypedQuery<OrdemServico> query = entityManager.createQuery(
					"SELECT DISTINCT os FROM ordem_servico os LEFT JOIN FETCH os.servicosList " +
					"WHERE os.cliente.id = :idCliente AND os.status IN (:executada, :paga) " +
					"ORDER BY os.dataExecucao DESC", OrdemServico.class);
			query.setParameter("idCliente", idCliente);
			query.setParameter("executada", StatusOrdemServico.EXECUTADA);
			query.setParameter("paga", StatusOrdemServico.PAGA);
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}

	public List<OrdemServico> listarHistoricoPorBicicleta(Long idBicicleta) {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		try {
			TypedQuery<OrdemServico> query = entityManager.createQuery(
					"SELECT DISTINCT os FROM ordem_servico os LEFT JOIN FETCH os.servicosList " +
					"WHERE os.bicicleta.id = :idBicicleta AND os.status IN (:executada, :paga) " +
					"ORDER BY os.dataExecucao DESC", OrdemServico.class);
			
			query.setParameter("idBicicleta", idBicicleta);
			query.setParameter("executada", StatusOrdemServico.EXECUTADA);
			query.setParameter("paga", StatusOrdemServico.PAGA);
			
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}
	
	public List<OrdemServico> listarHistoricoPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		try {
			TypedQuery<OrdemServico> query = entityManager.createQuery(
					"SELECT DISTINCT os FROM ordem_servico os LEFT JOIN FETCH os.servicosList item LEFT JOIN FETCH item.servico " +
					"LEFT JOIN FETCH os.bicicleta LEFT JOIN FETCH os.cliente " +
					"WHERE os.dataExecucao BETWEEN :dataInicio AND :dataFim AND os.status IN (:executada, :paga) " +
					"ORDER BY os.dataExecucao ASC", OrdemServico.class);
			
			query.setParameter("dataInicio", dataInicio);
			query.setParameter("dataFim", dataFim);
			query.setParameter("executada", StatusOrdemServico.EXECUTADA);
			query.setParameter("paga", StatusOrdemServico.PAGA);
			
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}
	
	public List<OrdemServico> listarPorStatus(StatusOrdemServico statusOrdemServico){
		EntityManager entityManager = new JPAUtil().getEntityManager();
		
		try {
			TypedQuery<OrdemServico> query = entityManager.createQuery("SELECT os FROM ordem_servico os WHERE os.status = :status", OrdemServico.class);
			query.setParameter("status", statusOrdemServico);
			
			return query.getResultList();
		} catch (NoResultException noResultException) {
			return null;
		} finally {
			entityManager.close();
		}
	}
	
	public List<OrdemServico> listarPorStatusComServicos(StatusOrdemServico statusOrdemServico) {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		try {
			TypedQuery<OrdemServico> query = entityManager.createQuery(
					"SELECT DISTINCT os FROM ordem_servico os LEFT JOIN FETCH os.servicosList " +
					"WHERE os.status = :status ORDER BY os.dataAgendamento ASC",
					OrdemServico.class);
			query.setParameter("status", statusOrdemServico);
			return query.getResultList();
		} catch (NoResultException noResultException) {
			return null;
		} finally {
			entityManager.close();
		}
	}
	
	public OrdemServico pesquisarComServicos(Long id) {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		try {
			List<OrdemServico> resultado = entityManager.createQuery(
					"SELECT DISTINCT os FROM ordem_servico os LEFT JOIN FETCH os.servicosList " +
					"WHERE os.id = :id",
					OrdemServico.class)
					.setParameter("id", id)
					.getResultList();
			return resultado.isEmpty() ? null : resultado.get(0);
		} catch (NoResultException noResultException) {
			return null;
		} finally {
			entityManager.close();
		}
	}
	
}

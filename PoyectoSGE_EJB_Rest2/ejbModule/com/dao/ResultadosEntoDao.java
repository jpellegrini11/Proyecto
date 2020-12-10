package com.dao;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.entidades.Entrenador;
import com.entidades.ResultadosEnto;

@Stateless
@LocalBean
public class ResultadosEntoDao {
	
	
	@PersistenceContext
	private EntityManager em;
	
	public ResultadosEntoDao() {
			
	}
	
	public void guardarResultadosEnto(ResultadosEnto resultadosEnto) throws SQLException {
		this.em.persist(resultadosEnto);
		em.flush();
		em.refresh(resultadosEnto);

	}

	public void actualizarResultadosEnto(ResultadosEnto resultadosEnto) throws SQLException {
		this.em.merge(resultadosEnto);
		em.flush();
		em.refresh(resultadosEnto);
	}

	public void borrarResultadosEnto(Long idResultadosEnto) throws SQLException {
		ResultadosEnto resultadosEnto = em.find(ResultadosEnto.class, idResultadosEnto);
		em.remove(resultadosEnto);
		em.flush();
		em.refresh(resultadosEnto);
	}

	public ResultadosEnto obtenerResultadosEnto(Long idResultadosEnto) {
		return this.em.find(ResultadosEnto.class, idResultadosEnto);
	}

	public List<ResultadosEnto> obternerTodos() {
		TypedQuery<ResultadosEnto> query = this.em.createQuery("SELECT a FROM ResultadosEnto a", ResultadosEnto.class);
		return query.getResultList();
	}
	
	public List<ResultadosEnto> filtroResultadosEntoUsu(String usuario)

	{
		TypedQuery<ResultadosEnto> query = em.createQuery("SELECT u FROM ResultadosEnto u WHERE u.usuarioDep LIKE :usuario", ResultadosEnto.class)
				.setParameter("usuario", usuario);
		return query.getResultList();
	}
	

}

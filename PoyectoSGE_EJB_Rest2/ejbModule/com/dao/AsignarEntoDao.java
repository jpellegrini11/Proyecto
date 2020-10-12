
package com.dao;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.entidades.AsignarEnto;

@Stateless
@LocalBean
public class AsignarEntoDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public AsignarEntoDao() {
			
	}
	
	public void guardarAsignarEnto(AsignarEnto asignarEnto) throws SQLException {
		this.em.persist(asignarEnto);
		em.flush();
		em.refresh(asignarEnto);

	}

	public void actualizarAsignarEnto(AsignarEnto asignarEnto) throws SQLException {
		this.em.merge(asignarEnto);
		em.flush();
		em.refresh(asignarEnto);
	}

	public void borrarAsignarEnto(Long idAsignarEnto) throws SQLException {
		AsignarEnto asignarEnto = em.find(AsignarEnto.class, idAsignarEnto);
		em.remove(asignarEnto);
		em.flush();
		em.refresh(asignarEnto);
	}

	public AsignarEnto obtenerAsignarEnto(Long idAsignarEnto) {
		return this.em.find(AsignarEnto.class, idAsignarEnto);
	}

	public List<AsignarEnto> obternerTodos() {
		TypedQuery<AsignarEnto> query = this.em.createQuery("SELECT a FROM AsignarEnto a", AsignarEnto.class);
		return query.getResultList();
	}

}


package com.dao;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.entidades.Entrena;
import com.entidades.Entrenador;

@Stateless
@LocalBean
public class EntrenaDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public EntrenaDao() {
			
	}
	

	
	public void guardarEntrena(Entrena entrena) throws SQLException {
		this.em.persist(entrena);

	}

	public void actualizarEntrena(Entrena entrena) throws SQLException {
		this.em.merge(entrena);
	}

	public void borrarEntrena(Long idEntrena) throws SQLException {
		Entrena entrena = em.find(Entrena.class, idEntrena);
		em.remove(entrena);
		em.flush();
	}

	public Entrena obtenerEntrena(Long idEntrena) {
		return this.em.find(Entrena.class, idEntrena);
	}

	public List<Entrena> obternerTodos() {
		TypedQuery<Entrena> query = this.em.createQuery("SELECT a FROM Entrena a", Entrena.class);
		return query.getResultList();
	}

}

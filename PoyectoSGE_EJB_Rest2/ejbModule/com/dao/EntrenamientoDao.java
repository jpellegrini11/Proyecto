package com.dao;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.entidades.Ejercicios;
import com.entidades.Entrenamiento;

@Stateless
@LocalBean
public class EntrenamientoDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public EntrenamientoDao() {
		
	}
	
	public void guardarEntrenamiento(Entrenamiento entrenamiento) throws SQLException {
		this.em.persist(entrenamiento);
		em.flush();
		em.refresh(entrenamiento);

	}

	public void actualizarEntrenamiento(Entrenamiento entrenamiento) throws SQLException {
		this.em.merge(entrenamiento);
	}

	public void borrarEntrenamiento(Long idEntrenamiento) throws SQLException {
		Entrenamiento entrenamiento = em.find(Entrenamiento.class, idEntrenamiento);
		em.remove(entrenamiento);
		em.flush();
	}

	public Entrenamiento obtenerEntrenamiento(Long idEntrenamiento) {
		return this.em.find(Entrenamiento.class, idEntrenamiento);
	}

	public List<Entrenamiento> obternerTodos() {
		TypedQuery<Entrenamiento> query = this.em.createQuery("SELECT a FROM Entrenamiento a", Entrenamiento.class);
		return query.getResultList();
	}
	
	public List<Entrenamiento> filtroEntrenamiento(String entrenamiento)

	{
		TypedQuery<Entrenamiento> query = em.createQuery("SELECT u FROM Entrenamiento u WHERE u.nombre LIKE :entrenamiento", Entrenamiento.class)
				.setParameter("entrenamiento", entrenamiento);
		return query.getResultList();
	}

}

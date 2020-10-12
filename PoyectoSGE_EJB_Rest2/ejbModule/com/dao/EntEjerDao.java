package com.dao;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.entidades.Deportista;
import com.entidades.Ejercicios;
import com.entidades.EntEjer;
import com.entidades.Entrenador;

@Stateless
@LocalBean
public class EntEjerDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public EntEjerDao(){
		
	}
	


	
	public List<EntEjer> filtroEntrenadorDao(long  idEjercicios)

	{
		TypedQuery<EntEjer> query = em.createQuery("SELECT u FROM EntEjer u WHERE u.ID_EJERCICIOS LIKE :idEjercicios", EntEjer.class)
				.setParameter("idEjercicios", idEjercicios);
		return query.getResultList();
	}
	
	
	public void guardarEntEjer(EntEjer entEjer) throws SQLException {
		
		this.em.persist(entEjer);
		em.flush();
		em.refresh(entEjer);
		
	}

	public void actualizarEntEjer(EntEjer entEjer) throws SQLException {
		this.em.merge(entEjer);
	}

	public void borrarEntEjer(Long idEnt_Ejer) throws SQLException {
		EntEjer entEjer = em.find(EntEjer.class, idEnt_Ejer);
		em.remove(entEjer);
		em.flush();
	}

	public EntEjer obtenerEnt_Ejer(Long idEnt_Ejer) {
		return this.em.find(EntEjer.class, idEnt_Ejer);
	}

	public List<EntEjer> obternerTodos() {
		TypedQuery<EntEjer> query = this.em.createQuery("SELECT e FROM EntEjer e", EntEjer.class);
		return query.getResultList();
	}
	
	public List<EntEjer> obtenerPorIdEjer(long idEjercicios) throws SQLException {
		System.out.println("obtener entEje "+idEjercicios);
		TypedQuery<EntEjer> query = em.createQuery("SELECT e FROM EntEjer e WHERE e.idEjercicios LIKE :idEjercicios", EntEjer.class)
				.setParameter("idEjercicios", idEjercicios);
		return query.getResultList();
	}


}

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

import com.entidades.Ejercicios;
import com.entidades.Entrenador;

@Stateless
@LocalBean
public class EjerciciosDao {
	
	@PersistenceContext
	private EntityManager em;

	  public void guardarEjercicios(Ejercicios ejercicios) throws SQLException {
			this.em.persist(ejercicios);
			em.flush();
			em.refresh(ejercicios);
			

		}

		public void actualizarEjercicios(Ejercicios ejercicios) throws SQLException {
			this.em. merge(ejercicios);
			em.flush();
		}

		public void borrarEjercicios(Long idEjercicios) throws SQLException {
			Ejercicios ejercicios = em.find(Ejercicios.class, idEjercicios);
			em.remove(ejercicios);
			em.flush();
		}

		public Ejercicios obtenerEjercicios(Long idEjercicios) {
			return this.em.find(Ejercicios.class, idEjercicios);
		}

		public List<Ejercicios> obternerTodos() {
			TypedQuery<Ejercicios> query = this.em.createQuery("SELECT a FROM Ejercicios a", Ejercicios.class);
			return query.getResultList();
		}

		public Long obtenerporId(Long idEjercicios) {
			this.em.find(Ejercicios.class, idEjercicios);
			return idEjercicios;
		}
		
		public Ejercicios obtenerEjerciciosIgual(String ejercicios) {
			try {
				Query q = em.createQuery("SELECT u FROM Ejercicios u WHERE u.nombre LIKE :ejercicios", Ejercicios.class);
				q.setParameter("ejercicios", ejercicios);

				return (Ejercicios) q.getSingleResult();

			} catch (NoResultException e) {
				return null;
			}
		}
		
		public List<Ejercicios> filtroEjercicios(String ejercicios)

		{
			TypedQuery<Ejercicios> query = em.createQuery("SELECT u FROM Ejercicios u WHERE u.nombre LIKE :ejercicios", Ejercicios.class)
					.setParameter("ejercicios", ejercicios);
			return query.getResultList();
		}

}

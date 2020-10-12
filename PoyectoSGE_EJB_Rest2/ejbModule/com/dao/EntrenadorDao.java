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

import com.entidades.Entrenador;
import com.entidades.Usuario;


@Stateless
@LocalBean
public class EntrenadorDao {

	@PersistenceContext
	private EntityManager em;

    public EntrenadorDao() {
        // TODO Auto-generated constructor stub
    }
    
	public List<Entrenador> filtroEntrenador(String nombre)

	{
		TypedQuery<Entrenador> query = em.createQuery("SELECT u FROM Entrenador u WHERE u.nombre LIKE :nombre", Entrenador.class)
				.setParameter("nombre", "%"+nombre+"%");
		return query.getResultList();
	}
	
	public List<Entrenador> filtroEntrenador2(String usuario)

	{
		TypedQuery<Entrenador> query = em.createQuery("SELECT u FROM Entrenador u WHERE u.nombre || u.deportes LIKE :usu", Entrenador.class)
				.setParameter("usu", "%"+usuario+"%");
		return query.getResultList();
	}
    
    public void guardarEntrenador(Entrenador entrenador) throws SQLException {
		this.em.persist(entrenador);
		em.flush();
		em.refresh(entrenador);

	}

	public void actualizarEntrenador(Entrenador entrenador) throws SQLException {
		this.em. merge(entrenador);
		em.flush();
		em.refresh(entrenador);
	}

	public void borrarEntrenador(Long idEntrenador) throws SQLException {
		Entrenador entrenador = em.find(Entrenador.class, idEntrenador);
		em.remove(entrenador);
		em.flush();
		em.refresh(entrenador);
	}

	public Entrenador obtenerEntrenador(Long idEntrenador) {
		return this.em.find(Entrenador.class, idEntrenador);
	}

	public List<Entrenador> obternerTodos() {
		TypedQuery<Entrenador> query = this.em.createQuery("SELECT a FROM Entrenador a", Entrenador.class);
		return query.getResultList();
	}

	public Long obtenerporId(Long idEntrenador) {
		this.em.find(Entrenador.class, idEntrenador);
		return idEntrenador;
	}
	
	public Entrenador obtenerEntrenadorIgual(String entrenador) {
		try {
			Query q = em.createQuery("SELECT u FROM Entrenador u WHERE u.usuario LIKE :entrenador", Entrenador.class);
			q.setParameter("entrenador", entrenador);

			return (Entrenador) q.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}
	


}


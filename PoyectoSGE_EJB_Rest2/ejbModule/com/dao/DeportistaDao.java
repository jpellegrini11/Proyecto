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


@Stateless
@LocalBean
public class DeportistaDao {

	@PersistenceContext
	private EntityManager em;

    public DeportistaDao() {
        // TODO Auto-generated constructor stub
    }
    
    public void guardarDeportista(Deportista deportista) throws SQLException {
		this.em.persist(deportista);
		em.flush();
		em.refresh(deportista);

	}

	public void actualizarDeportista(Deportista deportista) throws SQLException {
		this.em.merge(deportista);
		em.flush();
		em.refresh(deportista);
	}

	public void borrarDeportista(Long idDeportista) throws SQLException {
		Deportista deportista = em.find(Deportista.class, idDeportista);
		em.remove(deportista);
		em.flush();
		em.refresh(deportista);
	}

	public Deportista obtenerDeportista(Long idDeportista) {
		return this.em.find(Deportista.class, idDeportista);
	}

	public List<Deportista> obternerTodos() {
		TypedQuery<Deportista> query = this.em.createQuery("SELECT a FROM Deportista a", Deportista.class);
		return query.getResultList();
	}

	public Long obtenerporId(Long idDeportista) {
		this.em.find(Deportista.class, idDeportista);
		return idDeportista;
	}
	
	public Deportista obtenerDeportistaIgual(String deportista) {
		System.out.println("Entro dao Obtener"+deportista);
		try {
			
			Query q = em.createQuery("SELECT u FROM Deportista u WHERE u.usuario LIKE :deportista", Deportista.class);
			q.setParameter("deportista", deportista);

			return (Deportista) q.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}
	


}


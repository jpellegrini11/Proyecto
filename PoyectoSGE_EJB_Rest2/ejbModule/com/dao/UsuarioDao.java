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

import com.entidades.Usuario;


@Stateless
@LocalBean
public class UsuarioDao {

	@PersistenceContext
	private EntityManager em;

    public UsuarioDao() {
        // TODO Auto-generated constructor stub
    }
    
    public void guardarUsuario(Usuario usuario) throws SQLException {
		this.em.persist(usuario);

	}

	public void actualizarUsuario(Usuario usuario) throws SQLException {
		this.em.merge(usuario);
	}

	public void borrarUsuario(Long idUsuario) throws SQLException {
		Usuario usuario = em.find(Usuario.class, idUsuario);
		em.remove(usuario);
		em.flush();
	}

	public Usuario obtenerUsuario(Long idUsuario) {
		return this.em.find(Usuario.class, idUsuario);
	}

	public List<Usuario> obternerTodos() {
		TypedQuery<Usuario> query = this.em.createQuery("SELECT a FROM Usuario a", Usuario.class);
		return query.getResultList();
	}

	public Long obtenerporId(Long idUsuario) {
		this.em.find(Usuario.class, idUsuario);
		return idUsuario;
	}
	
	public Usuario obtenerUsuarioIgual(String usuario) {
		try {
			Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.usuario LIKE :usuario", Usuario.class);
			q.setParameter("usuario", usuario);

			return (Usuario) q.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}
	


}

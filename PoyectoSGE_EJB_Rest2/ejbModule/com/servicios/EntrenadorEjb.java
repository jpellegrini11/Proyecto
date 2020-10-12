package com.servicios;


import java.sql.SQLException;
import java.util.List;


import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.dao.EntrenadorDao;
import com.entidades.Ejercicios;
import com.entidades.Entrenador;
import com.entidades.Usuario;
import com.utils.SessionUtils;

@Stateless
@LocalBean
public class EntrenadorEjb {

	@EJB
	EntrenadorDao entrenadorDao;
	
	
	
 	public EntrenadorEjb() {
		
	}
	 
 	public String guardarPerfilEjb(String nombre,String descripcion,String deportes,String titulos,String logros,
 							Long cantPersonas,String dispHoraria,String ubicacion) throws SQLException{
		
		//El entrenador ya esta registrado en la app, tiene atributos usuario y compPerfil seteados.
		//Esta funcion actualiza el Entr con los datos que se cargan en el form
		
		//Creo entrenador con datos que provienen del formulario
//		Entrenador e= new Entrenador(nombre, descripcion, deportes, titulos, logros, cantPersonas, dispHoraria, ubicacion, (long) 1);
		
		//Recupero el nombre de usuario guardado en la seccion y lo utilizo para hacer la busqueda en la bd para obtener la ID_ENTR
		String usuario = (String) SessionUtils.getRequest().getSession(false).getAttribute("usuario");
		
		Entrenador ent1= obtenerEntrenadorIgual(usuario); 
		System.out.println("acutal Ent"+ent1.getUsuario());
		//Seteo los datos a entrenador
		ent1.setNombre(nombre);
		ent1.setDescripcion(descripcion);
		ent1.setDeportes(deportes);
		ent1.setTitulos(titulos);
		ent1.setLogros(logros);
		ent1.setCantPersonas(cantPersonas);
		ent1.setDispHoraria(dispHoraria);
		ent1.setUbicacion(ubicacion);
		ent1.setCompPerfil((long) 1);
		System.out.println("actu perfil ");
		//Actualizar el entr en la bd
		actualizar(ent1);
		
				
			return "homeEnt";
				
	}
 	
 	public List<Ejercicios> cargarListEjercicios() throws SQLException{
 		
 		String usuario = (String) SessionUtils.getRequest().getSession(false).getAttribute("usuario");
 		
 		System.out.println("entro cargalista Eejr "+usuario);
 		
 		Entrenador e= obtenerEntrenadorIgual(usuario);
 		System.out.println("size lista ejer"+e.getListEjer().size());
 		List<Ejercicios> listejer=e.getListEjer();
 		
 		return listejer;
 	}
 	
	public List<Entrenador> filtroEntrenador(String nombre)

	{
	
		return entrenadorDao.filtroEntrenador(nombre);
	}
	
	public List<Entrenador> filtroEntrenador2(String nombre)

	{
	
		return entrenadorDao.filtroEntrenador2(nombre);
	}
	
	
	public void alta(Entrenador entrenador) throws SQLException {

		
			this.entrenadorDao.guardarEntrenador(entrenador);
	
	}

	public void actualizar(Entrenador entrenador) throws SQLException {
		
		
		
			this.entrenadorDao.actualizarEntrenador(entrenador);
	
	 }

	 
	public void borrar(Long idEntrenador) throws SQLException {
		
			this.entrenadorDao.borrarEntrenador(idEntrenador);
	
	}
	
	 
	public Long obtenerPorId(Long id) throws SQLException{
		return this.entrenadorDao.obtenerporId(id);
	}

	 
	public List<Entrenador> obtenerTodos(){
		return this.entrenadorDao.obternerTodos();
	}

	 
	public Entrenador obtenerEntrenador(Long idEntrenador) throws SQLException {
		return this.entrenadorDao.obtenerEntrenador(idEntrenador);
	}
	
	  
	public Entrenador obtenerEntrenadorIgual(String entrenador) throws SQLException {
		return this.entrenadorDao.obtenerEntrenadorIgual(entrenador);
	}
}

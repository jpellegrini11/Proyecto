package com.servicios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.dao.EjerciciosDao;
import com.entidades.Ejercicios;
import com.entidades.Entrenador;
import com.utils.SessionUtils;

@Stateless
@LocalBean
public class EjerciciosEjb {
	
	@EJB
	EjerciciosDao ejerciciosDao;
	
	@EJB
	EntrenadorEjb entrenadorEjb;
	
public void alta(String nombre, String descripcion, String linkVideo) throws SQLException {
		
	System.out.println("alta ejer");
	if(ejerciciosDao.obtenerEjerciciosIgual(nombre) == null) {
	
	String videoUrlEmb= videoUrlEmb(linkVideo);	
	Ejercicios eje1= new Ejercicios(nombre, descripcion, videoUrlEmb);
	
	this.ejerciciosDao.guardarEjercicios(eje1);
	
	System.out.println("add ejerc");
	String usuario = (String) SessionUtils.getRequest().getSession(false).getAttribute("usuario");
	Entrenador ent1= entrenadorEjb.obtenerEntrenadorIgual(usuario);
	
	List<Ejercicios> listEje= new ArrayList<Ejercicios>();
	listEje.add(eje1);
	ent1.getListEjer().add(eje1);
	ent1.setListEjer(listEje);
	eje1.setEntrenador(ent1);
	
	mostMsjGrowl("El ejercico : "+nombre +" se creo correctamente");
	}
	else {
		
		mostMsjGrowl("No se guardo, ya existe un ejercico con ese nombre");
	}
}

public String videoUrlEmb(String url) {
	String url2 = url;
	String [] partes = url.split("/");
	String codigo = partes[3];		
		System.out.println(codigo);
	
	return url;
	
	
}

public void actualizar(Ejercicios ejercicios) throws SQLException {
	
		this.ejerciciosDao.actualizarEjercicios(ejercicios);
	}

 
public void borrar(Long idEjercicios) throws SQLException {
	
		this.ejerciciosDao.borrarEjercicios(idEjercicios);

}

 
public Long obtenerPorId(Long id) throws SQLException{
	return this.ejerciciosDao.obtenerporId(id);
}

 
public List<Ejercicios> obtenerTodos(){
	return this.ejerciciosDao.obternerTodos();
}

 
public Ejercicios obtenerEjercicios(Long idEjercicios) throws SQLException {
	return this.ejerciciosDao.obtenerEjercicios(idEjercicios);
}

  
public Ejercicios obtenerEjerciciosIgual(String ejercicios) throws SQLException {
	return this.ejerciciosDao.obtenerEjerciciosIgual(ejercicios);
}

public void mostMsjGrowl(String msj) {
	FacesContext.getCurrentInstance().addMessage("growl",
			new FacesMessage(FacesMessage.SEVERITY_INFO, msj, ""));
	
	
}
}

package com.bean;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.servicios.EjerciciosEjb;

import java.io.Serializable;
import java.sql.SQLException;

@Named("eje")
@SessionScoped
public class EjerciciosBean implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String	nombre;
	private String	deporte;
	private String	descripcion;
	private String linkVideo;
	
	@EJB
	EjerciciosEjb ejerciciosEjb;
	public EjerciciosBean() {}
	
	
	public String crearEjercicoBean() {
		String str="";
		
		try {
			ejerciciosEjb.alta(nombre, descripcion, linkVideo);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			mostMsj("El ejercicio no se guardo, vuelva a intentarlo mas tarde");
			e.printStackTrace();
		}
		
		return str;
		
		
	}
	
	public void mostMsjGrowl(String msj) {
		FacesContext.getCurrentInstance().addMessage("growl",
				new FacesMessage(FacesMessage.SEVERITY_INFO, msj, ""));
		
		
	}
	
	public void mostMsj(String msj) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, msj, ""));
		
		
	}
	

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public String getDeporte() {
		return deporte;
	}




	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}




	public String getLinkVideo() {
		return linkVideo;
	}




	public void setLinkVideo(String linkVideo) {
		this.linkVideo = linkVideo;
	}

}

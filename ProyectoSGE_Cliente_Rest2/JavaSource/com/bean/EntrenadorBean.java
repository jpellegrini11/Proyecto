
package com.bean;

import java.io.Serializable;
import java.sql.SQLException;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import com.dao.DeportistaDao;
import com.entidades.Deportista;
import com.entidades.Entrenador;
import com.servicios.EntrenadorEjb;



@Named("ent")
@SessionScoped
public class EntrenadorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	EntrenadorEjb entrenadorEjb;
	@EJB
	DeportistaDao deportistaDao;

	
	

	private String usuario;
	private	String nombre;
	//	Fotos.
	private	String	descripcion;
	private	String	deportes;
	private	String	titulos;
	private	String	logros;
	private	Long 	cantPersonas;
	private	String	dispHoraria;
	private	String	ubicacion;
	private Long 	compPerfil;
	private Deportista deportista;
	
	

	public EntrenadorBean() {
			}
	
	public String guardarPerfilBean() {
		String str= null;
			try {
				 str= entrenadorEjb.guardarPerfilEjb(nombre, descripcion, deportes, titulos, logros, cantPersonas, dispHoraria, ubicacion);
				 return str;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No se guardo correctamente el perfil. Vuelva a intentar", ""));
				e.printStackTrace();
			}
			return str;
				
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDeportes() {
		return deportes;
	}

	public void setDeportes(String deportes) {
		this.deportes = deportes;
	}

	public String getTitulos() {
		return titulos;
	}

	public void setTitulos(String titulos) {
		this.titulos = titulos;
	}

	public String getLogros() {
		return logros;
	}

	public void setLogros(String logros) {
		this.logros = logros;
	}

	public Long getCantPersonas() {
		return cantPersonas;
	}

	public void setCantPersonas(Long cantPersonas) {
		this.cantPersonas = cantPersonas;
	}

	public String getDispHoraria() {
		return dispHoraria;
	}

	public void setDispHoraria(String dispHoraria) {
		this.dispHoraria = dispHoraria;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Long getCompPerfil() {
		return compPerfil;
	}

	public void setCompPerfil(Long compPerfil) {
		this.compPerfil = compPerfil;
	}

	public Deportista getDeportista() {
		return deportista;
	}

	public void setDeportista(Deportista deportista) {
		this.deportista = deportista;
	}
	
	




}

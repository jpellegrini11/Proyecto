package com.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


@Entity

public class Entrenador implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_ENTRENADOR")
	private Long idEntrenador;
	
	
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
	private Long compPerfil;
	private String perfil;

	@OneToMany(mappedBy = "entrenador", fetch=FetchType.EAGER)
	private List<Ejercicios> listEjer;
	
	@OneToMany(mappedBy = "entrenador")
	private List<Deportista> listDep;
	
	@OneToMany(mappedBy = "entrenador")
	private List<AsignarEnto> listAsignarEnto;
	
	@OneToMany(mappedBy = "entrenador")
	private List<Entrenamiento> listEntrenamiento;
	
	@OneToMany(mappedBy = "entrenador")
	private List<Entrena> listEntrena;

	public Entrenador() {
		super();
	}


	public Entrenador(  String nombre, String descripcion, String deportes, String titulos, String logros,
			Long cantPersonas, String dispHoraria, String ubicacion, Long compPerfil) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.deportes = deportes;
		this.titulos = titulos;
		this.logros = logros;
		this.cantPersonas = cantPersonas;
		this.dispHoraria = dispHoraria;
		this.ubicacion = ubicacion;
		this.compPerfil = compPerfil;
	}


	public Long getIdEntrenador() {
		return idEntrenador;
	}


	public void setIdEntrenador(Long idEntrenador) {
		this.idEntrenador = idEntrenador;
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


	public List<Ejercicios> getListEjer() {
		return listEjer;
	}


	public void setListEjer(List<Ejercicios> listEjer) {
		this.listEjer = listEjer;
	}


	public List<Deportista> getListDep() {
		return listDep;
	}


	public void setListDep(List<Deportista> listDep) {
		this.listDep = listDep;
	}


	public List<AsignarEnto> getListAsignarEnto() {
		return listAsignarEnto;
	}


	public void setListAsignarEnto(List<AsignarEnto> listAsignarEnto) {
		this.listAsignarEnto = listAsignarEnto;
	}


	public List<Entrenamiento> getListEntrenamiento() {
		return listEntrenamiento;
	}


	public void setListEntrenamiento(List<Entrenamiento> listEntrenamiento) {
		this.listEntrenamiento = listEntrenamiento;
	}


	public List<Entrena> getListEntrena() {
		return listEntrena;
	}


	public void setListEntrena(List<Entrena> listEntrena) {
		this.listEntrena = listEntrena;
	}


	public String getPerfil() {
		return perfil;
	}


	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}


		
   
}

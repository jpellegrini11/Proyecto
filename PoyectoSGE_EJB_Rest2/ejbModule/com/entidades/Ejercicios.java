package com.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


@Entity

public class Ejercicios implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="ID_EJERCICIOS")
	private Long idEjercicios;
	
	private String	nombre;
	private String	deporte;
	private String	descripcion;
	private String linkVideo;

	
	@ManyToOne
	@JoinColumn(name = "ID_ENTRENADOR")
	private Entrenador entrenador;

	@ManyToOne
	@JoinColumn(name = "ID_ENTRENAMIENTO")
	private Entrenamiento entrenamiento; 
	
	
	public Ejercicios() {
		super();
	}



	public Ejercicios(String nombre, String deporte, String descripcion, String linkVideo) {
		super();
		this.nombre = nombre;
		this.deporte = deporte;
		this.descripcion = descripcion;
		this.linkVideo = linkVideo;

	
		
	}



	public Long getIdEjercicios() {
		return idEjercicios;
	}


	public void setIdEjercicios(Long idEjercicios) {
		this.idEjercicios = idEjercicios;
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


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public Entrenador getEntrenador() {
		return entrenador;
	}



	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}


	public Entrenamiento getEntrenamiento() {
		return entrenamiento;
	}


	public void setEntrenamiento(Entrenamiento entrenamiento) {
		this.entrenamiento = entrenamiento;
	}



	public String getLinkVideo() {
		return linkVideo;
	}


	public void setLinkVideo(String linkVideo) {
		this.linkVideo = linkVideo;
	}
	
   
}

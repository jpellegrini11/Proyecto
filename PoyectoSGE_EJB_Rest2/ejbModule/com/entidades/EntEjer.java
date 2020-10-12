package com.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class EntEjer implements Serializable{
	

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="ID_ENTEJER")
	private Long idEntEjer;
	
	@OneToOne
	@JoinColumn(name="ID_ENTRENADOR")
	private Entrenador entrenador;
	
	@OneToOne
	@JoinColumn(name="ID_EJERCICIOS")
	private Ejercicios ejercicios;
	
	@OneToOne
	@JoinColumn(name="ID_ENTRENAMIENTO")
	private Entrenamiento entrenamiento;
	
	private Long intensidad;
	private Long repeticiones;
	
	
	public EntEjer() {
		
	}

	public EntEjer(Entrenador entrenador, Ejercicios ejercicios, Long intensidad, Long repeticiones) {
		super();
		this.entrenador = entrenador;
		this.ejercicios = ejercicios;
		this.intensidad = intensidad;
		this.repeticiones = repeticiones;
	}


	public EntEjer(Entrenador entrenador, Ejercicios ejercicios) {
		super();
		this.entrenador = entrenador;
		this.ejercicios = ejercicios;
	}



	public Long getIdEntEjer() {
		return idEntEjer;
	}

	public void setIdEntEjer(Long idEntEjer) {
		this.idEntEjer = idEntEjer;
	}

	public Entrenador getEntrenador() {
		return entrenador;
	}


	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}



	public Ejercicios getEjercicios() {
		return ejercicios;
	}



	public void setEjercicios(Ejercicios ejercicios) {
		this.ejercicios = ejercicios;
	}



	public Long getIntensidad() {
		return intensidad;
	}



	public void setIntensidad(Long intensidad) {
		this.intensidad = intensidad;
	}



	public Long getRepeticiones() {
		return repeticiones;
	}



	public void setRepeticiones(Long repeticiones) {
		this.repeticiones = repeticiones;
	}



	public Entrenamiento getEntrenamiento() {
		return entrenamiento;
	}



	public void setEntrenamiento(Entrenamiento entrenamiento) {
		this.entrenamiento = entrenamiento;
	}



	
	
	

}

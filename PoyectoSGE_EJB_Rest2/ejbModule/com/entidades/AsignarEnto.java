package com.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


@Entity

public class AsignarEnto implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="ID_ASIGNARENTO")
	private Long idAsignarEnto;
	
	@OneToOne
	@JoinColumn(name="ID_ENTRENAMIENTO")
	private Entrenamiento entrenamiento;
	

	
	
	@ManyToMany
	private List<Deportista> listDeportista;
	


	@ManyToOne
	@JoinColumn(name="ID_ENTRENADOR")
	private Entrenador entrenador;
	
	@Temporal(TemporalType.DATE)
	private Date fechaIni;
	
	@Temporal(TemporalType.DATE)
	private Date fechaFin;
	
	private Float freqCard;

	private Float velocidad;
	
	private Float tiempoTotal;
	
	private String comentarios;

	public AsignarEnto() {
		super();
		
	}


	public AsignarEnto(Entrenamiento entrenamiento, List<Deportista> listDeportista, Entrenador entrenador,
			Date fechaIni, Date fechaFin, Float freqCard, Float velocidad, Float tiempoTotal) {
		super();
		this.entrenamiento = entrenamiento;
		this.listDeportista = listDeportista;
		this.entrenador = entrenador;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.freqCard = freqCard;
		this.velocidad = velocidad;
		this.tiempoTotal = tiempoTotal;
	}


	public Long getIdAsignarEnto() {
		return idAsignarEnto;
	}

	public void setIdAsignarEnto(Long idAsignarEnto) {
		this.idAsignarEnto = idAsignarEnto;
	}

	public Entrenamiento getEntrenamiento() {
		return entrenamiento;
	}

	public void setEntrenamiento(Entrenamiento entrenamiento) {
		this.entrenamiento = entrenamiento;
	}


	public Entrenador getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}

	public List<Deportista> getListDeportista() {
		return listDeportista;
	}

	public void setListDeportista(List<Deportista> listDeportista) {
		this.listDeportista = listDeportista;
	}

	public Date getFechaIni() {
		return fechaIni;
	}


	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}


	public Date getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	public Float getFreqCard() {
		return freqCard;
	}


	public void setFreqCard(Float freqCard) {
		this.freqCard = freqCard;
	}


	public Float getVelocidad() {
		return velocidad;
	}


	public void setVelocidad(Float velocidad) {
		this.velocidad = velocidad;
	}


	public Float getTiempoTotal() {
		return tiempoTotal;
	}


	public void setTiempoTotal(Float tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}


	public String getComentarios() {
		return comentarios;
	}


	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

   
}

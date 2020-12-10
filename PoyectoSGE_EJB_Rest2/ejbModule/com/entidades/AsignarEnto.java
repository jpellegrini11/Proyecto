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

	private String completado;
	
	private Long tiempoReal1;
	private Long tiempoReal2;
	private Long tiempoReal3;
	private Long tiempoReal4;
	private Long tiempoReal5;

	
	
	public Long getTiempoReal1() {
		return tiempoReal1;
	}


	public void setTiempoReal1(Long tiempoReal1) {
		this.tiempoReal1 = tiempoReal1;
	}


	public Long getTiempoReal2() {
		return tiempoReal2;
	}


	public void setTiempoReal2(Long tiempoReal2) {
		this.tiempoReal2 = tiempoReal2;
	}


	public Long getTiempoReal3() {
		return tiempoReal3;
	}


	public void setTiempoReal3(Long tiempoReal3) {
		this.tiempoReal3 = tiempoReal3;
	}


	public Long getTiempoReal4() {
		return tiempoReal4;
	}


	public void setTiempoReal4(Long tiempoReal4) {
		this.tiempoReal4 = tiempoReal4;
	}


	public Long getTiempoReal5() {
		return tiempoReal5;
	}


	public void setTiempoReal5(Long tiempoReal5) {
		this.tiempoReal5 = tiempoReal5;
	}


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


	public String getCompletado() {
		return completado;
	}


	public void setCompletado(String completado) {
		this.completado = completado;
	}






   
}

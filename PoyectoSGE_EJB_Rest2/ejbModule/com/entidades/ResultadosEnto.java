package com.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ResultadosEnto implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_RESULTADOSENTO")
	
	private Long idResultadosEnto;
	
	@OneToOne
	@JoinColumn(name="ID_ASIGNARENTO")
	private AsignarEnto asignarEnto;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	private Integer freqCard;

	private Double velocidad;
	
	private String comentarios;

	private String completado;
	private String usuarioDep;
	
	private Long tiempoReal1;
	private Long tiempoReal2;
	private Long tiempoReal3;
	private Long tiempoReal4;
	private Long tiempoReal5;
	private Long tiempoTotal;
	private Long polarFlow;
	private String deporte;

	public ResultadosEnto(Date fecha, Integer freqCard, Double velocidad, String usuarioDep, Long tiempoTotal,
			Long polarFlow,  String deporte) {
		super();
		this.fecha = fecha;
		this.freqCard = freqCard;
		this.setVelocidad(velocidad);
		this.usuarioDep = usuarioDep;
		this.tiempoTotal = tiempoTotal;
		this.polarFlow = polarFlow;
		this.deporte = deporte;
	}
	 
	
	public ResultadosEnto(AsignarEnto asignarEnto, Date fecha, Integer freqCard, Double velocidad, 
			String comentarios, String completado, Long tiempoReal1, Long tiempoReal2, Long tiempoReal3,
			Long tiempoReal4, Long tiempoReal5) {
		super();
		this.asignarEnto = asignarEnto;
		this.fecha = fecha;
		this.freqCard = freqCard;
		this.setVelocidad(velocidad);
		this.comentarios = comentarios;
		this.completado = completado;
		this.tiempoReal1 = tiempoReal1;
		this.tiempoReal2 = tiempoReal2;
		this.tiempoReal3 = tiempoReal3;
		this.tiempoReal4 = tiempoReal4;
		this.tiempoReal5 = tiempoReal5;
		
	}
	
	

	public ResultadosEnto(AsignarEnto asignarEnto, Date fecha, Integer freqCard, Double velocidad, String comentarios,
			String completado,String usuarioDep) {
		super();
		this.asignarEnto = asignarEnto;
		this.fecha = fecha;
		this.freqCard = freqCard;
		this.setVelocidad(velocidad);
		this.comentarios = comentarios;
		this.completado = completado;
		this.usuarioDep = usuarioDep;
		
	}



	public Long getIdResultadosEnto() {
		return idResultadosEnto;
	}

	public void setIdResultadosEnto(Long idResultadosEnto) {
		this.idResultadosEnto = idResultadosEnto;
	}

	public AsignarEnto getAsignarEnto() {
		return asignarEnto;
	}

	public void setAsignarEnto(AsignarEnto asignarEnto) {
		this.asignarEnto = asignarEnto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getFreqCard() {
		return freqCard;
	}

	public void setFreqCard(Integer freqCard) {
		this.freqCard = freqCard;
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

	ResultadosEnto(){
		
	}



	public String getUsuarioDep() {
		return usuarioDep;
	}



	public void setUsuarioDep(String usuarioDep) {
		this.usuarioDep = usuarioDep;
	}



	public Long getTiempoTotal() {
		return tiempoTotal;
	}



	public void setTiempoTotal(Long tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}



	public Long getPolarFlow() {
		return polarFlow;
	}



	public void setPolarFlow(Long polarFlow) {
		this.polarFlow = polarFlow;
	}


	public String getDeporte() {
		return deporte;
	}


	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}


	public Double getVelocidad() {
		return velocidad;
	}


	public void setVelocidad(Double velocidad) {
		this.velocidad = velocidad;
	}







	
}

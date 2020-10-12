package com.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Entrena
 *
 */
@Entity

public class Entrena implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_ENTRENA")
	private Long idEntrena;
	
	@ManyToOne
	@JoinColumn(name="ID_ENTRENADOR")
	private Entrenador entrenador;
	
	@ManyToOne
	@JoinColumn(name="ID_DEPORTISTA")
	private Deportista deportista;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	private Long miroEnt;
	private Long miroDep;
	
	public Entrena() {
		super();
	}

	
	
	public Entrena(Entrenador entrenador, Deportista deportista, Date fecha, Long miroEnt, Long miroDep) {
		super();
		this.entrenador = entrenador;
		this.deportista = deportista;
		this.fecha = fecha;
		this.miroEnt = miroEnt;
		this.miroDep = miroDep;
	}



	public Long getIdEntrena() {
		return idEntrena;
	}

	public void setIdEntrena(Long idEntrena) {
		this.idEntrena = idEntrena;
	}

	public Entrenador getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}

	public Deportista getDeportista() {
		return deportista;
	}

	public void setDeportista(Deportista deportista) {
		this.deportista = deportista;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getMiroEnt() {
		return miroEnt;
	}

	public void setMiroEnt(Long miroEnt) {
		this.miroEnt = miroEnt;
	}

	public Long getMiroDep() {
		return miroDep;
	}

	public void setMiroDep(Long miroDep) {
		this.miroDep = miroDep;
	}
	
   
}

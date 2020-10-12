package com.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Deportista
 *
 */
@Entity

public class Deportista implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="ID_DEPORTISTA")
	private Long idDesportista;
	
	private String usuario;
	private String nomCompleto;
	private String sociedadMedica;
	private Float peso;
	private Float altura;
	private String dispHoraria;
	private String alergias;
	private String lesiones;
	private String probSalud;
	private String obejtivos;
	private Float ferqCardMax;
	private Float ferqCardMin;
	private Long compPerfil;
	private String perfil;
	
	@ManyToOne
	@JoinColumn(name = "id_entrenador")
	private Entrenador entrenador;

	@ManyToMany(mappedBy = "listDeportista")
	private List<AsignarEnto> listAsignarEnto;
	
//	@ManyToOne
//	@JoinColumn(name = "ID_ASIGNARENTO")
//	private AsignarEnto asignarEnto;
	
	public Deportista() {
		super();
	}

	public Deportista(String sociedadMedica, Float peso, Float altura, String dispHoraria,
			String alergias, String lesiones, String probSalud, String obejtivos, Float ferqCardMax, Float ferqCardMin,
			Long compPerfil, String nomCompleto) {
		super();
		
		this.sociedadMedica = sociedadMedica;
		this.peso = peso;
		this.altura = altura;
		this.dispHoraria = dispHoraria;
		this.alergias = alergias;
		this.lesiones = lesiones;
		this.probSalud = probSalud;
		this.obejtivos = obejtivos;
		this.ferqCardMax = ferqCardMax;
		this.ferqCardMin = ferqCardMin;
		this.compPerfil = compPerfil;
		this.nomCompleto = nomCompleto;
	}


	public Long getIdDesportista() {
		return idDesportista;
	}



	public void setIdDesportista(Long idDesportista) {
		this.idDesportista = idDesportista;
	}



	public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	public String getSociedadMedica() {
		return sociedadMedica;
	}



	public void setSociedadMedica(String sociedadMedica) {
		this.sociedadMedica = sociedadMedica;
	}



	public Float getPeso() {
		return peso;
	}



	public void setPeso(Float peso) {
		this.peso = peso;
	}



	public Float getAltura() {
		return altura;
	}



	public void setAltura(Float altura) {
		this.altura = altura;
	}



	public String getDispHoraria() {
		return dispHoraria;
	}



	public void setDispHoraria(String dispHoraria) {
		this.dispHoraria = dispHoraria;
	}



	public String getAlergias() {
		return alergias;
	}



	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}



	public String getLesiones() {
		return lesiones;
	}



	public void setLesiones(String lesiones) {
		this.lesiones = lesiones;
	}



	public String getProbSalud() {
		return probSalud;
	}



	public void setProbSalud(String probSalud) {
		this.probSalud = probSalud;
	}



	public String getObejtivos() {
		return obejtivos;
	}



	public void setObejtivos(String obejtivos) {
		this.obejtivos = obejtivos;
	}



	public Float getFerqCardMax() {
		return ferqCardMax;
	}



	public void setFerqCardMax(Float ferqCardMax) {
		this.ferqCardMax = ferqCardMax;
	}



	public Float getFerqCardMin() {
		return ferqCardMin;
	}



	public void setFerqCardMin(Float ferqCardMin) {
		this.ferqCardMin = ferqCardMin;
	}



	public Long getCompPerfil() {
		return compPerfil;
	}



	public void setCompPerfil(Long compPerfil) {
		this.compPerfil = compPerfil;
	}



	public Entrenador getEntrenador() {
		return entrenador;
	}



	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}



//	public AsignarEnto getAsignarEnto() {
//		return asignarEnto;
//	}
//
//
//
//	public void setAsignarEnto(AsignarEnto asignarEnto) {
//		this.asignarEnto = asignarEnto;
//	}



	public String getNomCompleto() {
		return nomCompleto;
	}



	public void setNomCompleto(String nomCompleto) {
		this.nomCompleto = nomCompleto;
	}

//	public AsignarEnto getAsignarEnto() {
//		return asignarEnto;
//	}
//
//	public void setAsignarEnto(AsignarEnto asignarEnto) {
//		this.asignarEnto = asignarEnto;
//	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}



	public List<AsignarEnto> getListAsignarEnto() {
		return listAsignarEnto;
	}



	public void setListAsignarEnto(List<AsignarEnto> listAsignarEnto) {
		this.listAsignarEnto = listAsignarEnto;
	}


	

   
}

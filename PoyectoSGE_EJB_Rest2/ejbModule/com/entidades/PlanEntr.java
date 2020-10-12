package com.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: PlanEntr
 *
 */
@Entity

public class PlanEntr implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_PLANENTR")
	private Long idPlanEntr;
	
	private String nombre;
	private String deporte;
	private String etiquetas;
	
//	@OneToMany
//	@JoinColumn(name="ID_ENTRENAMIENTO")
//	private List<Entrenamiento> listEntra;
	

	public PlanEntr() {
		super();
	}


	public PlanEntr(Long idPlanEntr, String nombre, String deporte, String etiquetas, List<Entrenamiento> listEntra) {
		super();
		this.idPlanEntr = idPlanEntr;
		this.nombre = nombre;
		this.deporte = deporte;
		this.etiquetas = etiquetas;
//		this.listEntra = listEntra;
	}


	public Long getIdPlanEntr() {
		return idPlanEntr;
	}


	public void setIdPlanEntr(Long idPlanEntr) {
		this.idPlanEntr = idPlanEntr;
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


	public String getEtiquetas() {
		return etiquetas;
	}


	public void setEtiquetas(String etiquetas) {
		this.etiquetas = etiquetas;
	}


//	public List<Entrenamiento> getListEntra() {
//		return listEntra;
//	}
//
//
//	public void setListEntra(List<Entrenamiento> listEntra) {
//		this.listEntra = listEntra;
//	}
   
}

package com.entidades;

import java.io.Serializable;

import javax.persistence.*;


@Entity

public class Asignar implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_ASIGNAR")
	private Long idAsignar;
	
	@ManyToOne
	@JoinColumn(name="ID_PLANENTR")
	private PlanEntr listPlanEntr;
	
	@ManyToOne
	@JoinColumn(name="ID_DEPORTISTA")
	private Deportista deportista;
	
	

	public Asignar() {
		super();
	}


	public Asignar(PlanEntr listPlanEntr, Deportista deportista, String etiqueta) {
		super();
		
		this.listPlanEntr = listPlanEntr;
		this.deportista = deportista;
	}



	public Long getIdAsignar() {
		return idAsignar;
	}



	public void setIdAsignar(Long idAsignar) {
		this.idAsignar = idAsignar;
	}



	public PlanEntr getListPlanEntr() {
		return listPlanEntr;
	}



	public void setListPlanEntr(PlanEntr listPlanEntr) {
		this.listPlanEntr = listPlanEntr;
	}



	public Deportista getDeportista() {
		return deportista;
	}



	public void setDeportista(Deportista deportista) {
		this.deportista = deportista;
	}




   
}

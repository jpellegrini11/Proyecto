package com.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.swing.text.StyledEditorKit.BoldAction;

import com.dao.EntEjerDao;
import com.entidades.Ejercicios;
import com.entidades.EntEjer;
import com.entidades.Entrenador;
import com.entidades.Entrenamiento;
import com.servicios.EntEjerEjb;
import com.servicios.EntrenadorEjb;
import com.servicios.EntrenamientoEjb;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Named("entrenamiento")
@SessionScoped
public class EntrenameintoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	EntrenamientoEjb entrenamientoEjb;

	@EJB
	EntrenadorEjb entrenadorEjb;

	@EJB
	EntEjerEjb ent_EjerEjb;

	private String nombre;
	private String deporte;
	private Long tiempoRealizar;

	

	private String ejer;
	private List<String> listStrEjer;
	private Long repeticion;
	private Long intensidad;
	private Boolean visib;

	private String ejer2;
	private List<String> listStrEjer2;
	private Long repeticion2;
	private Long intensidad2;
	private Boolean visib2;

	private String ejer3;
	private List<String> listStrEjer3;
	private Long repeticion3;
	private Long intensidad3;
	private Boolean visib3;

	private String ejer4;
	private List<String> listStrEjer4;
	private Long repeticion4;
	private Long intensidad4;
	private Boolean visib4;

	private String ejer5;
	private List<String> listStrEjer5;
	private Long repeticion5;
	private Long intensidad5;
	private Boolean visib5;

	private Ejercicios ejercicio;

	private List<Ejercicios> listEjer;
	private List<Ejercicios> listEjercSelect;
	private Entrenador entrenador;
	private List<EntEjer> listEnt_Ejer;

	private int num;

	
	private List<String> etiquetas;



	@PostConstruct
	public void init() {
		
		
		visib = false;
		num = 1;
		System.out.println("Dentro init() listejerc");
		try {
			listEjer = entrenadorEjb.cargarListEjercicios();

			System.out.println(listEjer.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(listEjer.size());

		listStrEjer = new ArrayList<String>();
		
		for (Ejercicios ejercicios : listEjer) {

			listStrEjer.add(ejercicios.getNombre());

		}
		listStrEjer2=listStrEjer;
		listStrEjer3=listStrEjer;
		listStrEjer4=listStrEjer;
		listStrEjer5=listStrEjer;

	}


	public String guardarEntrenamientoBean() {
		String str="";
		Boolean b=false;
		b=entrenamientoEjb.existeNomEnt(nombre);
		if(b) {
			try {
				
				entrenamientoEjb.guardarEntrenamientoEjb(nombre, deporte, tiempoRealizar, etiquetas,ejer,repeticion,intensidad,
						ejer2,repeticion2,intensidad2,ejer3,repeticion3,intensidad3,ejer4,repeticion4,intensidad4,ejer5,repeticion5,intensidad5);
				mostMsjGrowl("El entrenamiento : "+nombre +", se creo correctamente");
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				System.out.println("entra sql exce");
				e.printStackTrace();
			}
			
		}else {
		mostMsj("El nombre del Entrenamiento ya existe, porfavor ingrese uno diferente");
		}
		return str;

	}
	
	
	public void agregarEjer() {

		if (num < 6) {
			num++;
		}
		System.out.println("num dentro agre: " + num);
		visib2 = true;

		if (num == 4) {
			visib3 = true;
			System.out.println("num dentro agre 4: " + num);
		}
		if (num == 5) {
			visib4 = true;
		}
		if (num == 6) {
			visib5 = true;
		}
	}

	public void eliminarEjer() {
		if (num > 2) {
			num--;
		}
		if (num == 5) {
			visib5 = false;

			System.out.println("num dentro elim 6: " + num);
		}
		if (num == 2) {
			visib2 = false;

			System.out.println("num dentro elim 3: " + num);
		}
		if (num == 3) {
			visib3 = false;

			System.out.println("num dentro elim 5: " + num);

		}
		if (num == 4) {
			visib4 = false;

			System.out.println("num dentro elim 4: " + num);
		}
	}

	public void mostMsj(String msj) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, msj, ""));
		
		
	}
	
	public void mostMsjGrowl(String msj) {
		FacesContext.getCurrentInstance().addMessage("growl",
				new FacesMessage(FacesMessage.SEVERITY_INFO, msj, ""));
		
		
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

	public Long getTiempoRealizar() {
		return tiempoRealizar;
	}

	public void setTiempoRealizar(Long tiempoRealizar) {
		this.tiempoRealizar = tiempoRealizar;
	}

	public Long getIntensidad() {
		return intensidad;
	}

	public void setIntensidad(Long intensidad) {
		this.intensidad = intensidad;
	}

	public Long getRepeticion() {
		return repeticion;
	}

	public void setRepeticion(Long repeticion) {
		this.repeticion = repeticion;
	}

	public Ejercicios getEjercicio() {
		return ejercicio;
	}

	public void setEjercicio(Ejercicios ejercicio) {
		this.ejercicio = ejercicio;
	}

	public List<Ejercicios> getListEjer() {
		return listEjer;
	}

	public void setListEjer(List<Ejercicios> listEjer) {
		this.listEjer = listEjer;
	}

	public Entrenador getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Ejercicios> getListEjercSelect() {
		return listEjercSelect;
	}

	public void setListEjercSelect(List<Ejercicios> listEjercSelect) {
		this.listEjercSelect = listEjercSelect;
	}

	public List<EntEjer> getListEnt_Ejer() {
		return listEnt_Ejer;
	}

	public void setListEnt_Ejer(List<EntEjer> listEnt_Ejer) {
		this.listEnt_Ejer = listEnt_Ejer;
	}

	public String getEjer() {
		return ejer;
	}

	public void setEjer(String ejer) {
		this.ejer = ejer;
	}

	public List<String> getListStrEjer() {
		return listStrEjer;
	}

	public void setListStrEjer(List<String> listStrEjer) {
		this.listStrEjer = listStrEjer;
	}

	public String getEjer2() {
		return ejer2;
	}

	public void setEjer2(String ejer2) {
		this.ejer2 = ejer2;
	}

	public List<String> getListStrEjer2() {
		return listStrEjer2;
	}

	public void setListStrEjer2(List<String> listStrEjer2) {
		this.listStrEjer2 = listStrEjer2;
	}

	public String getEjer3() {
		return ejer3;
	}

	public void setEjer3(String ejer3) {
		this.ejer3 = ejer3;
	}

	public List<String> getListStrEjer3() {
		return listStrEjer3;
	}

	public void setListStrEjer3(List<String> listStrEjer3) {
		this.listStrEjer3 = listStrEjer3;
	}

	public String getEjer4() {
		return ejer4;
	}

	public void setEjer4(String ejer4) {
		this.ejer4 = ejer4;
	}

	public List<String> getListStrEjer4() {
		return listStrEjer4;
	}

	public void setListStrEjer4(List<String> listStrEjer4) {
		this.listStrEjer4 = listStrEjer4;
	}

	public String getEjer5() {
		return ejer5;
	}

	public void setEjer5(String ejer5) {
		this.ejer5 = ejer5;
	}

	public List<String> getListStrEjer5() {
		return listStrEjer5;
	}

	public void setListStrEjer5(List<String> listStrEjer5) {
		this.listStrEjer5 = listStrEjer5;
	}

	public Long getRepeticion2() {
		return repeticion2;
	}

	public void setRepeticion2(Long repeticion2) {
		this.repeticion2 = repeticion2;
	}

	public Long getIntensidad2() {
		return intensidad2;
	}

	public void setIntensidad2(Long intensidad2) {
		this.intensidad2 = intensidad2;
	}

	public Long getRepeticion3() {
		return repeticion3;
	}

	public void setRepeticion3(Long repeticion3) {
		this.repeticion3 = repeticion3;
	}

	public Long getIntensidad3() {
		return intensidad3;
	}

	public void setIntensidad3(Long intensidad3) {
		this.intensidad3 = intensidad3;
	}

	public Long getRepeticion4() {
		return repeticion4;
	}

	public void setRepeticion4(Long repeticion4) {
		this.repeticion4 = repeticion4;
	}

	public Long getIntensidad4() {
		return intensidad4;
	}

	public void setIntensidad4(Long intensidad4) {
		this.intensidad4 = intensidad4;
	}

	public Long getRepeticion5() {
		return repeticion5;
	}

	public void setRepeticion5(Long repeticion5) {
		this.repeticion5 = repeticion5;
	}

	public Long getIntensidad5() {
		return intensidad5;
	}

	public void setIntensidad5(Long intensidad5) {
		this.intensidad5 = intensidad5;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Boolean getVisib() {
		return visib;
	}

	public void setVisib(Boolean visib) {
		this.visib = visib;
	}

	public Boolean getVisib2() {
		return visib2;
	}

	public void setVisib2(Boolean visib2) {
		this.visib2 = visib2;
	}

	public Boolean getVisib3() {
		return visib3;
	}

	public void setVisib3(Boolean visib3) {
		this.visib3 = visib3;
	}

	public Boolean getVisib4() {
		return visib4;
	}

	public void setVisib4(Boolean visib4) {
		this.visib4 = visib4;
	}

	public Boolean getVisib5() {
		return visib5;
	}

	public void setVisib5(Boolean visib5) {
		this.visib5 = visib5;
	}
	public List<String> getEtiquetas() {
		return etiquetas;
	}

	public void setEtiquetas(List<String> etiquetas) {
		this.etiquetas = etiquetas;
	}
}

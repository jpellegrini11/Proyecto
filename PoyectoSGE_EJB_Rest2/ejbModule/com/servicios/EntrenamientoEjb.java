package com.servicios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.component.UIData;

import com.dao.EjerciciosDao;
import com.dao.EntEjerDao;
import com.dao.EntrenadorDao;
import com.dao.EntrenamientoDao;
import com.entidades.Deportista;
import com.entidades.Ejercicios;
import com.entidades.EntEjer;
import com.entidades.Entrenamiento;
import com.entidades.Entrenador;
import com.entidades.Entrenamiento;
import com.utils.SessionUtils;


@Stateless
@LocalBean
public class EntrenamientoEjb {
	
	@EJB
	 EntrenamientoDao entrenamientoDao;
	@EJB
	 EntrenadorDao entrenadorDao;
	@EJB
	EjerciciosDao	ejerciciosDao;
	
	@EJB
	EntEjerDao entEjerDao;
	
	private Entrenamiento entrenamiento;
	private Entrenador entrenador; 
	private List<EntEjer> listEjercicios= new ArrayList<EntEjer>();
	private EntEjer ejercicios;
	private EntEjer ejercicios2;
	private EntEjer ejercicios3;
	private EntEjer ejercicios4;
	private EntEjer ejercicios5;
	private Long tiempoRealizarReal = (long)0;

	
	
public Boolean existeNomEnt(String entrenamiento) {
	 List<Entrenamiento> listEnt;
	Boolean bEnt=true;
	System.out.println("exiNom 1");
	 listEnt=entrenamientoDao.filtroEntrenamiento(entrenamiento);
	 System.out.println("exiNom 2");
	if(listEnt.size()>0) {
		bEnt=false;
	}
	return bEnt;
	
}	



public void guardarEntrenamientoEjb(String nombre, String deporte, List<String> etiquetas, 
		String ejer, Long repeticion, Long intensidad,Long tiempoRealizar,
		String ejer2, Long repeticion2, Long intensidad2,Long tiempoRealizar2, 
		String ejer3, Long repeticion3, Long intensidad3,Long tiempoRealizar3, 
		String ejer4, Long repeticion4, Long intensidad4,Long tiempoRealizar4, 
		String ejer5, Long repeticion5, Long intensidad5,Long tiempoRealizar5
		
		) throws SQLException {
	
	System.out.println("entro Ejb guardar entrenamiento");
	
	
	String usuario = (String) SessionUtils.getRequest().getSession(false).getAttribute("usuario");
	System.out.println(" guardar entrenamiento usuario  "+usuario);
	
	entrenador = entrenadorDao.obtenerEntrenadorIgual(usuario);
	
	
	System.out.println(" guardar entrenamiento usuario 2  "+entrenador.getUsuario());
		ejercicios = cargarEjercicios(repeticion,intensidad,tiempoRealizar,ejer);
	System.out.println("1 paso"+ejer);
		ejercicios2 = cargarEjercicios(repeticion2,intensidad2,tiempoRealizar2,ejer2);
		
		System.out.println("2 paso");
		
		ejercicios3 = cargarEjercicios(repeticion3,intensidad3,tiempoRealizar3,ejer3);
		System.out.println("3 paso");
		
		ejercicios4 = cargarEjercicios(repeticion4,intensidad4,tiempoRealizar4,ejer4);
		System.out.println("4 paso");
		
		ejercicios5 =cargarEjercicios(repeticion5,intensidad5,tiempoRealizar5,ejer5);
		
		System.out.println(" guardar entrenamiento paso metodo cargaEjer");
		
	


		
		Date fecha = new Date();
		entrenamiento=new Entrenamiento(nombre, deporte, tiempoRealizarReal,  etiquetas, entrenador, fecha);
		entrenamiento.setListEntEjer(listEjercicios);
		System.out.println(" guardar entrenamiento ");
		entrenamientoDao.guardarEntrenamiento(entrenamiento);
		
		if(ejercicios!=null) {
		ejercicios.setEntrenamiento(entrenamiento);
		}
		if(ejercicios2!=null) {
		ejercicios2.setEntrenamiento(entrenamiento);
		}
		if(ejercicios3!=null) {
		ejercicios3.setEntrenamiento(entrenamiento);
		}
		if(ejercicios4!=null) {
		ejercicios4.setEntrenamiento(entrenamiento);
		}
		if(ejercicios5!=null) {
		ejercicios5.setEntrenamiento(entrenamiento);
		}
		
		System.out.println(" guardar ejerc ");

		
}


// Metodo comprueba que los ejercicios tengan datos y carga atributos repet e intsi.Tambien cambia id 
//	y agrega a la listaEjer para guerda en la baseD
	public EntEjer cargarEjercicios(Long repeticion, Long intensidad,Long tiempoRealizar, String nomEjer) throws SQLException{
		
		
		
		System.out.println(" 1 idEjercicios :" +repeticion+intensidad+nomEjer);
		List<EntEjer> listEj=null;
		List<Ejercicios> listEj2=null;
		EntEjer e2 = null;
		System.out.println("  Ejercicios :");
		if(repeticion!=null && intensidad!=null) {
		
			if(repeticion>0 && intensidad>0 && tiempoRealizar>0){
			System.out.println(" entro if rep int :");
			listEj2= ejerciciosDao.filtroEjercicios(nomEjer);
			
			System.out.println(" e despues del DAa Nom "+listEj2.get(0).getNombre());
					
			 e2= new EntEjer (entrenador, listEj2.get(0), intensidad, repeticion,tiempoRealizar);
			 
			System.out.println(" e2 despues del DAO "+e2.getEjercicios().getNombre());		
			listEjercicios.add(e2);
			entEjerDao.guardarEntEjer(e2);
			tiempoRealizarReal = tiempoRealizarReal+tiempoRealizar;
			System.out.println("tiempoRealizarReal ="+tiempoRealizarReal);
			
		}else {
			
			e2=null;
		}
			
		}
		return e2;
			
	}
	

public void actualizarEntrenaEjb(Entrenamiento entrenamiento) throws SQLException {
	this.entrenamientoDao.actualizarEntrenamiento(entrenamiento);
}

public void borrarEntrenaEjb(Long idEntrenamiento) throws SQLException {
	entrenamientoDao.borrarEntrenamiento(idEntrenamiento);
}

public Entrenamiento obtenerEntrenaEjb(Long idEntrenamiento) {
	return this.entrenamientoDao.obtenerEntrenamiento(idEntrenamiento);
}

public List<Entrenamiento> obternerTodosEjb() {
	return entrenamientoDao.obternerTodos();
}

}

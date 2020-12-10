
package com.bean;


import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.*;

import com.dao.ResultadosEntoDao;
import com.entidades.Deportista;
import com.entidades.Entrenador;
import com.entidades.Entrenamiento;
import com.entidades.ResultadosEnto;
import com.servicios.DeportistaEjb;
import com.servicios.EntrenadorEjb;
import com.servicios.EntrenamientoEjb;
import com.utils.SessionUtils;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Named("chartRendimientoPrueba")
@ManagedBean
@SessionScoped
public class ChartRendimientoPrueba implements Serializable {

	private static final long serialVersionUID = 1L;
   private LineChartModel lineModelNadar;
   private LineChartModel lineModelCiclismo;
   private LineChartModel lineModelCorrer;
	EntrenamientoEjb entrenamientoEjb;
	List<Entrenamiento> listaEntrenamientos = new ArrayList<>();
	List<Double> listaEntrenamientosCiclismo = new ArrayList<>();
	List<Double> listaEntrenamientosCorrer = new ArrayList<>();
	List<Double> listaEntrenamientosNadar = new ArrayList<>();

	@EJB
	ResultadosEntoDao resultadosEntoDao;
	@EJB
	DeportistaEjb deportistaEjb;
	@EJB
	EntrenadorEjb entrenadorEjb;
	private List<ResultadosEnto> listResultadosEnto2;
	private ArrayList<ResultadosEnto> listResultadosEnto;
	private Deportista dep;
	private String usuario;
	private String nombre;

	
   @PostConstruct
   public void init() {
	   
	   cargarGraficosRend();
       createLineModels();
   }

   public void cargarGraficosRend() {
   	
   	System.out.println("**** metodo cargaGraficosRend ChartRendimientoPrueba");
   	 usuario = null;
		if(SessionUtils.getRequest().getRequestURI().contains("/ProyectoSGE_Cliente_Rest1/entrenador/")) {
			
			System.out.println("if 1 mostrarGraf");
			usuario = (String) SessionUtils.getRequest().getSession(false).getAttribute("usuario");
			try {
				Entrenador ent = entrenadorEjb.obtenerEntrenadorIgual(usuario);
				System.out.println("mostrarGrafico usuDep ="+ent.getUsuDep());
				usuario=ent.getUsuDep();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   	}else {
	
   	
   	 usuario = (String) SessionUtils.getRequest().getSession(false).getAttribute("usuario");
   	
   	}
   	if(resultadosEntoDao.filtroResultadosEntoUsu(usuario).size()>0) {
   		
	    	
   		filtroDatosEnto(usuario);
       
   	
   	}
   	if(listResultadosEnto.size()>0) {
   		   
   	for (ResultadosEnto resultadosEnto : listResultadosEnto) {
   		
			String nomEnto=resultadosEnto.getDeporte();
			Double vel = resultadosEnto.getVelocidad();
			
   		if(nomEnto.equals("CYCLING")) {
   			listaEntrenamientosCiclismo.add(vel);
   		}else if(nomEnto.equals("RUNNING")) {
   			System.out.println("nomEnto.equals(\"RUNNING\")");
   			listaEntrenamientosCorrer.add(vel);
   			System.out.println("nomEnto.equals(\"RUNNING\")"+listaEntrenamientosCorrer.size());
   		}else {
   			listaEntrenamientosNadar.add(vel);
   		}
   			
		}
   	}
   }
   
   public void filtroDatosEnto(String usuario) {
		
		System.out.println("**** Metodo filtoDatosEnto ChartRendimientoPrueba");
		listResultadosEnto2 = resultadosEntoDao.filtroResultadosEntoUsu(usuario);
   	listResultadosEnto= new ArrayList<ResultadosEnto>();
   	
   	System.out.println(listResultadosEnto2.size());
   	System.out.println(listResultadosEnto.size());
   	
   	for (ResultadosEnto listR1 : listResultadosEnto2) {
			
   		if(listR1.getPolarFlow()>0) {
   			
   			listResultadosEnto.add(listR1);
   			
   		}
		}
   	System.out.println(listResultadosEnto2.size());
   	System.out.println(listResultadosEnto.size());
	}
   
   //VALORES
   private LineChartModel initCategoryModelNadar() {
   	
   	LineChartModel modelNadar = new LineChartModel();
      	
//-------------------DESCOMENTAR TODO ESTO PARA USARLO CON LA BASE DE DATOS -----------------------------    	
   	
//   	listaEntrenamientos = entrenamientoEjb.obternerTodosEjb();
		
//   	for(int i = 0; i < listaEntrenamientos.size(); i ++) {
//   		if(listaEntrenamientos.get(i).getNombre().equals("CYCLING")) {
//   			listaEntrenamientosCiclismo.add(listaEntrenamientos.get(i).getRitmo());
//   		}else if(listaEntrenamientos.get(i).getNombre().equals("RUNNING")) {
//   			listaEntrenamientosCorrer.add(listaEntrenamientos.get(i).getRitmo());
//   		}else {
//   			listaEntrenamientosNadar.add(listaEntrenamientos.get(i).getRitmo());
//   		}
//   	}
		
   	//-------------------PARA PROBAR GRAFICAS CON DATOS, DESPUES BORRAR -----------------------------    
//		listaEntrenamientosNadar.add((double) 2);
//		listaEntrenamientosNadar.add((double) 4);
//		listaEntrenamientosNadar.add((double) 3);
//		listaEntrenamientosNadar.add((double) 4);
//		listaEntrenamientosNadar.add((double) 3);
//		listaEntrenamientosNadar.add((double) 2);
//		listaEntrenamientosNadar.add((double) 2);
//		listaEntrenamientosNadar.add((double) 2);
//		listaEntrenamientosNadar.add((double) 4);
//		listaEntrenamientosNadar.add((double) 4);
//		listaEntrenamientosNadar.add((double) 4);
//		listaEntrenamientosNadar.add((double) 4);
//		listaEntrenamientosNadar.add((double) 2);
//		listaEntrenamientosNadar.add((double) 2);
//		listaEntrenamientosNadar.add((double) 2);
//		listaEntrenamientosNadar.add((double) 3);
//		listaEntrenamientosNadar.add((double) 3);
//		listaEntrenamientosNadar.add((double) 2);
//		listaEntrenamientosNadar.add((double) 2);
//		listaEntrenamientosNadar.add((double) 2);
		
//		ChartSeries nombreUsuario = new ChartSeries();
//		nombreUsuario.setLabel("nombreUsuario");
//		ChartSeries minimoEsperado = new ChartSeries();
//   	minimoEsperado.setLabel("Ritmo mínimo esperado");
		
		ChartSeries nombreUsuarioNadar = new ChartSeries();
		nombreUsuarioNadar.setLabel("Ritmo");
		ChartSeries minimoEsperadoNadar = new ChartSeries();
   	minimoEsperadoNadar.setLabel("Ritmo mínimo esperado");
		
   	double sumaRitmo = 0;
   	double sumCuad = 0;
   	String s="";
   	if(listaEntrenamientosNadar.size() >= 20){
       	for(int i = listaEntrenamientosNadar.size() - 20; i <listaEntrenamientosNadar.size(); i++) {
       		nombreUsuarioNadar.set(i + 1, listaEntrenamientosNadar.get(i));
       		sumaRitmo = sumaRitmo + listaEntrenamientosNadar.get(i);
       	}
       	double promedioRitmo = sumaRitmo/listaEntrenamientosNadar.size();
       	
       	for(int i = listaEntrenamientosNadar.size() - 20; i <listaEntrenamientosNadar.size(); i++) {
       		sumCuad = sumCuad + Math.pow(promedioRitmo - listaEntrenamientosNadar.get(i), 2);
       	}
   		double dosDesvEst = 2*Math.sqrt(sumCuad/listaEntrenamientosNadar.size());
   		System.out.println(" Prom    " + promedioRitmo);
   		System.out.println(" Desv    " + dosDesvEst);
   		for(int i = listaEntrenamientosNadar.size() - 20; i <listaEntrenamientosNadar.size(); i++) {
   			minimoEsperadoNadar.set(i + 1, promedioRitmo - dosDesvEst);
   		}
       	modelNadar.addSeries(nombreUsuarioNadar);
       	modelNadar.addSeries(minimoEsperadoNadar);
       	return modelNadar;
   	}
       
		return modelNadar;
   }

   //VALORES
   private LineChartModel initCategoryModelCiclismo() {
    	
   	LineChartModel modelCiclismo = new LineChartModel();
    	
//   	listaEntrenamientos = entrenamientoEjb.obternerTodosEjb();
		
//   	for(int i = 0; i < listaEntrenamientos.size(); i ++) {
//   		if(listaEntrenamientos.get(i).getNombre().equals("CYCLING")) {
//   			listaEntrenamientosCiclismo.add(listaEntrenamientos.get(i).getRitmo());
//   	}
   	
   	
//   	
//		listaEntrenamientosCiclismo.add((double) 30);
//		listaEntrenamientosCiclismo.add((double) 40);
//		listaEntrenamientosCiclismo.add((double) 32);
//		listaEntrenamientosCiclismo.add((double) 34);
//		listaEntrenamientosCiclismo.add((double) 32);
//		listaEntrenamientosCiclismo.add((double) 32);
//		listaEntrenamientosCiclismo.add((double) 32);
//		listaEntrenamientosCiclismo.add((double) 32);
//		listaEntrenamientosCiclismo.add((double) 34);
//		listaEntrenamientosCiclismo.add((double) 34);
//		listaEntrenamientosCiclismo.add((double) 34);
//		listaEntrenamientosCiclismo.add((double) 34);
//		listaEntrenamientosCiclismo.add((double) 22);
//		listaEntrenamientosCiclismo.add((double) 32);
//		listaEntrenamientosCiclismo.add((double) 32);
//		listaEntrenamientosCiclismo.add((double) 31);
//		listaEntrenamientosCiclismo.add((double) 32);
//		listaEntrenamientosCiclismo.add((double) 23);
//		listaEntrenamientosCiclismo.add((double) 32);
//		listaEntrenamientosCiclismo.add((double) 32);
		
	   	ChartSeries nombreUsuarioCiclismo = new ChartSeries();
   	nombreUsuarioCiclismo.setLabel("Ritmo");
		ChartSeries minimoEsperadoCiclismo = new ChartSeries();
		minimoEsperadoCiclismo.setLabel("Ritmo mínimo esperado");
		
		double sumaRitmo = 0;
   	double sumCuad = 0;
   	
	   	if(listaEntrenamientosCiclismo.size() >= 20){
   		sumaRitmo = 0;
   		sumCuad = 0;
       	for(int i = listaEntrenamientosCiclismo.size() - 20; i <listaEntrenamientosCiclismo.size(); i++) {
       		nombreUsuarioCiclismo.set(i + 1, listaEntrenamientosCiclismo.get(i));
       		sumaRitmo = sumaRitmo + listaEntrenamientosCiclismo.get(i);
       		System.out.println("i " + i + " sumaRitmo " + sumaRitmo);
       	}
       	double promedioRitmo = sumaRitmo/listaEntrenamientosCiclismo.size();
       	
       	for(int i = listaEntrenamientosCiclismo.size() - 20; i <listaEntrenamientosCiclismo.size(); i++) {
       		sumCuad = sumCuad + Math.pow(promedioRitmo - listaEntrenamientosCiclismo.get(i), 2);
       	}
   		double dosDesvEst = 2*Math.sqrt(sumCuad/listaEntrenamientosCiclismo.size());
   		System.out.println(" Prom    " + promedioRitmo);
   		System.out.println(" Desv    " + dosDesvEst);
   		for(int i = listaEntrenamientosCiclismo.size() - 20; i <listaEntrenamientosCiclismo.size(); i++) {
   			minimoEsperadoCiclismo.set(i + 1, promedioRitmo - dosDesvEst);
   		}
       	modelCiclismo.addSeries(nombreUsuarioCiclismo);
       	modelCiclismo.addSeries(minimoEsperadoCiclismo);
       	return modelCiclismo;
   	}
    	return modelCiclismo;
   }
   
   private LineChartModel initCategoryModelCorrer() {
   	
   	LineChartModel modelCorrer = new LineChartModel();
   	
//-------------------DESCOMENTAR TODO ESTO PARA USARLO CON LA BASE DE DATOS -----------------------------    	
   	
//   	listaEntrenamientos = entrenamientoEjb.obternerTodosEjb();
		
//   	for(int i = 0; i < listaEntrenamientos.size(); i ++) {
//   		if(listaEntrenamientos.get(i).getNombre().equals("RUNNING")) {
//   			listaEntrenamientosCorrer.add(listaEntrenamientos.get(i).getRitmo());
//   	}
   	
//		listaEntrenamientosCorrer.add((double) 40);
//		listaEntrenamientosCorrer.add((double) 2.5);
//		listaEntrenamientosCorrer.add((double) 40);
//		listaEntrenamientosCorrer.add((double) 40);
//		listaEntrenamientosCorrer.add((double) 2.5);
//		listaEntrenamientosCorrer.add((double) 40.2);
//		listaEntrenamientosCorrer.add((double) 40.2);
//		listaEntrenamientosCorrer.add((double) 40.2);
//		listaEntrenamientosCorrer.add((double) 40.4);
//		listaEntrenamientosCorrer.add((double) 40.5);
//		listaEntrenamientosCorrer.add((double) 40.7);
//		listaEntrenamientosCorrer.add((double) 40);
//		listaEntrenamientosCorrer.add((double) 2.2);
//		listaEntrenamientosCorrer.add((double) 2.2);
//		listaEntrenamientosCorrer.add((double) 2.2);
//		listaEntrenamientosCorrer.add((double) 2.3);
//		listaEntrenamientosCorrer.add((double) 2.3);
//		listaEntrenamientosCorrer.add((double) 2.2);
//		listaEntrenamientosCorrer.add((double) 2.2);
//		listaEntrenamientosCorrer.add((double) 2.2);
		
   	ChartSeries nombreUsuarioCorrer = new ChartSeries();
		nombreUsuarioCorrer.setLabel("Ritmo");
		ChartSeries minimoEsperadoCorrer = new ChartSeries();
   	minimoEsperadoCorrer.setLabel("Ritmo mínimo esperado");
   	
   	double sumaRitmo = 0;
   	double sumCuad = 0;
   		System.out.println("Antes del if 1 ="+listaEntrenamientosCorrer);
   	if(listaEntrenamientosCorrer.size() >= 20){
   		sumaRitmo = 0;
   		sumCuad = 0;
       	for(int i = listaEntrenamientosCorrer.size() - 20; i <listaEntrenamientosCorrer.size(); i++) {
       		nombreUsuarioCorrer.set(i + 1, listaEntrenamientosCorrer.get(i));
       		sumaRitmo = sumaRitmo + listaEntrenamientosCorrer.get(i);
       		System.out.println(listaEntrenamientosCorrer.get(i));
       	}
       	System.out.println("Antes del if 1 ="+listaEntrenamientosCorrer);
       	double promedioRitmo = sumaRitmo/listaEntrenamientosCorrer.size();
       	
       	for(int i = listaEntrenamientosCorrer.size() - 20; i <listaEntrenamientosCorrer.size(); i++) {
       		sumCuad = sumCuad + Math.pow(promedioRitmo - listaEntrenamientosCorrer.get(i), 2);
       	}
   		double dosDesvEst = 2*Math.sqrt(sumCuad/listaEntrenamientosCorrer.size());
   		System.out.println(" Prom    " + promedioRitmo);
   		System.out.println(" Desv    " + dosDesvEst);
   		for(int i = listaEntrenamientosCorrer.size() - 20; i <listaEntrenamientosCorrer.size(); i++) {
   			minimoEsperadoCorrer.set(i + 1, promedioRitmo - dosDesvEst);
   		}
   		System.out.println("Antes del if 1 ="+listaEntrenamientosCorrer);
       	modelCorrer.addSeries(nombreUsuarioCorrer);
       	modelCorrer.addSeries(minimoEsperadoCorrer);
       	return modelCorrer;
   	}
   	return modelCorrer;
   }
   
   
   private void createLineModels() {
   	
       //AJUSTE DE EJES
       lineModelNadar = initCategoryModelNadar();
       Axis yAxis;
	if(listaEntrenamientosNadar.size()>0) {
       lineModelNadar.setTitle("Natación: Ritmo medio por entrenamiento");
       lineModelNadar.setLegendPosition("e");
       lineModelNadar.setShowPointLabels(false);
       lineModelNadar.getAxes().put(AxisType.X, new CategoryAxis("Entrenamiento"));
       yAxis = lineModelNadar.getAxis(AxisType.Y);
       yAxis.setLabel("Ritmo medio (min/100m)");
       yAxis.setMin(0);
       yAxis.setMax(Collections.max(listaEntrenamientosNadar) + 0.5);
       }
	
     //AJUSTE DE EJES
       lineModelCiclismo = initCategoryModelCiclismo();
       if(listaEntrenamientosCiclismo.size()>0) {
       lineModelCiclismo.setTitle("Ciclismo: Ritmo medio por entrenamiento");
       lineModelCiclismo.setLegendPosition("e");
       lineModelCiclismo.setShowPointLabels(false);
       lineModelCiclismo.getAxes().put(AxisType.X, new CategoryAxis("Entrenamiento"));
       yAxis = lineModelCiclismo.getAxis(AxisType.Y);
       yAxis.setLabel("Ritmo medio (km/h)");
       yAxis.setMin(Collections.min(listaEntrenamientosCiclismo) - 5);
       yAxis.setMax(Collections.max(listaEntrenamientosCiclismo) + 1);
       }
       
     //AJUSTE DE EJES
       lineModelCorrer = initCategoryModelCorrer();
       if(listaEntrenamientosCorrer.size()>0) {
       lineModelCorrer.setTitle("Carrera: Ritmo medio por entrenamiento");
       lineModelCorrer.setLegendPosition("e");
       lineModelCorrer.setShowPointLabels(false);
       lineModelCorrer.getAxes().put(AxisType.X, new CategoryAxis("Entrenamiento"));
       yAxis = lineModelCorrer.getAxis(AxisType.Y);
       yAxis.setLabel("Ritmo medio (min/km)");
       yAxis.setMin(0);
       yAxis.setMax(Collections.max(listaEntrenamientosCorrer) + 0.5);
       }
   }
   
   public void itemSelect(ItemSelectEvent event) {
       FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
               "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

       FacesContext.getCurrentInstance().addMessage(null, msg);
   }

   
   public LineChartModel getLineModelNadar() {
       return lineModelNadar;
   }
 
   public LineChartModel getLineModelCiclismo() {
       return lineModelCiclismo;
   }
 
   public LineChartModel getLineModelCorrer() {
       return lineModelCorrer;
   }

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
} 

}
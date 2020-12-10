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
import com.servicios.ResultadosEntoEjb;
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

@Named("chartRendimiento")
@ManagedBean
@SessionScoped
public class ChartRendimiento implements Serializable {

	private static final long serialVersionUID = 1L;
    private LineChartModel lineModelNadar =  new LineChartModel() ;
    private LineChartModel lineModelCiclismo = new LineChartModel();
    private LineChartModel lineModelCorrer = new LineChartModel();
    private List<LineChartModel> listLineChartModel= new ArrayList<LineChartModel>();
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

    @PostConstruct
    public void init() {
    	
//    	if(SessionUtils.getRequest().getRequestURI().contains("/ProyectoSGE_Cliente_Rest1/entrenador/")) {
//    		System.out.println("if SessionUtils.getRequest().getRequestURI() No carga graficos");
//			
//    	}else {
//	
    		cargarGraficosRend();
    	
//    	}
        createLineModels();
    }
    
    public void cargarGraficosRend() {
    	
    	System.out.println("******** Metodo cargaGraficosRend ChartRendimiento");
    	String usuario = null;
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
		
		System.out.println("***** Metodo filtoDatosEnto de ChartRendimiento");
		listResultadosEnto2 = resultadosEntoDao.filtroResultadosEntoUsu(usuario);
    	listResultadosEnto= new ArrayList<ResultadosEnto>();
    	
    	System.out.println(listResultadosEnto2.size());
    	System.out.println(listResultadosEnto.size());
    	
    	for (ResultadosEnto listR1 : listResultadosEnto2) {
			System.out.println("listR1.getPolarFlow ="+listR1.getPolarFlow());
    		if(listR1.getPolarFlow()> (long)0) {
    			    			
    			listResultadosEnto.add(listR1);
    			System.out.println("getPolarFlow "+listResultadosEnto.size());
    		}
		}
    	System.out.println(listResultadosEnto2.size());
    	System.out.println(listResultadosEnto.size());
	}

   

    //VALORES
    private LineChartModel initCategoryModel(int num) {
    	
//    	LineChartModel modelNadar = new LineChartModel();
//    	LineChartModel modelCiclismo = new LineChartModel();
//    	LineChartModel modelCorrer = new LineChartModel();

    	LineChartModel modelNadar = new LineChartModel();

//-------------------DESCOMENTAR TODO ESTO PARA USARLO CON LA BASE DE DATOS -----------------------------    	
    	
//    	listaEntrenamientos = entrenamientoEjb.obternerTodosEjb();
		
//    	for(int i = 0; i < listaEntrenamientos.size(); i ++) {
//    		if(listaEntrenamientos.get(i).getNombre().equals("CYCLING")) {
//    			listaEntrenamientosCiclismo.add(listaEntrenamientos.get(i).getRitmo());
//    		}else if(listaEntrenamientos.get(i).getNombre().equals("RUNNING")) {
//    			listaEntrenamientosCorrer.add(listaEntrenamientos.get(i).getRitmo());
//    		}else {
//    			listaEntrenamientosNadar.add(listaEntrenamientos.get(i).getRitmo());
//    		}
//    	}
		
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
//		
//		listaEntrenamientosCorrer.add((double) 2);
//		listaEntrenamientosCorrer.add((double) 4);
//		listaEntrenamientosCorrer.add((double) 3);
//		listaEntrenamientosCorrer.add((double) 4);
//		listaEntrenamientosCorrer.add((double) 3);
//		listaEntrenamientosCorrer.add((double) 2);
//		listaEntrenamientosCorrer.add((double) 2);
//		listaEntrenamientosCorrer.add((double) 2);
//		listaEntrenamientosCorrer.add((double) 4);
//		listaEntrenamientosCorrer.add((double) 4);
//		listaEntrenamientosCorrer.add((double) 4);
//		listaEntrenamientosCorrer.add((double) 4);
//		listaEntrenamientosCorrer.add((double) 2);
//		listaEntrenamientosCorrer.add((double) 2);
//		listaEntrenamientosCorrer.add((double) 2);
//		listaEntrenamientosCorrer.add((double) 3);
//		listaEntrenamientosCorrer.add((double) 3);
//		listaEntrenamientosCorrer.add((double) 2);
//		listaEntrenamientosCorrer.add((double) 2);
//		listaEntrenamientosCorrer.add((double) 2);
//		
//		listaEntrenamientosCiclismo.add((double) 2);
//		listaEntrenamientosCiclismo.add((double) 4);
//		listaEntrenamientosCiclismo.add((double) 3);
//		listaEntrenamientosCiclismo.add((double) 4);
//		listaEntrenamientosCiclismo.add((double) 3);
//		listaEntrenamientosCiclismo.add((double) 2);
//		listaEntrenamientosCiclismo.add((double) 2);
//		listaEntrenamientosCiclismo.add((double) 2);
//		listaEntrenamientosCiclismo.add((double) 4);
//		listaEntrenamientosCiclismo.add((double) 4);
//		listaEntrenamientosCiclismo.add((double) 4);
//		listaEntrenamientosCiclismo.add((double) 4);
//		listaEntrenamientosCiclismo.add((double) 2);
//		listaEntrenamientosCiclismo.add((double) 2);
//		listaEntrenamientosCiclismo.add((double) 2);
//		listaEntrenamientosCiclismo.add((double) 3);
//		listaEntrenamientosCiclismo.add((double) 3);
//		listaEntrenamientosCiclismo.add((double) 2);
//		listaEntrenamientosCiclismo.add((double) 2);
//		listaEntrenamientosCiclismo.add((double) 2);
		
//		ChartSeries nombreUsuario = new ChartSeries();
//		nombreUsuario.setLabel("nombreUsuario");
//		ChartSeries minimoEsperado = new ChartSeries();
//    	minimoEsperado.setLabel("Ritmo mínimo esperado");
		
    	ChartSeries nombreUsuarioNadar = null;
		ChartSeries minimoEsperadoNadar = null;
		if(num==1) {
		 nombreUsuarioNadar = new ChartSeries();
		nombreUsuarioNadar.setLabel("nombreUsuario");
		 minimoEsperadoNadar = new ChartSeries();
    	minimoEsperadoNadar.setLabel("Ritmo mínimo esperado");
    	}
    	
    	ChartSeries nombreUsuarioCiclismo = null;
		ChartSeries minimoEsperadoCiclismo = null;
		if(num==2) {
    	 nombreUsuarioCiclismo = new ChartSeries();
    	nombreUsuarioCiclismo.setLabel("nombreUsuario");
		 minimoEsperadoCiclismo = new ChartSeries();
		minimoEsperadoCiclismo.setLabel("Ritmo mínimo esperado");
    	}
		
    	ChartSeries nombreUsuarioCorrer = null;
		ChartSeries minimoEsperadoCorrer= null;
		if(num==3) {
    	 nombreUsuarioCorrer = new ChartSeries();
		nombreUsuarioCorrer.setLabel("nombreUsuario");
		 minimoEsperadoCorrer = new ChartSeries();
    	minimoEsperadoCorrer.setLabel("Ritmo mínimo esperado");
    	}
    	double sumaRitmo = 0;
			
    	double sumCuad = 0;
    	
    	if(listaEntrenamientosNadar.size() >= 20 && num==1){
    		System.out.println("listaEntrenamientosNadar.size() ="+listaEntrenamientosNadar.size());
        	for(int i = listaEntrenamientosNadar.size() - 20; i <listaEntrenamientosNadar.size(); i++) {
        		nombreUsuarioNadar.set(i + 1, listaEntrenamientosNadar.get(i));
        		sumaRitmo = sumaRitmo + listaEntrenamientosNadar.get(i);
        	}
        	double promedioRitmo = sumaRitmo/listaEntrenamientosNadar.size();
        	
        	for(int i = listaEntrenamientosNadar.size() - 20; i <listaEntrenamientosNadar.size(); i++) {
        		sumCuad = sumCuad + Math.pow(promedioRitmo - listaEntrenamientosNadar.get(i), 2);
        		System.out.println("sumCuad Nada ="+sumCuad);
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
        
    	if(listaEntrenamientosCiclismo.size() >= 20 && num==2){
    		
    		System.out.println("listaEntrenamientosCiclismo.size()= "+listaEntrenamientosCiclismo.size());
    		sumaRitmo = 0;
    		sumCuad = 0;
        	for(int i = listaEntrenamientosCiclismo.size() - 20; i <listaEntrenamientosCiclismo.size(); i++) {
        		nombreUsuarioCiclismo.set(i + 1, listaEntrenamientosCiclismo.get(i));
        		sumaRitmo = sumaRitmo + listaEntrenamientosCiclismo.get(i);
        	}
        	double promedioRitmo = sumaRitmo/listaEntrenamientosCiclismo.size();
        	
        	for(int i = listaEntrenamientosCiclismo.size() - 20; i <listaEntrenamientosCiclismo.size(); i++) {
        		sumCuad = sumCuad + Math.pow(promedioRitmo - listaEntrenamientosCiclismo.get(i), 2);
        		System.out.println("sumCuad bici ="+sumCuad);
        	}
    		double dosDesvEst = 2*Math.sqrt(sumCuad/listaEntrenamientosCiclismo.size());
    		System.out.println(" Prom    " + promedioRitmo);
    		System.out.println(" Desv    " + dosDesvEst);
    		for(int i = listaEntrenamientosCiclismo.size() - 20; i <listaEntrenamientosCiclismo.size(); i++) {
    			minimoEsperadoCiclismo.set(i + 1, promedioRitmo - dosDesvEst);
    		}
    		modelNadar.addSeries(nombreUsuarioCiclismo);
    		modelNadar.addSeries(minimoEsperadoCiclismo);
        	return modelNadar;
    	}
    	
    	if(listaEntrenamientosCorrer.size() >= 20 && num==3){
    		
    		System.out.println("listaEntrenamientosCorrer.size() = "+listaEntrenamientosCorrer.size());
    		sumaRitmo = 0;
    		sumCuad = 0;
        	for(int i = listaEntrenamientosCorrer.size() - 20; i <listaEntrenamientosCorrer.size(); i++) {
        		nombreUsuarioCorrer.set(i + 1, listaEntrenamientosCorrer.get(i));
        		sumaRitmo = sumaRitmo + listaEntrenamientosCorrer.get(i);
        	}
        	double promedioRitmo = sumaRitmo/listaEntrenamientosCorrer.size();
        	
        	for(int i = listaEntrenamientosCorrer.size() - 20; i <listaEntrenamientosCorrer.size(); i++) {
        		sumCuad = sumCuad + Math.pow(promedioRitmo - listaEntrenamientosCorrer.get(i), 2);
        		System.out.println("sumCuad Correr ="+sumCuad);
        	}
    		double dosDesvEst = 2*Math.sqrt(sumCuad/listaEntrenamientosCorrer.size());
    		System.out.println(" Prom    " + promedioRitmo);
    		System.out.println(" Desv    " + dosDesvEst);
    		for(int i = listaEntrenamientosCorrer.size() - 20; i <listaEntrenamientosCorrer.size(); i++) {
    			minimoEsperadoCorrer.set(i + 1, promedioRitmo - dosDesvEst);
    		}
    		modelNadar.addSeries(nombreUsuarioCorrer);
    		modelNadar.addSeries(minimoEsperadoCorrer);
        	return modelNadar;
    	}
    	
		return modelNadar;
    }

    private void createLineModels() {
    	
        Axis yAxis;
		//AJUSTE DE EJES
    	
        lineModelNadar = initCategoryModel(1);
        if(listaEntrenamientosNadar.size()>0) {
        	
        System.out.println("Natación initCategoryModel");
        	
        lineModelNadar.setTitle("Natación: Ritmo medio por entrenamiento");
        lineModelNadar.setLegendPosition("e");
        lineModelNadar.setShowPointLabels(false);
 //       lineModelNadar.getAxes().put(AxisType.X, new CategoryAxis("Entrenamiento"));
        yAxis = lineModelNadar.getAxis(AxisType.Y);
        yAxis.setLabel("Ritmo medio (min/100m)");
        yAxis.setMin(0);
        yAxis.setMax(Collections.max(listaEntrenamientosNadar) + 0.5);
    	}
    	
      //AJUSTE DE EJES
    
        lineModelCiclismo = initCategoryModel(2);
    	if(listaEntrenamientosCiclismo.size()>0) {
    		
    		System.out.println("Bici initCategoryModel");
    		
        lineModelCiclismo.setTitle("Ciclismo: Ritmo medio por entrenamiento");
        lineModelCiclismo.setLegendPosition("e");
        lineModelCiclismo.setShowPointLabels(false);
        
//        lineModelCiclismo.getAxes().put(AxisType.X, new CategoryAxis("Entrenamiento"));
        yAxis = lineModelCiclismo.getAxis(AxisType.Y);
        yAxis.setLabel("Ritmo medio (km/h)");
        yAxis.setMin(Collections.min(listaEntrenamientosCiclismo) - 5);
        yAxis.setMax(Collections.max(listaEntrenamientosCiclismo) + 1);
    	}
    	
      //AJUSTE DE EJES
    	
        lineModelCorrer = initCategoryModel(3);
        System.out.println("  lineModelCorrer = initCategoryModel(3);"+listaEntrenamientosCorrer.size());
        if(listaEntrenamientosCorrer.size()>0) {
        	
        	System.out.println("Correr initCategoryModel");
        	
        lineModelCorrer.setTitle("Carrera: Ritmo medio por entrenamiento");
        lineModelCorrer.setLegendPosition("e");
        lineModelCorrer.setShowPointLabels(false);
//        lineModelCorrer.getAxes().put(AxisType.X, new CategoryAxis("Entrenamiento"));
        yAxis = lineModelCorrer.getAxis(AxisType.Y);
        yAxis.setLabel("Ritmo medio (min/km)");
        yAxis.setMin(0);
        yAxis.setMax(Collections.max(listaEntrenamientosCorrer) + 1);
    	}
       
        listLineChartModel= new ArrayList<LineChartModel>();
        listLineChartModel.add(lineModelCiclismo);
        listLineChartModel.add(lineModelCorrer);
        listLineChartModel.add(lineModelNadar);
        
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

	public List<LineChartModel> getListLineChartModel() {
		return listLineChartModel;
	}

	public void setListLineChartModel(List<LineChartModel> listLineChartModel) {
		this.listLineChartModel = listLineChartModel;
	}

	public Deportista getDep() {
		return dep;
	}

	public void setDep(Deportista dep) {
		this.dep = dep;
	}

}

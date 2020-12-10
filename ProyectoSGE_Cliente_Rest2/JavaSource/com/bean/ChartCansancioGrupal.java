
package com.bean;


import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.*;

import com.entidades.Entrenamiento;
import com.servicios.EntrenamientoEjb;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Named("chartCansancioGrupal")
@ManagedBean
@SessionScoped
public class ChartCansancioGrupal implements Serializable {

	private static final long serialVersionUID = 1L;
    private LineChartModel lineModelNadar;
    private LineChartModel lineModelCiclismo;
    private LineChartModel lineModelCorrer;
	EntrenamientoEjb entrenamientoEjb;
	List<Entrenamiento> listaEntrenamientos = new ArrayList<>();
	List<String> listaUsuarios = new ArrayList<>();
	List<Integer> listaEntrenamientosCiclismo = new ArrayList<>();
	List<Integer> listaEntrenamientosCorrer = new ArrayList<>();
	List<Integer> listaEntrenamientosNadar = new ArrayList<>();
	
	
    @PostConstruct
    public void init() {
    	
    	//PASO 1
    	
        createLineModels();
    }

   
    //VALORES
    private LineChartModel initCategoryModelNadar() {
    	
    	LineChartModel modelNadar = new LineChartModel();
       	
//-------------------DESCOMENTAR TODO ESTO PARA USARLO CON LA BASE DE DATOS -----------------------------    	
    	
//    	listaEntrenamientos = entrenamientoEjb.obternerTodosEjb();
		
//    	for(int i = 0; i < listaEntrenamientos.size(); i ++) {
//    		if(istaEntrenamientos.get(i).getNombre().equals("SWIMMING"){
//    	listaEntrenamientosNadar.add(listaEntrenamientos.get(i).getFC());
//    		}
//    	}
		
    	//-------------------PARA PROBAR GRAFICAS CON DATOS, DESPUES BORRAR -----------------------------    
    	listaEntrenamientosNadar.add((Integer) 122);
		listaEntrenamientosNadar.add((Integer) 124);
		listaEntrenamientosNadar.add((Integer) 113);
		listaEntrenamientosNadar.add((Integer) 144);
		listaEntrenamientosNadar.add((Integer) 143);
		listaEntrenamientosNadar.add((Integer) 132);
		listaEntrenamientosNadar.add((Integer) 134);

		listaEntrenamientosNadar.add((Integer) 115);
		listaEntrenamientosNadar.add((Integer) 116);
		listaEntrenamientosNadar.add((Integer) 122);
		listaEntrenamientosNadar.add((Integer) 152);
		listaEntrenamientosNadar.add((Integer) 153);
		listaEntrenamientosNadar.add((Integer) 153);
		listaEntrenamientosNadar.add((Integer) 140);
		listaEntrenamientosNadar.add((Integer) 140);
		listaEntrenamientosNadar.add((Integer) 135);
	

		
//		ChartSeries nombreUsuario = new ChartSeries();
//		nombreUsuario.setLabel("nombreUsuario");
		for(int j = 0; j < listaUsuarios.size(); j++) {
		ChartSeries nombreUsuarioNadar = new ChartSeries();
		nombreUsuarioNadar.setLabel(listaUsuarios.get(j));
    	
        for(int i = 0; i <listaEntrenamientosNadar.size(); i++) {
        		nombreUsuarioNadar.set(i + 1, listaEntrenamientosNadar.get(i)
//        				.getFC()
        		);
        	}
        modelNadar.addSeries(nombreUsuarioNadar);
		}
        return modelNadar;
    }

    //VALORES
    private LineChartModel initCategoryModelCiclismo() {
     	
    	LineChartModel modelCiclismo = new LineChartModel();
     	
//    	listaEntrenamientos = entrenamientoEjb.obternerTodosEjb();
		
//    	for(int i = 0; i < listaEntrenamientos.size(); i ++) {
//    		if(listaEntrenamientos.get(i).getNombre().equals("CYCLING")) {
//    			listaEntrenamientosCiclismo.add(listaEntrenamientos.get(i).getFC());
//    	}

		listaEntrenamientosCiclismo.add((Integer) 120);
		listaEntrenamientosCiclismo.add((Integer) 123);
		listaEntrenamientosCiclismo.add((Integer) 112);
		listaEntrenamientosCiclismo.add((Integer) 124);
		listaEntrenamientosCiclismo.add((Integer) 112);
		listaEntrenamientosCiclismo.add((Integer) 112);
		listaEntrenamientosCiclismo.add((Integer) 132);
		listaEntrenamientosCiclismo.add((Integer) 132);
		listaEntrenamientosCiclismo.add((Integer) 130);
		listaEntrenamientosCiclismo.add((Integer) 134);
		listaEntrenamientosCiclismo.add((Integer) 134);
		listaEntrenamientosCiclismo.add((Integer) 134);
		listaEntrenamientosCiclismo.add((Integer) 122);
		listaEntrenamientosCiclismo.add((Integer) 112);
		listaEntrenamientosCiclismo.add((Integer) 112);
		listaEntrenamientosCiclismo.add((Integer) 121);
		listaEntrenamientosCiclismo.add((Integer) 142);
		listaEntrenamientosCiclismo.add((Integer) 123);
		listaEntrenamientosCiclismo.add((Integer) 132);
		listaEntrenamientosCiclismo.add((Integer) 132);
		
		for(int j = 0; j < listaUsuarios.size(); j++) {
			ChartSeries nombreUsuarioCiclismo = new ChartSeries();
			nombreUsuarioCiclismo.setLabel(listaUsuarios.get(j));
    	for(int i = listaEntrenamientosCiclismo.size() - 20; i <listaEntrenamientosCiclismo.size(); i++) {
        		nombreUsuarioCiclismo.set(i + 1, listaEntrenamientosCiclismo.get(i)
//        				.getFC()
        				);
        	}
        modelCiclismo.addSeries(nombreUsuarioCiclismo);
		}
        return modelCiclismo;
    }
    
    private LineChartModel initCategoryModelCorrer() {
    	
    	LineChartModel modelCorrer = new LineChartModel();
    	
//-------------------DESCOMENTAR TODO ESTO PARA USARLO CON LA BASE DE DATOS -----------------------------    	
    	
//    	listaEntrenamientos = entrenamientoEjb.obternerTodosEjb();
		
//    	for(int i = 0; i < listaEntrenamientos.size(); i ++) {
//    		if(listaEntrenamientos.get(i).getNombre().equals("RUNNING")) {
//    			listaEntrenamientosCorrer.add(listaEntrenamientos.get(i).getRitmo());
//    	}
    	
		listaEntrenamientosCorrer.add((Integer) 155);
		listaEntrenamientosCorrer.add((Integer) 154);
		listaEntrenamientosCorrer.add((Integer) 154);
		listaEntrenamientosCorrer.add((Integer) 154);
		listaEntrenamientosCorrer.add((Integer) 163);
		listaEntrenamientosCorrer.add((Integer) 164);
		listaEntrenamientosCorrer.add((Integer) 154);
		listaEntrenamientosCorrer.add((Integer) 164);
		listaEntrenamientosCorrer.add((Integer) 164);
		listaEntrenamientosCorrer.add((Integer) 164);
		listaEntrenamientosCorrer.add((Integer) 154);
		listaEntrenamientosCorrer.add((Integer) 154);
		listaEntrenamientosCorrer.add((Integer) 144);
		listaEntrenamientosCorrer.add((Integer) 153);
		listaEntrenamientosCorrer.add((Integer) 155);
		listaEntrenamientosCorrer.add((Integer) 144);
		listaEntrenamientosCorrer.add((Integer) 164);
		listaEntrenamientosCorrer.add((Integer) 165);
		listaEntrenamientosCorrer.add((Integer) 154);
		listaEntrenamientosCorrer.add((Integer) 154);
		
		for(int j = 0; j < listaUsuarios.size(); j++) {
			ChartSeries nombreUsuarioCorrer = new ChartSeries();
			nombreUsuarioCorrer.setLabel(listaUsuarios.get(j));	
		for(int i = listaEntrenamientosCorrer.size() - 20; i <listaEntrenamientosCorrer.size(); i++) {
        	nombreUsuarioCorrer.set(i + 1, listaEntrenamientosCorrer.get(i)

        	);
        	}
       	modelCorrer.addSeries(nombreUsuarioCorrer);
		}
        return modelCorrer;
    }
    
    
    private void createLineModels() {
    	
    	//PASO 2
    	
		listaUsuarios.add("Jose");
		listaUsuarios.add("Juan");
		listaUsuarios.add("Augusto");
		Axis yAxis;
		
        //AJUSTE DE EJES
        lineModelNadar = initCategoryModelNadar();
        
		if(listaEntrenamientosNadar.size()>0) {
        lineModelNadar.setTitle("Natación: Frecuencia cardíaca por entrenamiento");
        lineModelNadar.setLegendPosition("e");
        lineModelNadar.setShowPointLabels(false);
        lineModelNadar.getAxes().put(AxisType.X, new CategoryAxis("Entrenamiento"));
        yAxis = lineModelNadar.getAxis(AxisType.Y);
        yAxis.setLabel("Frecuencia cardíaca en ppm(pulsaciones por minuto)");
        yAxis.setMin(Collections.min(listaEntrenamientosNadar) - 5);
        yAxis.setMax(Collections.max(listaEntrenamientosNadar) + 5);
        }
      //AJUSTE DE EJES
        lineModelCiclismo = initCategoryModelCiclismo();
        if(listaEntrenamientosCiclismo.size()>0) {
        lineModelCiclismo.setTitle("Ciclismo: Frecuencia cardíaca por entrenamiento");
        lineModelCiclismo.setLegendPosition("e");
        lineModelCiclismo.setShowPointLabels(false);
        lineModelCiclismo.getAxes().put(AxisType.X, new CategoryAxis("Entrenamiento"));
        yAxis = lineModelCiclismo.getAxis(AxisType.Y);
        yAxis.setLabel("Frecuencia cardíaca (ppm)");
        yAxis.setMin(Collections.min(listaEntrenamientosCiclismo) - 5);
        yAxis.setMax(Collections.max(listaEntrenamientosCiclismo) + 5);
        }
      //AJUSTE DE EJES
        lineModelCorrer = initCategoryModelCorrer();
        if(listaEntrenamientosCorrer.size()>0) {
        lineModelCorrer.setTitle("Carrera: Frecuencia cardíaca por entrenamiento");
        lineModelCorrer.setLegendPosition("e");
        lineModelCorrer.setShowPointLabels(false);
        lineModelCorrer.getAxes().put(AxisType.X, new CategoryAxis("Entrenamiento"));
        yAxis = lineModelCorrer.getAxis(AxisType.Y);
        yAxis.setLabel("Frecuencia cardíaca (ppm)");
        yAxis.setMin(Collections.min(listaEntrenamientosCorrer) - 5);
        yAxis.setMax(Collections.max(listaEntrenamientosCorrer) + 5);
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
    

}

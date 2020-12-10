
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

@Named("chartCansancioInd")
@ManagedBean
@SessionScoped
public class ChartCansancio implements Serializable {

	private static final long serialVersionUID = 1L;
    private LineChartModel lineModelNadar;
    private LineChartModel lineModelCiclismo;
    private LineChartModel lineModelCorrer;
	EntrenamientoEjb entrenamientoEjb;
	List<Entrenamiento> listaEntrenamientos = new ArrayList<>();
	List<Integer> listaEntrenamientosCiclismo = new ArrayList<>();
	List<Integer> listaEntrenamientosCorrer = new ArrayList<>();
	List<Integer> listaEntrenamientosNadar = new ArrayList<>();

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
       	
       	System.out.println("**** Metodo cargaGraficosRend ChartCansancio");
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
    			Integer freqCardiaca = resultadosEnto.getFreqCard();
    			
       		if(nomEnto.equals("CYCLING")) {
       			listaEntrenamientosCiclismo.add(freqCardiaca);
       		}else if(nomEnto.equals("RUNNING")) {
       			System.out.println("nomEnto.equals(\"RUNNING\")");
       			listaEntrenamientosCorrer.add(freqCardiaca);
       			System.out.println("nomEnto.equals(\"RUNNING\")"+listaEntrenamientosCorrer.size());
       		}else {
       			listaEntrenamientosNadar.add(freqCardiaca);
       		}
       			
    		}
       	}
       }
       
       public void filtroDatosEnto(String usuario) {
    		
    		System.out.println("**** Metodo filtoDatosEnto ChartCansancio");
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
    	
//    	listaEntrenamientos = entrenamientoEjb.obternerTodosEjb();
		
//    	for(int i = 0; i < listaEntrenamientos.size(); i ++) {
//    		if(istaEntrenamientos.get(i).getNombre().equals("SWIMMING"){
//    	listaEntrenamientosNadar.add(listaEntrenamientos.get(i).getFC());
//    		}
//    	}
		
    	//-------------------PARA PROBAR GRAFICAS CON DATOS, DESPUES BORRAR -----------------------------    
//		listaEntrenamientosNadar.add((Integer) 142);
//		listaEntrenamientosNadar.add((Integer) 144);
//		listaEntrenamientosNadar.add((Integer) 143);
//		listaEntrenamientosNadar.add((Integer) 154);
//		listaEntrenamientosNadar.add((Integer) 143);
//		listaEntrenamientosNadar.add((Integer) 152);
//		listaEntrenamientosNadar.add((Integer) 142);
//
//		listaEntrenamientosNadar.add((Integer) 154);
//		listaEntrenamientosNadar.add((Integer) 142);
//		listaEntrenamientosNadar.add((Integer) 142);
//		listaEntrenamientosNadar.add((Integer) 152);
//		listaEntrenamientosNadar.add((Integer) 153);
//		listaEntrenamientosNadar.add((Integer) 153);
//		listaEntrenamientosNadar.add((Integer) 152);
//		listaEntrenamientosNadar.add((Integer) 152);
//		listaEntrenamientosNadar.add((Integer) 152);
		
//		ChartSeries nombreUsuario = new ChartSeries();
//		nombreUsuario.setLabel("nombreUsuario");
		
		ChartSeries nombreUsuarioNadar = new ChartSeries();
		nombreUsuarioNadar.setLabel("Cansancio");
    	
		if(listaEntrenamientosNadar.size()>19) {	
		for(int i = listaEntrenamientosNadar.size() - 20; i <listaEntrenamientosNadar.size(); i++)
        		{
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

//		listaEntrenamientosCiclismo.add((Integer) 130);
//		listaEntrenamientosCiclismo.add((Integer) 140);
//		listaEntrenamientosCiclismo.add((Integer) 132);
//		listaEntrenamientosCiclismo.add((Integer) 134);
//		listaEntrenamientosCiclismo.add((Integer) 132);
//		listaEntrenamientosCiclismo.add((Integer) 132);
//		listaEntrenamientosCiclismo.add((Integer) 132);
//		listaEntrenamientosCiclismo.add((Integer) 132);
//		listaEntrenamientosCiclismo.add((Integer) 134);
//		listaEntrenamientosCiclismo.add((Integer) 134);
//		listaEntrenamientosCiclismo.add((Integer) 134);
//		listaEntrenamientosCiclismo.add((Integer) 134);
//		listaEntrenamientosCiclismo.add((Integer) 122);
//		listaEntrenamientosCiclismo.add((Integer) 132);
//		listaEntrenamientosCiclismo.add((Integer) 132);
//		listaEntrenamientosCiclismo.add((Integer) 131);
//		listaEntrenamientosCiclismo.add((Integer) 132);
//		listaEntrenamientosCiclismo.add((Integer) 123);
//		listaEntrenamientosCiclismo.add((Integer) 132);
//		listaEntrenamientosCiclismo.add((Integer) 132);
		
	   	ChartSeries nombreUsuarioCiclismo = new ChartSeries();
    	nombreUsuarioCiclismo.setLabel("Cansancio");
    	if(listaEntrenamientosCiclismo.size()>19) {
    		
    	for(int i = listaEntrenamientosCiclismo.size() - 20; i <listaEntrenamientosCiclismo.size(); i++) {
        		nombreUsuarioCiclismo.set(i + 1, listaEntrenamientosCiclismo.get(i)

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
    	
//		listaEntrenamientosCorrer.add((Integer) 155);
//		listaEntrenamientosCorrer.add((Integer) 154);
//		listaEntrenamientosCorrer.add((Integer) 154);
//		listaEntrenamientosCorrer.add((Integer) 154);
//		listaEntrenamientosCorrer.add((Integer) 163);
//		listaEntrenamientosCorrer.add((Integer) 164);
//		listaEntrenamientosCorrer.add((Integer) 154);
//		listaEntrenamientosCorrer.add((Integer) 164);
//		listaEntrenamientosCorrer.add((Integer) 164);
//		listaEntrenamientosCorrer.add((Integer) 164);
//		listaEntrenamientosCorrer.add((Integer) 154);
//		listaEntrenamientosCorrer.add((Integer) 154);
//		listaEntrenamientosCorrer.add((Integer) 144);
//		listaEntrenamientosCorrer.add((Integer) 153);
//		listaEntrenamientosCorrer.add((Integer) 155);
//		listaEntrenamientosCorrer.add((Integer) 144);
//		listaEntrenamientosCorrer.add((Integer) 164);
//		listaEntrenamientosCorrer.add((Integer) 165);
//		listaEntrenamientosCorrer.add((Integer) 154);
//		listaEntrenamientosCorrer.add((Integer) 154);
		
    	ChartSeries nombreUsuarioCorrer = new ChartSeries();
		nombreUsuarioCorrer.setLabel("Cansancio");
        	
		System.out.println("**** ListaEntrenamientosCorrer = "+listaEntrenamientosCorrer);
		if(listaEntrenamientosCorrer.size()>19) {
			
		for(int i = listaEntrenamientosCorrer.size() - 20; i <listaEntrenamientosCorrer.size(); i++) {
        	nombreUsuarioCorrer.set(i + 1, listaEntrenamientosCorrer.get(i));
        	System.out.println("**** Nums ListaEntrenamientosCorrer = "+listaEntrenamientosCorrer.get(i));
        	
        	}
       	modelCorrer.addSeries(nombreUsuarioCorrer);
		}
        return modelCorrer;
    }
    
    
    private void createLineModels() {
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
		 Axis yAxis2;
        lineModelCiclismo = initCategoryModelCiclismo();
        if(listaEntrenamientosCiclismo.size()>0) {
        lineModelCiclismo.setTitle("Ciclismo: Frecuencia cardíaca por entrenamiento");
        lineModelCiclismo.setLegendPosition("e");
        lineModelCiclismo.setShowPointLabels(false);
        lineModelCiclismo.getAxes().put(AxisType.X, new CategoryAxis("Entrenamiento"));
        yAxis2 = lineModelCiclismo.getAxis(AxisType.Y);
        yAxis2.setLabel("Frecuencia cardíaca (ppm)");
        yAxis2.setMin(Collections.min(listaEntrenamientosCiclismo) - 5);
        yAxis2.setMax(Collections.max(listaEntrenamientosCiclismo) + 5);
        }
        
      //AJUSTE DE EJES
        Axis yAxis3;
        lineModelCorrer = initCategoryModelCorrer();
        if(listaEntrenamientosCorrer.size()>0) {
        lineModelCorrer.setTitle("Carrera: Frecuencia cardíaca por entrenamiento");
        lineModelCorrer.setLegendPosition("e");
        lineModelCorrer.setShowPointLabels(false);
        lineModelCorrer.getAxes().put(AxisType.X, new CategoryAxis("Entrenamiento"));
        yAxis3 = lineModelCorrer.getAxis(AxisType.Y);
        yAxis3.setLabel("Frecuencia cardíaca (ppm)");
        yAxis3.setMin(Collections.min(listaEntrenamientosCorrer) - 5);
        yAxis3.setMax(Collections.max(listaEntrenamientosCorrer) + 5);
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

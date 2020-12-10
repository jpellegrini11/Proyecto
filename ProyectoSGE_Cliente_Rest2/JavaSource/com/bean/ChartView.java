package com.bean;


import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.chart.*;

import com.dao.ResultadosEntoDao;
import com.entidades.AsignarEnto;
import com.entidades.Deportista;
import com.entidades.Entrenamiento;
import com.entidades.ResultadosEnto;
import com.servicios.ChartViewEjb;
import com.servicios.DeportistaEjb;
import com.servicios.EntrenadorEjb;
import com.utils.SessionUtils;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

@Named("chart")
@ManagedBean
@SessionScoped
public class ChartView implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LineChartModel lineModel1;
    private LineChartModel lineModel2;
    private LineChartModel zoomModel;
    private CartesianChartModel combinedModel;
    private CartesianChartModel fillToZero;
    private LineChartModel areaModel;
    private BarChartModel barModel;
    
    private HorizontalBarChartModel horizontalBarModel;
   
    private PieChartModel pieModel1;
    private PieChartModel pieModel2;
    private DonutChartModel donutModel1;
    private DonutChartModel donutModel2;
    private MeterGaugeChartModel meterGaugeModel1;
    private MeterGaugeChartModel meterGaugeModel2;
    private BubbleChartModel bubbleModel1;
    private BubbleChartModel bubbleModel2;
    private OhlcChartModel ohlcModel;
    private OhlcChartModel ohlcModel2;
    private PieChartModel livePieModel;
    private LineChartModel animatedModel1;
    private BarChartModel animatedModel2;
    private LineChartModel multiAxisModel;
    private LineChartModel dateModel;
    
    private List<AsignarEnto> listAsig;
    private List<ResultadosEnto> listResultadosEnto;
	private List<Entrenamiento> listEnto;

    
	@EJB
	DeportistaEjb deportistaEjb;
	@EJB
	ResultadosEntoDao resultadosEntoDao;
	@EJB
	EntrenadorEjb entrenadorEjb;
	
	@EJB
	ChartViewEjb chartViewEjb;
    
	
	private ArrayList<ResultadosEnto> listEntoEn;
	private ArrayList<ResultadosEnto> listEntoFe;
	private ArrayList<ResultadosEnto> listEntoMa;
	private ArrayList<ResultadosEnto> listEntoAb;
	private ArrayList<ResultadosEnto> listEntoMay;
	private ArrayList<ResultadosEnto> listEntoJu;
	private ArrayList<ResultadosEnto> listEntoJul;
	private ArrayList<ResultadosEnto> listEntoAg;
	private ArrayList<ResultadosEnto> listEntoSe;
	private ArrayList<ResultadosEnto> listEntoOc;
	private ArrayList<ResultadosEnto> listEntoNo;
	private ArrayList<ResultadosEnto> listEntoDi;
	

	
	private List<Number> listNumberBic = new ArrayList<Number>();
	private List<Number> listNumberNat = new ArrayList<Number>();
	private List<Number> listNumberCor = new ArrayList<Number>();
	private List<Number> listNumberGym = new ArrayList<Number>();
	
	private List<Number> listNumberBicEnto = new ArrayList<Number>();
	private List<Number> listNumberNatEnto = new ArrayList<Number>();
	private List<Number> listNumberCorEnto = new ArrayList<Number>();
	private List<Number> listNumberGymEnto = new ArrayList<Number>();
	
	

	private HorizontalBarChartModel horizontalBarModel2;

	private List<ResultadosEnto> listResultadosEnto2;

	private Deportista dep;

	public ChartView(){}

    @PostConstruct
    public void init() {
 
        
    	listEnto= new ArrayList<Entrenamiento>();
    	
    	listEntoEn= new ArrayList<ResultadosEnto>();
    	listEntoFe= new ArrayList<ResultadosEnto>();
    	listEntoMa= new ArrayList<ResultadosEnto>();
    	listEntoAb= new ArrayList<ResultadosEnto>();
    	listEntoMay= new ArrayList<ResultadosEnto>();
    	listEntoJu= new ArrayList<ResultadosEnto>();
    	listEntoJul= new ArrayList<ResultadosEnto>();
    	listEntoAg= new ArrayList<ResultadosEnto>();
    	listEntoSe= new ArrayList<ResultadosEnto>();
    	listEntoOc= new ArrayList<ResultadosEnto>();
    	listEntoNo= new ArrayList<ResultadosEnto>();
    	listEntoDi= new ArrayList<ResultadosEnto>();
    	System.out.println("url init chart "+SessionUtils.getRequest().getRequestURI().contains("/ProyectoSGE_Cliente_Rest1/entrenador/"));
    	
		if(SessionUtils.getRequest().getRequestURI().contains("/ProyectoSGE_Cliente_Rest1/entrenador/")) {
    		System.out.println("if SessionUtils.getRequest().getRequestURI() No carga graficos");
			
    	}else {
    		
    	
//    	listResultadosEnto = resultadosEntoDao.filtroResultadosEntoUsu("augustoas1992@gmail.com");
    	mostrarGrafico();
    	}
		
		 
		  
    	
    }
    
    public void mostrarGrafico() {
    	
    	String usuario = null;
		if(SessionUtils.getRequest().getRequestURI().contains("/ProyectoSGE_Cliente_Rest1/entrenador/")) {
    		System.out.println("if SessionUtils.getRequest().getRequestURI()"+dep.getNomCompleto() );
    		usuario=dep.getUsuario();
    	}else {
    	
    		usuario = (String) SessionUtils.getRequest().getSession(false).getAttribute("usuario");
    	}
    	if(resultadosEntoDao.filtroResultadosEntoUsu(usuario).size()>0) {
	    		
    		System.out.println("resultadosEntoDao.filtroResultadosEntoUsu(usuario).size() ="+resultadosEntoDao.filtroResultadosEntoUsu(usuario).size());
	    	
	    		filtoDatosEnto(usuario);
	        
	    	
	    	}
		System.out.println("resultadosEntoDao.filtroResultadosEntoUsu(usuario).size() ="+resultadosEntoDao.filtroResultadosEntoUsu(usuario).size());

	        
	    	if(listResultadosEnto.size()>0) {
	    		
	    		Date octIn= new Date();
	            Date octFn= new Date();
	            
	    		System.out.println("listResultadosEnto mayor 0");
	    		System.out.println(listResultadosEnto.size());
	    		System.out.println("listResultadosEnto ento nombre = "+listResultadosEnto.get(0).getAsignarEnto().getEntrenamiento().getNombre());
	    		
	    		System.out.println("getFechaIni() = "+listResultadosEnto.get(0).getAsignarEnto().getFechaIni().toString());
	    		System.out.println("getFechaIni() = "+listResultadosEnto.get(0).getAsignarEnto().getFechaIni().toString().contains("2020-12"));
	    		System.out.println("getFechaIni() = "+listResultadosEnto.get(0).getAsignarEnto().getFechaIni().toString().contains("2020-11"));

	    		if(listResultadosEnto.get(0).getAsignarEnto().getFechaIni().after(octIn)){
	    			System.out.println("if 1");
	    		}
	    		if(listResultadosEnto.get(0).getAsignarEnto().getFechaIni().before(octFn)){
	    			System.out.println("if 2");
	    		}
	    		if(listResultadosEnto.get(0).getAsignarEnto().getCompletado().equals("COMPLETO")){
	    			System.out.println("if 3");
	    		}

	    	     octIn=fechaGraf(1, 0, 2020);
	             octFn=fechaGraf(31, 0, 2020);
	             
	             System.out.println("octIn "+octIn);
	             System.out.println("octFn "+octFn);
	            
	 
				
	    		for (ResultadosEnto r1 : listResultadosEnto) {
			
	    			System.out.println("r1.getAsignarEnto().getFechaIni().toString() ="+r1.getAsignarEnto().getFechaIni().toString());
	    			
	    			if(r1.getAsignarEnto().getFechaIni().toString().contains("2020-01") && r1.getAsignarEnto().getCompletado().equals("COMPLETO")) {
	        			
	    				listEntoEn.add(r1);
	    				System.out.println("listEntoEn.size() = "+listEntoEn.size());
	    				
	    				
	        		}
	    			
	    			if(r1.getAsignarEnto().getFechaIni().toString().contains("2020-02") && r1.getAsignarEnto().getCompletado().equals("COMPLETO")) {
	        			
	    				listEntoFe.add(r1);
	    				System.out.println("listEntoFe.size() = "+listEntoFe.size());
	        		}
	    			
	    			if(r1.getAsignarEnto().getFechaIni().toString().contains("2020-03") && r1.getAsignarEnto().getCompletado().equals("COMPLETO")) {
	        			
	    				listEntoMa.add(r1);
	    				System.out.println("listEntoMa.size() = "+listEntoMa.size());
	        		}
	    			
	    			if(r1.getAsignarEnto().getFechaIni().toString().contains("2020-04") && r1.getAsignarEnto().getCompletado().equals("COMPLETO")) {
	        			
	    				listEntoAb.add(r1);
	    				System.out.println("listEntoAb.size() = "+listEntoAb.size());
	        		}
	    			if(r1.getAsignarEnto().getFechaIni().toString().contains("2020-05") && r1.getAsignarEnto().getCompletado().equals("COMPLETO")) {
	        			
	    				listEntoMay.add(r1);
	    				System.out.println("listEntoMay.size() = "+listEntoMay.size());
	        		}
	    			if(r1.getAsignarEnto().getFechaIni().toString().contains("2020-06") && r1.getAsignarEnto().getCompletado().equals("COMPLETO")) {
	        			
	    				listEntoJu.add(r1);
	    				System.out.println("listEntoJu.size() = "+listEntoJu.size());
	        		}
	    			if(r1.getAsignarEnto().getFechaIni().toString().contains("2020-07") && r1.getAsignarEnto().getCompletado().equals("COMPLETO")) {
	        			
	    				listEntoJul.add(r1);
	    				System.out.println("listEntoJul.size() = "+listEntoJul.size());
	        		}
	    			if(r1.getAsignarEnto().getFechaIni().toString().contains("2020-08") && r1.getAsignarEnto().getCompletado().equals("COMPLETO")) {
	        			
	    				listEntoAg.add(r1);
	    				System.out.println("listEntoAg.size() = "+listEntoAg.size());
	        		}
	    			if(r1.getAsignarEnto().getFechaIni().toString().contains("2020-09") && r1.getAsignarEnto().getCompletado().equals("COMPLETO")) {
	        			
	    				listEntoSe.add(r1);
	    				System.out.println("listEntoSe.size() = "+listEntoSe.size());
	        		}
	    			if(r1.getAsignarEnto().getFechaIni().toString().contains("2020-10") && r1.getAsignarEnto().getCompletado().equals("COMPLETO")) {
	        			
	    				listEntoOc.add(r1);
	    				System.out.println("listEntoOc.size() = "+listEntoOc.size());
	        		}
	    			if(r1.getAsignarEnto().getFechaIni().toString().contains("2020-11") && r1.getAsignarEnto().getCompletado().equals("COMPLETO")) {
	        			
	    				listEntoNo.add(r1);
	    				System.out.println("listEntoNo.size() = "+listEntoNo.size());
	        		}
	    			
	    			if(r1.getAsignarEnto().getFechaIni().toString().contains("2020-12") && r1.getAsignarEnto().getCompletado().equals("COMPLETO")) {
	        			
	    				listEntoDi.add(r1);
	    				System.out.println("listEntoDi.size() = "+listEntoDi.size());
	        		}
	    			
//	    			if(r1.getAsignarEnto().getFechaIni().after(fechaGraf(1, 11, 2020)) && r1.getAsignarEnto().getFechaIni().before(fechaGraf(31, 11, 2020)) && r1.getAsignarEnto().getCompletado().equals("COMPLETO")) {
//	        			
//	    				listEntoDi.add(r1);
//	    				System.out.println("listEntoDi.size() = "+listEntoDi.size());
//	        		}
				
	    		}
	    		
	        			
	    		cargaHorasDep(listEntoEn);
	    		cargaHorasDep(listEntoFe);
	    		cargaHorasDep(listEntoMa);
	    		cargaHorasDep(listEntoAb);
	    		cargaHorasDep(listEntoMay);
	    		cargaHorasDep(listEntoJu);
	    		cargaHorasDep(listEntoJul);
	    		cargaHorasDep(listEntoAg);
	    		cargaHorasDep(listEntoSe);
	    		cargaHorasDep(listEntoOc);
	    		cargaHorasDep(listEntoNo);
	    		cargaHorasDep(listEntoDi);
	    		
	    		System.out.println("listNumberBic carg ="+listNumberBic.size());
	    		System.out.println("listNumberNat carg ="+listNumberNat.size());
	    		System.out.println("listNumberCor carg ="+listNumberCor.size());
	    		System.out.println("listNumberGym carg ="+listNumberGym.size());
	    	
	    		for (Number num : listNumberBic) {
					
	    			System.out.println("num bici list = "+num);
				}
	    		
	    		for (Number num : listNumberGym) {
					
	    			System.out.println("num gym list = "+num);
				}
	    		
	    		createBarModels();
	    	
	    }else {
	    	System.out.println("No hay datos de resultado ENTO");
	    	createBarModels();
	    }
      	
      }
    	public void filtoDatosEnto(String usuario) {
    		
    		System.out.println("******** Metodo filtoDatosEnto ChartView");
    		listResultadosEnto2 = resultadosEntoDao.filtroResultadosEntoUsu(usuario);
        	listResultadosEnto= new ArrayList<ResultadosEnto>();
        	
        	System.out.println(listResultadosEnto2.size());
        	System.out.println(listResultadosEnto.size());
        	
        	for (ResultadosEnto listR1 : listResultadosEnto2) {
    			
        		if(listR1.getPolarFlow()<1) {
        			
        			System.out.println("listR1.getPolarFlow() ");
        			
        			listResultadosEnto.add(listR1);
        			System.out.println("getPolarFlow "+listResultadosEnto.size());
        		}
    		}
        	System.out.println(listResultadosEnto2.size());
        	System.out.println(listResultadosEnto.size());
    	}
    	
    public void cargaHorasDep(List<ResultadosEnto> listResultadosEnto) {
    	
    	 Number horasBic = 0;
    	 Long minBic = (long) 0;
    	 Number horasNat = 0;
    	 Long minNat = (long) 0;
    	 Number horasCor = 0;
    	 Long minCor = (long) 0;
    	 Number horasGym = 0;
    	 Long minGym = (long) 0;
    	 
    	 Number horasBicEnto = 0;
    	 Long minBicEnto = (long) 0;
    	 Number horasNatEnto = 0;
    	 Long minNatEnto = (long) 0;
    	 Number horasCorEnto = 0;
    	 Long minCorEnto = (long) 0;
    	 Number horasGymEnto = 0;
    	 Long minGymEnto = (long) 0;
    	
    	if(listResultadosEnto.size()>0) {
    	
    		for (ResultadosEnto r2 : listResultadosEnto) {
			
			if(r2.getAsignarEnto().getEntrenamiento().getDeporte().equals("BICICLETA")) {
				
				System.out.println("r2.getTiempoTotal()= "+r2.getTiempoTotal());
				
				 minBic = minBic+r2.getTiempoTotal()/60;
				 				
				horasBic= minBic;
				System.out.println("horasBic = "+horasBic);
				System.out.println("minBic = "+minBic);
				
				
				System.out.println("r2.getAsignarEnto().getEntrenamiento().getTiempoRealizar()= "+r2.getAsignarEnto().getEntrenamiento().getTiempoRealizar());
				minBicEnto = minBicEnto+r2.getAsignarEnto().getEntrenamiento().getTiempoRealizar()/60;
				
				horasBicEnto= minBicEnto;
				System.out.println("horasBicEnto = "+horasBicEnto);
				System.out.println("minBicEnto = "+minBicEnto);
				
			}
			
			if(r2.getAsignarEnto().getEntrenamiento().getDeporte().equals("NATACION")) {
				
				minNat = minNat+r2.getTiempoTotal()/60;
				
				horasNat= minNat;
				System.out.println("minNat = "+minNat);
				
				minNatEnto = minNatEnto+r2.getAsignarEnto().getEntrenamiento().getTiempoRealizar()/60;
				
				horasNatEnto= minNatEnto;
				System.out.println("horasNatEnto = "+horasNatEnto);
				System.out.println("minNatEnto = "+minNatEnto);
				
			}
			
			if(r2.getAsignarEnto().getEntrenamiento().getDeporte().equals("CORRER")) {
				
				minCor = minCor+r2.getTiempoTotal()/60;
				
				 horasCor= minCor;
				System.out.println("minCor = "+minCor);
				
				minCorEnto = minCorEnto+r2.getAsignarEnto().getEntrenamiento().getTiempoRealizar()/60;
				
				horasCorEnto= minCorEnto;
				System.out.println("horasCorEnto = "+horasCorEnto);
				System.out.println("minCorEnto = "+minCorEnto);
				
			}
			
			if(r2.getAsignarEnto().getEntrenamiento().getDeporte().equals("GYM Y EQUIPO FITNESS")) {
				
				minGym = minGym+r2.getTiempoTotal()/60;
				
				 horasGym= minGym;
				System.out.println("minGym = "+minGym);
				
				minGymEnto = minGymEnto+r2.getAsignarEnto().getEntrenamiento().getTiempoRealizar()/60;
				
				horasGymEnto= minGymEnto;
				System.out.println("horasGymEnto = "+horasGymEnto);
				System.out.println("minGymEnto = "+minGymEnto);
				
			}
		}
    	
    	}
    	 listNumberBic.add(horasBic); 
    	 listNumberNat.add(horasNat);
    	 listNumberCor.add(horasCor);
    	 listNumberGym.add(horasGym);
    	 
   
    	 listNumberBicEnto.add(horasBicEnto);
    	
    	 listNumberNatEnto.add(horasNatEnto);
    	
    	 listNumberCorEnto.add(horasCorEnto);

    	 listNumberGymEnto.add(horasGymEnto);



    	
    }
    
    private void createBarModels() {
        createBarModel();
        createHorizontalBarModel();
        createHorizontalBarModel2();
    }
    
    	 private void createHorizontalBarModel() {
    	        horizontalBarModel = new HorizontalBarChartModel();
    	        

    	        ChartSeries correr = new ChartSeries();
    	        correr.setLabel("CORRER");
    	        if(listNumberCor.size()>0) {
    	        cargaChartSeries(correr, listNumberCor);
    	        }else {
    	        	System.out.println("else listNumberCor.size()>0");
    	        }
    	        ChartSeries bici = new ChartSeries();
    	        bici.setLabel("BICI");
    	        if(listNumberBic.size()>0) {
    	        cargaChartSeries(bici, listNumberBic);
    	        
    	        }else {
	        	System.out.println("else listNumberBic.size()>0");
	            }
    	        
    	        ChartSeries nat = new ChartSeries();
    	        nat.setLabel("NAT");
    	        if(listNumberNat.size()>0) {
    	        cargaChartSeries(nat, listNumberNat);
    	        
    	        }else {
    	        	System.out.println("else listNumberNat.size()>0");
    	        }
    	        
    	        ChartSeries gym = new ChartSeries();
    	        gym.setLabel("GYM");
    	        if(listNumberGym.size()>0) {
    	        cargaChartSeries(gym, listNumberGym);
    	    }else {
	        	System.out.println("else listNumberGym.size()>0");
	        }
    	       
    	        horizontalBarModel.addSeries(correr);
    	        horizontalBarModel.addSeries(bici);
    	        	      
    	        horizontalBarModel.addSeries(nat);
    	        horizontalBarModel.addSeries(gym);
    	        
//    	        horizontalBarModel.setTitle("Grafico barras, horas dedicadas por deporte en el mes");
    	        
    	        horizontalBarModel.setLegendPosition("e");
    	        horizontalBarModel.setStacked(true);
    	        			
    			Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
    	        xAxis.setLabel("Horas");
    	        xAxis.setMin(0);
//    	        xAxis.setMax(200);
    	        

    	        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
    	        yAxis.setLabel("Meses");
    	    }
    	 
    	 private void createHorizontalBarModel2() {
    		 
 	        horizontalBarModel2 = new HorizontalBarChartModel();
    
 	        ChartSeries correr = new ChartSeries();
 	        correr.setLabel("CORRER Real");
 	        if(listNumberCor.size()>0) {
 	        cargaChartSeries(correr, listNumberCor);
    	 }else {
	        	System.out.println("else listNumberCor.size()>0");
	        }
 	        
 	        ChartSeries correrReal = new ChartSeries();
 	        correrReal.setLabel("CORRER");
 	        if(listNumberCorEnto.size()>0 && listNumberCor.size()>0) {
 	        	cargaChartSeries(correrReal, listNumberCorEnto);
    	 }else {
	        	System.out.println("else listNumberCor.size()>0 && listNumberCorEnto");
	        }
 	        horizontalBarModel2.addSeries(correr);
 	      
 	        horizontalBarModel2.addSeries(correrReal);
 	      
 	        horizontalBarModel2.setLegendPosition("e");
// 	        horizontalBarModel2.setStacked(true);
 	   
 			Axis xAxis = horizontalBarModel2.getAxis(AxisType.X);
 	        xAxis.setLabel("Horas");
 	        xAxis.setMin(0);
 	       
 	        Axis yAxis = horizontalBarModel2.getAxis(AxisType.Y);
 	        yAxis.setLabel("Meses");
 	    }
    	 
    	 
    	 private BarChartModel initBarModel() {
    	        BarChartModel model = new BarChartModel();

    	        ChartSeries graficoEnto = new ChartSeries();
    	        graficoEnto.setLabel("Horas Reales");
    	        
    	        if(listNumberCor.size()>0) {
    	        cargaChartSeries(graficoEnto, listNumberCor);
    	        }else {
	        	System.out.println("else listNumberCor.size()>0");
    	        }
	          	       

    	        ChartSeries graficoEntoReal = new ChartSeries();
    	        graficoEntoReal.setLabel("Horas");
    	        
    	        if(listNumberCorEnto.size()>0) {
    	        cargaChartSeries(graficoEntoReal, listNumberCorEnto);
    	        }else {
    	        	System.out.println("else listNumberCor.size()>0");
    	        }
 

    	        model.addSeries(graficoEnto);
    	        model.addSeries(graficoEntoReal);

    	        return model;
    	    }



    	    private void createBarModel() {
    	    	
    	        barModel = initBarModel();

//    	        barModel.setTitle("Bar Chart");
    	        barModel.setLegendPosition("ne");

    	        Axis xAxis = barModel.getAxis(AxisType.X);
    	        xAxis.setLabel("Meses");

    	        Axis yAxis = barModel.getAxis(AxisType.Y);
    	        yAxis.setLabel("Horas");
//    	        yAxis.setMin(0);
//    	        yAxis.setMax(200);
    	    }

	public void cargaChartSeries(ChartSeries chartSeries, List<Number> listNum ) {
		
		chartSeries.set("ENE", listNum.get(0));
		chartSeries.set("FEB", listNum.get(1));
		chartSeries.set("MAR", listNum.get(2));
		chartSeries.set("ABR", listNum.get(3));
		chartSeries.set("MAY", listNum.get(4));
		chartSeries.set("JUN", listNum.get(5));
		chartSeries.set("JUL", listNum.get(6));
		chartSeries.set("AGO", listNum.get(7));
		chartSeries.set("SEP", listNum.get(8));
		chartSeries.set("OCT", listNum.get(9));
		chartSeries.set("NOV", listNum.get(10));
		chartSeries.set("DIC", listNum.get(11));
		
	}
	
	public void cargaChartSeriesEnto(ChartSeries chartSeries, List<Number> listNumEnto, List<Number> listNum) {
		
		
			
			Number n1= convertCargaChartSeriesEnto(listNum.get(0),listNumEnto.get(0));
			chartSeries.set("ENE", n1);
			
			Number n2= convertCargaChartSeriesEnto(listNum.get(1),listNumEnto.get(1));
			chartSeries.set("FEB", n2);
			
		Number n3= convertCargaChartSeriesEnto(listNum.get(2),listNumEnto.get(2));
		chartSeries.set("MAR", n3);
		
		Number n4= convertCargaChartSeriesEnto(listNum.get(3),listNumEnto.get(3));
		chartSeries.set("ABR", n4);
		
		Number n5= convertCargaChartSeriesEnto(listNum.get(4),listNumEnto.get(4));
		chartSeries.set("MAY", n5);
		
		Number n6= convertCargaChartSeriesEnto(listNum.get(5),listNumEnto.get(5));
		chartSeries.set("JUN", n6);
		
		Number n7= convertCargaChartSeriesEnto(listNum.get(6),listNumEnto.get(6));
		chartSeries.set("JUL", n7);
		
		Number n8= convertCargaChartSeriesEnto(listNum.get(7),listNumEnto.get(7));
		chartSeries.set("AGO", n8);
		
		Number n9= convertCargaChartSeriesEnto(listNum.get(8),listNumEnto.get(8));
		chartSeries.set("SEP", n9);
		
		Number n10= convertCargaChartSeriesEnto(listNum.get(9),listNumEnto.get(9));
		chartSeries.set("OCT", n10);
		
		Number n11= convertCargaChartSeriesEnto(listNum.get(10),listNumEnto.get(10));
		chartSeries.set("NOV", n11);
		
		Number n12= convertCargaChartSeriesEnto(listNum.get(11),listNumEnto.get(11));
		chartSeries.set("DIC", n12);
		
	}
    
	public Number convertCargaChartSeriesEnto(Number num, Number numEnto) {
		
if(num.longValue()<numEnto.longValue()) {
			
			Number n3=numEnto.longValue()-num.longValue();
			System.out.println("num y numEnto = "+num+" "+numEnto);
			System.out.println("n3 = "+n3);
			
			return n3;
		}else {
			return 0;
		}
		
	}
	
    public Date fechaGraf(int date, int month, int year){
    	
    	Calendar c1= Calendar.getInstance();
    	if(date>27) {
    	c1.set(year, month+1, c1.getMinimum(2));
    	System.out.println("fecha c1 max = "+c1.toString());
    	}else {
    		c1.set(year, month, c1.getMinimum(2)+1);
    		System.out.println("fecha2 c1 min = "+c1.toString());
    	}
		return c1.getTime();
    	
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public LineChartModel getLineModel1() {
        return lineModel1;
    }

    public LineChartModel getLineModel2() {
        return lineModel2;
    }

    public LineChartModel getZoomModel() {
        return zoomModel;
    }

    public CartesianChartModel getCombinedModel() {
        return combinedModel;
    }

    public CartesianChartModel getAreaModel() {
        return areaModel;
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    public MeterGaugeChartModel getMeterGaugeModel1() {
        return meterGaugeModel1;
    }

    public MeterGaugeChartModel getMeterGaugeModel2() {
        return meterGaugeModel2;
    }

    public DonutChartModel getDonutModel1() {
        return donutModel1;
    }

    public DonutChartModel getDonutModel2() {
        return donutModel2;
    }

    public CartesianChartModel getFillToZero() {
        return fillToZero;
    }

    public BubbleChartModel getBubbleModel1() {
        return bubbleModel1;
    }

    public BubbleChartModel getBubbleModel2() {
        return bubbleModel2;
    }

    public OhlcChartModel getOhlcModel() {
        return ohlcModel;
    }

    public OhlcChartModel getOhlcModel2() {
        return ohlcModel2;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public HorizontalBarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }

    public LineChartModel getAnimatedModel1() {
        return animatedModel1;
    }

    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }

    public LineChartModel getMultiAxisModel() {
        return multiAxisModel;
    }

    public LineChartModel getDateModel() {
        return dateModel;
    }

    public PieChartModel getLivePieModel() {
        int random1 = (int) (Math.random() * 1000);
        int random2 = (int) (Math.random() * 1000);

        livePieModel.getData().put("Candidate 1", random1);
        livePieModel.getData().put("Candidate 2", random2);

        livePieModel.setTitle("Votes");
        livePieModel.setLegendPosition("ne");

        return livePieModel;
    }

    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);

        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 90);
        girls.set("2008", 120);

        model.addSeries(boys);
        model.addSeries(girls);

        return model;
    }

    private void createLineModels() {
        lineModel1 = initLinearModel();
        lineModel1.setTitle("Linear Chart");
        lineModel1.setLegendPosition("e");
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);

        lineModel2 = initCategoryModel();
        lineModel2.setTitle("Category Chart");
        lineModel2.setLegendPosition("e");
        lineModel2.setShowPointLabels(true);
        lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Years"));
        yAxis = lineModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Births");
        yAxis.setMin(0);
        yAxis.setMax(200);

        zoomModel = initLinearModel();
        zoomModel.setTitle("Zoom");
        zoomModel.setZoom(true);
        zoomModel.setLegendPosition("e");
        yAxis = zoomModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
    }

    private void createAreaModel() {
        areaModel = new LineChartModel();

        LineChartSeries boys = new LineChartSeries();
        boys.setFill(true);
        boys.setLabel("Boys");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);

        LineChartSeries girls = new LineChartSeries();
        girls.setFill(true);
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 90);
        girls.set("2008", 120);

        areaModel.addSeries(boys);
        areaModel.addSeries(girls);

        areaModel.setTitle("Area Chart");
        areaModel.setLegendPosition("ne");
        areaModel.setStacked(true);
        areaModel.setShowPointLabels(true);

        Axis xAxis = new CategoryAxis("Years");
        areaModel.getAxes().put(AxisType.X, xAxis);
        Axis yAxis = areaModel.getAxis(AxisType.Y);
        yAxis.setLabel("Births");
        yAxis.setMin(0);
        yAxis.setMax(300);
    }

   

   

    private void createCombinedModel() {
        combinedModel = new BarChartModel();

        BarChartSeries boys = new BarChartSeries();
        boys.setLabel("Boys");

        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);

        LineChartSeries girls = new LineChartSeries();
        girls.setLabel("Girls");

        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 135);
        girls.set("2008", 120);

        combinedModel.addSeries(boys);
        combinedModel.addSeries(girls);

        combinedModel.setTitle("Bar and Line");
        combinedModel.setLegendPosition("ne");
        combinedModel.setMouseoverHighlight(false);
        combinedModel.setShowDatatip(false);
        combinedModel.setShowPointLabels(true);
        Axis yAxis = combinedModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(200);
    }

    private void createMultiAxisModel() {
        multiAxisModel = new LineChartModel();

        BarChartSeries boys = new BarChartSeries();
        boys.setLabel("Boys");

        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);

        LineChartSeries girls = new LineChartSeries();
        girls.setLabel("Girls");
        girls.setXaxis(AxisType.X2);
        girls.setYaxis(AxisType.Y2);

        girls.set("A", 52);
        girls.set("B", 60);
        girls.set("C", 110);
        girls.set("D", 135);
        girls.set("E", 120);

        multiAxisModel.addSeries(boys);
        multiAxisModel.addSeries(girls);

        multiAxisModel.setTitle("Multi Axis Chart");
        multiAxisModel.setMouseoverHighlight(false);

        multiAxisModel.getAxes().put(AxisType.X, new CategoryAxis("Years"));
        multiAxisModel.getAxes().put(AxisType.X2, new CategoryAxis("Period"));

        Axis yAxis = multiAxisModel.getAxis(AxisType.Y);
        yAxis.setLabel("Birth");
        yAxis.setMin(0);
        yAxis.setMax(200);

        Axis y2Axis = new LinearAxis("Number");
        y2Axis.setMin(0);
        y2Axis.setMax(200);

        multiAxisModel.getAxes().put(AxisType.Y2, y2Axis);
    }

    private void createOhlcModels() {
        createOhlcModel1();
        createOhlcModel2();
    }

    private void createOhlcModel1() {
        ohlcModel = new OhlcChartModel();

        ohlcModel.add(new OhlcChartSeries(2007, 143.82, 144.56, 136.04, 136.97));
        ohlcModel.add(new OhlcChartSeries(2008, 138.7, 139.68, 135.18, 135.4));
        ohlcModel.add(new OhlcChartSeries(2009, 143.46, 144.66, 139.79, 140.02));
        ohlcModel.add(new OhlcChartSeries(2010, 140.67, 143.56, 132.88, 142.44));
        ohlcModel.add(new OhlcChartSeries(2011, 136.01, 139.5, 134.53, 139.48));
        ohlcModel.add(new OhlcChartSeries(2012, 124.76, 135.9, 124.55, 135.81));
        ohlcModel.add(new OhlcChartSeries(2013, 123.73, 129.31, 121.57, 122.5));

        ohlcModel.setTitle("OHLC Chart");
        ohlcModel.getAxis(AxisType.X).setLabel("Year");
        ohlcModel.getAxis(AxisType.Y).setLabel("Price Change $K/Unit");
    }

    private void createOhlcModel2() {
        ohlcModel2 = new OhlcChartModel();

        for (int i = 1; i < 41; i++) {
            ohlcModel2.add(new OhlcChartSeries(i, Math.random() * 80 + 80, Math.random() * 50 + 110, Math.random() * 20 + 80, Math.random() * 80 + 80));
        }

        ohlcModel2.setTitle("Candlestick");
        ohlcModel2.setCandleStick(true);
        ohlcModel2.getAxis(AxisType.X).setLabel("Sector");
        ohlcModel2.getAxis(AxisType.Y).setLabel("Index Value");
    }

    private void createBubbleModels() {
        bubbleModel1 = initBubbleModel();
        bubbleModel1.setTitle("Bubble Chart");
        bubbleModel1.getAxis(AxisType.X).setLabel("Price");
        Axis yAxis = bubbleModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(250);
        yAxis.setLabel("Labels");

        bubbleModel2 = initBubbleModel();
        bubbleModel2.setTitle("Custom Options");
        bubbleModel2.setShadow(false);
        bubbleModel2.setBubbleGradients(true);
        bubbleModel2.setBubbleAlpha(0.8);
        bubbleModel2.getAxis(AxisType.X).setTickAngle(-50);
        yAxis = bubbleModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(250);
        yAxis.setTickAngle(50);
    }

    private BubbleChartModel initBubbleModel() {
        BubbleChartModel model = new BubbleChartModel();

        model.add(new BubbleChartSeries("Acura", 70, 183, 55));
        model.add(new BubbleChartSeries("Alfa Romeo", 45, 92, 36));
        model.add(new BubbleChartSeries("AM General", 24, 104, 40));
        model.add(new BubbleChartSeries("Bugatti", 50, 123, 60));
        model.add(new BubbleChartSeries("BMW", 15, 89, 25));
        model.add(new BubbleChartSeries("Audi", 40, 180, 80));
        model.add(new BubbleChartSeries("Aston Martin", 70, 70, 48));

        return model;
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");

        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);

        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");

        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);

        model.addSeries(series1);
        model.addSeries(series2);

        return model;
    }

    private void createPieModels() {
        createPieModel1();
        createPieModel2();
        createLivePieModel();
    }

    private void createPieModel1() {
        pieModel1 = new PieChartModel();

        pieModel1.set("Brand 1", 540);
        pieModel1.set("Brand 2", 325);
        pieModel1.set("Brand 3", 702);
        pieModel1.set("Brand 4", 421);

        pieModel1.setTitle("Simple Pie");
        pieModel1.setLegendPosition("w");
        pieModel1.setShadow(false);
    }

    private void createPieModel2() {
        pieModel2 = new PieChartModel();

        pieModel2.set("Brand 1", 540);
        pieModel2.set("Brand 2", 325);
        pieModel2.set("Brand 3", 702);
        pieModel2.set("Brand 4", 421);

        pieModel2.setTitle("Custom Pie");
        pieModel2.setLegendPosition("e");
        pieModel2.setFill(false);
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(150);
        pieModel2.setShadow(false);
    }

    private void createDonutModels() {
        donutModel1 = initDonutModel();
        donutModel1.setTitle("Donut Chart");
        donutModel1.setLegendPosition("w");

        donutModel2 = initDonutModel();
        donutModel2.setTitle("Custom Options");
        donutModel2.setLegendPosition("e");
        donutModel2.setSliceMargin(5);
        donutModel2.setShowDataLabels(true);
        donutModel2.setDataFormat("value");
        donutModel2.setShadow(false);
    }

    private DonutChartModel initDonutModel() {
        DonutChartModel model = new DonutChartModel();

        Map<String, Number> circle1 = new LinkedHashMap<String, Number>();
        circle1.put("Brand 1", 150);
        circle1.put("Brand 2", 400);
        circle1.put("Brand 3", 200);
        circle1.put("Brand 4", 10);
        model.addCircle(circle1);

        Map<String, Number> circle2 = new LinkedHashMap<String, Number>();
        circle2.put("Brand 1", 540);
        circle2.put("Brand 2", 125);
        circle2.put("Brand 3", 702);
        circle2.put("Brand 4", 421);
        model.addCircle(circle2);

        Map<String, Number> circle3 = new LinkedHashMap<String, Number>();
        circle3.put("Brand 1", 40);
        circle3.put("Brand 2", 325);
        circle3.put("Brand 3", 402);
        circle3.put("Brand 4", 421);
        model.addCircle(circle3);

        return model;
    }

    private void createLivePieModel() {
        livePieModel = new PieChartModel();

        livePieModel.set("Candidate 1", 540);
        livePieModel.set("Candidate 2", 325);
    }

    private void createFillToZero() {
        fillToZero = new CartesianChartModel();

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");

        series1.set("4, -3, 3, 6, 2, -2", 0);

        fillToZero.addSeries(series1);
    }

    private MeterGaugeChartModel initMeterGaugeModel() {
        List<Number> intervals = new ArrayList<Number>() {
            {
                add(20);
                add(50);
                add(120);
                add(220);
            }
        };

        return new MeterGaugeChartModel(140, intervals);
    }

    private void createMeterGaugeModels() {
        meterGaugeModel1 = initMeterGaugeModel();
        meterGaugeModel1.setTitle("MeterGauge Chart");
        meterGaugeModel1.setGaugeLabel("km/h");
        meterGaugeModel1.setGaugeLabelPosition("bottom");

        meterGaugeModel2 = initMeterGaugeModel();
        meterGaugeModel2.setTitle("Custom Options");
        meterGaugeModel2.setSeriesColors("66cc66,93b75f,E7E658,cc6666");
        meterGaugeModel2.setGaugeLabel("km/h");
        meterGaugeModel2.setGaugeLabelPosition("bottom");
        meterGaugeModel2.setShowTickLabels(false);
        meterGaugeModel2.setLabelHeightAdjust(110);
        meterGaugeModel2.setIntervalOuterRadius(100);
    }

    private void createAnimatedModels() {
        animatedModel1 = initLinearModel();
        animatedModel1.setTitle("Line Chart");
        animatedModel1.setAnimate(true);
        animatedModel1.setLegendPosition("se");
        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);

        animatedModel2 = initBarModel();
        animatedModel2.setTitle("Bar Charts");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(200);
    }

    private void createDateModel() {
        dateModel = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");

        series1.set("2014-01-01", 51);
        series1.set("2014-01-06", 22);
        series1.set("2014-01-12", 65);
        series1.set("2014-01-18", 74);
        series1.set("2014-01-24", 24);
        series1.set("2014-01-30", 51);

        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");

        series2.set("2014-01-01", 32);
        series2.set("2014-01-06", 73);
        series2.set("2014-01-12", 24);
        series2.set("2014-01-18", 12);
        series2.set("2014-01-24", 74);
        series2.set("2014-01-30", 62);

        dateModel.addSeries(series1);
        dateModel.addSeries(series2);

        dateModel.setTitle("Zoom for Details");
        dateModel.setZoom(true);
        dateModel.getAxis(AxisType.Y).setLabel("Values");
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-50);
        axis.setMax("2014-02-01");
        axis.setTickFormat("%b %#d, %y");

        dateModel.getAxes().put(AxisType.X, axis);
    }

	public List<ResultadosEnto> getListResultadosEnto() {
		return listResultadosEnto;
	}

	public void setListResultadosEnto(List<ResultadosEnto> listResultadosEnto) {
		this.listResultadosEnto = listResultadosEnto;
	}



	public HorizontalBarChartModel getHorizontalBarModel2() {
		return horizontalBarModel2;
	}

	public void setHorizontalBarModel2(HorizontalBarChartModel horizontalBarModel2) {
		this.horizontalBarModel2 = horizontalBarModel2;
	}

	public Deportista getDep() {
		return dep;
	}

	public void setDep(Deportista dep) {
		this.dep = dep;
	}
}

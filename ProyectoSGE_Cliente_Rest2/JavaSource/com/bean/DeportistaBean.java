package com.bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.net.ssl.HttpsURLConnection;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.StreamedContent;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import com.dao.DeportistaDao;
import com.dao.UsuarioDao;
import com.entidades.AsignarEnto;
import com.entidades.Deportista;
import com.entidades.Ejercicios;
import com.entidades.EntEjer;
import com.entidades.Entrenador;
import com.entidades.Entrenamiento;
import com.entidades.ResultadosEnto;
import com.entidades.Usuario;
import com.google.common.io.CharStreams;
import com.servicios.AsignarEntoEjb;
import com.servicios.DeportistaEjb;
import com.servicios.EntrenadorEjb;
import com.servicios.ResultadosEntoEjb;
import com.servicios.UsuarioEjb;
import com.utils.SessionUtils;


@Named("dep")
@ManagedBean
@SessionScoped


public class DeportistaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	DeportistaEjb deportistaEjb;
	@EJB
	DeportistaDao deportistaDao;
	@EJB
	EntrenadorEjb entrenadorEjb;
	@EJB
	UsuarioDao usuarioDao;
	@EJB
	UsuarioEjb usuarioEjb;
	@EJB
	ResultadosEntoEjb resultadosEntoEjb;
	
	@EJB
	AsignarEntoEjb asignarEntoEjb;
	
	
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
	private String archivos;
	private String usuario;
	private Long compPerfil;
	
	
	private String strBuscar;

	private List<Entrenador> entListAll;
	
	
	private UIData entListado;
	private String nomCompleto;
	private List<AsignarEnto> listAsig;
	private AsignarEnto selectAsig;
	private AsignarEnto selectAsig2;
	private List<Ejercicios> listEje;
	private List<Entrenamiento> listEnto ; 
	private Entrenamiento selectEnto ; 
	
	private Integer freqCard;
	private Double velocidad;
	private Float tiempoTotal;
	private String comentario;
	private String upGuardar;
	private Boolean siToken;


	 private ScheduleModel eventModel;
	    
	    private ScheduleModel lazyEventModel;
	 
	    private ScheduleEvent event = new DefaultScheduleEvent();
	 
	    private boolean showWeekends = true;
	    private boolean tooltip = true;
	    private boolean allDaySlot = true;
	 
	    private String timeFormat;
	    private String slotDuration="00:30:00";
	    private String slotLabelInterval;
	    private String scrollTime="06:00:00";
	    private String minTime="04:00:00";
	    private String maxTime="20:00:00";
	    private String locale="en";
	    private String timeZone="";
	    private String clientTimeZone="local";
	    private String columnHeaderFormat="";
	    private String str;

		private String token;

		private String id_usuario;

		private String transaction_id;
		private ResultadosEnto resultadosEnto;

		private Object text3;

		private Deportista dep1;

		private String deporte;
		private String deporteSelect="";
		private List<String> listDeportes = new ArrayList<String>();

		private boolean bolCorrer = false;
		private boolean bolNadar = true;
		private boolean bolBici = false;
		private boolean bolCorrer2 = false;
		private boolean bolNadar2 = true;
  	    private boolean bolBici2 = false;

	public DeportistaBean() {

			}
	
	public void str() {
		
		
	}
 
    @PostConstruct
    public void init() {
    	
    	System.out.println("init DepBean");
    	
    	if(SessionUtils.getRequest().getRequestURI().contains("/ProyectoSGE_Cliente_Rest1/entrenador/")) {
    		System.out.println("if init Dep SessionUtils.getRequest().getRequestURI() No carga graficos");
			
    	}else {
    		
    	System.out.println("init DepBean desde ent");
		 String usuario = (String) SessionUtils.getRequest().getSession(false).getAttribute("usuario");
			 dep1 = deportistaDao.obtenerDeportistaIgual(usuario);
			
			if(dep1.getTokenPolarFlow() != null) {
			
				token = dep1.getTokenPolarFlow();
			id_usuario =String.valueOf(dep1.getUserIdPolarFlow());
			
			System.out.println("RelojInteligenteBean token= " +token);
			System.out.println("RelojInteligenteBean id_usuario= " +id_usuario);
//			cargarDatosPolarFlow();
			
			}else {
				System.out.println("token vacio");
			}

    	siToken = deportistaEjb.isToken();
    	listAsig = deportistaEjb.listarAsigDep();
    	listEnto= new ArrayList<Entrenamiento>();
    	listTodosUsu();
    	
    	listDeportes.add("CORRER");
    	listDeportes.add("BICICLETA");
    	listDeportes.add("NATACION");
    	
//    	System.out.println("init :"+listAsig.get(0).getEntrenador().getNombre()+" size"+listAsig.size());
//    	
//    	System.out.println("init :"+listAsig.get(0).getEntrenamiento().getNombre());
        eventModel = new DefaultScheduleModel();
        
        System.out.println("listAsig size "+listAsig.size());
        for (AsignarEnto a1 : listAsig) {
        	
        	System.out.println("for lista asig");
        	System.out.println("id asig "+a1.getIdAsignarEnto());
        	System.out.println("id idEnt "+a1.getEntrenamiento().getIdEntrenamiento());
        	System.out.println("id Ent size "+a1.getEntrenamiento().getListEntEjer().size());
        	System.out.println("id rep "+a1.getEntrenamiento().getListEntEjer().get(0).getRepeticiones());
//        	System.out.println("fecha Ini :"+a1.getEntrenamiento().getListEntEjer().get(0).getEjercicios().getNombre());
//			System.out.println("fecha Ini :"+a1.getFechaIni());
			listEnto.add(a1.getEntrenamiento());
		}
        
        if(listAsig.size()>0) {
        for (AsignarEnto a : listAsig) {
        	System.out.println("for list asig2");
        	
        	System.out.println(a.getFechaIni());
        	System.out.println(a.getFechaFin());
        	
        	
        	System.out.println("for list asig2");
        	String backColor = null;
        	switch (a.getEntrenamiento().getDeporte()) {
			case "NATACION":
				backColor = "cnat";
				break;
			case "BICICLETA":
				backColor = "cbic";
				break;
			case "CORRER":
				backColor = "ccor";
				break;
			case "GYM Y EQUIPO FITNESS":
				backColor = "cgym";
				break;

			default:
				break;
			}
        	   event = DefaultScheduleEvent.builder()
        			   .title(a.getEntrenamiento().getNombre())
                       .startDate(fechaInicioCal(a.getFechaIni(),1))
                       .endDate(fechaInicioCal(a.getFechaFin(),2))
                       .description(a.getEntrenamiento().getDeporte())
                       .styleClass(backColor)
                                             
                       .build();
        	   
               eventModel.addEvent(event);
        	
		}
        }


 
        lazyEventModel = (ScheduleModel) new LazyScheduleModel() {
             
            public void loadEvents(LocalDateTime start, LocalDateTime end) {
                for (int i=1; i<=5; i++) {
                    LocalDateTime random = getRandomDateTime(start);
                    addEvent(DefaultScheduleEvent.builder().title("Lazy Event " + i).startDate(random).endDate(random.plusHours(3)).build());
                }
            }
        };
    	}
    }

    public void verGrafPlCorrer() {
    	System.out.println("metodo verGrafPlCorrer");
    	
			setBolCorrer(true);
			setBolNadar(false);
			setBolBici(false);
    		}
			 public void verGrafPlBici() {	
				 System.out.println("metodo verGrafPlBici");
			setBolCorrer(false);
			setBolNadar(false);
			setBolBici(true);
			 }
			 
			 public void verGrafPlNadar() {	
				 System.out.println("metodo verGrafPlNadar");
			setBolCorrer(false);
			setBolNadar(true);
			setBolBici(false);
		
    }
			 
			 public void verGrafPlCorrer2() {
			    	System.out.println("metodo verGrafPlCorrer");
			    	
						setBolCorrer2(true);
						setBolNadar2(false);
						setBolBici(false);
			    		}
						 public void verGrafPlBici2() {	
							 System.out.println("metodo verGrafPlBici");
						setBolCorrer2(false);
						setBolNadar2(false);
						setBolBici2(true);
						 }
						 
						 public void verGrafPlNadar2() {	
							 System.out.println("metodo verGrafPlNadar");
						setBolCorrer2(false);
						setBolNadar2(true);
						setBolBici2(false);
					
			    }
    
 public void cargarDatosPolarFlow() {
		
		System.out.println("metodo cargaDatosPolar");
						//ESTE ES UN POST
						//OBTENER TRANSACTION_ID
		
			try {
				
				
						URL urlPOST3 = new URL("https://www.polaraccesslink.com/v3/users/" + id_usuario + "/exercise-transactions");
						try {		
						HttpsURLConnection https3 = (HttpsURLConnection) urlPOST3.openConnection();
						https3.setRequestMethod("POST");
						//HEADERS
						https3.setRequestProperty("Authorization", "Bearer " + token);
						https3.setRequestProperty("Content-Type", "application/json");
						https3.setDoOutput(true);
						https3.connect();
						text3 = null;
					    try (Reader reader3 = new InputStreamReader(https3.getInputStream())) {
					        text3 = CharStreams.toString(reader3);
					    }
					    System.out.println(text3.toString() + " devolucion del id transaction");
					    
					    if(!text3.toString().equals("")) {
					    	
					    
						JSONObject json3 = new JSONObject(text3.toString());
						int transaction_idInt = (int) json3.get("transaction-id");
						transaction_id = String.valueOf(transaction_idInt);
						System.out.println(transaction_id + "      transaction_id");
						System.out.println();	
						System.out.println("----------------HASTA ACA EL POST----------------");
					    }
					    
						//ESTE ES UN GET
						//OBTENER INFO DE USUARIO
						URL urlGET = new URL("https://www.polaraccesslink.com/v3/users/" + id_usuario);
						HttpsURLConnection httpsGet = (HttpsURLConnection) urlGET.openConnection();
						httpsGet.setRequestMethod("GET");
						//HEADERS
						httpsGet.setRequestProperty("Authorization", "Bearer " + token);
						httpsGet.setRequestProperty("Accept", "application/json;charset=UTF-8");
						httpsGet.setDoOutput(true);
						httpsGet.connect();
						BufferedReader inGet = new BufferedReader(
								  new InputStreamReader(httpsGet.getInputStream()));		
						String inputLineGet;
						StringBuffer contentGet = new StringBuffer();
						while ((inputLineGet = inGet.readLine()) != null) {
						    contentGet.append(inputLineGet);		   
						}
						inGet.close();
						JSONObject jsonGet = new JSONObject(contentGet.toString());
						System.out.println(contentGet + "      content");
						System.out.println();
						System.out.println("--------------HASTA ACA EL GET----------------");
					    
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						//ESTE ES UN GET
						//OBTENER EXCERCISE_ID
						
			if(!text3.toString().equals("")) {
							try {
								
								
								URL urlGET2 = new URL("https://www.polaraccesslink.com/v3/users/" + id_usuario + "/exercise-transactions/" + transaction_id);
								try {		
						HttpsURLConnection httpsGet2 = (HttpsURLConnection) urlGET2.openConnection();
						httpsGet2.setRequestMethod("GET");
						//HEADERS
						httpsGet2.setRequestProperty("Authorization", "Bearer " + token);
						httpsGet2.setRequestProperty("Accept", "application/json;charset=UTF-8");
						httpsGet2.setDoOutput(true);
						httpsGet2.connect();
						BufferedReader inGet2 = new BufferedReader(
								  new InputStreamReader(httpsGet2.getInputStream()));		
						String inputLineGet2;
						StringBuffer contentGet2 = new StringBuffer();
						while ((inputLineGet2 = inGet2.readLine()) != null) {
						    contentGet2.append(inputLineGet2);		   
						}
						inGet2.close();
						JSONObject jsonGet2 = new JSONObject(contentGet2.toString());
						JSONArray exercise_lista = jsonGet2.getJSONArray("exercises");
						System.out.println(exercise_lista.toString() + "       ex lista");
						for (int i=0; i < exercise_lista.length(); i++) {
							System.out.println(exercise_lista.getString(i));
							String []partesJsonGet2 = exercise_lista.getString(i).split("exercises/");
							System.out.println(partesJsonGet2[1] + "      exercise_id");
							
							//ESTE ES UN GET
							//OBTENER INFO ENTRENAMIENTO
							URL urlGET3 = new URL("https://www.polaraccesslink.com/v3/users/" + id_usuario + "/exercise-transactions/" + transaction_id + "/exercises/" + partesJsonGet2[1]);
							HttpsURLConnection httpsGet3 = (HttpsURLConnection) urlGET3.openConnection();
							httpsGet3.setRequestMethod("GET");
							//HEADERS
							httpsGet3.setRequestProperty("Authorization", "Bearer " + token);
							httpsGet3.setRequestProperty("Accept", "application/json;charset=UTF-8");
							httpsGet3.setDoOutput(true);
							httpsGet3.connect();
							BufferedReader inGet3 = new BufferedReader(
									  new InputStreamReader(httpsGet3.getInputStream()));		
							String inputLineGet3;
							StringBuffer contentGet3 = new StringBuffer();
							while ((inputLineGet3 = inGet3.readLine()) != null) {
							    contentGet3.append(inputLineGet3);		   
							}
							inGet3.close();
							JSONObject jsonGet3 = new JSONObject(contentGet3.toString());
							String deporte = (String) jsonGet3.get("sport");

							double distancia = 0;
							if(jsonGet3.toString().contains("distance")) {
								distancia = (double) jsonGet3.get("distance");
							}
							
							String tiempo = (String) jsonGet3.get("duration");
							
							System.out.println("Todo el json    " + jsonGet3.toString());
							
							String[] tiempo1 = tiempo.split("PT");
							System.out.println(tiempo1[0] + "P1 " + tiempo1[1] + " P2");
							String[] tiempo2 = null;
							String[] tiempo3 = null;
							String[] tiempo4 = null;
							if(tiempo1[1].contains("H")) {
								tiempo2 = tiempo1[1].split("H");
								tiempo3 = tiempo2[1].split("M");
								tiempo4 = tiempo3[1].split("S");
							}else if(tiempo1[1].contains("M")) {
								tiempo2 = new String[1];
								tiempo2[0] = "0";
								tiempo3 = tiempo1[1].split("M");
								tiempo4 = tiempo3[1].split("S");
							}else {
								tiempo2 = new String[1];
								tiempo2[0] = "0";
								tiempo3 = new String[1];
								tiempo3[0] = "0";
								tiempo4 = tiempo1[1].split("S");
							}
							
							int horas = Integer.parseInt(tiempo2[0]);
							int minutos = Integer.parseInt(tiempo3[0]);
							double segundos = Double.parseDouble(tiempo4[0]);
							double tiempoH = horas + minutos/60 + segundos/60/60;
							double tiempoM = horas*60 + minutos + segundos/60;
							double velocidad = 0;
							int velocidadEntero = 0;
							double velocidadSeg = 0;
							System.out.println(deporte);
							
							if(deporte == "CYCLING") {
								velocidadSeg = (distancia/1000)/tiempoH;
								System.out.println("La velocidad es de " + velocidadSeg + "km/h");
							} else if(deporte == "SWIMMING") {
								velocidad = tiempoM/distancia*100;
								velocidadEntero = (int) velocidad;
								velocidadSeg = (velocidad - velocidadEntero)*60;
								System.out.println("La velocidad es de " + velocidadEntero + "min" + velocidadSeg + "seg/100m");
							} else if(deporte.equals("RUNNING")) {
								velocidad = tiempoM/(distancia/1000);
								System.out.println(velocidad);
								velocidadEntero = (int) velocidad;
								velocidadSeg = (velocidad - velocidadEntero)*60;
								System.out.println("La velocidad es de " + velocidadEntero + "min" + velocidadSeg + "seg/km");
							}
							
							java.time.Duration d = java.time.Duration.parse(tiempo);
							System.out.println("Duration in seconds: " + d.get(java.time.temporal.ChronoUnit.SECONDS));
							
							String usuario = (String) SessionUtils.getRequest().getSession(false).getAttribute("usuario");
							long tiempoTotal = d.get(java.time.temporal.ChronoUnit.SECONDS);
							Date fecha = new Date();
							Long polarFlow = (long)1;
							freqCard = 0;
							
							resultadosEnto = new ResultadosEnto(fecha , freqCard, velocidadSeg, usuario, tiempoTotal, polarFlow , deporte );
							resultadosEntoEjb.guardarResultadosEntoEJB(resultadosEnto);
							
							System.out.println("Distancia: " + distancia + " Tiempo: " + tiempo + "   info ejercicio");
							System.out.println();
							System.out.println("--------------HASTA ACA EL GET----------------");
							
						}
						
						System.out.println();
						System.out.println("--------------HASTA ACA EL GET----------------");
						
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
									
							
			}
	
		
	}
		
		
    public void listarEntoEjerTabla() {
    	
    	List<EntEjer> listarEntoEjerTabla = selectAsig.getEntrenamiento().getListEntEjer();
    	
        for (EntEjer a1 : listarEntoEjerTabla) {
        	
        	System.out.println("for lista asig");
        	System.out.println("id idEnt "+a1.getEntrenamiento().getIdEntrenamiento());
        	System.out.println("id Ent size "+a1.getEntrenamiento().getListEntEjer().size());
        	System.out.println("id rep "+a1.getEntrenamiento().getListEntEjer().get(0).getRepeticiones());
//        	System.out.println("fecha Ini :"+a1.getEntrenamiento().getListEntEjer().get(0).getEjercicios().getNombre());
//			System.out.println("fecha Ini :"+a1.getFechaIni());
			
		}
    }
 
    public String guardarAsignarEnto() {
    	String str="";
    	Boolean bool=false;
    	upGuardar="";
    	
    
    	try {
    		
    		bool=asignarEntoEjb.guardarAsignarEntoBean(selectAsig, freqCard,velocidad,comentario);
    		if(bool) {
    			upGuardar="@all";
    			str="deportista/verEntrenamientos";
    			
    		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			mostMsjGrowl("Incorrecto no se logro enviar los datos de entrenaminto, intente mas tarde");
			e.printStackTrace();
		}
    	System.out.println("str guardarAsig"+str);
		return str;
     	
    	
    }
    
    
    private LocalDateTime fechaInicioCal(Date date, long n) {
    	
    	System.out.println("date "+date);
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	System.out.println("cal "+cal);
    	long dias=0;
    	
    	if(n==2) {
    	 dias = cal.get(Calendar.DAY_OF_YEAR)+1;
    	}
    	else {
    		 dias = cal.get(Calendar.DAY_OF_YEAR);
    	}
    	
    	Calendar cal3 = Calendar.getInstance();
    	Date f1=new Date();
    	cal3.setTime(f1);
    	
    	long dias3 = cal3.get(Calendar.DAY_OF_YEAR);
    	System.out.println("dias "+dias);
    	System.out.println("dias3 "+dias3);
    	
    	long fechaIni=dias-dias3;
    	
    	if(fechaIni>0) {
    		
    		System.out.println("if 1 "+fechaIni);
    		System.out.println("LocalDateTime.now().plusDays(fechaIni) "+LocalDateTime.now().plusDays(fechaIni).withHour(0).withMinute(0).withSecond(0).withNano(0).toString());

    		
        return LocalDateTime.now().plusDays(fechaIni).withHour(0).withMinute(0).withSecond(0).withNano(0);
        
    	}
    	if(fechaIni==0) {
    		
    		System.out.println("if 2 "+fechaIni);
    		
    		System.out.println("LocalDateTime.now().plusDays(-1) "+LocalDateTime.now().plusDays(fechaIni).withHour(0).withMinute(0).withSecond(0).withNano(0).toString());

    		return LocalDateTime.now().plusDays(-1).withHour(10).withMinute(0).withSecond(0).withNano(0);
            
        	}else {
        		System.out.println("if 3 "+fechaIni);
        		System.out.println("LocalDateTime.now().plusDays(fechaIni) "+LocalDateTime.now().plusDays(fechaIni).withHour(0).withMinute(0).withSecond(0).withNano(0).toString());

    		return LocalDateTime.now().plusDays(fechaIni).withHour(0).withMinute(0).withSecond(0).withNano(0);
    	}
    }
//    private LocalDateTime fechaFin(Date date) {
//    	
//        return LocalDateTime.now().minusDays(1).withHour(20).withMinute(0).withSecond(0).withNano(0);
//    }
    
	public String guardarPerfilBean()
	{
		String str=null;
		try {
			str=deportistaEjb.guardarPerfilEjb(sociedadMedica, peso, altura, dispHoraria
							, alergias, lesiones, probSalud, obejtivos, ferqCardMax, ferqCardMin,nomCompleto);
			System.out.println("guardPerff "+str);
			return str;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No se guardo correctamente el perfil. Vuelva a intentar", ""));
			e.printStackTrace();
		}
		return str;
	}
	

	public String listTodosUsu(){
				
		List<Entrenador> entListAll2 = null;
		System.out.println("str antes del primer if" + strBuscar);
		
		entListAll2 = entrenadorEjb.obtenerTodos();
		
		
		if (entListAll2.size() == 0) {
			entListAll2 = null;
			System.out.println("entro 2 if");
			mostMsjUsuNoEx();
		}else {
			
			entListAll = entListAll2;
		}
		
		
		return null;
	}
	
	public String filtroEnt() {

		List<Entrenador> entListAll2 = null;
		System.out.println("str antes del primer if" + strBuscar);
		
		entListAll2 = entrenadorEjb.filtroEntrenador2(strBuscar);
		
		
		if (entListAll2.size() == 0) {
			entListAll2 = null;
			System.out.println("entro 2 if");
			mostMsjUsuNoEx();
		}else {
			
			entListAll = entListAll2;
		}
		
		
		return null;
	}
	
	public String eliminarDepBean() {
		
		try {
			deportistaEjb.borrar();
			return "login2";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		
	}
	
//	public void selecionEnt() {
//		
//		try {
//			deportistaEjb.seleccionarEnt(entListado);
//		} catch (SQLException e) {
//			mostMsj("Debe selecionar e");
//			e.printStackTrace();
//		}
//		
//	}

	
	public void mostMsjGrowl(String msj) {
		FacesContext.getCurrentInstance().addMessage("growl",
				new FacesMessage(FacesMessage.SEVERITY_INFO, msj, ""));
		
		
	}
	
	public void mostMsjUsuNoEx() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "El Entrenador solicitado, no fue encontrado en el sistema.  Por favor realice nuevamente la búsqueda"));
}
	public void mostMsj(String msj) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", msj));
}
	
	public void mostMsjIngDat() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Debe ingresar un Entrenador "));
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

	public List<AsignarEnto> getListAsig() {
		return listAsig;
	}



	public void setListAsig(List<AsignarEnto> listAsig) {
		this.listAsig = listAsig;
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

	public String getArchivos() {
		return archivos;
	}

	public void setArchivos(String archivos) {
		this.archivos = archivos;
	}
	
	
	public String getNomCompleto() {
		return nomCompleto;
	}



	public void setNomCompleto(String nomCompleto) {
		this.nomCompleto = nomCompleto;
	}



	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Long getCompPerfil() {
		return compPerfil;
	}

	public void setCompPerfil(Long compPerfil) {
		this.compPerfil = compPerfil;
	}

	public String getStrBuscar() {
		return strBuscar;
	}

	public void setStrBuscar(String strBuscar) {
		this.strBuscar = strBuscar;
	}



	public List<Entrenador> getEntListAll() {
		return entListAll;
	}



	public void setEntListAll(List<Entrenador> entListAll) {
		this.entListAll = entListAll;
	}



	public UIData getEntListado() {
		return entListado;
	}



	public void setEntListado(UIData entListado) {
		this.entListado = entListado;
	}

	 public LocalDateTime getRandomDateTime(LocalDateTime base) {
	        LocalDateTime dateTime = base.withMinute(0).withSecond(0).withNano(0);
	        return dateTime.plusDays(((int) (Math.random()*30)));
	    }
	     
	 
	    public ScheduleModel getEventModel() {
	        return eventModel;
	    }
	     
	    public ScheduleModel getLazyEventModel() {
	        return lazyEventModel;
	    }
	 

	    private LocalDateTime previousDay8Pm() {
	        return LocalDateTime.now().minusDays(1).withHour(20).withMinute(0).withSecond(0).withNano(0);
	    }
	     
	    private LocalDateTime previousDay11Pm() {
	        return LocalDateTime.now().minusDays(1).withHour(23).withMinute(0).withSecond(0).withNano(0);
	    }
	     
	    private LocalDateTime today1Pm() {
	        return LocalDateTime.now().withHour(13).withMinute(0).withSecond(0).withNano(0);
	    }
	     
	    private LocalDateTime theDayAfter3Pm() {
	        return LocalDateTime.now().plusDays(1).withHour(15).withMinute(0).withSecond(0).withNano(0);
	    }
	 
	    private LocalDateTime today6Pm() {
	        return LocalDateTime.now().withHour(18).withMinute(0).withSecond(0).withNano(0);
	    }
	     
	    private LocalDateTime nextDay9Am() {
	        return LocalDateTime.now().plusDays(1).withHour(9).withMinute(0).withSecond(0).withNano(0);
	    }
	     
	    private LocalDateTime nextDay11Am() {
	        return LocalDateTime.now().plusDays(1).withHour(11).withMinute(0).withSecond(0).withNano(0);
	    }
	     
	    private LocalDateTime fourDaysLater3pm() {
	        return LocalDateTime.now().plusDays(4).withHour(15).withMinute(0).withSecond(0).withNano(0);
	    }
	 
	    private LocalDateTime sevenDaysLater0am() {
	        return LocalDateTime.now().plusDays(7).withHour(0).withMinute(0).withSecond(0).withNano(0);
	    }
	 
	    private LocalDateTime eightDaysLater0am() {
	        return LocalDateTime.now().plusDays(7).withHour(0).withMinute(0).withSecond(0).withNano(0);
	    }
	     
	    public LocalDate getInitialDate() {
	        return LocalDate.now().plusDays(1);
	    }
	 
	    public ScheduleEvent getEvent() {
	        return event;
	    }
	 
	    public void setEvent(ScheduleEvent event) {
	        this.event = event;
	    }
	     
	    public void addEvent() {
	        if (event.isAllDay()) {
	            //see https://github.com/primefaces/primefaces/issues/1164
	            if (event.getStartDate().toLocalDate().equals(event.getEndDate().toLocalDate())) {
	                event.setEndDate(event.getEndDate().plusDays(1));
	            }
	        }
	 
	        if(event.getId() == null)
	            eventModel.addEvent(event);
	        else
	            eventModel.updateEvent(event);
	         
	        event = new DefaultScheduleEvent();
	    }
	     
	    public void onEventSelect(SelectEvent<ScheduleEvent> selectEvent) {
	        event = selectEvent.getObject();
	    }
	     
	    public void onDateSelect(SelectEvent<LocalDateTime> selectEvent) {
	        event = DefaultScheduleEvent.builder().startDate(selectEvent.getObject()).endDate(selectEvent.getObject().plusHours(1)).build();
	    }
	     
	    public void onEventMove(ScheduleEntryMoveEvent event) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Delta:" + event.getDeltaAsDuration());
	         
	        addMessage(message);
	    }
	     
	    public void onEventResize(ScheduleEntryResizeEvent event) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Start-Delta:" + event.getDeltaStartAsDuration() + ", End-Delta: " + event.getDeltaEndAsDuration());
	         
	        addMessage(message);
	    }
	     
	    private void addMessage(FacesMessage message) {
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
	 
	    public boolean isShowWeekends() {
	        return showWeekends;
	    }
	 
	    public void setShowWeekends(boolean showWeekends) {
	        this.showWeekends = showWeekends;
	    }
	 
	    public boolean isTooltip() {
	        return tooltip;
	    }
	 
	    public void setTooltip(boolean tooltip) {
	        this.tooltip = tooltip;
	    }
	 
	    public boolean isAllDaySlot() {
	        return allDaySlot;
	    }
	 
	    public void setAllDaySlot(boolean allDaySlot) {
	        this.allDaySlot = allDaySlot;
	    }
	 
	    public String getTimeFormat() {
	        return timeFormat;
	    }
	 
	    public void setTimeFormat(String timeFormat) {
	        this.timeFormat = timeFormat;
	    }
	 
	    public String getSlotDuration() {
	        return slotDuration;
	    }
	 
	    public void setSlotDuration(String slotDuration) {
	        this.slotDuration = slotDuration;
	    }
	 
	    public String getSlotLabelInterval() {
	        return slotLabelInterval;
	    }
	 
	    public void setSlotLabelInterval(String slotLabelInterval) {
	        this.slotLabelInterval = slotLabelInterval;
	    }
	 
	    public String getScrollTime() {
	        return scrollTime;
	    }
	 
	    public void setScrollTime(String scrollTime) {
	        this.scrollTime = scrollTime;
	    }
	 
	    public String getMinTime() {
	        return minTime;
	    }
	 
	    public void setMinTime(String minTime) {
	        this.minTime = minTime;
	    }
	 
	    public String getMaxTime() {
	        return maxTime;
	    }
	 
	    public void setMaxTime(String maxTime) {
	        this.maxTime = maxTime;
	    }
	 
	    public String getLocale() {
	        return locale;
	    }
	 
	    public void setLocale(String locale) {
	        this.locale = locale;
	    }
	 
	    public String getTimeZone() {
	        return timeZone;
	    }
	 
	    public void setTimeZone(String timeZone) {
	        this.timeZone = timeZone;
	    }
	 
	    public String getClientTimeZone() {
	        return clientTimeZone;
	    }
	 
	    public void setClientTimeZone(String clientTimeZone) {
	        this.clientTimeZone = clientTimeZone;
	    }
	 
	    public String getColumnHeaderFormat() {
	        return columnHeaderFormat;
	    }
	 
	    public void setColumnHeaderFormat(String columnHeaderFormat) {
	        this.columnHeaderFormat = columnHeaderFormat;
	    }
	    
	    public static LocalDateTime asLocalDateTime(Date date) { 
	        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime(); 
	        }



		public AsignarEnto getSelectAsig() {
			return selectAsig;
		}



		public void setSelectAsig(AsignarEnto selectAsig) {
			this.selectAsig = selectAsig;
		}



		public List<Ejercicios> getListEje() {
			return listEje;
		}



		public void setListEje(List<Ejercicios> listEje) {
			this.listEje = listEje;
		}



		public List<Entrenamiento> getListEnto() {
			return listEnto;
		}



		public void setListEnto(List<Entrenamiento> listEnto) {
			this.listEnto = listEnto;
		}
		public Entrenamiento getSelectEnto() {
			return selectEnto;
		}
		public void setSelectEnto(Entrenamiento selectEnto) {
			this.selectEnto = selectEnto;
		}

		public String getStr() {
			return str;
		}

		public void setStr(String str) {
			this.str = str;
		}

		public AsignarEnto getSelectAsig2() {
			return selectAsig2;
		}

		public void setSelectAsig2(AsignarEnto selectAsig2) {
			this.selectAsig2 = selectAsig2;
		}

		public Integer getFreqCard() {
			return freqCard;
		}

		public void setFreqCard(Integer freqCard) {
			this.freqCard = freqCard;
		}



		public Float getTiempoTotal() {
			return tiempoTotal;
		}

		public void setTiempoTotal(Float tiempoTotal) {
			this.tiempoTotal = tiempoTotal;
		}

		public String getComentario() {
			return comentario;
		}

		public void setComentario(String comentario) {
			this.comentario = comentario;
		}

		public String getUpGuardar() {
			return upGuardar;
		}

		public void setUpGuardar(String upGuardar) {
			this.upGuardar = upGuardar;
		}

		public Boolean getSiToken() {
			return siToken;
		}

		public void setSiToken(Boolean siToken) {
			this.siToken = siToken;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public String getId_usuario() {
			return id_usuario;
		}

		public void setId_usuario(String id_usuario) {
			this.id_usuario = id_usuario;
		}

		public String getTransaction_id() {
			return transaction_id;
		}

		public void setTransaction_id(String transaction_id) {
			this.transaction_id = transaction_id;
		}

		public Double getVelocidad() {
			return velocidad;
		}

		public void setVelocidad(Double velocidad) {
			this.velocidad = velocidad;
		}

		public Deportista getDep1() {
			return dep1;
		}

		public void setDep1(Deportista dep1) {
			this.dep1 = dep1;
		}

		public String getDeporte() {
			return deporte;
		}

		public void setDeporte(String deporte) {
			this.deporte = deporte;
		}


		public boolean isBolCorrer() {
			return bolCorrer;
		}

		public void setBolCorrer(boolean bolCorrer) {
			this.bolCorrer = bolCorrer;
		}

		public boolean isBolNadar() {
			return bolNadar;
		}

		public void setBolNadar(boolean bolNadar) {
			this.bolNadar = bolNadar;
		}

		public boolean isBolBici() {
			return bolBici;
		}

		public void setBolBici(boolean bolBici) {
			this.bolBici = bolBici;
		}

		public String getDeporteSelect() {
			return deporteSelect;
		}

		public void setDeporteSelect(String deporteSelect) {
			this.deporteSelect = deporteSelect;
		}

		public List<String> getListDeportes() {
			return listDeportes;
		}

		public void setListDeportes(List<String> listDeportes) {
			this.listDeportes = listDeportes;
		}

		public boolean isBolCorrer2() {
			return bolCorrer2;
		}

		public void setBolCorrer2(boolean bolCorrer2) {
			this.bolCorrer2 = bolCorrer2;
		}

		public boolean isBolNadar2() {
			return bolNadar2;
		}

		public void setBolNadar2(boolean bolNadar2) {
			this.bolNadar2 = bolNadar2;
		}

		public boolean isBolBici2() {
			return bolBici2;
		}

		public void setBolBici2(boolean bolBici2) {
			this.bolBici2 = bolBici2;
		}

 

}

package com.bean;

import java.io.InputStream;
import java.io.Serializable;
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

import com.dao.DeportistaDao;
import com.dao.UsuarioDao;
import com.entidades.AsignarEnto;
import com.entidades.Deportista;
import com.entidades.Ejercicios;
import com.entidades.Entrenador;
import com.entidades.Entrenamiento;
import com.entidades.Usuario;
import com.servicios.AsignarEntoEjb;
import com.servicios.DeportistaEjb;
import com.servicios.EntrenadorEjb;
import com.servicios.UsuarioEjb;


@Named("dep")
@ViewScoped


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
	
	private Float freqCard;
	private Float velocidad;
	private Float tiempoTotal;
	private String comentario;
	private String upGuardar;

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


	public DeportistaBean() {

			}
	
	public void str() {
		
		
	}
	

	

 
    @PostConstruct
    public void init() {
    	
    	
//    	listAsig= asignarEntoEjb.listarAsigDep();
    	listAsig = deportistaEjb.listarAsigDep();
    	listEnto= new ArrayList<Entrenamiento>();
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

        	
        	
        	System.out.println("for list asig2");
        	   event = DefaultScheduleEvent.builder()
        			   .title(a.getEntrenamiento().getNombre())
                       .startDate(fechaInicioCal(a.getFechaIni(),1))
                       .endDate(fechaInicioCal(a.getFechaFin(),2))
                       .description(a.getEntrenamiento().getDeporte())
                                             
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
 
    public void guardarAsignarEnto() {
    	Boolean bool=false;
    	upGuardar="";
    	try {
    		
    		bool=asignarEntoEjb.guardarAsignarEntoBean(selectAsig, freqCard,velocidad,tiempoTotal,comentario);
    		if(bool) {
    			upGuardar="@all";
    		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			mostMsjGrowl("Incorrecto no se logro enviar los datos de entrenaminto, intente mas tarde");
			e.printStackTrace();
		}
    	
    	
    	
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
        return LocalDateTime.now().plusDays(fechaIni).withHour(0).withMinute(0).withSecond(0).withNano(0);
        
    	}
    	if(fechaIni==0) {
    		System.out.println("if 2 "+fechaIni);
            return LocalDateTime.now().withHour(1).withMinute(0).withSecond(0).withNano(0);
            
        	}else {
        		System.out.println("if 3 "+fechaIni);
    		return LocalDateTime.now().minusDays(fechaIni).withHour(0).withMinute(0).withSecond(0).withNano(0);
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

	public List<Entrenador> listTodosUsu(){
				
		return entListAll = entrenadorEjb.obtenerTodos();
		
	}
	
	public String filtroEnt() {

		entListAll = null;
		System.out.println("str antes del primer if" + strBuscar);
		
		entListAll = entrenadorEjb.filtroEntrenador2(strBuscar);
		
		
		if (entListAll.size() == 0) {
			entListAll = null;
			System.out.println("entro 2 if");
			mostMsjUsuNoEx();
		}
		
		return null;
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

		public Float getFreqCard() {
			return freqCard;
		}

		public void setFreqCard(Float freqCard) {
			this.freqCard = freqCard;
		}

		public Float getVelocidad() {
			return velocidad;
		}

		public void setVelocidad(Float velocidad) {
			this.velocidad = velocidad;
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

 

}

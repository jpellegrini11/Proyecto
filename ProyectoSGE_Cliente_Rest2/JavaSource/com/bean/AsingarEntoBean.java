 package com.bean;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import com.entidades.AsignarEnto;
import com.entidades.Deportista;
import com.entidades.Entrena;
import com.entidades.Entrenamiento;
import com.servicios.AsignarEntoEjb;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named("asignar")
@ManagedBean
@SessionScoped
public class AsingarEntoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Entrenamiento> listEnto;
	private Entrenamiento selectEnto2 = new Entrenamiento();
	private UIData selectEnto;
	private String buscar;
	private DualListModel<String> dualListDep;
	private List<Entrena> listEntrena2;
	private Date fechaIni;
	private Date fechaFin;
	private Float freqCard;
	private Float velocidad;
	private Float tiempoTotal;
	private Entrena entrena;
	private AsignarEnto selectAsignarEnto;
	private List<AsignarEnto> listAsignarEnto;
	private List<AsignarEnto> listSelectAsignarEnto;
	private Integer numSuma =-1;
	
	@EJB
	AsignarEntoEjb asignarEntoEjb;

	private List<Deportista> listDep;


	
	@PostConstruct
	public void init() {
		
		
		System.out.println("Inicio asigBean");
//		listAsignarEnto = asignarEntoEjb.listarAsigDep();
//		System.out.println("1"+listAsignarEnto.size());
//		System.out.println("2"+listAsignarEnto.get(3).getVelocidad());
//		System.out.println("3"+listAsignarEnto.get(3).getEntrenamiento().getNombre());
//		System.out.println("4"+listAsignarEnto.get(3).getListDeportista().size());
//		
		
		
//		AsignarEnto asig= asignarEntoEjb.ob
//				.obtenerAsignarEnto((long) 19);
//		System.out.println(asig.getListDeportista().get(0).getAlergias());
		
		listEnto = asignarEntoEjb.listarEntrenamientoEjb();
		
		
	     List<String> source = new ArrayList<String>();
	     
	     listDep = asignarEntoEjb.listarDeportistaEjb();
	   
	     if(listDep.size()>0) {
	     System.out.println("asignar listDep= "+listDep.size());
	     }
	     source=asignarEntoEjb.listarDepEntrenaEjb();

	        List<String> target = new ArrayList<String>();
	  
	        dualListDep = new DualListModel<String>(source, target);
	        System.out.println("size citnes: "+dualListDep.getSource().size());
	}
	
	public void navAsignarDep() {
		numSuma=-1;
		System.out.println("met nav :"+numSuma);
		
	}
	
	public void cargaSumaFotas() {
		numSuma = numSuma+1;
		System.out.println("numSuma ="+numSuma);
	}
	
	public void filtroEnto() {
		
		
	}
	
	public String seleccionarEntoBean() {
		String str="";
		System.out.println(dualListDep.getTarget().size());
//	for (int i = 0; i < dualListDep.getTarget().size(); i++) {
//		System.out.println(dualListDep.getTarget().get(i).toString());
		
		freqCard=(float) 0;
		velocidad=(float) 0;
		tiempoTotal=(float) 0;
		
		try {
			asignarEntoEjb.seleccionarEntoBeanEjb(dualListDep.getTarget(), selectEnto2, fechaIni, fechaFin, freqCard, velocidad, tiempoTotal);
			
		} catch (SQLException e) {
			mostMsjGrowl("Error, el entrenamiento:" +selectEnto2.getNombre() +", no se asigno. Vuelva a intentarlo mas tarde ");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
//	}
		
		return str;
	}
	
	
	public void mostMsjGrowl(String msj) {
		FacesContext.getCurrentInstance().addMessage("growl",
				new FacesMessage(FacesMessage.SEVERITY_INFO, msj, ""));
		
		
	}
	
  

  

	public List<Entrenamiento> getListEnto() {
		return listEnto;
	}

	public void setListEnto(List<Entrenamiento> listEnto) {
		this.listEnto = listEnto;
	}



	public String getBuscar() {
		return buscar;
	}

	public void setBuscar(String buscar) {
		this.buscar = buscar;
	}

	public Entrenamiento getSelectEnto2() {
		return selectEnto2;
	}

	public void setSelectEnto2(Entrenamiento selectEnto2) {
		this.selectEnto2 = selectEnto2;
	}

	public UIData getSelectEnto() {
		return selectEnto;
	}

	public void setSelectEnto(UIData selectEnto) {
		this.selectEnto = selectEnto;
	}


	public Entrena getEntrena() {
		return entrena;
	}

	public void setEntrena(Entrena entrena) {
		this.entrena = entrena;
	}

	public DualListModel<String> getDualListDep() {
		return dualListDep;
	}

	public void setDualListDep(DualListModel<String> dualListDep) {
		this.dualListDep = dualListDep;
	}

	public List<Entrena> getListEntrena2() {
		return listEntrena2;
	}

	public void setListEntrena2(List<Entrena> listEntrena2) {
		this.listEntrena2 = listEntrena2;
	}

	public Date getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
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

	public AsignarEnto getSelectAsignarEnto() {
		return selectAsignarEnto;
	}

	public void setSelectAsignarEnto(AsignarEnto selectAsignarEnto) {
		this.selectAsignarEnto = selectAsignarEnto;
	}

	public List<AsignarEnto> getListAsignarEnto() {
		return listAsignarEnto;
	}

	public void setListAsignarEnto(List<AsignarEnto> listAsignarEnto) {
		this.listAsignarEnto = listAsignarEnto;
	}

	public List<AsignarEnto> getListSelectAsignarEnto() {
		return listSelectAsignarEnto;
	}

	public void setListSelectAsignarEnto(List<AsignarEnto> listSelectAsignarEnto) {
		this.listSelectAsignarEnto = listSelectAsignarEnto;
	}

	public Integer getNumSuma() {
		return numSuma;
	}

	public void setNumSuma(Integer numSuma) {
		this.numSuma = numSuma;
	}

	public List<Deportista> getListDep() {
		return listDep;
	}

	public void setListDep(List<Deportista> listDep) {
		this.listDep = listDep;
	}

	
}

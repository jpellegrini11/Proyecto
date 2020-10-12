package com.servicios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;

import com.dao.AsignarEntoDao;
import com.dao.DeportistaDao;
import com.dao.EntrenadorDao;
import com.entidades.Asignar;
import com.entidades.AsignarEnto;
import com.entidades.Deportista;
import com.entidades.Entrena;
import com.entidades.Entrenador;
import com.entidades.Entrenamiento;
import com.utils.SessionUtils;

@Stateless
@LocalBean
public class AsignarEntoEjb {
	
	private List<Entrenamiento> listEnto;
	private Entrenador entrenador;
	private List<Entrena> listEntrena;
	
	private List<String> source;
	private HashMap<String ,String> listNomIndex= new HashMap<String, String>();
	private List<Deportista> listDep;
	private AsignarEnto asignarEnto;
	private List<AsignarEnto> listAsignarEnto;
	private List<AsignarEnto> listAsignarEnto2;
	
	@EJB
	EntrenadorDao entrenadorDao;
	@EJB
	DeportistaDao deportistaDao;
	@EJB
	AsignarEntoDao asignarEntoDao;


	
	public Boolean guardarAsignarEntoBean(AsignarEnto asignarEnto ,
			Float freqCard, Float velocidad, Float tiempoTotal, String comentarios) throws SQLException {
		
		Boolean bool=false;
		
		if(freqCard==null || velocidad ==null || tiempoTotal==null) {
			
			mostMsjGrowl("Faltan datos, ingrese freq, velocidad y tiempo");
			
		}else {
			
		
		
	
			
			asignarEnto = asignarEntoDao.obtenerAsignarEnto(asignarEnto.getIdAsignarEnto());
			asignarEnto.setFreqCard(freqCard);
			asignarEnto.setVelocidad(velocidad);
			asignarEnto.setTiempoTotal(tiempoTotal);
			asignarEnto.setComentarios(comentarios);
			
			asignarEntoDao.actualizarAsignarEnto(asignarEnto);
			mostMsjGrowl("Correcto, los datos se enviaron de tu entrenador");
			bool=true;
		
		}
		return bool;

	}
	//Listar todos los deportista que se les asigno entrenamiento, pero se carga otra lista con solo los entrenamientos 
	//designados para ese deportista
	public List<AsignarEnto> listarAsigDep(){
		
//		AsignarEnto asig= asignarEntoDao.obtenerAsignarEnto((long) 19);
//		System.out.println(asig.getListDeportista().get(0).getAlergias());
		
		listAsignarEnto = asignarEntoDao.obternerTodos();
		listAsignarEnto2= new ArrayList<AsignarEnto>();
		System.out.println(listAsignarEnto.size());
		System.out.println(listAsignarEnto.size());
		System.out.println(listAsignarEnto.get(0).getListDeportista().get(0).getAlergias());
		String usuario = (String) SessionUtils.getRequest().getSession(false).getAttribute("usuario");
		System.out.println("antes for asig");
		int i=0;
		for (AsignarEnto a : listAsignarEnto) {
			
			System.out.println("for asig");
			if(a.getListDeportista().get(i).getUsuario().equals(usuario)) {
				listAsignarEnto2.add(a);
				System.out.println("for dentro asig");

			}
			i++;
		}
		
		return listAsignarEnto;
		
		
	}
	
	public  List<Entrenamiento> listarEntrenamientoEjb(){
		
		String usuario = (String) SessionUtils.getRequest().getSession(false).getAttribute("usuario");
		
		entrenador = entrenadorDao.obtenerEntrenadorIgual(usuario);
		listEnto=entrenador.getListEntrenamiento();

		System.out.println("listDep Size "+entrenador.getListDep().size());
		System.out.println("listEntrena Size "+entrenador.getListEntrena().size());
		
		return listEnto;
	}
	
	//Lista los deportistas que pertenecen al entrenador, para mostrarlos en el pick de asignarDep
	public  List<String> listarDepEntrenaEjb(){
		
		String usuario = (String) SessionUtils.getRequest().getSession(false).getAttribute("usuario");
		
		entrenador = entrenadorDao.obtenerEntrenadorIgual(usuario);
		listEntrena=entrenador.getListEntrena();
		System.out.println("listarDepEntrenaEjb "+listEntrena.size() );
		
		source = new ArrayList<String>();
	     for (Entrena e : listEntrena) {
	    	 int n=0;
	    	 n++;
	    	 
	    	 String index=String.valueOf(n);
	    	 System.out.println("index "+index);
	    	 String nomIndex=index+" "+e.getDeportista().getNomCompleto().toString();
	    	 listNomIndex.put(nomIndex, e.getDeportista().getUsuario());
	    	 System.out.println(nomIndex);
	    	
	    	 source.add(nomIndex);
	    	 
	 
		}

	     
		return source;
		
		
	}
	
	//Datos del entrenamieto que selecciona el entrenador para luego asignar a deportistas en el pick.
	public  String seleccionarEntoBeanEjb(List<String> target,Entrenamiento selectEnto,Date fechaIni,
			Date fechaFin, Float freqCard, Float velocidad, Float tiempoTotal) throws SQLException {
		
		String usu =null;
		listDep= new ArrayList<Deportista>();
//		Entrenamiento entrenamiento= (Entrenamiento) selectEnto.getRowData();
		
//		System.out.println("entrenamiento desde pick "+entrenamiento.getNombre());
		
		for (String s : target) {
			
			usu=listNomIndex.get(s.toString());
			System.out.println("usuario target: "+usu);
			Deportista dep1= deportistaDao.obtenerDeportistaIgual(usu);
			System.out.println("antes de listdep "+dep1.getNomCompleto());
			listDep.add(dep1);
			
			System.out.println("fecha");
			asignarEnto= new AsignarEnto(selectEnto, listDep, entrenador, fechaIni, fechaFin, freqCard, velocidad, tiempoTotal);
			asignarEntoDao.guardarAsignarEnto(asignarEnto);
//			asignarEnto.getListDeportista().add(dep1);
			
			listAsignarEnto = new ArrayList<AsignarEnto>();
			listAsignarEnto.add(asignarEnto);
			dep1.getListAsignarEnto().add(asignarEnto);
			dep1.setListAsignarEnto(listAsignarEnto);
			System.out.println("actualizar dep1");
			deportistaDao.actualizarDeportista(dep1);
		}
		
		return "";
	}
	
	public void mostMsj(String msj) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, msj, ""));
		
		
	}
	
	public void mostMsjGrowl(String msj) {
		FacesContext.getCurrentInstance().addMessage("growl",
				new FacesMessage(FacesMessage.SEVERITY_INFO, msj, ""));
		
		
	}
	
	
	
}

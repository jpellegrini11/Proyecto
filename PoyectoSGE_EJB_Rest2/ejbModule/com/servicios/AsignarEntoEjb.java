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
import com.dao.EntEjerDao;
import com.dao.EntrenadorDao;
import com.dao.EntrenamientoDao;
import com.dao.ResultadosEntoDao;
import com.entidades.Asignar;
import com.entidades.AsignarEnto;
import com.entidades.Deportista;
import com.entidades.EntEjer;
import com.entidades.Entrena;
import com.entidades.Entrenador;
import com.entidades.Entrenamiento;
import com.entidades.ResultadosEnto;
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
	private List<Deportista> listDep2;
	private AsignarEnto asignarEnto;
	private List<AsignarEnto> listAsignarEnto;
	private List<AsignarEnto> listAsignarEnto2;
	private AsignarEnto asignarEnto2;
	private ResultadosEnto resultadosEnto;
	private Long tiempoTotal;
	
	@EJB
	EntrenadorDao entrenadorDao;
	@EJB
	DeportistaDao deportistaDao;
	@EJB
	AsignarEntoDao asignarEntoDao;
	@EJB
	EntEjerDao	entEjerDao;
	
	@EJB
	ResultadosEntoEjb resultadosEntoEjb;
	
	@EJB
	EntrenamientoDao entrenamientoDao;
	
	@EJB
	ResultadosEntoDao resultadosEntoDao;
	


	
	//Metodo para que el deportista cargue los datos de su entrenamiento
	public Boolean guardarAsignarEntoBean(AsignarEnto asignarEnto ,
			Integer freqCard, Double velocidad, String comentarios) throws SQLException {
		
		Boolean bool=false;
		System.out.println("guarda entro");
		if(freqCard==null || velocidad ==null || comentarios.equals("")) {
			
			mostMsjGrowl("Faltan datos, ingrese freq, velocidad y comentario");
			
		}else {
			
		
			System.out.println("guarda");
			System.out.println("guardarAsignarEntoBean "+asignarEnto.getEntrenamiento().getListEntEjer().get(0).getTiempoReal());
			asignarEnto2 = asignarEntoDao.obtenerAsignarEnto(asignarEnto.getIdAsignarEnto());
			
			
//			asignarEnto2.setFreqCard(freqCard);
//			asignarEnto2.setVelocidad(velocidad);
//			asignarEnto2.setComentarios(comentarios);
			asignarEnto2.setCompletado("COMPLETO");
			
			List<EntEjer> listEntEjer;
			listEntEjer = asignarEnto.getEntrenamiento().getListEntEjer();
			
			
			String usuario = (String) SessionUtils.getRequest().getSession(false).getAttribute("usuario");
			Deportista dep1= deportistaDao.obtenerDeportistaIgual(usuario);
			

//			for (EntEjer entEjer : listEntEjer) {
//				entEjerDao.actualizarEntEjer(entEjer);
//			}
			
			Date fecha= new Date();
			String completado ="COMPLETO";
			
			
			 resultadosEnto = new ResultadosEnto(asignarEnto2, fecha, freqCard, velocidad, comentarios, completado,usuario);
								
			if(listEntEjer.size()>0) {
				
				System.out.println("if 1 cargarTiempo ");
			cargarTiempo(listEntEjer);
			}
			resultadosEnto.setTiempoTotal(tiempoTotal);
			resultadosEnto.setPolarFlow((long) 0);
			System.out.println("actualizarAsignarEnto");

			asignarEntoDao.actualizarAsignarEnto(asignarEnto2);
			
			System.out.println("guardarResultadosEnto");
//			 resultadosEntoDao.guardarResultadosEnto(resultadosEnto);
			
			bool=resultadosEntoEjb.guardarResultadosEntoEJB(resultadosEnto);
			if(bool) {
				mostMsjGrowl("Correcto, los datos se enviaron a su entrenador");
			}
		}
		return bool;

	}
	public AsignarEnto getAsignarEnto() {
		return asignarEnto;
	}
	public void setAsignarEnto(AsignarEnto asignarEnto) {
		this.asignarEnto = asignarEnto;
	}
	public AsignarEnto getAsignarEnto2() {
		return asignarEnto2;
	}
	public void setAsignarEnto2(AsignarEnto asignarEnto2) {
		this.asignarEnto2 = asignarEnto2;
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
		int n=0;
	     for (Entrena e : listEntrena) {
	    	 
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
	
	public  List<Deportista> listarDeportistaEjb(){
		
		String usuario = (String) SessionUtils.getRequest().getSession(false).getAttribute("usuario");
		
		
		System.out.println("metodo listarDeportistaEjb");
		entrenador = entrenadorDao.obtenerEntrenadorIgual(usuario);
		listEntrena = entrenador.getListEntrena();
		List<Deportista> listDep3 = new ArrayList<Deportista>();
		if(listEntrena.size()>0) {
		System.out.println("listEntrena " +listEntrena.size()+" "+listEntrena.get(0).getDeportista().getNomCompleto());
		}
		for (Entrena e : listEntrena) {
			System.out.println(e.getDeportista().getNomCompleto());
	
			listDep3.add(e.getDeportista());
		}
	
		
		return listDep3;

	}
	
	//Datos del entrenamieto que selecciona el entrenador para luego asignar a deportistas en el pick.
	public  String seleccionarEntoBeanEjb(List<String> target,Entrenamiento selectEnto,Date fechaIni,
			Date fechaFin, Float freqCard, Float velocidad, Float tiempoTotal) throws SQLException {
		
		Date fechaActual=new Date();
		System.out.println(fechaActual+" "+fechaActual.toString()+"  "+fechaIni.compareTo(fechaActual));
		System.out.println(fechaIni+" "+fechaIni.toString()+"  "+fechaFin.compareTo(fechaActual));
		System.out.println(fechaFin+" "+fechaFin.toString()+"  "+fechaFin.compareTo(fechaIni));
		
		if(fechaIni.compareTo(fechaActual)<0 || fechaFin.compareTo(fechaActual)<0 || fechaFin.compareTo(fechaIni)<0)
		{
			mostMsjGrowl("Debe ingresear fecha inicio y final. La fecha y hora final no puede ser menor a la de inicio o la actual");
		}			
		else{
		
		String usu =null;
		listDep= new ArrayList<Deportista>();
//		Entrenamiento entrenamiento= (Entrenamiento) selectEnto.getRowData();
		
//		System.out.println("entrenamiento desde pick "+entrenamiento.getNombre());
		
		System.out.println("target size "+target.size());
		for (String s : target) {
			
			usu=listNomIndex.get(s.toString());
			System.out.println("usuario target: "+usu);
			Deportista dep1= deportistaDao.obtenerDeportistaIgual(usu);
			System.out.println("antes de listdep "+dep1.getNomCompleto());
			listDep.add(dep1);
		}
			System.out.println("fecha");
			
			asignarEnto= new AsignarEnto(selectEnto, listDep, entrenador, fechaIni, fechaFin, freqCard, velocidad, tiempoTotal);
			
			System.out.println("entro  ENTEJER1");
			
			asignarEnto.setCompletado("NO COMPLETO");
			
			System.out.println("entro  ENTEJER2");
			List<EntEjer> listEntEjer =new ArrayList<EntEjer>();
			List<EntEjer> listEntEjer2 =new ArrayList<EntEjer>();
			System.out.println("entro  ENTEJER3");
			
			listEntEjer = asignarEnto.getEntrenamiento().getListEntEjer() ;
			System.out.println("entro  ENTEJER4");
				
			
//			asignarEnto = asignarEntoDao.obtenerAsignarEnto(asignarEnto.getIdAsignarEnto());
			System.out.println("entro  ENTEJER5");
//			asignarEnto.getEntrenamiento().setListEntEjer(listEntEjer);
			System.out.println("entro  ENTEJER6");
		
			asignarEntoDao.guardarAsignarEnto(asignarEnto);
						
			listAsignarEnto = new ArrayList<AsignarEnto>();
			listAsignarEnto.add(asignarEnto);
//			dep1.getListAsignarEnto().add(asignarEnto);
			for (Deportista dep : listDep) {
				
				dep.setListAsignarEnto(listAsignarEnto);
				System.out.println("actualizar dep");
				deportistaDao.actualizarDeportista(dep);
			}
						
			Entrenamiento ento1= new Entrenamiento();
			
			System.out.println("entro  ENTEJER7");
			
			ento1 = entrenamientoDao.obtenerEntrenamiento(selectEnto.getIdEntrenamiento());
//			ento1.setListEntEjer(listEntEjer2);
			System.out.println("entro  ENTEJER8 "+ento1.getNombre());
			entrenamientoDao.actualizarEntrenamiento(ento1);
			
			mostMsjGrowl("El entrenamiento: "+selectEnto.getNombre() +", se asigno correctamente");
			
		}
		
		return "";
	}
	
	public void cargarTiempo(List<EntEjer> listEntEjer){
		
		tiempoTotal=(long) 0;
		System.out.println("cargarTiempo listEntEjer.size() "+ listEntEjer.size());
		if(listEntEjer.size()>0) {
			
			System.out.println("listEntEjer.size()==1");
			System.out.println("listEntEjer.get(0).getTiempoReal() "+listEntEjer.get(0).getTiempoReal());
			resultadosEnto.setTiempoReal1(listEntEjer.get(0).getTiempoReal());
			tiempoTotal =tiempoTotal+listEntEjer.get(0).getTiempoReal();
		}
		if(listEntEjer.size()>1) {
	
	System.out.println("listEntEjer.size()==2");
	resultadosEnto.setTiempoReal2(listEntEjer.get(1).getTiempoReal());
	tiempoTotal =tiempoTotal+listEntEjer.get(1).getTiempoReal();
	
		}
		if(listEntEjer.size()>2) {
	
	System.out.println("listEntEjer.size()==3");
	
	resultadosEnto.setTiempoReal3(listEntEjer.get(2).getTiempoReal());
	tiempoTotal =tiempoTotal+listEntEjer.get(2).getTiempoReal();
		}
		if(listEntEjer.size()>3) {
	
	System.out.println("listEntEjer.size()==4");
	resultadosEnto.setTiempoReal4(listEntEjer.get(3).getTiempoReal());
	tiempoTotal =tiempoTotal+listEntEjer.get(3).getTiempoReal();
	
		}
		if(listEntEjer.size()==5) {
	
	System.out.println("listEntEjer.size()==5");
	
	resultadosEnto.setTiempoReal5(listEntEjer.get(4).getTiempoReal());
	tiempoTotal =tiempoTotal+listEntEjer.get(4).getTiempoReal();
   }

		
	}
	
	public void mostMsj(String msj) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, msj, ""));
		
		
	}
	
	public void mostMsjGrowl(String msj) {
		FacesContext.getCurrentInstance().addMessage("growl",
				new FacesMessage(FacesMessage.SEVERITY_INFO, msj, ""));
		
		
	}
	public Long getTiempoTotal() {
		return tiempoTotal;
	}
	public void setTiempoTotal(Long tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}
	public List<Deportista> getListDep2() {
		return listDep2;
	}
	public void setListDep2(List<Deportista> listDep2) {
		this.listDep2 = listDep2;
	}
	
	
	
}

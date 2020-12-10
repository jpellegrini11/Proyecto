
package com.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import com.dao.DeportistaDao;
import com.dao.EntrenadorDao;
import com.dao.ResultadosEntoDao;
import com.entidades.Deportista;
import com.entidades.Entrenador;
import com.entidades.ResultadosEnto;
import com.servicios.AsignarEntoEjb;
import com.servicios.EntrenadorEjb;
import com.utils.SessionUtils;



@Named("ent")
@ManagedBean
@SessionScoped
public class EntrenadorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	EntrenadorEjb entrenadorEjb;
	@EJB
	DeportistaDao deportistaDao;

	@EJB
	AsignarEntoEjb asignarEntoEjb;
	@EJB
	EntrenadorDao entrenadorDao;
	@EJB
	ResultadosEntoDao resultadosEntoDao;

	

	private String usuario;
	private	String nombre;
	//	Fotos.
	private	String	descripcion;
	private	String	deportes;
	private	String	titulos;
	private	String	logros;
	private	Long 	cantPersonas;
	private	String	dispHoraria;
	private	String	ubicacion;
	private Long 	compPerfil;
	private Deportista deportista;

	private List<Deportista> listDep;
	private Deportista depSelect;
	
	private boolean bolCorrer = false;

	private boolean bolNadar = true;

	private boolean bolBici = false;
	private boolean bolCorrer2 = false;

	private boolean bolNadar2 = true;

	private boolean bolBici2 = false;

	private List<ResultadosEnto> listResultadosEnto2;

	private List<ResultadosEnto> listResultadosEnto;
	
	private ResultadosEnto selectResultadosEnto;

	private Entrenador ent1;

	public EntrenadorBean() {
			}
	
	@PostConstruct
	public void init() {
		 String usuario = (String) SessionUtils.getRequest().getSession(false).getAttribute("usuario");
		setEnt1(entrenadorDao.obtenerEntrenadorIgual(usuario));
		 
	 setListDep(asignarEntoEjb.listarDeportistaEjb());
	}
	
	public void listarResultadosEnto() {
		
		System.out.println("metodo listarResultadosEnto ");

		String usuario = depSelect.getUsuario();

		if(resultadosEntoDao.filtroResultadosEntoUsu(usuario).size()>0) {
    		
    		System.out.println("resultadosEntoDao.filtroResultadosEntoUsu(usuario).size() ="+resultadosEntoDao.filtroResultadosEntoUsu(usuario).size());
	    	
	    		filtoDatosEnto(usuario);
	        
	    	
	    	}
	}
	
	public void filtoDatosEnto(String usuario) {
		
		System.out.println("******* Metodo filtoDatosEnto Entrenador");
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
	
	public void actualizarDepEnt() throws SQLException {
		System.out.println("****** Metodo actualizarDepEnt1 ="+depSelect.getUsuario());
		
		
		Entrenador ent = new Entrenador();
		ent.setUsuDep(depSelect.getUsuario());
		entrenadorEjb.actualizar(ent);
		  
	}
	public String guardarPerfilBean() {
		String str= null;
			try {
				 str= entrenadorEjb.guardarPerfilEjb(nombre, descripcion, deportes, titulos, logros, cantPersonas, dispHoraria, ubicacion);
				 mostMsjGrowl("Exito su perfil se guardo");
				 return str;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No se guardo correctamente el perfil. Vuelva a intentar", ""));
				e.printStackTrace();
			}
			return str;
				
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
							setBolBici2(false);
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
	
	public void mostMsjGrowl(String msj) {
		FacesContext.getCurrentInstance().addMessage("growl",
				new FacesMessage(FacesMessage.SEVERITY_INFO, msj, ""));
		
		
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDeportes() {
		return deportes;
	}

	public void setDeportes(String deportes) {
		this.deportes = deportes;
	}

	public String getTitulos() {
		return titulos;
	}

	public void setTitulos(String titulos) {
		this.titulos = titulos;
	}

	public String getLogros() {
		return logros;
	}

	public void setLogros(String logros) {
		this.logros = logros;
	}

	public Long getCantPersonas() {
		return cantPersonas;
	}

	public void setCantPersonas(Long cantPersonas) {
		this.cantPersonas = cantPersonas;
	}

	public String getDispHoraria() {
		return dispHoraria;
	}

	public void setDispHoraria(String dispHoraria) {
		this.dispHoraria = dispHoraria;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Long getCompPerfil() {
		return compPerfil;
	}

	public void setCompPerfil(Long compPerfil) {
		this.compPerfil = compPerfil;
	}

	public Deportista getDeportista() {
		return deportista;
	}

	public void setDeportista(Deportista deportista) {
		this.deportista = deportista;
	}

	public List<Deportista> getListDep() {
		return listDep;
	}

	public void setListDep(List<Deportista> listDep) {
		this.listDep = listDep;
	}

	public Deportista getDepSelect() {
		return depSelect;
	}

	public void setDepSelect(Deportista depSelect) {
		this.depSelect = depSelect;
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

	public List<ResultadosEnto> getListResultadosEnto2() {
		return listResultadosEnto2;
	}

	public void setListResultadosEnto2(List<ResultadosEnto> listResultadosEnto2) {
		this.listResultadosEnto2 = listResultadosEnto2;
	}

	public List<ResultadosEnto> getListResultadosEnto() {
		return listResultadosEnto;
	}

	public void setListResultadosEnto(List<ResultadosEnto> listResultadosEnto) {
		this.listResultadosEnto = listResultadosEnto;
	}

	public ResultadosEnto getSelectResultadosEnto() {
		return selectResultadosEnto;
	}

	public void setSelectResultadosEnto(ResultadosEnto selectResultadosEnto) {
		this.selectResultadosEnto = selectResultadosEnto;
	}

	public Entrenador getEnt1() {
		return ent1;
	}

	public void setEnt1(Entrenador ent1) {
		this.ent1 = ent1;
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

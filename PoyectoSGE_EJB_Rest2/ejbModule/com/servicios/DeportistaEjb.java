package com.servicios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.dao.AsignarEntoDao;
import com.dao.DeportistaDao;
import com.dao.EntrenadorDao;
import com.entidades.AsignarEnto;
import com.entidades.Deportista;
import com.utils.SessionUtils;

@Stateless
@LocalBean
public class DeportistaEjb {

	@EJB
	DeportistaDao deportistaDao;
	
	@EJB
	EntrenadorDao entrenadorDao;
	
	@EJB
	AsignarEntoDao asignarEntoDao;
	
	List<AsignarEnto> listAsignarEnto;

	 
 	public DeportistaEjb() {
		
	}

 
    
    public String guardarPerfilEjb(String sociedadMedica, Float peso, Float altura, String dispHoraria, String alergias,
			String lesiones, String probSalud, String obejtivos, Float ferqCardMax, Float ferqCardMin,String nomCompleto)
			  {

		// El deportista ya esta registrado en la app, tiene atributos usuario y
		// compPerfil seteados.
		// Esta funcion actualiza el Dep con los datos que se cargan en el form

		// Recupero el nombre de usuario guardado en la seccion y lo utilizo para hacer
		// la busqueda en la bd para obtener la ID_Dep
		
		
//		Deportista dep1 = deportistaDao.obtenerDeportistaIgual(usuario);
//		dep1.setAlergias("no");
		System.out.println("guardPerf sociedadMedica:  " + sociedadMedica);

		
//Creo deportista con datos que provienen del formulario


//		dep1=d;
		System.out.println("update");
		
//		Long idDep = obtenerDeportistaIgual(usuario).getIdDesportista();
//		System.out.println("idDep: " + idDep);
//Seteo los datos a Dep
//		d.setIdDesportista(idDep);
//		Deportista d = new Deportista(sociedadMedica, peso, altura, dispHoraria, alergias, lesiones, probSalud,
//				obejtivos, ferqCardMax, ferqCardMin, (long) 1,nomCompleto);

//		d.setUsuario(usuario);
//Actualizar el entr en la bd
		try {
			String usuario = (String) SessionUtils.getRequest().getSession(false).getAttribute("usuario");
			System.out.println("guardPerf usu " + usuario);
			
			Deportista dep1 = deportistaDao.obtenerDeportistaIgual(usuario);
			
			dep1.setSociedadMedica(sociedadMedica);
			dep1.setPeso(peso);
			dep1.setAltura(altura);
			dep1.setDispHoraria(dispHoraria);
			dep1.setAlergias(alergias);
			dep1.setLesiones(lesiones);
			dep1.setProbSalud(probSalud);
			dep1.setObejtivos(obejtivos);
			dep1.setFerqCardMax(ferqCardMax);
			dep1.setFerqCardMin(ferqCardMin);
			dep1.setCompPerfil((long) 1);
			dep1.setNomCompleto(nomCompleto);
			
			System.out.println("update2");
			actualizar(dep1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "homeDep";

	}
    
    public List<AsignarEnto> listarAsigDep(){

    	
        System.out.println("listarAsigDep");
        
    	String usuario = (String) SessionUtils.getRequest().getSession(false).getAttribute("usuario");
    	
    	System.out.println("guardPerf usu " + usuario);
    	
    	Deportista dep1 = deportistaDao.obtenerDeportistaIgual(usuario);
    	if(dep1.getListAsignarEnto().size()==0) {
    		System.out.println("Entro if size");
    		listAsignarEnto= new ArrayList<AsignarEnto>();
    	}
    	else {
    		System.out.println("entro else");
    	listAsignarEnto  = dep1.getListAsignarEnto();
    	}
    	return listAsignarEnto;
    }


	 

	
	public void alta(Deportista deportista) throws SQLException {

		
			this.deportistaDao.guardarDeportista(deportista);
	
	}

	public void actualizar(Deportista deportista) throws SQLException {
		
			this.deportistaDao.actualizarDeportista(deportista);
		}

	 
	public void borrar(Long idDeportista) throws SQLException {
		
			this.deportistaDao.borrarDeportista(idDeportista);
	
	}
	
	 
	public Long obtenerPorId(Long id) throws SQLException{
		return this.deportistaDao.obtenerporId(id);
	}

	 
	public List<Deportista> obtenerTodos(){
		return this.deportistaDao.obternerTodos();
	}

	 
	public Deportista obtenerDeportista(Long idDeportista) throws SQLException {
		return this.deportistaDao.obtenerDeportista(idDeportista);
	}
	
	  
	public Deportista obtenerDeportistaIgual(String deportista) throws SQLException {
		return this.deportistaDao.obtenerDeportistaIgual(deportista);
	}
	
	
}

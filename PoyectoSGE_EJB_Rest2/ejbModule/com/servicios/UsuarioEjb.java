package com.servicios;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.dao.DeportistaDao;
import com.dao.EntrenadorDao;
import com.dao.UsuarioDao;
import com.entidades.Deportista;
import com.entidades.Entrenador;
import com.entidades.Usuario;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.utils.SessionUtils;

/**
 * Session Bean implementation class UsuarioBean
 */
@Stateless
@LocalBean

public class UsuarioEjb{

	@EJB
	private UsuarioDao usuarioDao;
	
	@EJB
	private DeportistaDao deportistaDao;
	
	@EJB
	private EntrenadorDao entrenadorDao;


	
	private Usuario usu;
	private Entrenador ent;
	private Deportista dep;
	
	public UsuarioEjb() {
		
	}

	
//	public void fireBase(String idToken) throws IOException, FirebaseAuthException {
//		
//		
//FileInputStream serviceAccount =
//  new FileInputStream("path/to/serviceAccountKey.json");
//
//FirebaseOptions options = new FirebaseOptions.Builder()
//  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//  .setDatabaseUrl("https://aug-bot.firebaseio.com")
//  .build();
//
//FirebaseApp.initializeApp(options);
//
////idToken comes from the client app (shown above)
//FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
//String uid = decodedToken.getUid();
//System.out.println("token:  "+uid);
//	}




	


    public void logout() throws IOException {
    	HttpSession session = SessionUtils.getSession();
    	session.invalidate();
    	ExternalContext ex= SessionUtils.getExternalContext();
    	ex.redirect(ex.getRequestContextPath() + "/");
    	
    	
//    	 externalContext.redirect(externalContext.getRequestContextPath() + "/");
        
    }

  
	
    public String login(String usuarioL) throws SQLException {
    	
    	Boolean u = false;
    	
    	Long compPerif;
    		System.out.println("antes 1 if login "+usuarioL);
    		
    		if (deportistaDao.obtenerDeportistaIgual(usuarioL) != null) {
    			System.out.println("If dentro 1 "+usuarioL);
    			compPerif= deportistaDao.obtenerDeportistaIgual(usuarioL).getCompPerfil();
    			
    			String perfil= "DEPORTISTA";
    		cargarSeccionUsu(usuarioL,perfil,compPerif);
    		
    		
    		System.out.println("If dentro compPerfil "+compPerif);
    			if(compPerif==1) {
    				
    				return "deportista/homeDep"
    						+ "";
    			}else {
    				return "deportista/cargaPerfilDep";
    			}
    		}
    		
    	else {
    		if (entrenadorDao.obtenerEntrenadorIgual(usuarioL) != null) {
    			System.out.println("If ent"+usuarioL);
    		String perfil2= "ENTRENADOR";
    		compPerif= entrenadorDao.obtenerEntrenadorIgual(usuarioL).getCompPerfil();
    		cargarSeccionUsu(usuarioL,perfil2,compPerif);
    		
    		if(compPerif==1) {
    			
				return "entrenador/homeEnt";
			}else {
				return "entrenador/cargaPerfilEnt";
			}
    	}
    		System.out.println("No hay prefil");
    	
    	return "login2";
    }
}
//	public String login(String usuarioL, String contrasenaL) throws Exception{
//			 usu = null;
//			 System.out.println(FacesContext.getCurrentInstance().getPartialViewContext().toString());
//			 FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts().add("console.log('imprime111');");
//			
//				if (usuarioDao.obtenerUsuarioIgual(usuarioL) != null) {
//	
//					usu = usuarioDao.obtenerUsuarioIgual(usuarioL);
//					System.out.println("entro");
//					if (usu.getContrasena().equals(contrasenaL)) {
//						System.out.println("entro2");
//						
//						cargarSeccionUsu();
//						if (usu.getCompPerf().toString().equals("0") && usu.getPerfil().equals("DEPORTISTA")) {
//						
//							System.out.println("perfil"+usu.getPerfil().toString() +" "+ usu.getCompPerf());
//							
//							return "deportista/cargaPerfilDep";
//						}
//						if (usu.getCompPerf().toString().equals("0") && usu.getPerfil().equals("ENTRENADOR")) {
//							
//							System.out.println("perfil"+usu.getPerfil().toString()+" "+ usu.getCompPerf());
//							return "entrenador/cargaPerfilEnt";
//						}
//						else {
//						
//							System.out.println("perfil"+usu.getPerfil().toString());
//							return "home";
//							}
//					}
//					mostMsjIncorr();
//	
//					return null;
//	
//				} else {
//					mostMsjIncorr();
//					return null;
//				}
//				return null;
//		}
	public String registarUsu(String perfil, String mail) {
		
		
		String str="login2";
		  System.out.println("btnRegistrar metodo reg");

		  if(perfil.equals("DEPORTISTA")) {
			  
			  if (deportistaDao.obtenerDeportistaIgual(mail) != null) { 
			  
				  mostMsjGrowl("Ya se encuentra registrado en SGE");
				  return str;
				  
		
		  }else {
			  
			  Deportista d1=new Deportista();
			  d1.setUsuario(mail);
			  d1.setCompPerfil((long) 0);
			  d1.setPerfil(perfil);
			  try {
				deportistaDao.guardarDeportista(d1);
				str= "deportista/cargaPerfilDep";
				cargarSeccionUsu(mail, perfil, (long) 0);
				mostMsjGrowl("Se registro correctamente");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No se registro correctamente. Vuelva a intentar", ""));

				e.printStackTrace();
			}
		  }
			  }
		  			  
			  if(perfil.equals("ENTRENADOR")) {
				  
				  if (entrenadorDao.obtenerEntrenadorIgual(mail) != null) {
					  
					  mostMsjGrowl("Ya se encuentra registrado en SGE");
					  return str;
	
		  }else {
			  
			  Entrenador e1=new Entrenador();
			  e1.setUsuario(mail);
			  e1.setCompPerfil((long) 0);
			  e1.setPerfil(perfil);
			  try {
				entrenadorDao.guardarEntrenador(e1);
				str= "entrenador/cargaPerfilEnt";
				mostMsjGrowl("Se registro correctamente");
				cargarSeccionUsu(mail, perfil, (long) 0);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No se registro correctamente. Vuelva a intentar", ""));

				e.printStackTrace();
			}
		  }
		  }
			 
		return str;
		
	}
	public void cargarSeccionUsu(String usuario, String perfil, Long compPerfil ) {
	
		HttpSession session = SessionUtils.getSession();
		session.setAttribute("usuario", usuario);
		
		session.setAttribute("perfil", perfil);
		session.setAttribute("compPerfil", compPerfil);
	}

	public void mostMsjIncorr() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario o Constraseña incorrecta", ""));
	}
	
	public void mostMsjGrowl(String msj) {
		FacesContext.getCurrentInstance().addMessage("growl",
				new FacesMessage(FacesMessage.SEVERITY_INFO, msj, ""));
		
		
	}
	
	public void alta(Usuario usuario) throws SQLException {

		try {
			this.usuarioDao.guardarUsuario(usuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	 
	public void actualizar(Usuario usuario) throws SQLException {

		try {
			this.usuarioDao.actualizarUsuario(usuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	 
	public void borrar(Long idUsuario) throws SQLException {

		try {
			this.usuarioDao.borrarUsuario(idUsuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	 
	public Long obtenerPorId(Long id) throws SQLException{
		return this.usuarioDao.obtenerporId(id);
	}

	 
	public List<Usuario> obtenerTodos(){
		return this.usuarioDao.obternerTodos();
	}

	 
	public Usuario obtenerUsuario(Long idUsuario) throws SQLException {
		return this.usuarioDao.obtenerUsuario(idUsuario);
	}
	
	  
	public Usuario obtenerUsuarioIgual(String usuario) throws SQLException {
		return this.usuarioDao.obtenerUsuarioIgual(usuario);
	}
	
	 
//	public List<UsuarioDTO> obtenerTodosUsuariosList() throws SQLException {
//		try {
//			return this.usuarioDao.obtenerTodosAlimentosList();
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//			throw new SQLException("Ocurrió un error al obtener el listado de Usuarios");
//		}
//	}

//	 
//	public List<UsuarioDTO> obtenerUsuarioIgualList(String usuario, String contraseña) throws SQLException {
//		try {
//			return this.usuarioDao.obtenerUsuarioIgualList(usuario, contraseña);
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//			throw new SQLException("Ocurrió un error al obtener el usuario");
//		}
//	}
	
}
package com.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jboss.resteasy.spi.metadata.ResourceBuilder.ParameterBuilder;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleModel;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;
import org.primefaces.shaded.json.JSONString;

import com.dao.DeportistaDao;
import com.entidades.AsignarEnto;
import com.entidades.Deportista;
import com.entidades.Entrenamiento;
import com.google.common.io.CharStreams;
import com.servicios.DeportistaEjb;
import com.servicios.RelojInteligenteEJB;
import com.utils.SessionUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named("reloj")
@ViewScoped
public class RelojInteligenteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	RelojInteligenteEJB relojInteligenteEJB;
	
	@EJB
	DeportistaDao deportistaDao;
	@EJB
	DeportistaEjb deportistaEjb;
	
	private String token;
	private int user_id;
	private String id_usuario;
	private String transaction_id;
	private String titulo= "Reloj Inteligente";
	
	public RelojInteligenteBean() {

	}
	
	
	public void sincronizarRelojBean() {
//		String redirectURL = "https://flow.polar.com/oauth2/authorization?response_type=code&scope=accesslink.read_all&client_id=030046e1-9054-483a-9202-66acd4c067ae";
//	    Faces.redirect(redirectURL);
		System.out.println("Llego al Bean del cliente");
		relojInteligenteEJB.sincronizarRelojEJB();
	}
	
	
	public void obtenerToken() {
		
		System.out.println("metodo obtenerToken()");
		String url = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("url");
		String [] partes = url.split("=");
		String codigo = partes[1];
		System.out.println(codigo);
		String clientID = "da50c34b-ee22-483f-bc84-3a3c30defce8";
		String secretkey = "5f7ed917-96b8-4df2-8fef-632fcdb1f80b";
		String clientSecret = clientID + ":" + secretkey;
		String authorization = "Basic ZGE1MGMzNGItZWUyMi00ODNmLWJjODQtM2EzYzMwZGVmY2U4OjVmN2VkOTE3LTk2YjgtNGRmMi04ZmVmLTYzMmZjZGIxZjgwYg==";

		
		String usuario = (String) SessionUtils.getRequest().getSession(false).getAttribute("usuario");
		Deportista dep = deportistaDao.obtenerDeportistaIgual(usuario);
		
		if(dep.getTokenPolarFlow()==null) {
		
		URL urlPOST = null;
		
		try {
			urlPOST = new URL("https://polarremote.com/v2/oauth2/token");
			try {
				HttpsURLConnection https = (HttpsURLConnection) urlPOST.openConnection();
				https.setRequestMethod("POST");
				
				//HEADERS
				https.setRequestProperty("Authorization", authorization);
				https.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				https.setRequestProperty("Accept", "application/json;charset=UTF-8");
				https.setDoOutput(true);
				
				//BODY
				Map<String, String> parameters = new HashMap<>();
				parameters.put("grant_type", "authorization_code");
				parameters.put("code", codigo);
				https.connect();
				DataOutputStream out;
				out = new DataOutputStream(https.getOutputStream());
				out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
				out.flush();
				out.close();
				https.connect();
				System.out.println();
				BufferedReader in = new BufferedReader(
						  new InputStreamReader(https.getInputStream()));
						String inputLine;
						StringBuffer content = new StringBuffer();
						while ((inputLine = in.readLine()) != null) {
						    content.append(inputLine);   
						}
						in.close();
						//OBTENER EL JSON
						JSONObject json = new JSONObject(content.toString());
						System.out.println(content + "      content");
						System.out.println();		
						
						token = (String) json.get("access_token");
						user_id = (int) json.get("x_user_id");
						id_usuario = String.valueOf(user_id);
						
						
						Long id= Long.valueOf(user_id);
						
						int tokenExpire = (int) json.get("expires_in");
						Long expire= Long.valueOf(tokenExpire);
						
						deportistaEjb.guardarTokenPolarFlow(token, expire, id);
						
						System.out.println("este es el token: " + token);
						System.out.println();		
						System.out.println("este es el user_id: " + user_id);
						System.out.println();
						
//						//ESTE ES UN POST
//						//REGISTRAR EL USUARIO CON MEMBER_ID
						URL urlPOST2 = new URL("https://www.polaraccesslink.com/v3/users");

									HttpsURLConnection https2 = (HttpsURLConnection) urlPOST2.openConnection();
									https2.setRequestMethod("POST");
									//HEADERS
									https2.setRequestProperty("Authorization", "Bearer " + token);
									https2.setRequestProperty("Content-Type", "application/json");
									https2.setDoOutput(true);
									//BODY
									//Pasar idDeportista al idRegister de polarFlow si es necesario
//									Long longID= (long) 888;
//									String id= String.valueOf(longID);
//									System.out.println("id = "+id);
//									String sID=("{\"member-id\":\"User_id")+id+("\"}");
//									byte[] paramsString = ("{\"member-id\":\"User_id_+id+\"}").getBytes(StandardCharsets.UTF_8);
//									
									byte[] paramsString = ("{\"member-id\":\"User_id_997\"}").getBytes(StandardCharsets.UTF_8);
									int length = paramsString.length;
									https2.setFixedLengthStreamingMode(length);
									https2.connect();
									try(OutputStream os = https2.getOutputStream()) {
									    os.write(paramsString);
									}
									String text = null;
								    try (Reader reader = new InputStreamReader(https2.getInputStream())) {
								        text = CharStreams.toString(reader);
								    }
									JSONObject json2 = new JSONObject(text.toString());
									System.out.println(text + "      content");
									System.out.println();	
									System.out.println("----------------HASTA ACA EL POST----------------");
						
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
		
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}	


}

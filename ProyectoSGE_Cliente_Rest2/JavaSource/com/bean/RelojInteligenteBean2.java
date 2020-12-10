package com.bean;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.net.ssl.HttpsURLConnection;

import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

import com.google.common.io.CharStreams;
import com.servicios.RelojInteligenteEJB;
import com.utils.SessionUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named("reloj2")
@ViewScoped
public class RelojInteligenteBean2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	RelojInteligenteEJB relojInteligenteEJB;
	private String id_usuario;
	private String token;
	
	
	public void sincronizarRelojBean() {
//		String redirectURL = "https://flow.polar.com/oauth2/authorization?response_type=code&scope=accesslink.read_all&client_id=030046e1-9054-483a-9202-66acd4c067ae";
//	    Faces.redirect(redirectURL);
		System.out.println("Llego al Bean del cliente");
		relojInteligenteEJB.sincronizarRelojEJB();
	}
	
	public void lineaFC() {
		//obtener 20 valores de FC de entrenamiento de cada deporte de la base de datos
		int[] listaFC = new int[5];
		listaFC[0] = 1;
		listaFC[1] = 2;
		listaFC[2] = 2;
		listaFC[3] = 2;
		listaFC[4] = 10;
		
		//esta lista la escribo de ejemplo, despu'es hay que sustituirla por la de FC de entrenamientos
		int x = 0;
		for(int i = 0; i < listaFC.length; i++) {
			x = listaFC[i] + x;
		}
		double promedioFC = x/listaFC.length;
		double sumCuad = 0;
		for(int i = 0; i < listaFC.length; i++) {
			sumCuad = sumCuad + Math.pow(promedioFC - listaFC[i], 2);
		}
		double dosDesvEst = 2*Math.sqrt(sumCuad/listaFC.length);
		//!!!!     promedioFC - dosDsvEst     es el valor a poner en la linea roja !!!!!
	}
	public void obtenerToken() throws IOException {
		String url = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("url");
		String [] partes = url.split("=");
		String codigo = partes[1];
		System.out.println(codigo);
		String clientID = "04c2ef19-6117-4738-b529-86eefbe6f621";
		String secretkey = "326cc1e2-02e6-409b-a473-42f43b15c9f2";
		String clientSecret = clientID + ":" + secretkey;
		String authorization = "Basic MDRjMmVmMTktNjExNy00NzM4LWI1MjktODZlZWZiZTZmNjIxOjMyNmNjMWUyLTAyZTYtNDA5Yi1hNDczLTQyZjQzYjE1YzlmMg==";

		
						
//					
						
						//ESTE ES UN POST
						//OBTENER TRANSACTION_ID
						URL urlPOST3 = new URL("https://www.polaraccesslink.com/v3/users/" + id_usuario + "/exercise-transactions");
						HttpsURLConnection https3 = (HttpsURLConnection) urlPOST3.openConnection();
						https3.setRequestMethod("POST");
						//HEADERS
						https3.setRequestProperty("Authorization", "Bearer " + token);
						https3.setRequestProperty("Content-Type", "application/json");
						https3.setDoOutput(true);
						https3.connect();
						String text3 = null;
					    try (Reader reader3 = new InputStreamReader(https3.getInputStream())) {
					        text3 = CharStreams.toString(reader3);
					    }
					    System.out.println(text3.toString() + " devolucion del id transaction");
						
					    JSONObject json3 = new JSONObject(text3.toString());
						int transaction_idInt = (int) json3.get("transaction-id");
						String transaction_id = String.valueOf(transaction_idInt);
						System.out.println(transaction_id + "      transaction_id");
						System.out.println();	
						System.out.println("----------------HASTA ACA EL POST----------------");
						
						

						//ESTE ES UN GET
						//OBTENER EXCERCISE_ID
//						String transaction_id = "214536305";
						URL urlGET2 = new URL("https://www.polaraccesslink.com/v3/users/" + id_usuario + "/exercise-transactions/" + transaction_id);
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
							
							System.out.println("Todo el json    " + jsonGet3.toString());
							String tiempo = (String) jsonGet3.get("duration");
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
								velocidad = (distancia/1000)/tiempoH;
								System.out.println("La velocidad es de " + velocidad + "km/h");
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
							System.out.println(jsonGet3.toString());
							System.out.println("Distancia: " + distancia + " Tiempo en horas: " + tiempoH + "Tiempo en minutos: " + tiempoM + "      info ejercicio");
							System.out.println();

							System.out.println("--------------HASTA ACA EL GET----------------");
						}
						System.out.println();
						System.out.println("--------------HASTA ACA EL GET----------------");

//						Date fecha= new Date();
//						Float freqCard=(float) 0;
//						 String usuario = (String) SessionUtils.getRequest().getSession(false).getAttribute("usuario");
//						 
//
//						Long polarFlow = (long) 1;
//						
//						java.time.Duration d = java.time.Duration.parse(tiempo);
//						System.out.println("Duration in seconds: " + d.get(java.time.temporal.ChronoUnit.SECONDS));
//						long tiempo2 = d.get(java.time.temporal.ChronoUnit.SECONDS);
//						String deporte = (String) jsonGet3.get("detailed-sport-info");
//						velocidad = (float) (distancia/tiempo2);
						
		
		
	}


}
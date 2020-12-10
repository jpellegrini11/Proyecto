package com.bean;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.net.ssl.HttpsURLConnection;

import org.primefaces.shaded.json.JSONObject;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import com.entidades.Entrenador;
public class Prueb {
	
	 
	 
	public static void main(String[] args) {

		String id= "999";
		byte[] paramsString = ("{\"member-id\":\"User_id_+id+\"}").getBytes(StandardCharsets.UTF_8);
		System.out.println("paramsString "+paramsString.toString());
		
//		  obtenerToken();
			
//		String url = "https://www.youtube.com/watch?v=mbJiCTZ7MLE";
//		String [] partes = url.split("/");
//		String codigo = partes[3];		
//			System.out.println(codigo);


		
		
//		Date octIn= new Date();
//		System.out.println(octIn.toString());
//		Number n;
//		Long l=(long) 2;
//		n= l+l;
//		
//		System.out.println(n);
		
//		Date fechaActual=new Date();
//		Date fechaIni=new Date();
//		
//		Date fechaFin=new Date();
//		
//		Calendar fechaIni2;
//		fechaIni2=Calendar.getInstance();
//		fechaIni2.set(2020, 9, 30);
//		fechaIni=fechaIni2.getTime();
//		
//		Calendar fechaFin2;
//		System.out.println(fechaIni.toString());
//		System.out.println(fechaActual.toString());
//		
//	
//		System.out.println(fechaIni.compareTo(fechaActual));
//		System.out.println(fechaIni.compareTo(fechaActual));
//		if(fechaIni.compareTo(fechaActual)==-1)
//		{
//			System.out.println(fechaIni.getTime()+" "+fechaActual.getTime());
//			
//		}else {
//			System.out.println(fechaIni.getTime()+" "+fechaActual.getTime());
//		System.out.println("nooo");
//		}
//			if( fechaFin.getTime()<fechaIni.getTime()) {
//				
//			}
//			if( fechaFin.getTime()==fechaIni.getTime()) {
//			
//			}
//					if( fechaFin.getTime()<fechaActual.getTime()) {
//			
//		
//			
//		
//		}
		
//		PythonInterpreter interpreter=new PythonInterpreter();
//		interpreter.execfile("C:/Users/Administrador/git/repository/ProyectoSGE_Cliente_Rest2/JavaSource/com/bean/prueba3.py"); 
//		
//		PyObject str1 = interpreter.eval("repr(sum(10,50))"); 
//	    System.out.println(str1.toString()); 
//		
//		
//		    try(PythonInterpreter pyInterp = new PythonInterpreter()) {
//		      pyInterp.exec("print('Hello Python World!')");
//		    }
//
//Entrenador e1= new Entrenador();
//e1.setNombre("Aug");
//System.out.println(e1.getNombre());
		
//Date d= new Date();
//
//Calendar calendario = Calendar.getInstance();
//calendario.setTime(d);
//long hora = calendario.get(Calendar.HOUR_OF_DAY);
//long minutos = calendario.get(Calendar.MINUTE);
//long segundos = calendario.get(Calendar.DAY_OF_YEAR);
//System.out.println(" "+segundos+" "+minutos );
// 
//segundos = segundos-410;
//if(segundos<0) {
//System.out.println("menor 0: "+segundos);
//}
//System.out.println(d);

//	System.out.println(asLocalDateTime(d));	
//	
//	LocalDateTime l= asLocalDateTime(d);
//	System.out.println(l);
//		HashMap<String, String> map= new HashMap<String, String>();
//		map.put("nom", "aug");
//		System.out.println(map.get("nom"));
//		List<String> str= new ArrayList<String>();
//		str.add("nom");
//		System.out.println(str.get(0));
//		System.out.println(map.get(str.get(0)));
//		
//		
//		List<String> target = new ArrayList<String>();
//		target.add("nom");
//		target.add("nom");
//		System.out.println(target.size());
//		System.out.println(target.get(0).hashCode());
//		System.out.println(target.get(1).hashCode());
		
//		// TODO Auto-generated method stub
//		 DualListModel<String> cities;
//	     List<String> source = new ArrayList<String>();
//	     List<String> source2 = new ArrayList<String>();
//	     Map<String, String>v= new HashMap<String, String>();
//	     
//	     v.put("usu", "usuario real max");
//	     v.put("usu2", "usuario real max2");
//	     System.out.println(v.toString());
//	     
//	        List<String> target = new ArrayList<String>();
//	        source.add("Istanbul");
//	        source.add("Ankara");
//	        source.add("Izmir");
//	        source.add("Antalya");
//	        source.add(v.values().toString());
//	        
//	       
//	        source2.add("1");
//	        source2.add("3");
//	        source2.add("5");
//	        source2.add("7");
//	        source2.add("8");
//	        //more cities
//	        cities = new DualListModel<String>(source, target);
//	        
//	   
//	    	
//	    	
//	    		System.out.println(cities.getTarget().size());
//	    	for (int i = 0; i < cities.getTarget().size(); i++) {
//	    		cities.getTarget().get(i).toString();
//	    	}
//	    	
//    		System.out.println(cities.getSource().size());
//    	for (int i = 0; i < cities.getSource().size(); i++) {
//    		int n=4;
//    		System.out.println(cities.getSource().get(i).toString());
//
//    		
//    	}

	    	
	}
	 private static void obtenerToken() {
		 System.out.println("metodo ObtenerToken");
			
//			String url = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("url");
//			String [] partes = url.split("=");
//			String codigo = partes[1];
			String codigo = "030046e1-9054-483a-9202-66acd4c067ae";
			System.out.println(codigo);
			String clientID = "04c2ef19-6117-4738-b529-86eefbe6f621";
			String secretkey = "326cc1e2-02e6-409b-a473-42f43b15c9f2";
			String clientSecret = clientID + ":" + secretkey;
			String authorization = "Basic MDRjMmVmMTktNjExNy00NzM4LWI1MjktODZlZWZiZTZmNjIxOjMyNmNjMWUyLTAyZTYtNDA5Yi1hNDczLTQyZjQzYjE1YzlmMg==";
//					"Basic " + Base64.getEncoder().encodeToString(clientSecret.getBytes());

			
			URL urlPOST = null;
		
				try {
					
					urlPOST = new URL("https://polarremote.com/v2/oauth2/token");
					
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
				System.out.println("metodo ObtenerToken2");
		//		URLConnection con = null;
		
		//			con = urlPOST.openConnection();
					HttpsURLConnection https = (HttpsURLConnection) urlPOST.openConnection();
					
				
					System.out.println("metodo ObtenerToken3");
					https.setRequestMethod("POST");
					//HEADERS
					https.setRequestProperty("Authorization", authorization);
					https.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
					https.setRequestProperty("Accept", "application/json;charset=UTF-8");
		//			HttpsURLConnection https = (HttpsURLConnection)con;
					
					System.out.println("metodo ObtenerToken4");
					
					https.setDoOutput(true);
					//BODY
					Map<String, String> parameters = new HashMap<>();
					parameters.put("grant_type", "authorization_code");
					parameters.put("code", codigo);
					System.out.println("metodo ObtenerToken5");
					https.connect();
					
				
					
					System.out.println("metodo ObtenerToken6");
					DataOutputStream out;
					out = new DataOutputStream(https.getOutputStream());
					out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
					out.flush();
					out.close();
					https.connect();
					
					
					
					System.out.println("metodo ObtenerToken7");
					
					BufferedReader in = new BufferedReader(new InputStreamReader(https.getInputStream()));
					System.out.println("metodo ObtenerToken8");
					if (https.getResponseCode() != 200) {
						 throw new RuntimeException("Error : HTTP error code : " +https.getResponseCode());
						 }				
							String inputLine;
							StringBuffer content = new StringBuffer();
							while ((inputLine = in.readLine()) != null) {
							    content.append(inputLine);
							   
							}
							
							System.out.println("metodo ObtenerToken9");
							
							in.close();
							JSONObject json = new JSONObject(content.toString());
//							json.get(key)
							System.out.println(content + "      content");
							System.out.println();	
							
							String token = (String) json.get("access_token");
												
							int user_id = (int) json.get("x_user_id");
												
							System.out.println("este es el token: " + token);
							System.out.println();		
							
							System.out.println("este es el user_id: " + user_id);
							System.out.println();
							
							URL obj = new URL("https://www.polaraccesslink.com/v3/"+"user_id");
													
							HttpURLConnection con = (HttpURLConnection) obj.openConnection();
							con.setRequestMethod("POST");
							
							int responseCode = con.getResponseCode();
							BufferedReader in2 = new BufferedReader(new InputStreamReader(con.getInputStream()));
							String inputLine2;
							StringBuffer response = new StringBuffer();
							while ((inputLine = in.readLine()) != null) {
							    response.append(inputLine);
							}
							in.close();
							System.out.println(response.toString());
							
							
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		
	}
	LocalDateTime nextDay11Am(Date d) {
		 Calendar calendario = Calendar.getInstance();
		 calendario.setTime(d);
		 int hora = calendario.get(Calendar.HOUR_OF_DAY);
		 int minutos = calendario.get(Calendar.MINUTE);
		 int segundos = calendario.get(Calendar.SECOND);
		 int segundos2 = calendario.get(Calendar.DAY_OF_MONTH);
		 System.out.println(" "+segundos2+" "+minutos );
		    return LocalDateTime.now().plusDays(1).withHour(11).withMinute(0).withSecond(0).withNano(0);
		}
	
	public static LocalDateTime asLocalDateTime(Date date) { 
	    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime(); 
	    } 

}

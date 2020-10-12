package com.bean;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.primefaces.model.DualListModel;

import com.entidades.Deportista;
import com.entidades.Entrena;
import com.entidades.Entrenador;

public class Prueb {
	
	 
	 
	public static void main(String[] args) {
		


		
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

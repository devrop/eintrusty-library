package com.eintrusty;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.springframework.http.HttpMethod;

import com.eintrusty.stream.CollectorsCustom;

public class TestCollectors {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	
		//vectorHttpMethod
		//Arrays.asList(HttpMethod.GET, )
		
		HttpMethod ht = findOneOnList("post");
		
		
		System.out.println("method : " + ht);

	}
	
	//@SuppressWarnings("static-access")
	public static HttpMethod findOneOnList(String name){
		try{
			Vector<HttpMethod> vectorHttpMethod = new Vector<>(
					Arrays.asList(HttpMethod.GET, HttpMethod.POST,HttpMethod.PUT, HttpMethod.PATCH,
							HttpMethod.HEAD, HttpMethod.OPTIONS, HttpMethod.DELETE,HttpMethod.TRACE));
			HttpMethod hm = vectorHttpMethod
					.stream().filter(
							r -> r.matches(name.toUpperCase())
							
							).collect(CollectorsCustom.toSingleton());
			
			
					
		  return hm;
		}catch(Exception e) {
		  return null;
		}
		
		
	}

}

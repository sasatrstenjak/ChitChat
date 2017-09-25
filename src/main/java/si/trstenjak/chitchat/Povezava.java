package si.trstenjak.chitchat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;


public class Povezava {
	
	public static ArrayList<Uporabnik> uporabniki()
			throws ClientProtocolException, IOException, URISyntaxException {
		String responseBody = Request.Get("http://chitchat.andrej.com/users").execute().returnContent().asString();

		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Uporabnik>> t = new TypeReference<List<Uporabnik>>() {
		};
		mapper.setDateFormat(new ISO8601DateFormat());
		ArrayList<Uporabnik> uporabniki = mapper.readValue(responseBody, t);
		return uporabniki;
	}
	
	///// Prijava in odjava /////
	
	public static void prijavi(String ime) {
		String time = Long.toString(new Date().getTime());
		URI uri;
		try {
			uri = new URIBuilder("http://chitchat.andrej.com/users").
					addParameter("username", ime).addParameter("stop-cache", time).
					build();
			String responseBody = Request.Post(uri).execute().returnContent().asString();
			System.out.println(responseBody);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	public static void odjavi(String ime) {
		String time = Long.toString(new Date().getTime());
		URI uri;
		try {
			uri = new URIBuilder("http://chitchat.andrej.com/users").
					addParameter("username", ime).addParameter("stop-cache", time).
					build();
			String responseBody = Request.Delete(uri).execute().returnContent().asString();
			System.out.println(responseBody);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	///// Prejemanje sporočil /////
	
	public static List<Sporocilo> prejmi(String ime)
			throws URISyntaxException, ClientProtocolException, IOException{
		
		String time = Long.toString(new Date().getTime());
		ObjectMapper mapper = new ObjectMapper(); //ObjectMapper: pretvarja JSON stringe v Java objekte in obratno
		URI uri = new URIBuilder("http://chitchat.andrej.com/messages")
				.addParameter("username", ime).addParameter("stop-cache", time)
				.build();
		
		String responseBody = Request.Get(uri).execute().returnContent().asString();
		mapper.setDateFormat(new ISO8601DateFormat());
		TypeReference<List<Sporocilo>> t = new TypeReference<List<Sporocilo>>() {
		};
			
		List<Sporocilo> prejeto = mapper.readValue(responseBody, t);
			
		return prejeto;		
	}
	
	
	////// Pošiljanje sporočil /////
	
	public static void poslji_javno(String posiljatelj, String besedilo) throws URISyntaxException, ClientProtocolException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String time = Long.toString(new Date().getTime());
		
		URI uri = new URIBuilder("http://chitchat.andrej.com/messages")
				.addParameter("username", posiljatelj).addParameter("stop-cache", time)
				.build();
		
		Sporocilo sporocilo = new Sporocilo (true, besedilo);
		String jsonSporocilo = mapper.writeValueAsString(sporocilo);
		
		String responseBody = Request.Post(uri).
				bodyString(jsonSporocilo, ContentType.APPLICATION_JSON)
				.execute().returnContent().asString();
		System.out.println(responseBody);
	}
	
	public static void poslji_zasebno(String posiljatelj, String prejemnik, String besedilo) throws URISyntaxException, ClientProtocolException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		String time = Long.toString(new Date().getTime());
		
		URI uri = new URIBuilder("http://chitchat.andrej.com/messages")
				.addParameter("username", posiljatelj).addParameter("stop-cache", time)
				.build();
		
		Sporocilo sporocilo = new Sporocilo (false, posiljatelj, prejemnik, besedilo);
		String jsonSporocilo = mapper.writeValueAsString(sporocilo);
		
		String responseBody = Request.Post(uri).
				bodyString(jsonSporocilo, ContentType.APPLICATION_JSON)
				.execute().returnContent().asString();
		System.out.println(responseBody);
	}
}


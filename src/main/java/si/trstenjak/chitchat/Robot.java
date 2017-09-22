package si.trstenjak.chitchat;
import si.trstenjak.chitchat.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.client.ClientProtocolException;

public class Robot extends TimerTask {
	private ChatFrame chat;
	
	public Robot(ChatFrame chat) {
		this.chat = chat;
	}
	
	/**
	 * Activate the robot!
	 */
	
	public void activate() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(this, 5000, 1000);
	}
	
	
	@Override
	public void run() {
		List<Sporocilo> prejeta_sporocila;
		
		if (chat.online == true){
			try {
				prejeta_sporocila = Povezava.prejmi(chat.ime);
				for (Sporocilo m : prejeta_sporocila){
					System.out.println(m.isJavno());
					System.out.println(m.getPrejemnik());
					System.out.println(m.getBesedilo());
					if (m.isJavno()){
						chat.receiveMessage(m.getPosiljatelj(), m.getBesedilo());
					}
					
					else {
						chat.receiveMessage("Privatno sporočilo od:" + m.getPosiljatelj(), m.getBesedilo());
					}
				}
			} 
			catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
				
		}
		
		/*ArrayList<Uporabnik> trenutno_prisotni;
		try {
			trenutno_prisotni = Povezava.uporabniki();

			ArrayList<Uporabnik> seznam = trenutno_prisotni;
			Uporabnik[] osebe = new Uporabnik[seznam.size()];
			seznam.toArray(osebe);
			chat.uporabniki.setListData(osebe);
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}

	

}

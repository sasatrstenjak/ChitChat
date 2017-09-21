package si.trstenjak.chitchat;

import java.io.IOException;
import java.net.URISyntaxException;
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
			String uporabnik = chat.ime;
			try {
				prejeta_sporocila = Povezava.prejmi(uporabnik);
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
	}

	

}

package si.trstenjak.chitchat;
import java.io.IOException;
import java.net.URISyntaxException;
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
		if (chat.online == true) {
			try {
				chat.gumb_prijava.setEnabled(false);
				chat.gumb_odjava.setEnabled(true);
				chat.receiveMessage();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	
			/*try {
				chat.izpisiPrijavljene();
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
		
		else {
			chat.gumb_odjava.setEnabled(false);
			chat.gumb_prijava.setEnabled(true);
		}
	}
}

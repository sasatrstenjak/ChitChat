package si.trstenjak.chitchat;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sporocilo { //objekt Sporocilo z atributi javno, prejemnik, ..
	
	private Boolean global;
	private String recipient;
	private String sender;
	private String text;
	
	public Sporocilo() {
	}
	

	public Sporocilo(Boolean javno, String besedilo) {
		super();
		this.global = javno;
		this.text = besedilo;
	}
	
	
	public Sporocilo(Boolean javno, String prejemnik, String besedilo){
		this.global = javno;
		this.recipient = prejemnik;
		this.text = besedilo;
	}
	
	
	public Sporocilo(Boolean javno, String posiljatelj, String prejemnik, String besedilo){
		this.global = javno;
		this.sender = posiljatelj;
		this.recipient = prejemnik;
		this.text = besedilo;
	}
	
	public String toString() {
		return "[global=" + global + ", recipient=" + recipient + ", sender=" + sender + ", text=" + text + "]";
	}
	
	@JsonProperty("javno")
	public Boolean isJavno() {
		return global;
	}
	
	public void setJavno(Boolean javno){
		this.global = javno;
	}
	
	@JsonProperty("prejemnik")
	public String getPrejemnik() {
		return recipient;
	}
	
	public void setPrejemnik(String prejemnik){
		this.recipient = prejemnik;
	}
	
	@JsonProperty("posiljatelj")
	public String getPosiljatelj() {
		return sender;
	}
	
	public void setPosiljatelj(String posiljatelj){
		this.sender = posiljatelj;
	}
	
	@JsonProperty("besedilo")
	public String getBesedilo() {
		return text;
	}
	
	public void setBesedilo(String besedilo){
		this.text = besedilo;
	}
}

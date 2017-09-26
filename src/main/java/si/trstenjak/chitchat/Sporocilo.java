package si.trstenjak.chitchat;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sporocilo { //objekt Sporocilo z atributi javno, prejemnik, ..
	
	private Boolean global;
	private String recipient;
	private String sender;
	private String text;
	private Date sentAt;
	
	public Sporocilo() {
	}
	
	public Sporocilo(String posiljatelj, String besedilo){
		this.global = true;
		this.sender = posiljatelj;
		this.text = besedilo;
	}
	
	
	public Sporocilo(String posiljatelj, String prejemnik, String besedilo){
		this.global = false;
		this.sender = posiljatelj;
		this.recipient = prejemnik;
		this.text = besedilo;
	}
	
	public String toString() {
		return "[global=" + global + ", recipient=" + recipient + ", sender=" + sender + ", text=" + text + "]";
	}
	
	@JsonProperty("global")
	public Boolean isJavno() {
		return global;
	}
	
	public void setJavno(Boolean javno){
		this.global = javno;
	}
	
	@JsonProperty("recipient")
	public String getPrejemnik() {
		return recipient;
	}
	
	public void setPrejemnik(String prejemnik){
		this.recipient = prejemnik;
	}
	
	@JsonProperty("sender")
	public String getPosiljatelj() {
		return sender;
	}
	
	public void setPosiljatelj(String posiljatelj){
		this.sender = posiljatelj;
	}
	
	@JsonProperty("text")
	public String getBesedilo() {
		return text;
	}
	
	public void setBesedilo(String besedilo){
		this.text = besedilo;
	}
	
	@JsonProperty("sent_at")
	public Date getSentAt() {
		return sentAt;
	}

	public void setSentAt(Date sentAt) {
		this.sentAt = sentAt;
	}
}

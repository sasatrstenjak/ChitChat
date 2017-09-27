package si.trstenjak.chitchat;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sporocilo {
	
	private Boolean javno;
	private String prejemnik;
	private String posiljatelj;
	private String text;
	private Date sentAt;
	
	public Sporocilo() {
	}
	
	public Sporocilo(String posiljatelj, String text){
		this.javno = true;
		this.posiljatelj = posiljatelj;
		this.text = text;
	}
	
	
	public Sporocilo(String posiljatelj, String prejemnik, String text){
		this.javno = false;
		this.posiljatelj = posiljatelj;
		this.prejemnik = prejemnik;
		this.text = text;
	}
	
	public String toString() {
		return "[global=" + javno + ", recipient=" + prejemnik + ", sender=" + posiljatelj + ", text=" + text + "sent_at=" + sentAt + "]";
	}
	
	@JsonProperty("global")
	public Boolean isJavno() {
		return javno;
	}
	
	public void setJavno(Boolean javno){
		this.javno = javno;
	}
	
	@JsonProperty("recipient")
	public String getPrejemnik() {
		return prejemnik;
	}
	
	public void setPrejemnik(String prejemnik){
		this.prejemnik = prejemnik;
	}
	
	@JsonProperty("sender")
	public String getPosiljatelj() {
		return posiljatelj;
	}
	
	public void setPosiljatelj(String posiljatelj){
		this.posiljatelj = posiljatelj;
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

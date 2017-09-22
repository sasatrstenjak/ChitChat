package si.trstenjak.chitchat;
import si.trstenjak.chitchat.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sporocilo { //objekt Sporocilo z atributi javno, prejemnik, ..
	
	private Boolean javno;
	private String prejemnik;
	private String posiljatelj;
	private String besedilo;
	private Date poslano_ob;
	
	public Sporocilo() {
	}
	
	/**
	 * @param javno
	 * @param besedilo
	 */
	public Sporocilo(Boolean javno, String besedilo) {
		super();
		this.javno = javno;
		this.besedilo = besedilo;
	}
	
	/**
	 * @param javno
	 * @param prejemnik
	 * @param besedilo
	 */
	
	public Sporocilo(Boolean javno, String prejemnik, String besedilo){
		this.javno = javno;
		this.prejemnik = prejemnik;
		this.besedilo = besedilo;
	}
	
	/**
	 * @param javno
	 * @param posiljatelj
	 * @param prejemnik
	 * @param besedilo
	 * @param poslano_ob
	 */
	public Sporocilo(Boolean javno, String posiljatelj, String prejemnik, String besedilo, Date poslano_ob){
		this.javno = javno;
		this.posiljatelj = posiljatelj;
		this.prejemnik = prejemnik;
		this.besedilo = besedilo;
		this.poslano_ob = poslano_ob;
	}
	
	public String toString() {
		return "[global=" + javno + ", recipient=" + prejemnik + ", sender=" + posiljatelj + ", text=" + besedilo + ", sent_at="
				+ poslano_ob + "]";
	}
	
	@JsonProperty("javno")
	public Boolean isJavno() {
		return javno;
	}
	
	public void setJavno(Boolean javno){
		this.javno = javno;
	}
	
	@JsonProperty("prejemnik")
	public String getPrejemnik() {
		return prejemnik;
	}
	
	public void setPrejemnik(String prejemnik){
		this.prejemnik = prejemnik;
	}
	
	@JsonProperty("posiljatelj")
	public String getPosiljatelj() {
		return posiljatelj;
	}
	
	public void setPosiljatelj(String posiljatelj){
		this.posiljatelj = posiljatelj;
	}
	
	@JsonProperty("besedilo")
	public String getBesedilo() {
		return besedilo;
	}
	
	public void setBesedilo(String besedilo){
		this.besedilo = besedilo;
	}
	
	@JsonProperty("poslano_ob")
	public Date getPoslano_ob(){
		return poslano_ob;
	}
	
	public void setPoslano_ob(Date poslano_ob) {
		this.poslano_ob = poslano_ob;
	}
}

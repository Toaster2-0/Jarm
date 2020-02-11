package de.jarm.gui.daten;

public class Benutzer {
	
	private String email;
	private String password;
	private String benutzername;
	
	public String getVorname() {
		return benutzername;
	}
	public void setVorname(String vorname) {
		this.benutzername = vorname;
	}
	public Benutzer(String nachname, String email, String password, String vorname) {
		this.email = email;
		this.password = password;
		this.benutzername = vorname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}

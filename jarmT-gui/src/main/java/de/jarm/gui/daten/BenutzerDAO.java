package de.jarm.gui.daten;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BenutzerDAO {
	
//	private Datei userDatei;
//	private Map<String,Benutzer> users;
//	
//	public BenutzerDAO() {
//		userDatei = new Datei("users");
//		ladeBenutzer();
//	}
//	
//	public void ladeBenutzer() {
//		users = new HashMap<String, Benutzer>();
//		try {
//			String dateiInhalt = userDatei.lese();	
//			String[] alleBenutzer = dateiInhalt.split("\n");
//			for (String einBenutzer : alleBenutzer) {
//				String[] einBenutzerDaten = einBenutzer.split("<;;;>");
//				//												Nachname,				 email,				 passwort,			 vorname
//				users.put(einBenutzerDaten[1], new Benutzer(einBenutzerDaten[0], einBenutzerDaten[1], einBenutzerDaten[2], einBenutzerDaten[3]));
//			}	
//		} catch (IOException e) {
//			System.out.println("Keine Datei vorhanden\n");
//		}
//	}
//	
//	public void saveBenutzer(Benutzer b) {
//		String userZeile = b.getNachname() + "<;;;>" + b.getEmail() + "<;;;>" + b.getPassword() + "<;;;>" + b.getVorname() + "\n";
//		try {
//			userDatei.schreibe(userZeile, true);
//			users.put(b.getEmail(), b);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public Benutzer findBenutzerByEmail(String email) {
//		return users.get(email);
//	}
	

	
}

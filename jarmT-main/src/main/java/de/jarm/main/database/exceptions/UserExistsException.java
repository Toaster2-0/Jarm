package de.jarm.main.database.exceptions;

public class UserExistsException extends Exception {
	
	public UserExistsException() {
		super("Unter dieser Email-Adresse ist bereits ein Benutzer registriert");
	}
	
}

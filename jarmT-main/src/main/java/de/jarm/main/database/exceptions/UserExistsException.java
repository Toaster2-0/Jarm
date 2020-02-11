package de.jarm.main.database.exceptions;

public class UserExistsException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7547685418239273868L;

	public UserExistsException() {
		super("Unter dieser Email-Adresse ist bereits ein Benutzer registriert");
	}
	
}

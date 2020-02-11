package de.jarm.main.database.exceptions;

public class NoConnectionToDbException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8984349807252558662L;

	public NoConnectionToDbException() {
		super("Es gibt ein Problem mit der Datenbankverbindung");
	}
	
}

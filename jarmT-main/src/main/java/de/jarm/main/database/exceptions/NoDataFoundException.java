package de.jarm.main.database.exceptions;

public class NoDataFoundException extends Exception {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8796018071176592949L;

	public NoDataFoundException() {
		super("Zu Ihrer Anfrage wurden leider keine Daten gefunden!");
	}
}

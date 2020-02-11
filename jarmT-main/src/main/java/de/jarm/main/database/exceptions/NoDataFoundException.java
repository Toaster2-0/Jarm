package de.jarm.main.database.exceptions;

public class NoDataFoundException extends Exception {
	
	
	public NoDataFoundException() {
		super("Zu Ihrer Anfrage wurden leider keine Daten gefunden!");
	}
}

package de.jarm.main.database.exceptions;

public class NoConnectionToDbException extends Exception {
	
	public NoConnectionToDbException() {
		super("Es gibt ein Problem mit der Datenbankverbindung");
	}
	
}

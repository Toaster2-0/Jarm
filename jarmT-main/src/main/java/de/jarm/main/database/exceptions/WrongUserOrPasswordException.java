package de.jarm.main.database.exceptions;

public class WrongUserOrPasswordException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6128542462777847761L;

	public WrongUserOrPasswordException() {
		super("Email oder Passwort falsch!");
	}

}

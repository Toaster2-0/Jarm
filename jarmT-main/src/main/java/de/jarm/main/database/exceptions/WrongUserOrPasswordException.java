package de.jarm.main.database.exceptions;

public class WrongUserOrPasswordException extends Exception {

	public WrongUserOrPasswordException() {
		super("Email oder Passwort falsch!");
	}

}

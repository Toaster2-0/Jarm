package de.jarm.main.data;

import java.util.List;
import de.jarm.main.database.exceptions.ValidierungsException;


import database.user.UserDAO;
import de.jarm.main.interfaces.UserServiceInterface;


public class UserService implements UserServiceInterface {

	private UserDAO userDAO = new UserDAO();
	
	@Override
	public User create(String name, String password, String email) throws ValidierungsException {
		validateInput(email, name, password);
		try {
			User u = userDAO.addUser(new User(name,password,email));
			return u;
		} catch(Exception e) {
			throw new ValidierungsException(e.getMessage());
		}
	}



	@Override
	public User login(String email, String password) throws ValidierungsException {
		validateInput(email, "name", password);
		try {
			User u = userDAO.logIn(email, password);
			return u;
		} catch(Exception e) {
			throw new ValidierungsException(e.getMessage());
		}
	}

	@Override
	public void remove(User user) {
		// TODO Auto-generated method stub

	}
	
	private void validateInput(String email, String name, String passwort) throws ValidierungsException {
		if (email.equals("")) {
			throw new ValidierungsException("Bitte email angeben!");
		} else if (!isValidEmailAddress(email)) {
			throw new ValidierungsException("Bitte gültige email angeben!");
		}
		if (name.equals("")) {
			throw new ValidierungsException("Bitte einen Namen angeben!");
		}
		if (passwort.equals("")) {
			throw new ValidierungsException("Bitte ein Passwort festlegen!");
		}
	}
	
	private boolean isValidEmailAddress(String email) {
        String ePattern = ".*@.*\\..*";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
	}

	@Override
	public User getUser(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserById(int id) throws ValidierungsException {
		try {
			return userDAO.loadUserById(id);
		} catch (Exception e) {
			throw new ValidierungsException(e.getMessage());
		}
	}

	@Override
	public List<User> findUserByNameOrEmail(String input) throws ValidierungsException {
		try {
			return userDAO.searchUserByNameOrEmail(input);
		} catch(Exception e) {
			throw new ValidierungsException(e.getMessage());
		}
	}



	@Override
	public List<User> findUserInProjectByNameOrEmail(String input, int projectId) throws ValidierungsException {
		try {
			return userDAO.searchUserInProjectByNameOrEmail(input, projectId);
		} catch(Exception e) {
			throw new ValidierungsException(e.getMessage());
		}
	}
}

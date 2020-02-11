package de.jarm.main.data;

import java.util.List;
import de.jarm.main.database.exceptions.ValidierungsException;



import de.jarm.main.interfaces.UserServiceInterface;

public class UserServiceDummy implements UserServiceInterface {
	private List<User> userList;
	
	public UserServiceDummy(List<User> userList) {
		this.userList = userList;

	}

	@Override
	public User create(String name, String password, String email) throws ValidierungsException {
		User newUser = new User(name, password, email);
		userList.add(newUser);
		return newUser;
	}
	
	public User create(String name, String password) throws ValidierungsException {
		return create(name, password, null);
	}

	@Override
	public User login(String user, String password) {
		for (User userFromList : userList) {
			if(userFromList.getName().equals(user) && userFromList.getPassword().equals(password)) {
				return userFromList;
			}
		}
		return null;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public User getUser(String name) {
		for (User user : userList) {
			if(user.getName().equals(name)) {
				return user;
			}
		}
		return null;
		
	}
	public User getUser(int id) {
		for (User user : userList) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	@Override
	public void remove(User user) {
		userList.remove(user);
		
	}

	@Override
	public List<User> findUserByNameOrEmail(String input) throws ValidierungsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserById(int id) throws ValidierungsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUserInProjectByNameOrEmail(String input, int projectId) throws ValidierungsException {
		// TODO Auto-generated method stub
		return null;
	}

}

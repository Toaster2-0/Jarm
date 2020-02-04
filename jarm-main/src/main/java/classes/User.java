package classes;

import java.util.ArrayList;
import java.util.List;

public class User {
	private int iD;
	private String name;
	private String password;
	private List<Project> projects = new ArrayList<>();
	//Unnecessary
	private String email;

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	void setPassword(String password) {
		this.password = password;
	}
	
	public User(int iD, String name, String password) {
		this(iD, name, password, null);
	}
	
	public User(int iD, String name, String password, String email) {
		setiD(iD);
		setName(name);
		setPassword(password);
		setEmail(email);
		
	}

	public void newProject(Project project) {
		projects.add(project);
	}

	void setName(String name) {
		this.name = name;
	}
	
	int getUserID() {
		return iD;
	}
	
	private void setiD(int iD) {
		this.iD = iD;
	}
	
	String getPassword() {
		return password;
	}
	
	String getUser() {
		return name;
	}
}
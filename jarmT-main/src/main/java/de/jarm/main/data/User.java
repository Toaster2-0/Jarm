package de.jarm.main.data;


public class User {
	private int id;
	private String name;
	private String password;
	//Unnecessary
	private String email;

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public User(String name, String password) {
		this(name, password, null);
	}
	
	public User(int id, String name, String password, String email) {
		setId(id);
		setName(name);
		setPassword(password);
		setEmail(email);
		
	}
	
	public User(String name, String password, String email) {
		this.name = name;
		this.password = password;
		this.email = email;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return name;
	}
}
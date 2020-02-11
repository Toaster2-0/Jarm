package de.jarm.main.data;

import java.time.LocalDateTime;
import java.util.List;

public class Project {
		
	private int id;
	private String title;
	private User owner;
	private LocalDateTime creationDate;
	private List<ProjectToDo> toDos;
	private List<User> subscribers;
	private List<Message> messages;
	
	public Project(int id, String title, User owner, LocalDateTime creationDate) {
		this.id = id;
		this.title = title;
		this.owner = owner;
		this.creationDate = creationDate;
	}
	
	private Project(String title, User owner, LocalDateTime creationDate) {
		setTitle(title);
		setOwner(owner);
		setCreationDate(creationDate);
	}
	
	public Project(String title, User owner) {
		this(title, owner, LocalDateTime.now());
	}
	
	public List<ProjectToDo> getToDos() {
		return toDos;
	}

	public void setToDos(List<ProjectToDo> toDos) {
		this.toDos = toDos;
	}

	public List<User> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(List<User> subscribers) {
		this.subscribers = subscribers;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creatDate) {
		this.creationDate = creatDate;
	}


}

package de.jarm.main.data;

import java.time.LocalDateTime;

public class Message {
	private int id;
	private String message;
	private LocalDateTime creationDate;
	private User author;
	
	public Message(String message, User author) {
		this(message, LocalDateTime.now(), author);
	}

	public Message(String message, LocalDateTime creationDate, User author) {
		setMessage(message);
		setCreationDate(creationDate);
		setAuthor(author);
	}
	
	public Message(int id, String message, LocalDateTime creationDate, User author) {
		this.id = id;
		this.message = message;
		this.creationDate = creationDate;
		this.author = author;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int iD) {
		this.id = iD;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime crationDate) {
		this.creationDate = crationDate;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
	

	
	
	
}

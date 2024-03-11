package model;

import java.time.LocalDateTime;

public class Group {

	private String id;
	private User owner;
	private String name;
	private String topic;
	private String description;
	private LocalDateTime createdAt;
	
	public Group(
			User owner, String name, String topic, 
			String description, LocalDateTime createdAt) {
		
		this.owner = owner;
		this.name = name;
		this.topic = topic;
		this.description = description;
		this.createdAt = createdAt;
	}
	
	public Group(
			String id, User owner, String name, 
			String topic, String description, LocalDateTime createdAt) {
		
		this.id = id;
		this.owner = owner;
		this.name = name;
		this.topic = topic;
		this.description = description;
		this.createdAt = createdAt;
	}
	
	public String getId() {	return id; }
	public User getOwner() { return owner; }
	public String getName() { return name; }
	public String getTopic() { return topic; }
	public String getDescription() { return description; }
	public LocalDateTime getCreatedAt() { return createdAt; }
	
	public void setId(String id) { this.id = id; }	
	public void setOwner(User owner) { this.owner = owner; }	
	public void setName(String name) { this.name = name; }	
	public void setTopic(String topic) { this.topic = topic; }	
	public void setDescription(String description) { this.description = description; }	
	public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt;	}
}

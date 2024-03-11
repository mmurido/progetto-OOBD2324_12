package model;

import java.time.LocalDateTime;

public class Post {
	
	private String id;
    private String text;
    private LocalDateTime createdAt;
    private Group group;
    private User author;
    
	public Post(String text, LocalDateTime createdAt, Group group, User author) {
		this.text = text;
		this.createdAt = createdAt;
		this.group = group;
		this.author = author;
	}
    
	public Post(String id, String text, LocalDateTime createdAt, Group group, User author) {
		this.id = id;
		this.text = text;
		this.createdAt = createdAt;
		this.group = group;
		this.author = author;
	}
	
	public String getId() { return id; }
	public String getText() { return text; }
	public LocalDateTime getCreatedAt() { return createdAt; }
	public Group getGroup() { return group; }
	public User getAuthor() { return author; }
	
	public void setId(String id) { this.id = id; }
	public void setText(String text) { this.text = text; }
	public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
	public void setGroup(Group group) { this.group = group; }
	public void setAuthor(User author) { this.author = author; }
}

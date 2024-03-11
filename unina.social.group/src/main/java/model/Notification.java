package model;

import java.time.LocalDateTime;

public class Notification {
	
	private String text;
	private LocalDateTime sentAt;
	private Post post;
	private User recipient;
	
	public Notification(String text, LocalDateTime sentAt, Post post, User recipient) {
		this.text = text;
		this.sentAt = sentAt;
		this.post = post;
		this.recipient = recipient;
	}

	public String getText() { return text; }
	public LocalDateTime getSentAt() { return sentAt; }
	public Post getPost() { return post; }
	public User getRecipient() { return recipient; }

	public void setText(String text) { this.text = text; }
	public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }
	public void setPost(Post post) { this.post = post; } 
	public void setRecipient(User recipient) { this.recipient = recipient; }
	
}

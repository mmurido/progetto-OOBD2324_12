package model;

import java.time.LocalDateTime;

public class Comment {

    private Post post;
    private User author;
    private String text;
    private LocalDateTime createdAt;
    
	public Comment(Post post, User author, String text, LocalDateTime createdAt) {
		this.post = post;
		this.author = author;
		this.text = text;
		this.createdAt = createdAt;
	}

	public Post getPost() { return post; }
	public User getAuthor() { return author; }
	public String getText() { return text; }
	public LocalDateTime getCreatedAt() { return createdAt; }

	public void setPost(Post post) { this.post = post; }
	public void setAuthor(User author) { this.author = author; }
	public void setText(String text) { this.text = text; }
	public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}


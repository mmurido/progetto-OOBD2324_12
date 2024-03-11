package model;

import java.time.LocalDate;

public class Like {
	
    private Post post;
    private User user;
    private LocalDate date;
    
	public Like(Post post, User user, LocalDate date) {
		this.post = post;
		this.user = user;
		this.date = date;
	}

	public Post getPost() { return post; }
	public User getUser() { return user; }
	public LocalDate getDate() { return date; }

	public void setPost(Post post) { this.post = post; }
	public void setUser(User user) { this.user = user; }
	public void setDate(LocalDate date) { this.date = date; }	
}

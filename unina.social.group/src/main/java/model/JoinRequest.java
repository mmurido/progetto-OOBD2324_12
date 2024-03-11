package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class JoinRequest {

	private User sender;
	private User recipient;
	private LocalDateTime sentAt;
	private boolean accepted;
	private LocalDate acceptedAt;
	private Group group;
	
	public JoinRequest(
			User sender, User recipient, LocalDateTime sentAt, Group group) {
		
		this.sender = sender;
		this.recipient = recipient;
		this.sentAt = sentAt;
		this.group = group;
	}

	public User getSender() { return sender; }
	public User getRecipient() { return recipient; }
	public LocalDateTime getSentAt() { return sentAt; }
	public boolean isAccepted() { return accepted; }
	public LocalDate getAcceptedAt() { return acceptedAt; }
	public Group getGroup() { return group; }

	public void setSender(User sender) { this.sender = sender; }
	public void setRecipient(User recipient) { this.recipient = recipient; }
	public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }
	public void setAccepted(boolean accepted) { this.accepted = accepted; }
	public void setAcceptedAt(LocalDate acceptedAt) { this.acceptedAt = acceptedAt; }
	public void setGroup(Group group) {	this.group = group;	}
}
package controllers;

import model.Utente;

public class UserSession {
	
	private Utente loggedUser;

	public void setLoggedUser(Utente user) {
		this.loggedUser = user;
	}
	
	public Utente getLoggedUser() {
		return loggedUser;
	}
}

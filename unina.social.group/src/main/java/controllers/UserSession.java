package controllers;

import model.Utente;

public class UserSession {
	
	private static Utente loggedUser;

	public static void setLoggedUser(Utente user) {
		loggedUser = user;
	}
	
	public static Utente getLoggedUser() {
		return loggedUser;
	}
}

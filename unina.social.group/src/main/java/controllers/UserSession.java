package controllers;

import model.User;

public class UserSession {

	private static User loggedUser;

	public static void setLoggedUser(User user) {
		loggedUser = user;
	}
	
	public static User getLoggedUser() {
		return loggedUser;
	}
	
	public static String getLoggedUserUsername() {
		return loggedUser.getUsername();
	}
	
	public static String getLoggedUserName() {
		return loggedUser.getName();
	}
	
	public static String getLoggedUserSurname() {
		return loggedUser.getSurname();
	}
}

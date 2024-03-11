package controllers;

import DAO.UserDAO;
import gui.loginPage.LoginPage;
import model.User;

public class LoginPageController {
	
	private UserDAO userDAO;
	private LoginPage loginPage;
	
	public LoginPageController(LoginPage loginPage) {
		this.userDAO = new UserDAO();
		this.loginPage = loginPage;
	}
	
	public void onLoginButtonClicked(String username, String password) {
		boolean authenticated = userDAO.authenticate(username, password);
		
		if (authenticated) {
			User user = userDAO.getByUsername(username);
			UserSession.setLoggedUser(user);
			Navigation.navigateToMainPage();
		}
		else {
			loginPage.handleAuthenticationError();
		}
	}
}

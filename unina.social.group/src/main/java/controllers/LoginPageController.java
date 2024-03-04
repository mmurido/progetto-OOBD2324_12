package controllers;

import DAO.UtenteDAO;
import gui.Navigation;
import gui.loginPage.LoginPage;
import model.Utente;

public class LoginPageController {
	
	private UtenteDAO utenteDAO;
	private LoginPage loginPage;
	
	public LoginPageController(LoginPage loginPage) {
		this.utenteDAO = new UtenteDAO();
		this.loginPage = loginPage;
	}
	
	public void onLoginButtonClicked(String username, String password) {
		boolean authenticated = utenteDAO.authenticate(username, password);
		
		if (authenticated) {
			Utente user = utenteDAO.getByUsername(username);
			UserSession.setLoggedUser(user);
			Navigation.navigateToMainPage();
		}
		else {
			loginPage.handleAuthenticationError();
		}
	}
}

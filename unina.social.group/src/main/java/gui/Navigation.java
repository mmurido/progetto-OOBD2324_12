package gui;

import controllers.UserSession;
import gui.mainpage.MainPage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Navigation {

	private UserSession userSession;
	
	public Navigation(UserSession userSession) {
		this.userSession = userSession;
	}
	
	public void goBackToLoginScreen(Stage primaryStage) {
		LoginScreen loginScreen = new LoginScreen();
		try 
		{
			Thread.sleep(500);
			primaryStage.close();
			Thread.sleep(1500);
			loginScreen.start(new Stage());
		} 
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void goToMainPage(Stage primaryStage) {
        MainPage mainPage = new MainPage(userSession);
        Scene scene = mainPage.getScene(primaryStage);
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.setResizable(true);
	}
	
	public void goToSearchPage(AnchorPane pane) throws Exception {
        pane.getChildren().clear();
		SearchPage searchPage = new SearchPage(userSession);
		pane.getChildren().add(searchPage.setupSearchPage());
	}
	
	public void goToHomepage(AnchorPane pane) throws Exception {
		pane.getChildren().clear();
		Homepage homepage = new Homepage(userSession);
		pane.getChildren().add(homepage.setupHomepage());
	}
	

}

package gui;

import javafx.stage.Stage;

public class Navigation {

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
}

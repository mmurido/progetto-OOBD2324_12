package gui;

import gui.analyticsPage.AnalyticsPage;
import gui.homePage.Homepage;
import gui.loginPage.LoginPage;
import gui.mainPage.MainPage;
import gui.searchPage.SearchPage;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navigation {
	
	private static Stage primaryStage;
	private static MainPage mainPage;
	private static Homepage homepage;
	private static SearchPage searchPage;
	private static AnalyticsPage analyticsPage;
	private static UnderConstructionPage underConstructionPage;
	
    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

	public static void navigateToLoginPage() {
		LoginPage loginScreen = new LoginPage();

		try 
		{
			Thread.sleep(500);
			primaryStage.close();
			Thread.sleep(1500);
			loginScreen.start(new Stage());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void navigateToMainPage() {
        mainPage = new MainPage(primaryStage);
        Scene scene = mainPage.scene;
        
		primaryStage.setScene(scene);
		primaryStage.requestFocus();
		
	    Platform.runLater(() -> {
	        primaryStage.centerOnScreen();
	    });

		navigateToHomepage();	
	}
	
	public static void navigateToSearchPage() {
		searchPage = new SearchPage();
		mainPage.innerBorderPane.setCenter(searchPage);
	}
	
	public static void navigateToHomepage() {
		homepage = new Homepage();
		mainPage.innerBorderPane.setCenter(homepage);
	}
	
	public static void navigateToAnalyticsPage() {
		analyticsPage = new AnalyticsPage();
		mainPage.innerBorderPane.setCenter(analyticsPage);
	}
	
	public static void navigateToUnderConstructionPage() {
		underConstructionPage = new UnderConstructionPage();
		mainPage.innerBorderPane.setCenter(underConstructionPage);
	}

}

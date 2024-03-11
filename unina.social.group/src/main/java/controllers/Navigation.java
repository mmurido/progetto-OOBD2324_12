package controllers;

import gui.analyticsPage.AnalyticsPage;
import gui.commonComponents.UnderConstructionPage;
import gui.groupOverview.GroupOverview;
import gui.homePage.Homepage;
import gui.loginPage.LoginPage;
import gui.mainPage.MainPage;
import gui.searchPage.SearchPage;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Navigation {
	
	private static Stage primaryStage;
	private static BorderPane innerBorderPane;
	private static Scene scene;
	private static MainPage mainPage;
	private static Homepage homepage;
	private static SearchPage searchPage;
	private static AnalyticsPage analyticsPage;
	private static UnderConstructionPage underConstructionPage;
	private static GroupOverview groupOverview;
	private static Pane currentPage;
	private static Pane previousPage;
	
    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }
    
    public static void setInnerBorderPane(BorderPane innerBorderPane) {
        Navigation.innerBorderPane = innerBorderPane;
    }
    
    public static void setScene(Scene scene) {
        Navigation.scene = scene;
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
        
		primaryStage.setScene(scene);
		
	    Platform.runLater(() -> {
	        primaryStage.centerOnScreen();
	        primaryStage.requestFocus();
	    });

		navigateToHomepage();	
	}
	
	public static void navigateToSearchPage() {
		searchPage = new SearchPage();
		innerBorderPane.setCenter(searchPage);
		previousPage = currentPage;
		currentPage = searchPage;
	}
	
	public static void navigateToHomepage() {
		homepage = new Homepage();
		innerBorderPane.setCenter(homepage);
		previousPage = currentPage;
		currentPage = homepage;
	}
	
	public static void navigateToAnalyticsPage() {
		analyticsPage = new AnalyticsPage();
		innerBorderPane.setCenter(analyticsPage);
		previousPage = currentPage;
		currentPage = analyticsPage;
	}
	
	public static void navigateToUnderConstructionPage() {
		underConstructionPage = new UnderConstructionPage();
		innerBorderPane.setCenter(underConstructionPage);
		previousPage = currentPage;
		currentPage = underConstructionPage;
	}
	
	public static void navigateToGroupOverview(
			String groupId, String groupName, String groupTopic, 
			String groupDescription, String groupOwnerUsername) {
		
		groupOverview = new GroupOverview(groupId, groupName, groupTopic, groupDescription, groupOwnerUsername);
		innerBorderPane.setCenter(groupOverview);
		previousPage = currentPage;
		currentPage = groupOverview;
	}
	
	public static void navigateToPreviousPage() {
		innerBorderPane.setCenter(previousPage);
		currentPage = previousPage;
	}

}

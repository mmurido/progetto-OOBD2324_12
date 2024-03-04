package gui;

<<<<<<< Updated upstream
=======
import gui.analyticsPage.AnalyticsPage;
import gui.homepage.Homepage;
import gui.loginPage.LoginPage;
import gui.mainpage.MainPage;
import gui.searchPage.SearchPage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
>>>>>>> Stashed changes
import javafx.stage.Stage;

public class Navigation {
	
	private static Stage primaryStage;

<<<<<<< Updated upstream
	public void goBackToLoginScreen(Stage primaryStage) {
		LoginScreen loginScreen = new LoginScreen();
=======
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
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
=======
	
	public static void navigateToMainPage() {
        mainPage = new MainPage(primaryStage);
        Scene scene = mainPage.scene;
        
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.setResizable(true);
		primaryStage.show();
		primaryStage.requestFocus();
		
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
>>>>>>> Stashed changes
}

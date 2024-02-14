package gui.homepage;

import gui.Navigation;
import gui.SearchScreen;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Homepage {

	private Stage primaryStage;
	private Scene scene;
	private BorderPane borderPane;
	private AnchorPane collapsedSidebar;
	private AnchorPane expandedSidebar;
	private AnchorPane contentPane;
	private double xOffset = 0;
	private double yOffset = 0;
	
	private WindowControls windowControls = new WindowControls();
	private HomepageCollapsedSidebarBuilder homepageCollapsedSidebarBuilder = new HomepageCollapsedSidebarBuilder();
	private HomepageExpandedSidebarBuilder homepageExpandedSidebarBuilder =  new HomepageExpandedSidebarBuilder();
	private Navigation navigation = new Navigation();
	
	public Scene getScene(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.createHomepage();
		return scene;
	}

	private Scene createHomepage() {
		
		//CREATE SHADOW PANE
		StackPane shadowPane = new StackPane();
		shadowPane.setStyle("-fx-background-color: transparent;");
		shadowPane.setPrefSize(500, 500);
		scene = new Scene(shadowPane);
		scene.setFill(Color.TRANSPARENT);
		
		//CREATE BORDER PANE
		borderPane = new BorderPane();
		borderPane.setStyle("-fx-background-color: white; " + "-fx-background-radius: 5;"
				+ "-fx-effect: dropshadow(gaussian, gray, 10, 0, 0, 0);");
		borderPane.setMinSize(700, 550);
		borderPane.setPadding(new Insets(4, 4, 4, 4));
		StackPane.setAlignment(borderPane, javafx.geometry.Pos.CENTER);
		StackPane.setMargin(borderPane, new javafx.geometry.Insets(10, 10, 10, 10));
		shadowPane.getChildren().add(borderPane);

		//ADD CONTENT PANE
		contentPane = new AnchorPane();
		contentPane.setStyle("-fx-background-color: white;");
		borderPane.setCenter(contentPane);
		
		//ADD WINDOW CONTROLS
		windowControls.setStage(primaryStage);
		windowControls.setWindow(borderPane);
		contentPane.getChildren().add(windowControls.setUpWindowControls());
		
		//ADD COLLAPSED SIDEBAR
		collapsedSidebar = homepageCollapsedSidebarBuilder.createCollapsedSidebar();
		borderPane.setLeft(collapsedSidebar);
		
		//ADD EXPANDED SIDEBAR
		expandedSidebar = homepageExpandedSidebarBuilder.createExpandedSidebar();
		
		//SET UP HOW TO SWITCH BETWEEN COLLAPSED AND EXPANDED SIDEBAR
		(collapsedSidebar.lookup("#sidebarExpandButton")).setOnMouseClicked(e -> {expandSidebar();});
		(expandedSidebar.lookup("#collapseSidebarToggleButton")).setOnMouseClicked(e -> {collapseSidebar();});
		
		//SET BEHAVIOR OF LOGOUT BUTTONS WHEN CLICKED
		(collapsedSidebar.lookup("#collapsedSidebarLogoutButton")).setOnMouseClicked(e -> {navigation.goBackToLoginScreen(primaryStage);});
		(expandedSidebar.lookup("#expandedSidebarLogoutButton")).setOnMouseClicked(e -> {navigation.goBackToLoginScreen(primaryStage);});

		//SET BEHAVIOR OF SEARCH BUTTON WHEN CLICKED
		(collapsedSidebar.lookup("#collapsedSidebarSearchButton")).setOnMouseClicked(e -> {
			SearchScreen searchScreen = new SearchScreen();
			AnchorPane p = searchScreen.a();
			contentPane.getChildren().add(p);
		});

		
		this.makeWindowMovable();
		
		bindCollapsedAndExpandedSidebarButtons();

		return scene;
		
	}
	
	private void collapseSidebar() {

		// animate sidebar sliding to the left
		Timeline timeline = new Timeline();
		KeyValue keyValueWidth = new KeyValue(((Rectangle) expandedSidebar.lookup("#slider")).widthProperty(), 175);
		KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.2), keyValueWidth);
		timeline.getKeyFrames().add(keyFrame);
		timeline.play();

		timeline.setOnFinished(e -> {
			borderPane.setLeft(null);
			borderPane.setLeft(collapsedSidebar);
			collapsedSidebar.setVisible(true);
		});

		// animate sidebar toggle button sliding to the left
		TranslateTransition sidebarToggleButtonTranslateTransition = new TranslateTransition(Duration.seconds(0.2), expandedSidebar.lookup("collapseSidebarToggleButton"));
		sidebarToggleButtonTranslateTransition.setToX(-90);
		sidebarToggleButtonTranslateTransition.play();

		// animate logout button sliding to the left
		TranslateTransition logoutButtonTranslateTransition = new TranslateTransition(Duration.seconds(0.2), expandedSidebar.lookup("expandedSidebarLogoutButton"));
		logoutButtonTranslateTransition.setToX(-90);
		logoutButtonTranslateTransition.play();
	}
	
	private void expandSidebar() {

		// animate sidebar sliding to the right
		Timeline timeline = new Timeline();
		KeyValue keyValueWidth = new KeyValue(((Rectangle) expandedSidebar.lookup("#slider")).widthProperty(), 0);
		KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.2), keyValueWidth);
		timeline.getKeyFrames().add(keyFrame);

		borderPane.setLeft(null);
		borderPane.setLeft(expandedSidebar);
		expandedSidebar.setVisible(true);

		timeline.play();

		// animate sidebar toggle button sliding to the right
		TranslateTransition sidebarToggleButtonTranslateTransition = new TranslateTransition(Duration.seconds(0.2),	expandedSidebar.lookup("#collapseSidebarToggleButton"));
		sidebarToggleButtonTranslateTransition.setToX(0);
		sidebarToggleButtonTranslateTransition.play();

		// animate logout button sliding to the right
		TranslateTransition logoutButtonTranslateTransition = new TranslateTransition(Duration.seconds(0.2), expandedSidebar.lookup("#expandedSidebarLogoutButton"));
		logoutButtonTranslateTransition.setToX(0);
		logoutButtonTranslateTransition.play();
	}
	
	private void bindCollapsedAndExpandedSidebarButtons() {
		((ToggleButton) collapsedSidebar.lookup("#collapsedSidebarHomeButton")).selectedProperty().bindBidirectional(
				((ToggleButton) expandedSidebar.lookup("#expandedSidebarHomeButton")).selectedProperty());
		((ToggleButton) collapsedSidebar.lookup("#collapsedSidebarSearchButton")).selectedProperty().bindBidirectional(
				((ToggleButton) expandedSidebar.lookup("#expandedSidebarSearchButton")).selectedProperty());
		((ToggleButton) collapsedSidebar.lookup("#collapsedSidebarMessageButton")).selectedProperty().bindBidirectional(
				((ToggleButton) expandedSidebar.lookup("#expandedSidebarMessageButton")).selectedProperty());
		((ToggleButton) collapsedSidebar.lookup("#collapsedSidebarNotificationButton")).selectedProperty()
				.bindBidirectional(((ToggleButton) expandedSidebar.lookup("#expandedSidebarNotificationButton"))
						.selectedProperty());
		((ToggleButton) collapsedSidebar.lookup("#collapsedSidebarAnalyticsButton")).selectedProperty()
				.bindBidirectional(
						((ToggleButton) expandedSidebar.lookup("#expandedSidebarAnalyticsButton")).selectedProperty());
		((ToggleButton) collapsedSidebar.lookup("#collapsedSidebarSettingsButton")).selectedProperty()
				.bindBidirectional(
						((ToggleButton) expandedSidebar.lookup("#expandedSidebarSettingsButton")).selectedProperty());
	}
	
	private void makeWindowMovable() {
		// Handle mouse pressed event to start dragging
		borderPane.setOnMousePressed(event -> {
			xOffset = event.getSceneX();
			yOffset = event.getSceneY();
		});

		// Handle mouse dragged event to move the stage
		borderPane.setOnMouseDragged(event -> {
			primaryStage.setX(event.getScreenX() - xOffset);
			primaryStage.setY(event.getScreenY() - yOffset);
		});

		contentPane.setOnMouseClicked(e -> {
			if (expandedSidebar.isVisible()) {
				collapseSidebar();
			}
		});
	}
	

}

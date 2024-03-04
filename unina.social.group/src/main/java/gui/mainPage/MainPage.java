package gui.mainPage;

import controllers.MainPageController;
import gui.ShadowPane;
import gui.WindowControls;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainPage extends BorderPane {

	private MainPageController controller;
	private Stage primaryStage;
	public ShadowPane shadowPane;
	public Scene scene;
	public BorderPane innerBorderPane;
	public AnchorPane windowControlsBar;
	public WindowControls windowControls;
	public CollapsedSidebar collapsedSidebar;
	public ExpandedSidebar expandedSidebar;
	private double xOffset = 0;
	private double yOffset = 0;
	
	public MainPage(Stage primaryStage) {
		this.primaryStage = primaryStage;
		controller = new MainPageController(this);
		initializeComponents();
		layoutComponents();
	}
	
	private void initializeComponents() {
		this.setMinSize(950, 550);
		this.setPadding(new Insets(4, 4, 4, 4));
		StackPane.setMargin(this, new javafx.geometry.Insets(10, 10, 10, 10));
		this.setStyle(
				"-fx-background-color: white; -fx-background-radius: 5;" +
				"-fx-effect: dropshadow(gaussian, gray, 10, 0, 0, 0);"
		);
		
		setupShadowPane();
		setupWindowControls();		
		innerBorderPane = new BorderPane();		
		collapsedSidebar = new CollapsedSidebar();
		expandedSidebar = new ExpandedSidebar();
		bindCollapsedAndExpandedSidebarButtons();
		controller.configureButtonsBehavior();
		handleClickOutsideOfSidebar();
		makeStageDraggable();
		
        DoubleBinding adjustedHeight = Bindings.subtract(this.heightProperty(), 8.0);
		expandedSidebar.slider.heightProperty().bind(adjustedHeight);
	}
	
	private void layoutComponents() {
		shadowPane.getChildren().add(this);
		this.setLeft(collapsedSidebar);
		this.setCenter(innerBorderPane);
		innerBorderPane.setTop(windowControlsBar);
		windowControlsBar.getChildren().add(windowControls);
		windowControlsBar.setPadding(new Insets(0, 0, 5, 0));
	}
	
	private void setupShadowPane() {
		shadowPane = new ShadowPane();
		scene = new Scene(shadowPane);
		scene.setFill(Color.TRANSPARENT);
	}

	private void setupWindowControls() {
		windowControlsBar = new AnchorPane();
		windowControls = new WindowControls(primaryStage, this);
	}
	
	private void animateSidebarSliding(double targetWidth, Node sidebar) {
	    Timeline timeline = new Timeline();
	    KeyValue keyValueWidth = new KeyValue(expandedSidebar.slider.widthProperty(), targetWidth);
	    KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.2), keyValueWidth);
	    timeline.getKeyFrames().add(keyFrame);
	    timeline.play();

	    if (sidebar == collapsedSidebar) {
		    timeline.setOnFinished(e -> {
		        this.setLeft(null);
		        this.setLeft(sidebar);
		        sidebar.setVisible(true);
		    });
	    }
	    
	    if (sidebar == expandedSidebar) {
	        this.setLeft(null);
	        this.setLeft(sidebar);
	        sidebar.setVisible(true);
	    }

	    TranslateTransition toggleButtonTranslateTransition = new TranslateTransition(
	    		Duration.seconds(0.2), expandedSidebar.topSection.collapseButton);
	    toggleButtonTranslateTransition.setToX(targetWidth == 0 ? 0 : -90);
	    toggleButtonTranslateTransition.play();

	    TranslateTransition logoutButtonTranslateTransition = new TranslateTransition(
	    		Duration.seconds(0.2), expandedSidebar.bottomSection.logoutButton);
	    logoutButtonTranslateTransition.setToX(targetWidth == 0 ? 0 : -90);
	    logoutButtonTranslateTransition.play();
	}

	public void collapseSidebar() {
	    animateSidebarSliding(175, collapsedSidebar);
	}

	public void expandSidebar() {
	    animateSidebarSliding(0, expandedSidebar);
	}
	
	private void bindCollapsedAndExpandedSidebarButtons() {
		 collapsedSidebar.homeButton.selectedProperty()
		 .bindBidirectional(expandedSidebar.homeButton.selectedProperty());

		 collapsedSidebar.searchButton.selectedProperty()
		 .bindBidirectional(expandedSidebar.searchButton.selectedProperty());
		
		 collapsedSidebar.messagesButton.selectedProperty()
		 .bindBidirectional(expandedSidebar.messagesButton.selectedProperty());
		
		 collapsedSidebar.notificationsButton.selectedProperty()
		 .bindBidirectional(expandedSidebar.notificationsButton.selectedProperty());
		
		 collapsedSidebar.analyticsButton.selectedProperty()
		 .bindBidirectional(expandedSidebar.analyticsButton.selectedProperty());
		
		 collapsedSidebar.settingsButton.selectedProperty()
		 .bindBidirectional(expandedSidebar.settingsButton.selectedProperty());
	}
	
    private void makeStageDraggable() {
        windowControlsBar.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        windowControlsBar.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
    }
    
	private void handleClickOutsideOfSidebar() {
		innerBorderPane.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
			if (event.getTarget() != windowControls.maximizeButton 
					&& event.getTarget() != windowControls.minimizeButton
					&& event.getTarget() != windowControlsBar)
				collapseSidebar();
		});
	}
}

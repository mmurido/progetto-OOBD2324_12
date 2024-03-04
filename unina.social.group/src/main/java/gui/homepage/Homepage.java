package gui.homepage;

<<<<<<< Updated upstream
<<<<<<<< Updated upstream:unina.social.group/src/main/java/gui/homepage/Homepage.java
import gui.Navigation;
========
import controllers.MainPageController;
import gui.ShadowPane;
>>>>>>>> Stashed changes:unina.social.group/src/main/java/gui/mainpage/MainPage.java
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
<<<<<<<< Updated upstream:unina.social.group/src/main/java/gui/homepage/Homepage.java
========
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
>>>>>>>> Stashed changes:unina.social.group/src/main/java/gui/mainpage/MainPage.java
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
<<<<<<<< Updated upstream:unina.social.group/src/main/java/gui/homepage/Homepage.java
import javafx.scene.control.ToggleButton;
========
import javafx.scene.input.MouseEvent;
>>>>>>>> Stashed changes:unina.social.group/src/main/java/gui/mainpage/MainPage.java
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

<<<<<<<< Updated upstream:unina.social.group/src/main/java/gui/homepage/Homepage.java
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
========
public class MainPage extends BorderPane{

	private MainPageController controller;
	public Stage primaryStage;
	public Scene scene;
	public ShadowPane shadowPane;
	public BorderPane innerBorderPane;
	public AnchorPane windowControlsBar;
	public WindowControls windowControls;
	public CollapsedSidebar collapsedSidebar;
	public ExpandedSidebar expandedSidebar;
	private double xOffset = 0;
	private double yOffset = 0;
	
	public MainPage(Stage primaryStage) {
>>>>>>>> Stashed changes:unina.social.group/src/main/java/gui/mainpage/MainPage.java
		this.primaryStage = primaryStage;
		controller = new MainPageController(this);
		initializeComponents();		
		layoutComponents();
	}
<<<<<<<< Updated upstream:unina.social.group/src/main/java/gui/homepage/Homepage.java

	private Scene createHomepage() {
========
	
	private void initializeComponents() {		
		this.setMinSize(950, 550);
		this.setPadding(new Insets(4, 4, 4, 4));	
		StackPane.setMargin(this, new javafx.geometry.Insets(10, 10, 10, 10));
		this.setStyle(
				"-fx-background-color: white; -fx-background-radius: 5;" +
				"-fx-effect: dropshadow(gaussian, gray, 10, 0, 0, 0);"
		);
>>>>>>>> Stashed changes:unina.social.group/src/main/java/gui/mainpage/MainPage.java
		
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
<<<<<<<< Updated upstream:unina.social.group/src/main/java/gui/homepage/Homepage.java
		
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
		(expandedSidebar.lookup("#collapseSidebarToggleButton")).setOnMouseClicked(e -> {collapseSidebar();});
		(collapsedSidebar.lookup("#sidebarExpandButton")).setOnMouseClicked(e -> {expandSidebar();});
		
		//SET BEHAVIOR OF LOGOUT BUTTONS WHEN CLICKED
		(expandedSidebar.lookup("#expandedSidebarLogoutButton")).setOnMouseClicked(e -> {navigation.goBackToLoginScreen(primaryStage);});
		(collapsedSidebar.lookup("#collapsedSidebarLogoutButton")).setOnMouseClicked(e -> {navigation.goBackToLoginScreen(primaryStage);});

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
	

========
	}
	
	private void setupWindowControls() {
		windowControlsBar = new AnchorPane();		
		windowControls = new WindowControls(primaryStage, this);
	}
	
	private void handleClickOutsideOfSidebar() {
		innerBorderPane.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
			if (event.getTarget() != windowControls.maximizeButton 
					&& event.getTarget() != windowControls.minimizeButton
					&& event.getTarget() != windowControlsBar)
				collapseSidebar();
		});
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

	    TranslateTransition toggleButtonTranslateTransition = new TranslateTransition(Duration.seconds(0.2), expandedSidebar.topSection.collapseButton);
	    toggleButtonTranslateTransition.setToX(targetWidth == 0 ? 0 : -90);
	    toggleButtonTranslateTransition.play();

	    TranslateTransition logoutButtonTranslateTransition = new TranslateTransition(Duration.seconds(0.2), expandedSidebar.bottomSection.logoutButton);
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
		collapsedSidebar.homeButton.selectedProperty().bindBidirectional(expandedSidebar.homeButton.selectedProperty());
		collapsedSidebar.searchButton.selectedProperty().bindBidirectional(expandedSidebar.searchButton.selectedProperty());
		collapsedSidebar.messagesButton.selectedProperty().bindBidirectional(expandedSidebar.messagesButton.selectedProperty());
		collapsedSidebar.notificationsButton.selectedProperty().bindBidirectional(expandedSidebar.notificationsButton.selectedProperty());
		collapsedSidebar.analyticsButton.selectedProperty().bindBidirectional(expandedSidebar.analyticsButton.selectedProperty());
		collapsedSidebar.settingsButton.selectedProperty().bindBidirectional(expandedSidebar.settingsButton.selectedProperty());
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
>>>>>>>> Stashed changes:unina.social.group/src/main/java/gui/mainpage/MainPage.java
=======
import controllers.HomepageController;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Homepage extends BorderPane {
	
	private HomepageController controller;
	public AnchorPane topSection;
	public StackPane banner;
	public Text title;
	public BorderPane innerBorderPane;
	public VBox rightSection;
	public GroupDisplaySection groupDisplaySection;
	public SearchAndSortBox searchAndSortBox;
	public SuggestedGroupsSection suggestedGroupsSection;

	public Homepage() {
		this.controller = new HomepageController(this);
		initializeComponents();
		layoutComponents();
	}
	
	private void initializeComponents() {
		this.setStyle("-fx-background-color: #f9f9f9;");

		setupTopSection();

		innerBorderPane = new BorderPane();
		groupDisplaySection = new GroupDisplaySection();

		rightSection = new VBox(30);
		BorderPane.setMargin(rightSection, new Insets(20, 30, 20, 30));

		searchAndSortBox = new SearchAndSortBox();
		suggestedGroupsSection = new SuggestedGroupsSection();

		configureSearchBehavior();
		handleSortButtonClicked();
		handleDeleteButtonClicked();
		
		adjustContentToPageSize();
	}
	
	private void layoutComponents() {
		AnchorPane.setTopAnchor(this, 0.0);
		AnchorPane.setLeftAnchor(this, 0.0);
		AnchorPane.setRightAnchor(this, 0.0);
		AnchorPane.setBottomAnchor(this, 0.0);
		
		AnchorPane.setLeftAnchor(banner, 0.0);
		AnchorPane.setTopAnchor(banner, 0.0);
		AnchorPane.setRightAnchor(banner, 0.0);
		
		AnchorPane.setTopAnchor(title, 20.0);
		AnchorPane.setLeftAnchor(title, 130.0);
		
		topSection.getChildren().addAll(banner, title);
		this.setTop(topSection);
		
    	this.setCenter(innerBorderPane);
		innerBorderPane.setCenter(groupDisplaySection);
		
		rightSection.getChildren().addAll(searchAndSortBox, suggestedGroupsSection);
		innerBorderPane.setRight(rightSection);
	}
    
    private void setupTopSection() {
    	topSection = new AnchorPane();		
		setupBanner();
		setupTitle();
    }
    
    private void setupBanner() {
		banner = new StackPane();
		banner.setPrefSize(0, 80);
        banner.prefWidthProperty().bind(this.widthProperty());
		banner.setStyle("-fx-background-color: #00958c");

    }
    
    private void setupTitle() {
		title = new Text("I tuoi gruppi:");
		title.setStyle(
				"-fx-font-family: 'Product Sans'; -fx-font-size: 30;" + 
				"-fx-fill: white; -fx-font-weight: bold;"
		);
    }
    
    private void handleSortButtonClicked() {
		searchAndSortBox.sortAZButton.setOnAction(event -> {
			controller.onSortAZButtonClicked();
		});
		
		searchAndSortBox.sortZAButton.setOnAction(event -> {
			controller.onSortZAButtonClicked();
		});
    }
    
    private void configureSearchBehavior() {
        searchAndSortBox.searchField.setOnAction(event -> {
            controller.handleSearch();
        });
    }
    
    private void handleDeleteButtonClicked() {
		searchAndSortBox.deleteButton.setOnAction(e -> {
			System.out.println("cliccato");
			controller.onDeleteButtonClicked();
		});
    }
    
    private void adjustContentToPageSize() {
        groupDisplaySection.widthProperty().addListener((observable, oldValue, newValue) -> {
            double newWidth = newValue.doubleValue();
            if (newWidth == 304 || newWidth == 712) {
                innerBorderPane.setRight(null);
            }
            else {
            	innerBorderPane.setRight(rightSection);
            }
        });
    }
    
>>>>>>> Stashed changes
}

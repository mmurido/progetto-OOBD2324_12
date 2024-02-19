package gui.mainpage;

import controllers.UserSession;
import gui.IconUtils;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class MainPageExpandedSidebarBuilder {
	
	public static final String HIGHLIGHTED_STYLE = "-fx-background-color: #136472; -fx-background-radius: 10";
	public static final String UNHIGHLIGHTED_STYLE = "-fx-background-color: #0e5460; -fx-background-radius: 10";

	private IconUtils iconUtils = new IconUtils();
	private MainPageSidebarButtonsUtils homepageSidebarButtonsUtils = new MainPageSidebarButtonsUtils();

	private UserSession userSession;
	private AnchorPane expandedSidebar;	
	private VBox expandedSidebarNavigationButtons;
	private VBox navigationButtonsLabels;
	private HBox expandedSidebarTopSection;
	private HBox expandedSidebarBottomSection;
	private HBox userProfileBox;
	private VBox userInfoBox;
	
	public MainPageExpandedSidebarBuilder(UserSession userSession) {
		this.userSession = userSession;
	}
	
	public AnchorPane createExpandedSidebar() {
		//CREATE EXPANDED SIDEBAR
		expandedSidebar = new AnchorPane();

		expandedSidebar.setStyle(
				"-fx-background-color: #0e5460;" + 
				"-fx-background-radius: 10;" +
				"-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 0);"
		);

		double width = 230;
		double height = 200;
		expandedSidebar.setPrefSize(width, height);

		expandedSidebar.setVisible(false);	
		
		//ADD BOTTOM LINE
		Rectangle expandedSidebarSeparatingLine = new Rectangle(220, 0.7);
		expandedSidebarSeparatingLine.setFill(Color.WHITE);
		AnchorPane.setBottomAnchor(expandedSidebarSeparatingLine, 65.0);
		AnchorPane.setLeftAnchor(expandedSidebarSeparatingLine, 5.0);
		expandedSidebar.getChildren().add(expandedSidebarSeparatingLine);
		
		//ADD NAVIGATION BUTTONS
		expandedSidebarNavigationButtons = new VBox();
		AnchorPane.setTopAnchor(expandedSidebarNavigationButtons, 80.0);
		AnchorPane.setLeftAnchor(expandedSidebarNavigationButtons, 10.0);
		expandedSidebarNavigationButtons.setSpacing(7);

		createExpandedSidebarNavigationButton("expandedSidebarHomeButton", new ImageView(iconUtils.homeIcon));
		createExpandedSidebarNavigationButton("expandedSidebarSearchButton", new ImageView(iconUtils.searchIcon));
		createExpandedSidebarNavigationButton("expandedSidebarNotificationButton", new ImageView(iconUtils.notificationIcon));
		createExpandedSidebarNavigationButton("expandedSidebarMessageButton", new ImageView(iconUtils.messageIcon));
		createExpandedSidebarNavigationButton("expandedSidebarSettingsButton", new ImageView(iconUtils.settingsIcon));
		createExpandedSidebarNavigationButton("expandedSidebarAnalyticsButton", new ImageView(iconUtils.analyticsIcon));

		expandedSidebar.getChildren().add(expandedSidebarNavigationButtons);
		
		//ADD LABELS
		navigationButtonsLabels = new VBox();
		navigationButtonsLabels.setMouseTransparent(true);
		AnchorPane.setTopAnchor(navigationButtonsLabels, 91.0);
		AnchorPane.setLeftAnchor(navigationButtonsLabels, 60.0);
		navigationButtonsLabels.setSpacing(29);

		this.createNavigationButtonsLabels("Home");
		this.createNavigationButtonsLabels("Cerca...");
		this.createNavigationButtonsLabels("Notifiche");
		this.createNavigationButtonsLabels("Messaggi");
		this.createNavigationButtonsLabels("Impostazioni");
		this.createNavigationButtonsLabels("Statistiche");

		expandedSidebar.getChildren().add(navigationButtonsLabels);
		
		//ADD TOP SECTION
		createTopSection();
		
		//ADD BOTTOM SECTION
		createBottomSection();
		
		//ADD SLIDER
		Rectangle slider = new Rectangle(175, 0);
		slider.setId("slider");
		AnchorPane.setRightAnchor(slider, 0.0);
		slider.setFill(Color.WHITE);
		slider.heightProperty().bind(expandedSidebar.heightProperty());
		expandedSidebar.getChildren().add(slider);
		
		return expandedSidebar;
	}

	private void createTopSection() {
		expandedSidebarTopSection = new HBox();
		expandedSidebar.getChildren().add(expandedSidebarTopSection);
		AnchorPane.setTopAnchor(expandedSidebarTopSection, 10.0);
		AnchorPane.setLeftAnchor(expandedSidebarTopSection, 20.0);
		expandedSidebarTopSection.setSpacing(50);
		
		//ADD LOGO
		ImageView logo = new ImageView(iconUtils.logo);
		logo.setFitWidth(110);
		logo.setFitHeight(50);
		logo.setTranslateY(+10);
		logo.setStyle("-fx-effect: dropshadow(gaussian, black, 3, 0, 0, 0);");
		logo.setMouseTransparent(true);
		expandedSidebarTopSection.getChildren().add(logo);		
		
		//ADD COLLAPSE SIDEBAR BUTTON
		Button collapseSidebarButton = createExpandedSidebarUtilityButton("collapseSidebarToggleButton", new ImageView(iconUtils.navigationIcon));
		expandedSidebarTopSection.getChildren().add(collapseSidebarButton);
	}

	private void createBottomSection() {
		expandedSidebarBottomSection = new HBox();
		expandedSidebar.getChildren().add(expandedSidebarBottomSection);
		AnchorPane.setBottomAnchor(expandedSidebarBottomSection, 10.0);
		AnchorPane.setLeftAnchor(expandedSidebarBottomSection, 7.0);
		expandedSidebarBottomSection.setSpacing(3);
		
		//ADD PROFILE BOX
		userProfileBox = new HBox();
		expandedSidebarBottomSection.getChildren().add(userProfileBox);

		userProfileBox.setStyle(MainPageSidebarButtonsUtils.HIGHLIGHTED_STYLE);
		userProfileBox.setPrefSize(173, 45);
		userProfileBox.setPadding(new Insets(5, 8, 4, 10));
		userProfileBox.setSpacing(8);
		userProfileBox.setOnMouseEntered(e -> userProfileBox.setCursor(javafx.scene.Cursor.HAND));
		userProfileBox.setOnMouseExited(e -> userProfileBox.setCursor(javafx.scene.Cursor.DEFAULT));

		//ADD PROFILE PICTURE TO PROFILE BOX
		ImageView profilePicture = new ImageView(iconUtils.profilePicture);
		profilePicture.setFitHeight(35);
		profilePicture.setFitWidth(35);

		Rectangle profilePictureShape = new Rectangle(profilePicture.getFitWidth(), profilePicture.getFitHeight());
		profilePictureShape.setArcHeight(10);
		profilePictureShape.setArcWidth(10);
		profilePicture.setClip(profilePictureShape);
		userProfileBox.getChildren().add(profilePicture);
		
		//ADD PROFILE INFO TO PROFILE BOX
		userInfoBox = new VBox();
		userInfoBox.setPadding(new Insets(3, 0, 0, 0));

		Text firstAndLastName = new Text(userSession.getLoggedUser().getNome() + " " + userSession.getLoggedUser().getCognome());
		firstAndLastName.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 12; -fx-fill: white; -fx-font-weight: bold;");
		userInfoBox.getChildren().add(firstAndLastName);

		Text username = new Text(userSession.getLoggedUser().getUsername());
		username.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 12; -fx-fill: white;");
		userInfoBox.getChildren().add(username);

		firstAndLastName.setMouseTransparent(true);
		username.setMouseTransparent(true);

		userProfileBox.getChildren().add(userInfoBox);
		
		//ADD LOGOUT BUTTON
		Button logoutButton = createExpandedSidebarUtilityButton("expandedSidebarLogoutButton", new ImageView(iconUtils.logoutIcon));
		expandedSidebarBottomSection.getChildren().add(logoutButton);
		((Button) expandedSidebarBottomSection.lookup("#expandedSidebarLogoutButton")).getGraphic().setTranslateX(-3);
	}

	private void createNavigationButtonsLabels(String label) {
		Label navigationButtonLabel = new Label(label);
		navigationButtonLabel.setStyle(
				"-fx-font-family: 'Product Sans';" + 
				"-fx-font-size: 14;" + 
				"-fx-text-fill: white;"
		);
		navigationButtonsLabels.getChildren().add(navigationButtonLabel);
	}

	private void createExpandedSidebarNavigationButton(String id, ImageView icon) {
		ToggleButton navigationButton = new ToggleButton();
		expandedSidebarNavigationButtons.getChildren().add(navigationButton);
		navigationButton.setId(id);

		double width = 210;
		double height = 40;
		navigationButton.setPrefSize(width, height);

		double xOffset = -87.5;
		icon.setTranslateX(xOffset);
		iconUtils.setIcon(navigationButton, icon);

		navigationButton.setStyle("-fx-background-color: #0e5460;" + "-fx-background-radius: 10;"
				+ "-fx-text-fill: white;" + "-fx-font-family: 'Product Sans';" + "-fx-font-size: 14;"
				+ "-fx-content-display: right;" + "-fx-graphic-text-gap: 10;");

		navigationButton.setOnMouseEntered(e -> {homepageSidebarButtonsUtils.highlightButton(navigationButton);});
		navigationButton.setOnMouseExited(e -> {homepageSidebarButtonsUtils.unhighlightButtonIfNotSelected(navigationButton);});
		navigationButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				navigationButton.setStyle(HIGHLIGHTED_STYLE);
				navigationButton.setMouseTransparent(true);
			}
			else {
				navigationButton.setStyle(UNHIGHLIGHTED_STYLE);
				navigationButton.setMouseTransparent(false);			
			}
		});
	}

	private Button createExpandedSidebarUtilityButton(String id, ImageView icon) {
		Button utilityButton = new Button();
		//expandedSidebarBottomSection.getChildren().add(utilityButton);
		utilityButton.setId(id);
		double width = 40;
		double height = 40;
		utilityButton.setPrefSize(width, height);
		utilityButton.setTranslateX(-173);
		utilityButton.setStyle("-fx-background-color: transparent; -fx-background-radius: 10");
		iconUtils.setIcon(utilityButton, icon);		
		utilityButton.setOnMouseEntered(e -> {iconUtils.zoomInIcon(icon);});
		utilityButton.setOnMouseExited(e -> {iconUtils.zoomOutIcon(icon);});
		return utilityButton;
	}

}

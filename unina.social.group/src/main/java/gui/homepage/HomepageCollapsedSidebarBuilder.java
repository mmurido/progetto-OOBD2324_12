package gui.homepage;

import gui.IconUtils;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HomepageCollapsedSidebarBuilder {

	public static final String HIGHLIGHTED_STYLE = "-fx-background-color: #136472; -fx-background-radius: 10";
	public static final String UNHIGHLIGHTED_STYLE = "-fx-background-color: #0e5460; -fx-background-radius: 10";

	private HomepageSidebarButtonsUtils homepageSidebarButtonsUtils = new HomepageSidebarButtonsUtils();
	private IconUtils iconUtils = new IconUtils();
	
	private AnchorPane collapsedSidebar;
	private VBox vBox;
	private ToggleGroup collapsedSidebarNavigationButtonsToggleGroup;
	
	public AnchorPane createCollapsedSidebar() {
		//CREATE
		collapsedSidebar = new AnchorPane();

		//SET STYLE
		collapsedSidebar.setStyle(
				"-fx-background-color: #0e5460;" + 
				"-fx-background-radius: 10;" +
				"-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 0);");
		
		//SET SIZE
		double closedSidebarWidth = 55;
		double closedSidebarHeight = 200;
		collapsedSidebar.setPrefSize(closedSidebarWidth, closedSidebarHeight);
		
		//ADD BUTTONS		
		this.addButtonsToCollapsedSideBar();

		//ADD BOTTOM LINE
		Rectangle collapsedSidebarSeparatingLine = new Rectangle(45, 0.7);
		collapsedSidebarSeparatingLine.setFill(Color.WHITE);
		AnchorPane.setBottomAnchor(collapsedSidebarSeparatingLine, 65.0);
		AnchorPane.setLeftAnchor(collapsedSidebarSeparatingLine, 5.0);
		collapsedSidebar.getChildren().add(collapsedSidebarSeparatingLine);
		
		return collapsedSidebar;
	}

	private void addButtonsToCollapsedSideBar() {

		// ADD NAVIGATION BUTTONS
		vBox = new VBox();
		AnchorPane.setTopAnchor(vBox, 80.0);
		AnchorPane.setLeftAnchor(vBox, 7.0);
		vBox.setSpacing(7);
		collapsedSidebarNavigationButtonsToggleGroup = new ToggleGroup();
		this.createCollapsedSidebarNavigationButton("collapsedSidebarHomeButton", new ImageView(iconUtils.homeIcon));
		this.createCollapsedSidebarNavigationButton("collapsedSidebarSearchButton", new ImageView(iconUtils.searchIcon));
		this.createCollapsedSidebarNavigationButton("collapsedSidebarNotificationButton", new ImageView(iconUtils.notificationIcon));
		this.createCollapsedSidebarNavigationButton("collapsedSidebarMessageButton", new ImageView(iconUtils.messageIcon));
		this.createCollapsedSidebarNavigationButton("collapsedSidebarSettingsButton", new ImageView(iconUtils.settingsIcon));
		this.createCollapsedSidebarNavigationButton("collapsedSidebarAnalyticsButton", new ImageView(iconUtils.analyticsIcon));
		collapsedSidebar.getChildren().add(vBox);

		// ADD LOGOUT BUTTON
		this.createCollapsedSidebarUtilityButton("collapsedSidebarLogoutButton", new ImageView(iconUtils.logoutIcon));
		((ToggleButton) collapsedSidebar.lookup("#collapsedSidebarLogoutButton")).getGraphic().setTranslateX(-3);
		AnchorPane.setBottomAnchor(collapsedSidebar.lookup("#collapsedSidebarLogoutButton"), 10.0);
		AnchorPane.setLeftAnchor(collapsedSidebar.lookup("#collapsedSidebarLogoutButton"), 7.0);

		// ADD EXPAND SIDEBAR BUTTON
		this.createCollapsedSidebarUtilityButton("sidebarExpandButton", new ImageView(iconUtils.navigationIcon));
		AnchorPane.setTopAnchor(collapsedSidebar.lookup("#sidebarExpandButton"), 10.0);
		AnchorPane.setLeftAnchor(collapsedSidebar.lookup("#sidebarExpandButton"), 7.0);
	}

	private ToggleButton createToggleButton(String id, ImageView icon) {
		//CREATE
		ToggleButton toggleButton = new ToggleButton();
		toggleButton.setId(id);

		//SET STYLE
		toggleButton.setStyle("-fx-background-color: #0e5460; -fx-background-radius: 10");

		//SET SIZE
		double width = 40;
		double height = 40;
		toggleButton.setPrefSize(width, height);

		//SET CONTENT
		iconUtils.setIcon(toggleButton, icon);
		
		return toggleButton;
	}
	
	private void createCollapsedSidebarNavigationButton(String id, ImageView icon) {
		//CREATE
		ToggleButton navigationButton = this.createToggleButton(id, icon);
		
		//SET BEHAVIOR
		navigationButton.setOnMouseEntered(e -> {homepageSidebarButtonsUtils.highlightButton(navigationButton);});
		navigationButton.setOnMouseExited(e -> {homepageSidebarButtonsUtils.unhighlightButtonIfNotSelected(navigationButton);});
		navigationButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {navigationButton.setStyle(HIGHLIGHTED_STYLE);}
			else {navigationButton.setStyle(UNHIGHLIGHTED_STYLE);}
		});
		
		//SET TOGGLE GROUP
		navigationButton.setToggleGroup(collapsedSidebarNavigationButtonsToggleGroup);
		
		//ADD TO VBOX
		vBox.getChildren().add(navigationButton);
	}

	private void createCollapsedSidebarUtilityButton(String id, ImageView icon) {
		//CREATE
		ToggleButton utilityButton = createToggleButton(id, icon);		
		collapsedSidebar.getChildren().add(utilityButton);
		
		//SET BEHAVIOR
		utilityButton.setOnMouseEntered(e -> {homepageSidebarButtonsUtils.highlightButton(utilityButton);});
		utilityButton.setOnMouseExited(e -> {homepageSidebarButtonsUtils.unhighlightButton(utilityButton);});	
	}

}

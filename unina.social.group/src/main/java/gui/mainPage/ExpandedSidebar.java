package gui.mainpage;

import gui.IconUtils;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class ExpandedSidebar extends AnchorPane {
		
	public SidebarTopSection topSection;
	public VBox navigationButtonsContainer;
	public NavigationButton homeButton;
	public NavigationButton searchButton;
	public NavigationButton notificationsButton;
	public NavigationButton messagesButton;
	public NavigationButton settingsButton;
	public NavigationButton analyticsButton;
	public Line line;
	public SidebarBottomSection bottomSection;
	public Rectangle slider;

	private static final String SIDEBAR_STYLE =
			"-fx-background-color: #0e5460; -fx-background-radius: 10;";
	
	public ExpandedSidebar() {
        initializeComponents();
        layoutComponents();
	}
	
	private void initializeComponents() {
		this.setStyle(SIDEBAR_STYLE);
		double width = 230;
		double height = 200;
		this.setPrefSize(width, height);
		this.setVisible(false);	
		
		topSection = new SidebarTopSection();		
		setUpNavigationButtons();
		initializeBottomLine();		
		bottomSection = new SidebarBottomSection();
		setupSlider();
	}
	
	private void layoutComponents() {
		this.getChildren().addAll(
				topSection, navigationButtonsContainer, 
				line, bottomSection, slider);
		
		AnchorPane.setTopAnchor(navigationButtonsContainer, 80.0);
		AnchorPane.setLeftAnchor(navigationButtonsContainer, 10.0);
		
		AnchorPane.setBottomAnchor(line, 65.0);
		AnchorPane.setLeftAnchor(line, 5.0);
		
		AnchorPane.setTopAnchor(slider, 0.0);
		AnchorPane.setBottomAnchor(slider, 0.0);
		AnchorPane.setRightAnchor(slider, -1.0);
	}

	private void setUpNavigationButtons() {
		navigationButtonsContainer = new VBox(7);

		ToggleGroup toggleGroup = new ToggleGroup();
		
		homeButton = new NavigationButton(IconUtils.homeIcon, "Home", toggleGroup);
		searchButton = new NavigationButton(IconUtils.searchIcon, "Cerca...", toggleGroup);
		notificationsButton = new NavigationButton(IconUtils.bellIcon, "Notifiche", toggleGroup);
		messagesButton = new NavigationButton(IconUtils.messageIcon, "Messaggi", toggleGroup);
		settingsButton = new NavigationButton(IconUtils.settingsIcon, "Impostazioni", toggleGroup);
		analyticsButton = new NavigationButton(IconUtils.analyticsIcon, "Statistiche", toggleGroup);
		
		navigationButtonsContainer.getChildren().addAll(
				homeButton, searchButton, notificationsButton, 
				messagesButton, settingsButton, analyticsButton);
	}
	
	private void initializeBottomLine() {
		line = new Line(0, 0, 220, 0);
        line.setStroke(Color.WHITE);
        line.setStrokeWidth(0.7);
	}
	
	private void setupSlider() {
		slider = new Rectangle(175, 0);
		slider.setFill(Color.WHITE);
	}

}

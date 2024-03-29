package gui.mainPage;

import controllers.Navigation;
import gui.commonComponents.IconUtils;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class CollapsedSidebar extends AnchorPane {
	
	VBox navigationButtonsContainer;
	NavigationButton homeButton;
	NavigationButton searchButton;
	NavigationButton notificationsButton;
	NavigationButton messagesButton;
	NavigationButton settingsButton;
	NavigationButton analyticsButton;
	SidebarButton logoutButton;
	SidebarButton expandButton;
	Line line;
	
	private static final String SIDEBAR_STYLE = 
			"-fx-background-color: #0e5460; -fx-background-radius: 10;" +
			"-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 0);";
	
    public CollapsedSidebar() {
        initializeComponents();
        layoutComponents();
        setButtonsBehavior();
    }

    private void initializeComponents() {
    	initializeSidebar();
    	initializeNavigationButtons();
    	
    	logoutButton = new SidebarButton(IconUtils.logoutIcon);
		logoutButton.getGraphic().setTranslateX(-3);
		
		expandButton = new SidebarButton(IconUtils.navigationIcon);

		line = new Line(0, 0, 45, 0);
        line.setStroke(Color.WHITE);
        line.setStrokeWidth(0.7);
    }
    
    private void layoutComponents() {		
		this.getChildren().add(expandButton);
		AnchorPane.setTopAnchor(expandButton, 10.0);
		AnchorPane.setLeftAnchor(expandButton, 7.0);
		
		this.getChildren().add(navigationButtonsContainer);
		AnchorPane.setTopAnchor(navigationButtonsContainer, 80.0);
		AnchorPane.setLeftAnchor(navigationButtonsContainer, 7.0);
		
		this.getChildren().add(logoutButton);
		AnchorPane.setBottomAnchor(logoutButton, 10.0);
		AnchorPane.setLeftAnchor(logoutButton, 7.0);
		
		this.getChildren().add(line);
		AnchorPane.setBottomAnchor(line, 65.0);
		AnchorPane.setLeftAnchor(line, 5.0);	
    }
    
    private void initializeSidebar() {
		double width = 55;
		double height = 200;
		this.setPrefSize(width, height);
    	this.setStyle(SIDEBAR_STYLE);    	
    }
    
    private void initializeNavigationButtons() {
		navigationButtonsContainer = new VBox(7);
		
		ToggleGroup toggleGroup = new ToggleGroup();
		
		homeButton = new NavigationButton(IconUtils.homeIcon, toggleGroup);
		searchButton = new NavigationButton(IconUtils.searchIcon, toggleGroup);
		notificationsButton = new NavigationButton(IconUtils.bellIcon, toggleGroup);
		messagesButton = new NavigationButton(IconUtils.messageIcon, toggleGroup);
		settingsButton = new NavigationButton(IconUtils.settingsIcon, toggleGroup);
		analyticsButton = new NavigationButton(IconUtils.analyticsIcon, toggleGroup);
		
		navigationButtonsContainer.getChildren().addAll(
				homeButton, searchButton, notificationsButton, 
				messagesButton, settingsButton, analyticsButton);
    }
    
    private void setButtonsBehavior() {
   		homeButton.setOnMouseClicked(e -> {
    		Navigation.navigateToHomepage();
    	});
   		
    	searchButton.setOnMouseClicked(e -> {
    		Navigation.navigateToSearchPage();
    	});
    		
    	analyticsButton.setOnMouseClicked(e -> {
    		Navigation.navigateToAnalyticsPage();
    	});
    	
    	logoutButton.setOnMouseClicked(e -> {
    		Navigation.navigateToLoginPage();
    	});

    	setBehaviorForNotImplementedButton(messagesButton);
    	setBehaviorForNotImplementedButton(notificationsButton);
    	setBehaviorForNotImplementedButton(settingsButton);
    }
    
    private void setBehaviorForNotImplementedButton(ToggleButton button) {
		button.setOnMouseClicked(e -> {
			Navigation.navigateToUnderConstructionPage();
		});
    }

}

package controllers;

import gui.Navigation;
import gui.mainPage.MainPage;
import javafx.scene.control.ToggleButton;

public class MainPageController {
	
	private MainPage mainPage;
	
	public MainPageController(MainPage mainPage) {
		this.mainPage = mainPage;
	}
	
	public void configureButtonsBehavior() {
		onHomeButtonClicked(mainPage.collapsedSidebar.homeButton);
		onHomeButtonClicked(mainPage.expandedSidebar.homeButton);
		
		onSearchButtonClicked(mainPage.collapsedSidebar.searchButton);
		onSearchButtonClicked(mainPage.expandedSidebar.searchButton);
		
		onAnalyticsButtonClicked(mainPage.collapsedSidebar.analyticsButton);
		onAnalyticsButtonClicked(mainPage.expandedSidebar.analyticsButton);
		
		onLogoutButtonClicked(mainPage.collapsedSidebar.logoutButton);
		onLogoutButtonClicked(mainPage.expandedSidebar.bottomSection.logoutButton);
		
		onExpandButtonClicked(mainPage.collapsedSidebar.expandButton);
		
		onCollapseButtonClicked(mainPage.expandedSidebar.topSection.collapseButton);
		
		onOtherButtonClicked(mainPage.collapsedSidebar.messagesButton);
		onOtherButtonClicked(mainPage.expandedSidebar.messagesButton);
		
		onOtherButtonClicked(mainPage.collapsedSidebar.notificationsButton);
		onOtherButtonClicked(mainPage.expandedSidebar.notificationsButton);
		
		onOtherButtonClicked(mainPage.collapsedSidebar.settingsButton);
		onOtherButtonClicked(mainPage.expandedSidebar.settingsButton);		
	}

	private void onHomeButtonClicked(ToggleButton homeButton) {
		homeButton.setOnMouseClicked(e -> {
			Navigation.navigateToHomepage();
		});
	}
	
	private void onSearchButtonClicked(ToggleButton searchButton) {
		searchButton.setOnMouseClicked(e -> {
			Navigation.navigateToSearchPage();
		});
	}
	
	private void onAnalyticsButtonClicked(ToggleButton analyticsButton) {
		analyticsButton.setOnMouseClicked(e -> {
			Navigation.navigateToAnalyticsPage();
		});
	}
	
	private void onLogoutButtonClicked(ToggleButton logoutButton) {
		logoutButton.setOnMouseClicked(e -> {
			Navigation.navigateToLoginPage();
		});
	}
	
	private void onExpandButtonClicked(ToggleButton expandButton) {
		expandButton.setOnMouseClicked(e -> {
			mainPage.expandSidebar();
		});
	}
	
	private void onCollapseButtonClicked(ToggleButton collapseButton) {
		collapseButton.setOnMouseClicked(e -> {
			mainPage.collapseSidebar();
		});
	}
	
	private void onOtherButtonClicked(ToggleButton button) {
		button.setOnMouseClicked(e -> {
			Navigation.navigateToUnderConstructionPage();
		});
	}

}

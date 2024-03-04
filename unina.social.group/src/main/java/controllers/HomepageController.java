package controllers;

import gui.homepage.Homepage;
import javafx.scene.control.ToggleButton;

public class HomepageController {

	private Homepage homepage;
	
	public HomepageController(Homepage homepage) {
		this.homepage = homepage;
	}
	
	public void onSortAZButtonClicked() {
		if (homepage.searchAndSortBox.sortAZButton.isSelected()) {
			homepage.groupDisplaySection.displaySortedAZ();
		} else {
			homepage.groupDisplaySection.displayCurrentlyDisplayed();
		}
	}
	
	public void onSortZAButtonClicked() {
		if (homepage.searchAndSortBox.sortZAButton.isSelected()) {
			homepage.groupDisplaySection.displaySortedZA();
		} else {
			homepage.groupDisplaySection.displayCurrentlyDisplayed();
		}
	}
	
	public void handleSearch() {
		String textEntered = homepage.searchAndSortBox.searchField.getText();
		if (!textEntered.isEmpty()) {
			homepage.searchAndSortBox.searchField.clear();
			homepage.searchAndSortBox.searchField.setEditable(false);
			homepage.searchAndSortBox.toggleGroup.getToggles().forEach(toggle -> ((ToggleButton) toggle).setSelected(false));
			homepage.groupDisplaySection.displaySearchResults(textEntered);
			homepage.searchAndSortBox.highlightText(textEntered);
			homepage.searchAndSortBox.addDeleteButton();
		}
	}
	
    public void onDeleteButtonClicked() {
		homepage.searchAndSortBox.searchBar.getChildren().removeAll(
				homepage.searchAndSortBox.highlightedText, 
				homepage.searchAndSortBox.deleteButton
		);
		
		homepage.searchAndSortBox.searchField.setEditable(true);

		homepage.groupDisplaySection.displayAll();
    }

}

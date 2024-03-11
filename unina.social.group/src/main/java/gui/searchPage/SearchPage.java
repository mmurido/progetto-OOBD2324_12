package gui.searchPage;

import java.util.List;

import controllers.SearchPageController;
import gui.commonComponents.GroupCard;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class SearchPage extends BorderPane {
	
	SearchPageController controller;
	SearchPageTopSection topSection;
	ResultsDisplaySection resultsDisplaySection;

    public SearchPage() {
		controller = new SearchPageController(this);
    	initializeComponents();
    	layoutComponents();
		configureSearchBehavior();
    }
  	
	private void initializeComponents() {
		this.setStyle("-fx-background-color: white;");
		topSection = new SearchPageTopSection();
		topSection.banner.prefWidthProperty().bind(this.widthProperty());
		resultsDisplaySection = new ResultsDisplaySection();
	}
	
	private void layoutComponents() {
		AnchorPane.setTopAnchor(this, 0.0);
		AnchorPane.setLeftAnchor(this, 0.0);
		AnchorPane.setRightAnchor(this, 0.0);
		AnchorPane.setBottomAnchor(this, 0.0);
		
		this.setTop(topSection);
		this.setCenter(resultsDisplaySection);
	}
	
	private void configureSearchBehavior() {
		topSection.searchField.setOnKeyPressed(event -> {
			String textEntered = topSection.searchField.getText();

			if (event.getCode() != KeyCode.ENTER || textEntered.isEmpty()) 
				return;

			ToggleButton selectedFilter = (ToggleButton) topSection.filterButtonsToggleGroup.getSelectedToggle();

			if (selectedFilter == null) {
				topSection.pulsateFilterButtons();
			} else {
				controller.performSearch(selectedFilter.getText(), textEntered);
			}	
		});
	}
	
	public void displayResults(List<GroupCard> results) {
		resultsDisplaySection.displayResults(results);
	}
	
	public void displayNoResultsMessage() {
		resultsDisplaySection.displayNoResultsMessage();
	}
	
}

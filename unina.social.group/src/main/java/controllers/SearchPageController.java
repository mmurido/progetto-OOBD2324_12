package controllers;

import java.util.ArrayList;
import java.util.List;
import DAO.GruppoDAO;
import gui.searchPage.SearchPage;
import javafx.scene.control.ToggleButton;
import model.Gruppo;

public class SearchPageController {
	
	private SearchPage searchPage;
	private GruppoDAO gruppoDAO;
	
	public SearchPageController(SearchPage searchPage) {
		this.searchPage = searchPage;
        this.gruppoDAO = new GruppoDAO();
	}
	
	public void onEnterKeyPressed() {
		handleSearch();
	}
	
	private void handleSearch() {
		String textEntered = searchPage.topSection.searchField.getText();
	
		if (!textEntered.isEmpty()) {
			ToggleButton selectedFilter = 
					(ToggleButton) searchPage.topSection.filterButtonsToggleGroup.getSelectedToggle();

			if (selectedFilter == null) {
				searchPage.topSection.pulsateFilterButtons();
			} 
			else {
				performSearch(selectedFilter.getText(), textEntered);
			}
		}
	}

	private void performSearch(String searchBy, String textEntered) {
		searchPage.topSection.searchField.clear();
		
		List<Gruppo> searchResults = new ArrayList<>();
	
		if (searchBy.equals("nome")) {
			searchResults = searchGroupsByName(textEntered);
	    }
		else if (searchBy.equals("tema")) {
	        searchResults = searchGroupsByTopic(textEntered);
	    }
	
	    if (searchResults.isEmpty()) {
	    	searchPage.resultsDisplaySection.displayNoResultsMessage();
	    } else {
	    	searchPage.resultsDisplaySection.displaySearchResults(searchResults);
	    }  
	}
	
    public List<Gruppo> searchGroupsByName(String searchTerm) {
        return gruppoDAO.getByName(searchTerm);
    }
    
    public List<Gruppo> searchGroupsByTopic(String searchTerm) {
        return gruppoDAO.getByTopic(searchTerm);
    }

}

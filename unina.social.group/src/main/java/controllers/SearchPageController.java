package controllers;

import java.util.ArrayList;
import java.util.List;

import DAO.GroupDAO;
import gui.commonComponents.GroupCard;
import gui.searchPage.SearchPage;
import model.Group;

public class SearchPageController {
	
	private SearchPage searchPage;
	private GroupDAO groupDAO;
	
	public SearchPageController(SearchPage searchPage) {
		this.searchPage = searchPage;
        this.groupDAO = new GroupDAO();
	}

	public void performSearch(String searchBy, String textEntered) {		
		List<Group> searchResults = new ArrayList<>();
	
		if (searchBy.equals("nome")) {
			searchResults = searchGroupsByName(textEntered);
	    }
		else if (searchBy.equals("tema")) {
	        searchResults = searchGroupsByTopic(textEntered);
	    }
	
	    if (searchResults.isEmpty()) {
	    	searchPage.displayNoResultsMessage();
	    } else {
	    	createGroupCardsToBeDisplayed(searchResults);
	    }  
	}
	
    public List<Group> searchGroupsByName(String searchTerm) {
        return groupDAO.getByName(searchTerm);
    }
    
    public List<Group> searchGroupsByTopic(String searchTerm) {
        return groupDAO.getByTopic(searchTerm);
    }
	
	private void createGroupCardsToBeDisplayed(List<Group> searchResults) {
		List<GroupCard> groupCardsToBeDisplayed = new ArrayList<>();
		
		for (Group group : searchResults) {
			GroupCard groupCard = createGroupCard(group);
			groupCardsToBeDisplayed.add(groupCard);
		}
		
		searchPage.displayResults(groupCardsToBeDisplayed);
	}
	
    private GroupCard createGroupCard(Group group) {
    	String groupId = group.getId();
    	String groupName = group.getName();
    	String groupTopic = group.getTopic();
    	String groupDescription = group.getDescription();
    	String groupOwnerUsername = group.getOwner().getUsername();
    	
    	GroupCard groupCard = new GroupCard(groupId, groupName, groupTopic, groupDescription, groupOwnerUsername);
    	
        return groupCard;
    }

}

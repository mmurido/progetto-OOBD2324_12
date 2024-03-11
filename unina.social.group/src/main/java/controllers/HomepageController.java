package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import DAO.GroupDAO;
import gui.commonComponents.GroupCard;
import gui.homePage.Homepage;
import model.Group;

public class HomepageController extends GroupController {

	Homepage homepage;
	List<Group> allUserGroups;
	List<Group> unsortedGroups;
	List<Group> displayedGroups;

	public HomepageController(Homepage homepage) {
		this.homepage = homepage;
		getAllUserGroups(UserSession.getLoggedUserUsername());
	}
	
    private void getAllUserGroups(String username) {
    	GroupDAO groupDAO = new GroupDAO();
    	allUserGroups = new ArrayList<>();
    	
    	List<String> groupIds = groupDAO.getUserGroupIds(username);
        for (String currentId : groupIds) {
        	allUserGroups.add(groupDAO.getById(currentId));
        }
    }
	
	private List<GroupCard> getGroupCardsToDisplay(List<Group> groups) {
        List<GroupCard> groupCardsToDisplay = new ArrayList<>();  
        for (Group group : groups) {
            GroupCard groupCard = createGroupCard(group);
        	groupCardsToDisplay.add(groupCard);
        }
        return groupCardsToDisplay;
	}
	
	public void onSortAZButtonSelected() {
		unsortedGroups = displayedGroups;
		
    	List<Group> sortedGroups = new ArrayList<>(displayedGroups);
        Collections.sort(sortedGroups, Comparator.comparing(Group::getName));
        
        List<GroupCard> groupCardsToDisplay = getGroupCardsToDisplay(sortedGroups);
    	homepage.displayUserGroups(groupCardsToDisplay);
	}
	
	public void onSortZAButtonSelected() {
		unsortedGroups = displayedGroups;

    	List<Group> sortedGroups = new ArrayList<>(displayedGroups);
        Collections.sort(sortedGroups, Comparator.comparing(Group::getName, Collections.reverseOrder()));
        
        List<GroupCard> groupCardsToDisplay = getGroupCardsToDisplay(sortedGroups);
    	homepage.displayUserGroups(groupCardsToDisplay);
	}
	
	public void onSortButtonUnselected() {
        List<GroupCard> groupCardsToDisplay = getGroupCardsToDisplay(unsortedGroups);
    	homepage.displayUserGroups(groupCardsToDisplay);
	}

	public void handleSearch(String textEntered) {
    	List<Group> matchingGroups = new ArrayList<>(allUserGroups);
    	matchingGroups.removeIf(element -> 
    		!element.getName().toLowerCase().contains(textEntered.toLowerCase())
    	);
    	
        List<GroupCard> groupCardsToDisplay = getGroupCardsToDisplay(matchingGroups);
    	homepage.displayUserGroups(groupCardsToDisplay);
    	
    	displayedGroups = matchingGroups;
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
    
	public void getAllUserGroupsGroupCards() {
        List<GroupCard> groupCardsToDisplay = getGroupCardsToDisplay(allUserGroups);
        homepage.displayUserGroups(groupCardsToDisplay);
        displayedGroups = allUserGroups;
	}
    
	public void getSuggestedGroupsGroupCards() {
		List<Group> suggestedGroups = getSuggestedGroups(UserSession.getLoggedUserUsername());
        List<GroupCard> groupCardsToDisplay = getGroupCardsToDisplay(suggestedGroups);
        homepage.displaySuggestedGroups(groupCardsToDisplay);
	}
	
    private List<Group> getSuggestedGroups(String username) {
    	GroupDAO groupDAO = new GroupDAO();
    	return groupDAO.getSuggestedGroups(username);
    }
    
}

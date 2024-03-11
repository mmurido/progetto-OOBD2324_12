package controllers;

import DAO.GroupDAO;
import DAO.JoinRequestDAO;

public class GroupController {

	private GroupDAO groupDAO;
	private JoinRequestDAO joinRequestDAO;

    public GroupController() {
        this.groupDAO = new GroupDAO();
    	this.joinRequestDAO = new JoinRequestDAO();
    }

    public int getMemberCount(String groupId) { 
    	return groupDAO.getMemberCount(groupId); 
    }
    
    public int getPostCount(String groupId) { 
    	return groupDAO.getPostCount(groupId); 
    }
    
    public boolean isUserMemberOfGroup(String groupId, String username) {
    	return groupDAO.isMember(groupId, username);
    }
 
	public boolean sendJoinRequest(String groupId, String groupOwnerUsername) {
		return joinRequestDAO.insert(groupId, UserSession.getLoggedUserUsername(), groupOwnerUsername);
	}
	
    public boolean checkIfUserHasPendingRequest(String groupId, String username) {
    	return joinRequestDAO.hasPendingAccessRequest(groupId, username);
    }
    
    public void onGroupCardClicked(
    		String groupId, String groupName, String groupTopic, 
			String groupDescription, String groupOwnerUsername) {
    	
    	Navigation.navigateToGroupOverview(
    			groupId, groupName, groupTopic, 
    			groupDescription, groupOwnerUsername);
    }
    
}

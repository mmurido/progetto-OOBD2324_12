package controllers;

import java.util.ArrayList;
import java.util.List;
import DAO.GruppoDAO;
import DAO.MembroGruppoDAO;
import DAO.RichiestaDiAccessoDAO;
import model.Gruppo;
import model.Utente;

public class GroupController {

	private GruppoDAO gruppoDAO;
	private MembroGruppoDAO membroGruppoDAO;
	private RichiestaDiAccessoDAO richiestaDiAccessoDAO;

    public GroupController() {
        this.gruppoDAO = new GruppoDAO();
    	this.membroGruppoDAO = new MembroGruppoDAO();
    	this.richiestaDiAccessoDAO = new RichiestaDiAccessoDAO();
    }

    public int getMemberCount(Gruppo group) {
        return gruppoDAO.geMemberCount(group.getIdGruppo());
    }
    
    public int getPostCount(Gruppo group) {
        return gruppoDAO.getPostCount(group.getIdGruppo());
    }
    
    public boolean isUserMemberOfGroup(Gruppo group, Utente user) {
    	return membroGruppoDAO.isMember(group.getIdGruppo(), user.getIdUtente());
    }
    
    public List<Gruppo> getAllUserGroups(Utente user) {
    	List<String> groupIds = membroGruppoDAO.getUserGroupIds(user.getIdUtente());
    	
    	List<Gruppo> groups = new ArrayList<>();
    	
        for (String currentId : groupIds) {
            groups.add(gruppoDAO.getById(currentId));
        }
        
        return groups;
    }

    public List<Gruppo> getSuggestedGroups(Utente user) {
    	return gruppoDAO.getSuggestedGroups(user.getIdUtente());
    }
    
	public boolean sendJoinRequest(Gruppo group) {
		return richiestaDiAccessoDAO.insert(group.getIdGruppo(), UserSession.getLoggedUser().getIdUtente(), group.getOwner().getIdUtente());
	}
	
    public boolean checkIfUserHasPendingRequest(Gruppo group, Utente user) {
    	return richiestaDiAccessoDAO.hasPendingAccessRequest(group.getIdGruppo(), user.getIdUtente());
    }
}

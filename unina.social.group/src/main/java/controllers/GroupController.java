package controllers;

import java.sql.SQLException;
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

    public GroupController() throws SQLException {
        this.gruppoDAO = new GruppoDAO();
    	this.membroGruppoDAO = new MembroGruppoDAO();
    	this.richiestaDiAccessoDAO = new RichiestaDiAccessoDAO();
    }

    public int getMemberCount(Gruppo group) throws Exception {
        return gruppoDAO.geMemberCount(group.getIdGruppo());
    }
    
    public int getPostCount(Gruppo group) throws Exception {
        return gruppoDAO.getPostCount(group.getIdGruppo());
    }
    
    public boolean isUserMemberOfGroup(Gruppo group, Utente user) throws Exception {
    	return membroGruppoDAO.isMember(group.getIdGruppo(), user.getIdUtente());
    }
    
    public List<Gruppo> getAllUserGroups(Utente user) throws Exception {
    	List<String> groupIds = membroGruppoDAO.getUserGroupIds(user.getIdUtente());
    	
    	List<Gruppo> groups = new ArrayList<>();
    	
        for (String currentId : groupIds) {
            groups.add(gruppoDAO.getById(currentId));
        }
        
        return groups;
    }
    
    public List<Gruppo> getSuggestedGroups(Utente user) throws Exception {
    	return gruppoDAO.getSuggestedGroups(user.getIdUtente());
    }
}

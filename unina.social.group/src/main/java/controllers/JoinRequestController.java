package controllers;

import DAO.RichiestaDiAccessoDAO;
import model.Gruppo;
import model.Utente;

public class JoinRequestController {

	private RichiestaDiAccessoDAO richiestaDiAccessoDAO;

	public JoinRequestController() {
		this.richiestaDiAccessoDAO = new RichiestaDiAccessoDAO();
	}
	
	public boolean sendJoinRequest(Gruppo group) throws Exception {
		return richiestaDiAccessoDAO.insert(group.getIdGruppo(), UserSession.getLoggedUser().getIdUtente(), group.getOwner().getIdUtente());
	}
	
    public boolean checkIfUserHasPendingRequest(Gruppo group, Utente user) throws Exception{
    	return richiestaDiAccessoDAO.hasPendingAccessRequest(group.getIdGruppo(), user.getIdUtente());
    }
}

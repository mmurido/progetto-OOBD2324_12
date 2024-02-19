package controllers;

import java.sql.SQLException;
import java.util.List;

import DAO.GruppoDAO;
import model.Gruppo;

public class GroupSearchController {

	private GruppoDAO gruppoDAO;

    public GroupSearchController() throws SQLException {
        this.gruppoDAO = new GruppoDAO();
    }

    public List<Gruppo> searchGroupsByName(String searchTerm) throws Exception {
        return gruppoDAO.getByName(searchTerm);
    }
    
    public List<Gruppo> searchGroupsByTopic(String searchTerm) throws Exception {
        return gruppoDAO.getByTopic(searchTerm);
    }
}

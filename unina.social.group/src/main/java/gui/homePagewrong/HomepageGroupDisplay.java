package gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import controllers.GroupController;
import controllers.UserSession;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Gruppo;

public class HomepageGroupDisplay {
	
	private UserSession userSession;
	private GroupController groupController;
	private List<Gruppo> allUserGroups;
	private List<Gruppo> currentlyDisplayedGroups;
	private TilePane groupsTilePane;
	private IconUtils iconUtils;
	
	public HomepageGroupDisplay(UserSession userSession) throws Exception {
		this.userSession = userSession;
		this.groupController = new GroupController();
		this.allUserGroups = groupController.getAllUserGroups(userSession.getLoggedUser());
		this.iconUtils = new IconUtils();
	}
	
	public void setGroupsTilePane (TilePane groupsTilePane) {
		this.groupsTilePane = groupsTilePane;
	}
	
	public void displayAll() {
		try {
			this.displayGroups(allUserGroups);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void displayCurrentlyDisplayed() {
		try {
			this.displayGroups(currentlyDisplayedGroups);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void displaySortedAZ() {
    	List<Gruppo> sorted = new ArrayList<>(currentlyDisplayedGroups);
        Collections.sort(sorted, Comparator.comparing(Gruppo::getNome));
        
		groupsTilePane.getChildren().clear();
        for (Gruppo group : sorted) {
            VBox groupVBox = createGroupVBox(group);
            groupsTilePane.getChildren().add(groupVBox);
        }
	}
	   
    public void displaySortedZA() {
    	List<Gruppo> sorted = new ArrayList<>(currentlyDisplayedGroups);
        Collections.sort(sorted, Comparator.comparing(Gruppo::getNome, Collections.reverseOrder()));
        
		groupsTilePane.getChildren().clear();
        for (Gruppo group : sorted) {
            VBox groupVBox = createGroupVBox(group);
            groupsTilePane.getChildren().add(groupVBox);
        }    
    }
    
    public void displaySearchResults(String text) {
    	List<Gruppo> groups = new ArrayList<>(allUserGroups);
    	
    	groups.removeIf(element -> !element.getNome().toLowerCase().contains(text.toLowerCase()));
    	
        this.displayGroups(groups);
    }
     
	private void displayGroups(List<Gruppo> groupList) {
		groupsTilePane.getChildren().clear();
        for (Gruppo group : groupList) {
            VBox groupVBox = createGroupVBox(group);
            groupsTilePane.getChildren().add(groupVBox);
        }
        currentlyDisplayedGroups = groupList;
	}
	
    private VBox createGroupVBox(Gruppo group) {
        VBox groupVBox = new VBox(5);
        groupVBox.setPrefWidth(250);
        groupVBox.setStyle(
        		"-fx-background-color: #fefefe;" + 
        		"-fx-background-radius: 5;" + 
        		"-fx-padding: 20;" + 
        		"-fx-effect: dropshadow(gaussian, gray, 5, 0, 0, 0);"
        );
        
        HBox name = new HBox(5);
        System.out.println("per gruppo " + group.getNome() + " l'owner è " + group.getOwner().getIdUtente());
        System.out.println("e l'id del logged user è: " + userSession.getLoggedUser().getIdUtente());
        if ( group.getOwner().getIdUtente().equals(userSession.getLoggedUser().getIdUtente()) ) {
        	ImageView crown = new ImageView(iconUtils.crown);
        	crown.setFitWidth(18);
        	crown.setFitHeight(18);
        	name.getChildren().add(crown);
        }
        
        Text groupName = new Text(group.getNome());
        groupName.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 16; -fx-fill: #0e5460; -fx-font-weight: bold;");
		VBox.setMargin(groupName, new Insets(0, 0, -7, 0));
		name.getChildren().add(groupName);

        Text groupTopic = new Text("Tema: " + group.getTema().getTema());
        groupTopic.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 14; -fx-fill: #e6ad38; -fx-font-weight: bold;");
        
        Text groupDescription = new Text(group.getDescrizione());
        groupDescription.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 13; -fx-fill: black;");
        groupDescription.setWrappingWidth(250);

        groupVBox.getChildren().addAll(name, groupTopic, groupDescription);
        return groupVBox;
    }
}

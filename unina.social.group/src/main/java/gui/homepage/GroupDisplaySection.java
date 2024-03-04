package gui.homepage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import controllers.GroupController;
import controllers.UserSession;
import gui.searchPage.GroupCard;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import model.Gruppo;

public class GroupDisplaySection  extends ScrollPane {
	
	private GroupController controller;
	private List<Gruppo> allUserGroups;
	private List<Gruppo> currentlyDisplayedGroups;
	private TilePane groupsTilePane;
		
	public GroupDisplaySection() {
		this.controller = new GroupController();
		allUserGroups = controller.getAllUserGroups(UserSession.getLoggedUser());
		initializeComponents();
	}
	
	private void initializeComponents() {
		setupGroupsTilePane();
		setupScrollPane();
		displayAll();
	}
	
	private void setupScrollPane() {
		this.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
	    this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		String cssPath = Homepage.class.getResource("/css/scrollPaneStyle.css").toExternalForm();
	    this.getStylesheets().add(cssPath);

		AnchorPane.setLeftAnchor(this, 0.0);
		AnchorPane.setRightAnchor(this, 0.0);
		AnchorPane.setBottomAnchor(this, 0.0);
		
        this.setContent(groupsTilePane);
	}
	
	private void setupGroupsTilePane() {
      groupsTilePane = new TilePane();
      groupsTilePane.setOrientation(Orientation.HORIZONTAL);
      groupsTilePane.setPadding(new Insets(20, 30, 40, 130));
      groupsTilePane.setHgap(20);
      groupsTilePane.setVgap(20);
      groupsTilePane.setPrefColumns(3);
      groupsTilePane.prefWidthProperty().bind(this.widthProperty());
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
            GroupCard groupCard = createGroupCard(group);
            groupsTilePane.getChildren().add(groupCard);
        }
	}
	   
    public void displaySortedZA() {
    	List<Gruppo> sorted = new ArrayList<>(currentlyDisplayedGroups);
        Collections.sort(sorted, Comparator.comparing(Gruppo::getNome, Collections.reverseOrder()));
        
		groupsTilePane.getChildren().clear();
        for (Gruppo group : sorted) {
            GroupCard groupCard = createGroupCard(group);
            groupsTilePane.getChildren().add(groupCard);
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
            GroupCard groupCard = createGroupCard(group);
            groupsTilePane.getChildren().add(groupCard);
        }
        currentlyDisplayedGroups = groupList;
	}
	
    private GroupCard createGroupCard(Gruppo group) {
    	GroupCard groupCard = new GroupCard(group);
    	groupCard.getChildren().removeAll(
    			groupCard.memberCountDisplay, 
    			groupCard.postCountDisplay, 
    			groupCard.joinGroupButton
    	);
        return groupCard;
    }
	
//    private VBox createGroupVBox(Gruppo group) {
//        VBox groupVBox = new VBox(5);
//        groupVBox.setPrefWidth(250);
//        groupVBox.setStyle(
//        		"-fx-background-color: #fefefe;" + 
//        		"-fx-background-radius: 5;" + 
//        		"-fx-padding: 20;" + 
//        		"-fx-effect: dropshadow(gaussian, gray, 5, 0, 0, 0);"
//        );
//        
//        HBox name = new HBox(5);
//        if ( group.getOwner().getIdUtente().equals(UserSession.getLoggedUser().getIdUtente()) ) {
//        	ImageView crown = new ImageView(IconUtils.crown);
//        	crown.setFitWidth(18);
//        	crown.setFitHeight(18);
//        	name.getChildren().add(crown);
//        }
//        
//        Text groupName = new Text(group.getNome());
//        groupName.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 16; -fx-fill: #0e5460; -fx-font-weight: bold;");
//		VBox.setMargin(groupName, new Insets(0, 0, -7, 0));
//		name.getChildren().add(groupName);
//
//        Text groupTopic = new Text("Tema: " + group.getTema().getTema());
//        groupTopic.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 14; -fx-fill: #e6ad38; -fx-font-weight: bold;");
//        
//        Text groupDescription = new Text(group.getDescrizione());
//        groupDescription.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 13; -fx-fill: black;");
//        groupDescription.setWrappingWidth(250);
//
//        groupVBox.getChildren().addAll(name, groupTopic, groupDescription);
//        return groupVBox;
//    }
}

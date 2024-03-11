package gui.homePage;

import java.util.List;

import controllers.HomepageController;
import gui.commonComponents.GroupCard;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;

public class GroupDisplaySection  extends ScrollPane {
	
	TilePane groupsTilePane;
		
	public GroupDisplaySection(HomepageController controller) {
		initializeComponents();
	}
	
	private void initializeComponents() {
		setupGroupsTilePane();
		setupScrollPane();
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
	
	private void setupScrollPane() {
		this.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
	    this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		String cssPath = GroupDisplaySection.class.getResource("/css/scrollPaneStyle.css").toExternalForm();
	    this.getStylesheets().add(cssPath);

		AnchorPane.setLeftAnchor(this, 0.0);
		AnchorPane.setRightAnchor(this, 0.0);
		AnchorPane.setBottomAnchor(this, 0.0);
		
        this.setContent(groupsTilePane);
	}

	
//	public void displaySortedAZ() {
//    	List<Gruppo> sorted = new ArrayList<>(currentlyDisplayedGroups);
//        Collections.sort(sorted, Comparator.comparing(Gruppo::getNome));
//        
//		groupsTilePane.getChildren().clear();
//        for (Gruppo group : sorted) {
//            GroupCard groupCard = createGroupCard(group);
//            groupsTilePane.getChildren().add(groupCard);
//        }
//	}
//	   
//    public void displaySortedZA() {
//    	List<Gruppo> sorted = new ArrayList<>(currentlyDisplayedGroups);
//        Collections.sort(sorted, Comparator.comparing(Gruppo::getNome, Collections.reverseOrder()));
//        
//		groupsTilePane.getChildren().clear();
//        for (Gruppo group : sorted) {
//            GroupCard groupCard = createGroupCard(group);
//            groupsTilePane.getChildren().add(groupCard);
//        }    
//    }
//    
//    public void displaySearchResults(String text) {
//    	List<Gruppo> groups = new ArrayList<>(allUserGroups);
//    	
//    	groups.removeIf(element -> !element.getNome().toLowerCase().contains(text.toLowerCase()));
//    	
//        this.displayGroups(groups);
//    }
     
	public void displayUserGroups(List<GroupCard> groupCardsToDisplay) {
		groupsTilePane.getChildren().clear();
        for (GroupCard groupCard : groupCardsToDisplay) {
            groupsTilePane.getChildren().add(groupCard);
        }
	}
	
//    private GroupCard createGroupCard(Gruppo group) {
//    	GroupCard groupCard = new GroupCard(group);
//    	groupCard.getChildren().removeAll(
//    			groupCard.memberCountDisplay, 
//    			groupCard.postCountDisplay, 
//    			groupCard.joinGroupButton
//    	);
//        return groupCard;
//    }

}
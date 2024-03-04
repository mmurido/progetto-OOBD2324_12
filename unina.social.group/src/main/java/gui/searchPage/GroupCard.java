package gui.searchPage;

import controllers.GroupController;
import controllers.UserSession;
import gui.IconUtils;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Gruppo;

public class GroupCard extends VBox {
	
	private GroupController controller;
	public Gruppo group;
	public HBox nameBox;
	public Text groupName;
	public Text groupTopic;
	public Text groupDescription;
	public int memberCount;
	public HBox memberCountDisplay;
	public int postCount;
	public HBox postCountDisplay;
	public Button joinGroupButton;

	private static final String CARD_STYLE = 
			"-fx-background-color: #fefefe; -fx-background-radius: 5;" + 
			"-fx-padding: 20; -fx-effect: dropshadow(gaussian, gray, 5, 0, 0, 0);";
	
	public GroupCard(Gruppo group) {
		controller = new GroupController();
		this.group = group;
		initializeComponents();
	}
	
	private void initializeComponents() {
		this.setSpacing(5);
		this.setStyle(CARD_STYLE);		
		this.setPrefWidth(250);
		displayGroupName();
		displayGroupTopic();
		displayGroupDescription();
		displayMemberCount();
		displayPostCount();
		displayJoinGroupButton();
	}
	
	private void displayGroupName() {
        nameBox = new HBox(5);
        String groupOwnerId = group.getOwner().getIdUtente();
        String loggedUserId = UserSession.getLoggedUser().getIdUtente();
        if (groupOwnerId.equals(loggedUserId)) {
        	ImageView crown = new ImageView(IconUtils.crown);
        	crown.setFitWidth(18);
        	crown.setFitHeight(18);
        	nameBox.getChildren().add(crown);
        }
		
		groupName = new Text(group.getNome());
		groupName.setStyle(
				"-fx-font-family: 'Product Sans'; -fx-font-size: 16;" + 
				"-fx-fill: #0e5460; -fx-font-weight: bold;"
		);
		VBox.setMargin(groupName, new Insets(0, 0, -7, 0));
		
		nameBox.getChildren().add(groupName);
		this.getChildren().add(nameBox);
	}
	
	private void displayGroupTopic() {
		groupTopic = new Text("Tema: " + group.getTema().getTema());
		groupTopic.setStyle(
				"-fx-font-family: 'Product Sans'; -fx-font-size: 14;" + 
				"-fx-fill: #e6ad38; -fx-font-weight: bold;"
		);
		
		this.getChildren().add(groupTopic);
	}
	
	private void displayGroupDescription() {
		groupDescription = new Text(group.getDescrizione());
		groupDescription.setStyle(
				"-fx-font-family: 'Product Sans'; -fx-font-size: 13; -fx-fill: black;");
		groupDescription.setWrappingWidth(250);
		
		this.getChildren().add(groupDescription);
	}
	
	private void displayMemberCount() {
		memberCountDisplay = new HBox(4);
		
		ImageView icon = new ImageView(IconUtils.membersIcon);
	    icon.setFitWidth(15);
		icon.setFitHeight(15);
		
		memberCount = controller.getMemberCount(group);
	
		Text countText = new Text();
		countText.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 13; -fx-fill: #999999;");
		 
		if (memberCount == 1) {	countText.setText(memberCount + " membro"); } 
		else { countText.setText(memberCount + " membri"); }
		  
		memberCountDisplay.getChildren().addAll(icon, countText);
		
		this.getChildren().add(memberCountDisplay);
	}
	
	private void displayPostCount() {
		postCountDisplay = new HBox(4);
		
		ImageView icon = new ImageView(IconUtils.membersIcon);
	    icon.setFitWidth(15);
		icon.setFitHeight(15);
		
		postCount = controller.getPostCount(group);
	
		Text countText = new Text();
		countText.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 13; -fx-fill: #999999;");
		countText.setText(postCount + " post");
		  
		postCountDisplay.getChildren().addAll(icon, countText);
		
		this.getChildren().add(postCountDisplay);
	}

	private void displayJoinGroupButton() {
		joinGroupButton = new Button();
		VBox.setMargin(joinGroupButton, new Insets(10, 0, 0, 0));
		
		boolean isUserMember = controller.isUserMemberOfGroup(group, UserSession.getLoggedUser());

		if (isUserMember) {
			joinGroupButton.setText("Sei già un membro");
			joinGroupButton.setStyle(
					"-fx-background-color: #eeeeee; -fx-text-fill: #bcbcbc;" + 
					"-fx-font-family: 'Product Sans'; -fx-font-size: 11; -fx-font-style: italic;"
			);
		} 
		else {
			boolean userHasPendingRequest = controller.checkIfUserHasPendingRequest(group,UserSession.getLoggedUser());

			if (userHasPendingRequest) {
				joinGroupButton.setText("✓ Richiesta inviata");
				joinGroupButton.setStyle(
						"-fx-background-color: #eeeeee; -fx-text-fill: #bcbcbc;" + 
						"-fx-font-family: 'Product Sans'; -fx-font-size: 11; -fx-font-style: italic;"
				);
			}

			if (!userHasPendingRequest) {
				joinGroupButton.setText("Richiedi accesso");
				joinGroupButton.setStyle(
						"-fx-background-color: #63b448; -fx-text-fill: white; -fx-font-family: 'Product Sans';" + 
						"-fx-font-size: 11; -fx-font-style: italic; -fx-font-weight: bold"
				);

				joinGroupButton.setCursor(Cursor.HAND);

				joinGroupButton.setOnAction(event -> {
					handleJoinGroupButtonClick(group, joinGroupButton);
				});
			}
		}

		this.getChildren().add(joinGroupButton);
	}
	
	private void handleJoinGroupButtonClick(Gruppo group, Button joinGroupButton) {
		boolean insertedSuccessfully = controller.sendJoinRequest(group);

		if (insertedSuccessfully) {
			joinGroupButton.setText("✓ Richiesta inviata");
			joinGroupButton.setStyle(
					"-fx-background-color: #eeeeee; -fx-text-fill: #bcbcbc;" + 
					"-fx-font-family: 'Product Sans'; -fx-font-size: 11; -fx-font-style: italic;"
			);
		}
	}
}

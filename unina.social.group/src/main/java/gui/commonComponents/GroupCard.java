package gui.commonComponents;

import controllers.GroupController;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GroupCard extends VBox {
	
	private GroupController controller;
	
	String groupId;
	String groupName;
	String groupTopic;
	String groupDescription;
	String groupOwnerUsername;
	
	HBox groupNameBox;
	Text groupTopicText;
	Label groupDescriptionLabel;
	HBox memberCountBox;
	HBox postCountBox;
	Button joinGroupButton;

	private static final String CARD_STYLE = 
			"-fx-background-color: #fefefe; -fx-background-radius: 5;" + 
			"-fx-padding: 20; -fx-effect: dropshadow(gaussian, gray, 5, 0, 0, 0);";
	
	public GroupCard(
			String groupId, String groupName, String groupTopic, 
			String groupDescription, String groupOwnerUsername) {
		
		this.groupId = groupId;
		this.groupName = groupName;
		this.groupTopic = groupTopic;
		this.groupDescription = groupDescription;
		this.groupOwnerUsername = groupOwnerUsername;
		
		this.controller = new GroupController();
		
		initializeComponents();
		layoutComponents();
		handleClicks();
	}
	
	private void initializeComponents() {
		this.setSpacing(5);
		this.setStyle(CARD_STYLE);		
		this.setPrefWidth(250);
		this.setCursor(Cursor.HAND);
		
		groupNameBox = GroupCommonElements.setupGroupName(groupName, groupOwnerUsername);
		groupTopicText = GroupCommonElements.setupGroupTopic(groupTopic);
		setupGroupDescription();
		memberCountBox = GroupCommonElements.setupMemberCount(groupId);
		postCountBox = GroupCommonElements.setupPostCount(groupId);
		joinGroupButton = GroupCommonElements.setupJoinGroupButton(groupId, groupOwnerUsername);
	}
	
	private void layoutComponents() {
		this.getChildren().addAll(
				groupNameBox, groupTopicText, groupDescriptionLabel,
				memberCountBox, postCountBox, joinGroupButton
		);
	}
	
	private void setupGroupDescription() {
		groupDescriptionLabel = new Label(groupDescription);
		groupDescriptionLabel.setWrapText(true);
		groupDescriptionLabel.setStyle(
				"-fx-font-family: 'Product Sans'; -fx-font-size: 13; -fx-text-fill: black;");
	}
	
	private void handleClicks() {
		this.setOnMouseClicked(e -> {
			controller.onGroupCardClicked(groupId, groupName, groupTopic, groupDescription, groupOwnerUsername);
		});
	}
	
	public void removeJoinGroupButton() {
		getChildren().remove(joinGroupButton);
	}
}

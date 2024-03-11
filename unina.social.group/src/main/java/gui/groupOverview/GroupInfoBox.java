package gui.groupOverview;

import gui.commonComponents.GroupCommonElements;
import gui.commonComponents.IconUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GroupInfoBox extends VBox {
	
	String groupId;
	String groupName;
	String groupTopic;
	String groupDescription;
	String groupOwnerUsername;
	HBox groupHeaderBox;
	VBox descriptionBox;
	VBox ownerInfoBox;
	VBox countsBox;
	Button newPostButton;
	
	private static final String NEW_POST_BUTTON_DEFAULT_STYLE =
			"-fx-background-color: #00958c; -fx-background-radius: 10;" + 
			"-fx-font-family: 'Product Sans'; -fx-text-fill: white;" + 
			"-fx-font-size: 14; -fx-font-weight: bold;";
	
	private static final String NEW_POST_BUTTON_HIGHLIGHTED_STYLE =
			"-fx-background-color: #007972; -fx-background-radius: 10;" + 
			"-fx-font-family: 'Product Sans'; -fx-text-fill: white;" + 
			"-fx-font-size: 14; -fx-font-weight: bold;";
	
	public GroupInfoBox(
			String groupId, String groupName, String groupTopic, 
			String groupDescription, String groupOwnerUsername) {
		
		this.groupId = groupId;
		this.groupName = groupName;
		this.groupTopic = groupTopic;
		this.groupDescription = groupDescription;
		this.groupOwnerUsername = groupOwnerUsername;
		
		initializeComponents();
	}
	
	private void initializeComponents() {
		setupGroupHeaderBox();
		setupDescription();
		setupGroupOwnerInfo();
		setupCountsBox();
		setupNewPostButton();
		
		this.setSpacing(15);
		this.getChildren().addAll(groupHeaderBox, descriptionBox, ownerInfoBox, countsBox, newPostButton);
		this.setPadding(new Insets(10, 20, 0, 20));
		this.setStyle(
				"-fx-background-radius: 10; -fx-background-color: white;" + 
				"-fx-border-color: #ded2d2; -fx-border-radius: 10;"
		);
	}
	
	private void setupGroupHeaderBox() {
		HBox groupNameBox = GroupCommonElements.setupGroupName(groupName, groupOwnerUsername);
		Text groupTopicText = GroupCommonElements.setupGroupTopic(groupTopic);		
		VBox vbox = new VBox(groupNameBox, groupTopicText);
		
		Button joinGroupButton = GroupCommonElements.setupJoinGroupButton(groupId, groupOwnerUsername);
		
		groupHeaderBox = new HBox(10, vbox, joinGroupButton);
		groupHeaderBox.setAlignment(Pos.CENTER);
		groupHeaderBox.setPadding(new Insets(0, 0, 10, 0));
		groupHeaderBox.setStyle("-fx-border-color: #cecece; -fx-border-width: 0 0 1 0;");
	}
	
	private void setupDescription() {
		Label label = new Label("About");
		label.setStyle(
				"-fx-text-fill: #5b5b5b; -fx-font-family: 'Product Sans';" + 
				"-fx-font-size: 13; -fx-font-weight: bold;"
		);
		
		Text description = new Text(groupDescription);
		description.setWrappingWidth(200);
		description.setStyle("-fx-fill: #858585; -fx-font-family: 'Product Sans'; -fx-font-size: 12;");
		
		descriptionBox = new VBox(label, description);
	}
	
	private void setupGroupOwnerInfo() {
		Label label = new Label("Owner");
		label.setStyle(
				"-fx-text-fill: #5b5b5b; -fx-font-family: 'Product Sans';" + 
				"-fx-font-size: 13; -fx-font-weight: bold;"
		);
		
		Text owner = new Text(groupOwnerUsername);
		owner.setStyle("-fx-fill: #858585; -fx-font-family: 'Product Sans'; -fx-font-size: 12;");
		
		ownerInfoBox = new VBox(label, owner);
	}
	
	private void setupCountsBox() {
		HBox memberCountBox = GroupCommonElements.setupMemberCount(groupId);
		HBox postCountBox = GroupCommonElements.setupPostCount(groupId);		
		countsBox = new VBox(5, memberCountBox, postCountBox);
	}
	
	private void setupNewPostButton() {
		ImageView plusIcon = new ImageView(IconUtils.plusIcon);
		plusIcon.setFitWidth(20);
		plusIcon.setFitHeight(20);	
		
		newPostButton = new Button("Nuovo post", plusIcon);
		newPostButton.setCursor(Cursor.HAND);
		newPostButton.setPadding(new Insets(5, 8, 5, 5));
		newPostButton.setStyle(NEW_POST_BUTTON_DEFAULT_STYLE);
		
		newPostButton.setOnMouseEntered(e -> {
			newPostButton.setStyle(NEW_POST_BUTTON_HIGHLIGHTED_STYLE);
		});
		
		newPostButton.setOnMouseExited(e -> {
			newPostButton.setStyle(NEW_POST_BUTTON_DEFAULT_STYLE);
		});
	}
}

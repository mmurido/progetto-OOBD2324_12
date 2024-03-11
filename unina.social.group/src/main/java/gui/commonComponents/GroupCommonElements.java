package gui.commonComponents;

import controllers.GroupController;
import controllers.UserSession;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GroupCommonElements {
	
	private static GroupController controller = new GroupController();
	
	public static HBox setupGroupName(String groupName, String groupOwnerUsername) {
        HBox hbox = new HBox(5);
        
        String loggedUserUsername = UserSession.getLoggedUserUsername();
        
        if (groupOwnerUsername.equals(loggedUserUsername)) {
        	ImageView crown = new ImageView(IconUtils.crown);
        	crown.setFitWidth(18);
        	crown.setFitHeight(18);
        	hbox.getChildren().add(crown);
        }
		
		Text text = new Text(groupName);
		VBox.setMargin(text, new Insets(0, 0, -7, 0));
		text.setStyle(
				"-fx-font-family: 'Product Sans'; -fx-font-size: 16;" + 
				"-fx-fill: #0e5460; -fx-font-weight: bold;"
		);
		
		hbox.getChildren().add(text);
		
		return hbox;
	}
	
	public static Text setupGroupTopic(String groupTopic) {
		Text text = new Text("Tema: " + groupTopic);
		text.setStyle(
				"-fx-font-family: 'Product Sans'; -fx-font-size: 14;" + 
				"-fx-fill: #e6ad38; -fx-font-weight: bold;"
		);
		
		return text;
	}
	
	public static HBox setupMemberCount(String groupId) {		
		ImageView icon = new ImageView(IconUtils.membersIcon);
		icon.setFitWidth(15);
		icon.setFitHeight(15);
	
		Text text = new Text();
		text.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 13; -fx-fill: #999999;");
		
		int memberCount = controller.getMemberCount(groupId);

		if (memberCount == 1) { 
			text.setText(memberCount + " membro"); 
		} else { 
			text.setText(memberCount + " membri"); 
		}
		
		HBox hbox = new HBox(4, icon, text);

		return hbox;
	}
	
	public static HBox setupPostCount(String groupId) {		
		ImageView icon = new ImageView(IconUtils.postIcon);
		icon.setFitWidth(15);
		icon.setFitHeight(15);
		
		int postCount = controller.getPostCount(groupId);
		
		Text text = new Text();
		text.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 13; -fx-fill: #999999;");
		text.setText(postCount + " post");
		
		HBox hbox = new HBox(4, icon, text);

		return hbox;
	}

	public static Button setupJoinGroupButton(String groupId, String groupOwnerUsername) {
		Button joinGroupButton = new Button();
		VBox.setMargin(joinGroupButton, new Insets(10, 0, 0, 0));
		
		boolean isMember = controller.isUserMemberOfGroup(groupId, UserSession.getLoggedUserUsername());

		if (isMember) {
			joinGroupButton.setText("Sei già un membro");
			joinGroupButton.setStyle(
					"-fx-background-radius: 5; -fx-background-color: #eeeeee; -fx-text-fill: #bcbcbc;" + 
					"-fx-font-family: 'Product Sans'; -fx-font-size: 11; -fx-font-style: italic;"
			);
			return joinGroupButton;
		}
			
		boolean userHasPendingRequest = 
				controller.checkIfUserHasPendingRequest(groupId, UserSession.getLoggedUserUsername());
		
		if (userHasPendingRequest) {
			joinGroupButton.setText("✓ Richiesta inviata");
			joinGroupButton.setStyle(
					"-fx-background-radius: 5; -fx-background-color: #eeeeee; -fx-text-fill: #bcbcbc;" + 
					"-fx-font-family: 'Product Sans'; -fx-font-size: 11; -fx-font-style: italic;"
			);
			return joinGroupButton;
		}

		joinGroupButton.setText("Richiedi accesso");
		joinGroupButton.setCursor(Cursor.HAND);
		joinGroupButton.setStyle(
				"-fx-background-radius: 5; -fx-background-color: #63b448; -fx-text-fill: white;" + 
				"-fx-font-family: 'Product Sans';-fx-font-size: 11; -fx-font-style: italic;" + 
				"-fx-font-weight: bold"
		);

		joinGroupButton.setOnAction(event -> {
			handleJoinGroupButtonClick(joinGroupButton, groupId, groupOwnerUsername);
		});
		
		return joinGroupButton;
	}
	
	private static void handleJoinGroupButtonClick(Button joinGroupButton, String groupId, String groupOwnerUsername) {
		boolean insertedSuccessfully = controller.sendJoinRequest(groupId, groupOwnerUsername);

		if (insertedSuccessfully) {
			joinGroupButton.setText("✓ Richiesta inviata");
			joinGroupButton.setStyle(
					"-fx-background-radius: 5; -fx-background-color: #eeeeee; -fx-text-fill: #bcbcbc;" + 
					"-fx-font-family: 'Product Sans'; -fx-font-size: 11; -fx-font-style: italic;"
			);
		}
	}
}

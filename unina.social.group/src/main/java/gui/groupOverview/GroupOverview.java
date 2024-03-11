package gui.groupOverview;

import java.util.List;

import controllers.GroupOverviewController;
import controllers.Navigation;
import gui.commonComponents.IconUtils;
import gui.commonComponents.PostCard;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class GroupOverview extends BorderPane {
	
	String groupId;
	String groupName;
	String groupTopic;
	String groupDescription;
	String groupOwnerUsername;
	
	private GroupOverviewController controller;
	GroupInfoBox groupInfoBox;
	ScrollPane scrollPane;
	FeedBox feedBox;
	Button goBackButton;
	PostEditorPopup postEditorPopup;

	public GroupOverview(
			String groupId, String groupName, String groupTopic, 
			String groupDescription, String groupOwnerUsername) {
		
		this.controller = new GroupOverviewController(this);
		
		this.groupId = groupId;
		this.groupName = groupName;
		this.groupTopic = groupTopic;
		this.groupDescription = groupDescription;
		this.groupOwnerUsername = groupOwnerUsername;
		
		initializeComponents();	
		layoutComponents();
		handlePageSizeChanges();	
		handleNewPostButtonClick();
		handlePostButtonClick();
	}
	
	private void initializeComponents() {
		this.setStyle("-fx-background-color: #f6f6f6;");
		
		setupGoBackButton();		
		groupInfoBox = new GroupInfoBox(
				groupId, groupName, groupTopic, 
				groupDescription, groupOwnerUsername);	
		feedBox = new FeedBox();
		controller.populateFeedBoxFor(groupId);
		setupScrollPane();		
		DoubleBinding adjustedWidth = Bindings.subtract(scrollPane.widthProperty(), 30.0);
		feedBox.prefWidthProperty().bind(adjustedWidth);
		postEditorPopup = new PostEditorPopup(this);
	}
	
	private void layoutComponents() {
		this.setTop(goBackButton);
		this.setCenter(scrollPane);
		this.setRight(groupInfoBox);
		scrollPane.setContent(feedBox);
	}
	
	private void setupScrollPane() {
		scrollPane = new ScrollPane();
		scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
		
		String cssPath = GroupOverview.class.getResource("/css/scrollPaneStyle.css").toExternalForm();
		scrollPane.getStylesheets().add(cssPath);	
		
		scrollPane.setContent(feedBox);
	}

	public void setupGoBackButton() {
		ImageView leftArrow = new ImageView(IconUtils.leftArrowIcon);
		leftArrow.setFitWidth(33);
		leftArrow.setFitHeight(28);
		
		goBackButton = new Button("", leftArrow);
		goBackButton.setCursor(Cursor.HAND);
		goBackButton.setStyle("-fx-background-color: transparent;");
				
		goBackButton.setOnMouseClicked(e -> {
			Navigation.navigateToPreviousPage();
		});
	}
	
	private void handlePageSizeChanges() {
		this.widthProperty().addListener((obs, oldWidth, newWidth) -> {
			if (newWidth.intValue() == 886) {
				this.setPadding(new Insets(50, 80, 30, 80));
				goBackButton.setTranslateX(-78);
				goBackButton.setTranslateY(-48);
			}

			if (newWidth.intValue() == 1216) {
				this.setPadding(new Insets(50, 200, 30, 200));
				goBackButton.setTranslateX(-198);
				goBackButton.setTranslateY(-48);
			}
		});
	}
	
	private void handleNewPostButtonClick() {
		groupInfoBox.newPostButton.setOnMouseClicked(e -> {
			postEditorPopup.show();
		});
	}
	
	public void handleNoPostsScenario() {
		feedBox.displayNoPostsMessage();
	}
	
	public void displayPostCards(List<PostCard> postCards) {
		feedBox.displayPostCards(postCards);
	}
	
	public void handleNonMemberScenario() {
		feedBox.lockFeed();
		groupInfoBox.newPostButton.setVisible(false);
	}
	
	private void handlePostButtonClick() {
		postEditorPopup.postButton.setOnMouseClicked(e -> {
			String textEntered = postEditorPopup.textArea.getText();
			if (textEntered != null) {
				postEditorPopup.textArea.clear();
				feedBox.getChildren().clear();
				controller.onPostButtonClicked(groupId, textEntered);
				postEditorPopup.windowControls.closeButton.fire();
			}
		});
	}
	
}

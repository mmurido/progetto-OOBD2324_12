package gui.commonComponents;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class PostCard extends BorderPane {
	
	String postId;
    String postText;
    LocalDateTime createdAt;
    String groupId;
    String postAuthorUsername;
    String postAuthorName;
    String postAuthorSurname;

	HBox postHeader;
	ImageView profilePicture;
	VBox authorInfoBox;
	VBox postContentBox;
	HBox interactionBar;
	ImageView likeIcon;
	ImageView commentIcon;
	Text postBody;

	
	private static final String POST_CARD_STYLE =
			"-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, white , #f9f9ff);" +
		    "-fx-background-radius: 5;" + "-fx-effect: dropshadow(gaussian, gray, 5, 0, 0, 0);";
	
	public PostCard(
			String postId, String postText, LocalDateTime createdAt, String groupId, 
			String postAuthorUsername, String postAuthorName, String postAuthorSurname) {
		
		this.postId = postId;
		this.postText = postText;
		this.createdAt = createdAt;
		this.groupId = groupId;
		this.postAuthorUsername = postAuthorUsername;
		this.postAuthorName = postAuthorName;
		this.postAuthorSurname = postAuthorSurname;
		
		initializeComponents();
		layoutComponents();
	}
	
	private void initializeComponents() {
		this.setPadding(new Insets(5, 15, 5, 15));
		this.setMinWidth(300);
		this.setStyle(POST_CARD_STYLE);
	
		setupPostHeader();
		setupPostContent();
		setupInteractionBar();
	}
	
	private void layoutComponents() {
		this.setTop(postHeader);
		this.setCenter(postContentBox);
		this.setBottom(interactionBar);
		
	}

	private void setupPostHeader() {
		postHeader = new HBox(10);
		postHeader.setAlignment(Pos.CENTER_LEFT);
		postHeader.setPrefHeight(30);
		postHeader.setPadding(new Insets(5, 0, 5, 0));
		postHeader.setStyle("-fx-border-color: #cecece; -fx-border-width: 0 0 1 0;");
		setupProfilePicture();
		setupAuthorInfoBox();
		
		postHeader.getChildren().addAll(profilePicture, authorInfoBox);
	}
	
	private void setupProfilePicture() {
		profilePicture = new ImageView(IconUtils.profilePicture);
		profilePicture.setFitHeight(38);
		profilePicture.setFitWidth(38);

		Rectangle profilePictureShape = new Rectangle(
				profilePicture.getFitWidth(), profilePicture.getFitHeight());		
		profilePictureShape.setArcHeight(10);
		profilePictureShape.setArcWidth(10);
		profilePicture.setClip(profilePictureShape);
	}
	
	private void setupAuthorInfoBox() {
		Text usernameText = new Text(postAuthorUsername);
		usernameText.setStyle(
				"-fx-font-family: 'Product Sans'; -fx-font-size: 15;" + 
				"-fx-fill: black; -fx-font-weight: bold;"
		);

		Text fullNameText = new Text(postAuthorName + " " + postAuthorSurname);
		fullNameText.setStyle(
				"-fx-font-family: 'Product Sans'; -fx-font-size: 13; -fx-fill: #999999;"
		);
		
		authorInfoBox = new VBox(usernameText, fullNameText);
		authorInfoBox.setPadding(new Insets(3, 0, 0, 0));
	}

	private void setupPostContent() {
		postBody = new Text(postText);
		postBody.setStyle(
				"-fx-font-family: 'Product Sans'; -fx-font-size: 15; -fx-text-fill: black;"
		);
		enableDynamicTextWrapping();
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");
        String formattedDate = createdAt.toLocalDate().format(formatter);		
        Text datePosted = new Text(formattedDate);
		datePosted.setStyle("-fx-fill: #858585; -fx-font-family: 'Product Sans'; -fx-font-size: 13;");
		
		postContentBox = new VBox(15, postBody, datePosted);
		postContentBox.setPadding(new Insets(10, 5, 5, 20));
	}

	private void setupInteractionBar() {		
		likeIcon = new ImageView(IconUtils.seeThroughLikeIcon);
		likeIcon.setFitWidth(15);
		likeIcon.setFitHeight(15);
		
		commentIcon = new ImageView(IconUtils.seeThroughCommentIcon);
		commentIcon.setFitWidth(15);
		commentIcon.setFitHeight(15);
		commentIcon.setTranslateY(2);
		
		interactionBar = new HBox(10, likeIcon, commentIcon);
		interactionBar.setPadding(new Insets(10, 10, 10, 0));
		interactionBar.setPrefHeight(30);
		interactionBar.setStyle(
				"-fx-border-color: #cecece; -fx-border-width: 1 0 0 0;"
		);
	}

	public void displayLikeCount(int likeCount) {
		likeIcon.setImage(IconUtils.highlightedLikeIcon);

		Text likeCountText = new Text(likeCount + " like");
		HBox.setMargin(likeCountText, new Insets(0, 10, 0, -5));
		likeCountText.setStyle(
				"-fx-font-family: 'Product Sans'; -fx-font-size: 15;" + 
			    "-fx-fill: #e6ad38; -fx-font-weight: bold;"
		);
		
		interactionBar.getChildren().add(1, likeCountText);
	}

	public void displayCommentsCount(int commentsCount) {
		commentIcon.setImage(IconUtils.highlightedCommentIcon);

		Text commentCountText = new Text();
		commentCountText.setStyle(
				"-fx-font-family: 'Product Sans'; -fx-font-size: 15;" + 
				"-fx-fill: #e6ad38; -fx-font-weight: bold;"
		);

		if (commentsCount == 1) {
			commentCountText.setText(commentsCount + " commento");
		}

		if (commentsCount != 1) {
			commentCountText.setText(commentsCount + " commenti");
		}
		
		HBox.setMargin(commentCountText, new Insets(0, 0, 0, -5));
		
		interactionBar.getChildren().add(2, commentCountText);
	}
	
	private void enableDynamicTextWrapping() {
		Platform.runLater(() -> {
			postBody.setWrappingWidth(this.getWidth() - 20);
		});
		
		this.widthProperty().addListener((obs, oldWidth, newWidth) -> {
			postBody.setWrappingWidth(newWidth.intValue() - 20);
		});
	}
}

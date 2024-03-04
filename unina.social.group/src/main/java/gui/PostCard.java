package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.Post;

public class PostCard extends BorderPane {
	
	public Post post;
	public HBox postHeader;
	public ImageView profilePicture;
	public VBox authorInfoBox;
	public Text authorUsername;
	public Text authorFirstAndLastName;
	public Label postBody;
	public HBox interactionBar;
	public ImageView likeIcon;
	public ImageView commentIcon;
	
	private static final String POST_CARD_STYLE =
			"-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, white , #f9f9ff);" +
		    "-fx-background-radius: 5;" + "-fx-effect: dropshadow(gaussian, gray, 5, 0, 0, 0);";
	
	public PostCard(Post post) {
		this.post = post;
		initializeComponents();
		layoutComponents();
	}
	
	private void initializeComponents() {
		this.setPadding(new Insets(5, 15, 5, 15));
		this.setMinWidth(200);
		this.setStyle(POST_CARD_STYLE);
	
		setupPostHeader();
		setupPostContent();
		setupInteractionBar();
	}
	
	private void layoutComponents() {
		this.setTop(postHeader);
		this.setCenter(postBody);
		this.setBottom(interactionBar);
		
		postHeader.getChildren().addAll(profilePicture, authorInfoBox);
		authorInfoBox.getChildren().addAll(authorUsername, authorFirstAndLastName);
		interactionBar.getChildren().addAll(likeIcon, commentIcon);
	}

	private void setupPostHeader() {
		postHeader = new HBox(10);
		postHeader.setPrefHeight(30);
		postHeader.setPadding(new Insets(5, 0, 5, 0));
		postHeader.setStyle("-fx-border-color: #cecece;" + "-fx-border-width: 0 0 1 0;");
		setupProfilePicture();
		setupAuthorInfoBox();
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
		authorInfoBox = new VBox();
		authorInfoBox.setPadding(new Insets(3, 0, 0, 0));

		String username = post.getAutore().getUsername();
		authorUsername = new Text(username);
		authorUsername.setStyle(
				"-fx-font-family: 'Product Sans'; -fx-font-size: 15;" + 
				"-fx-fill: black; -fx-font-weight: bold;"
		);

		String firstAndLastName = post.getAutore().getNome() + " " + post.getAutore().getCognome();
		authorFirstAndLastName = new Text(firstAndLastName);
		authorFirstAndLastName.setStyle(
				"-fx-font-family: 'Product Sans'; -fx-font-size: 15; -fx-fill: black;"
		);
	}

	private void setupPostContent() {
		postBody = new Label(post.getTesto());
		postBody.setPadding(new Insets(10, 0, 20, 0));
		postBody.setWrapText(true);
		postBody.setStyle(
				"-fx-font-family: 'Product Sans'; -fx-font-size: 15; -fx-text-fill: black;"
		);
	}

	private void setupInteractionBar() {
		interactionBar = new HBox(10);
		interactionBar.setPadding(new Insets(10, 10, 10, 10));
		interactionBar.setPrefHeight(30);
		interactionBar.setStyle(
				"-fx-border-color: #cecece; -fx-border-width: 1 0 0 0;"
		);
		
		likeIcon = new ImageView(IconUtils.solidLikeIcon);
		likeIcon.setFitWidth(15);
		likeIcon.setFitHeight(15);
		
		commentIcon = new ImageView(IconUtils.solidCommentIcon);
		commentIcon.setFitWidth(15);
		commentIcon.setFitHeight(15);
		commentIcon.setTranslateY(2);
	}

	public void displayLikeCount(int likeCount) {
		likeIcon.setImage(IconUtils.highlightedLikeIcon);

		Text likeCountText = new Text(likeCount + " like");
		likeCountText.setStyle(
				"-fx-font-family: 'Product Sans'; -fx-font-size: 15;" + 
			    "-fx-fill: #e6ad38; -fx-font-weight: bold;"
		);
		HBox.setMargin(likeCountText, new Insets(0, 10, 0, -5));
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
}

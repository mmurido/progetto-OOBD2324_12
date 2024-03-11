package gui.groupOverview;

import java.util.List;

import gui.commonComponents.IconUtils;
import gui.commonComponents.PostCard;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class FeedBox extends VBox {

	private static final String FEED_BOX_STYLE = 
			"-fx-background-radius: 10; -fx-background-color: white;" + 
			"-fx-border-color: #ded2d2; -fx-border-radius: 10;";

	public FeedBox() {
		this.setStyle(FEED_BOX_STYLE);
	}

	public void displayNoPostsMessage() {
		this.setSpacing(5);
		this.setPadding(new Insets(40, 20, 40, 20));
		this.setAlignment(Pos.CENTER);
		
		ImageView ghostGif = new ImageView(IconUtils.ghostGif);
		ghostGif.setFitWidth(80);
		ghostGif.setFitHeight(110);
		
		Text line1 = new Text("Ancora nessun post...");
		line1.setStyle("-fx-fill: #858585; -fx-font-family: 'Product Sans'; -fx-font-size: 14;");
		
		this.getChildren().addAll(ghostGif, line1);
	}
	
	public void displayPostCards(List<PostCard> postCards) {
		this.setSpacing(50);
		this.setPadding(new Insets(10, 20, 25, 20));
		this.setAlignment(Pos.CENTER_LEFT);
		
		Label label = new Label("Posts");
		VBox.setMargin(label, new Insets(0, 0, -40, 0));
		label.setStyle(
				"-fx-text-fill: #5b5b5b; -fx-font-family: 'Product Sans';" + 
				"-fx-font-size: 16; -fx-font-weight: bold;"
		);
		
		this.getChildren().add(label);
		
		for(PostCard postCard: postCards) {
			this.getChildren().add(postCard);
		}
	}
	
	public void lockFeed() {
		this.setSpacing(5);
		this.setPadding(new Insets(30, 25, 30, 25));
		
		ImageView lockIcon = new ImageView(IconUtils.lockIcon);
		lockIcon.setFitWidth(35);
		lockIcon.setFitHeight(35);
		
		Text line1 = new Text("Non hai accesso ai post di questo gruppo");
		line1.setStyle(
				"-fx-fill: #5b5b5b; -fx-font-family: 'Product Sans';" + 
				"-fx-font-size: 18; -fx-font-weight: bold;"
		);
		
		Text line2 = new Text(
				"Solo i membri di questo gruppo possono visualizzarne i contenuti.\n Richiedi l'accesso.");
		line2.setStyle(
				"-fx-fill: #858585; -fx-font-family: 'Product Sans'; -fx-font-size: 12;"
		);
		line2.setTextAlignment(TextAlignment.CENTER);
		
		this.getChildren().addAll(lockIcon, line1, line2);
		this.setAlignment(Pos.CENTER);		
	}
	
}

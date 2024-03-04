package gui;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class UnderConstructionPage  extends StackPane {

	public UnderConstructionPage() {				
		AnchorPane.setTopAnchor(this, 0.0);
		AnchorPane.setBottomAnchor(this, 0.0);
		AnchorPane.setLeftAnchor(this, 0.0);
		AnchorPane.setRightAnchor(this, 0.0);
		
		VBox noResultsFoundMessage = new VBox();
		noResultsFoundMessage.setAlignment(Pos.CENTER);

		ImageView image = new ImageView(IconUtils.underConstructionImage);
		image.setFitWidth(480);
		image.setFitHeight(300);

		Text line1 = new Text("UNDER CONSTRUCTION");
		line1.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 20; -fx-fill: #656565; -fx-font-weight: bold;");

		noResultsFoundMessage.getChildren().addAll(image, line1);
		this.getChildren().add(noResultsFoundMessage);
	}
}

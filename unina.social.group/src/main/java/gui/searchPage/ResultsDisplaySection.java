package gui.searchPage;

import java.util.List;

import gui.commonComponents.GroupCard;
import gui.commonComponents.IconUtils;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ResultsDisplaySection extends AnchorPane {
	
	TilePane resultsTilePane;
	ScrollPane scrollPane;
	
	public ResultsDisplaySection() {
		initializeComponents();
		layoutComponents();
	}
	
	private void initializeComponents() {
		this.setStyle("-fx-background-color: #f6f6f6; -fx-border-color: #bcbcbc;");
		setupScrollPane();
		setupResultsTilePane();
	}
	
	private void layoutComponents() {
		AnchorPane.setTopAnchor(scrollPane, 0.0);
		AnchorPane.setLeftAnchor(scrollPane, 0.0);
		AnchorPane.setRightAnchor(scrollPane, 0.0);
		AnchorPane.setBottomAnchor(scrollPane, 0.0);
		
		scrollPane.setContent(resultsTilePane);
	}
	
	private void setupScrollPane() {
		scrollPane = new ScrollPane();
		scrollPane.setStyle("-fx-background: #f6f6f6; -fx-background-color: transparent;");
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

		String cssPath = SearchPage.class.getResource("/css/scrollPaneStyle.css").toExternalForm();
		scrollPane.getStylesheets().add(cssPath);
	}
	
	private void setupResultsTilePane() {
		resultsTilePane = new TilePane();
		resultsTilePane.setOrientation(Orientation.HORIZONTAL);
		resultsTilePane.setPadding(new Insets(20, 130, 40, 130));
		resultsTilePane.setHgap(20);
		resultsTilePane.setVgap(20);
		resultsTilePane.setPrefColumns(3);
		resultsTilePane.prefWidthProperty().bind(scrollPane.widthProperty());
	}
	
	public void displayResults(List<GroupCard> results) {
		getChildren().clear();
		getChildren().add(scrollPane);
		resultsTilePane.getChildren().clear();
		
		for (GroupCard groupCard : results) {
			resultsTilePane.getChildren().add(groupCard);
		}
	}

	public void displayNoResultsMessage() {
		StackPane stackPane = new StackPane();
		stackPane.setStyle("-fx-background-color: #f6f6f6;");

		AnchorPane.setTopAnchor(stackPane, 0.0);
		AnchorPane.setLeftAnchor(stackPane, 0.0);
		AnchorPane.setRightAnchor(stackPane, 0.0);
		AnchorPane.setBottomAnchor(stackPane, 0.0);
		
		VBox noResultsFoundMessage = new VBox();
		noResultsFoundMessage.setAlignment(Pos.CENTER);

		ImageView image = new ImageView(IconUtils.noResultsFound);
		image.setFitWidth(250);
		image.setFitHeight(150);

		Text line1 = new Text("Nessun risultato.");
		line1.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 20; -fx-fill: #656565; -fx-font-weight: bold;");

		Text line2 = new Text("Ci dispiace, non abbiamo trovato risultati");
		line2.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 16; -fx-fill: #999999;");

		Text line3 = new Text("corrispondenti alla tua ricerca");
		line3.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 16; -fx-fill: #999999;");

		noResultsFoundMessage.getChildren().addAll(image, line1, line2, line3);
		stackPane.getChildren().add(noResultsFoundMessage);
		
		this.getChildren().clear();
		this.getChildren().add(stackPane);
	}

}

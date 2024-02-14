package gui;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class SearchScreen {

	IconUtils iconUtils = new IconUtils();
	
	public StackPane a() {
		
		StackPane searchBar = new StackPane();
		
		TextField searchField = new TextField();
		searchField.setMinSize(250, 30);
		searchField.setMaxWidth(520);
		searchField.setStyle(
				"-fx-background-radius: 20;" +
				"-fx-background-color: #fbfbfb;" +
				"-fx-border-color: #dbdbdb;" +
				"-fx-border-radius: 20;" +
				"-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 8, 0, 0, 0);"
		);
		
		AnchorPane.setTopAnchor(searchBar, 80.0);
		AnchorPane.setLeftAnchor(searchBar, 50.0);
		searchField.setPadding(new Insets(0, 0, 0, 35));

		
		searchField.prefWidthProperty().bind(Bindings.createDoubleBinding(
                () -> searchField.getText().length() * 7.5 + 5, // Adjust multiplier and constant as needed
                searchField.textProperty()
                
        ));
		
		ImageView searchBarIcon = new ImageView(iconUtils.searchBarIcon);
		searchBarIcon.setFitWidth(15);
		searchBarIcon.setFitHeight(15);
		StackPane.setAlignment(searchBarIcon, Pos.CENTER_LEFT);
		searchBarIcon.setTranslateX(10);


		searchBar.getChildren().addAll(searchField, searchBarIcon);





		return searchBar;

	}
	
}

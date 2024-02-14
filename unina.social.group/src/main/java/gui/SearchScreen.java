package gui;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class SearchScreen {

	IconUtils iconUtils = new IconUtils();
	
	public AnchorPane a() {
		
		//BASE PANE
		AnchorPane basePane = new AnchorPane();
		AnchorPane.setTopAnchor(basePane, 70.0);
		AnchorPane.setLeftAnchor(basePane, 80.0);
		AnchorPane.setRightAnchor(basePane, 0.0);
		AnchorPane.setBottomAnchor(basePane, 0.0);

		//VBOX
		VBox topSection = new VBox(10);
		basePane.getChildren().add(topSection);
		AnchorPane.setTopAnchor(topSection, 0.0);
		AnchorPane.setLeftAnchor(topSection, 0.0);
		
		//TESTO 1
		Text text = new Text("Trova nuovi gruppi!");
		text.setStyle(
				"-fx-font-family: 'Product Sans';" + 
				"-fx-font-size: 30;" + 
				"-fx-fill: #faa905;" +
				"-fx-font-weight: bold;"
		);
		topSection.getChildren().add(text);
		
		//TESTO 2
		Text text2 = new Text("Ricerca gruppi per nome o per tema.");
		text2.setStyle(
				"-fx-font-family: 'Product Sans';" + 
				"-fx-font-size: 16;" + 
				"-fx-fill: black;"
		);
		VBox.setMargin(text2, new Insets(0, 0, 20, 5));
		topSection.getChildren().add(text2);
		
		//SEARCH BAR (STACK PANE WITH TEXTFIELD + ICON)
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
		searchField.setPadding(new Insets(0, 5, 0, 35));
		
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
		topSection.getChildren().add(searchBar);
		

		
		

		
//		AnchorPane.setTopAnchor(searchBar, 150.0);
//		AnchorPane.setLeftAnchor(searchBar, 50.0);
		

		

		



		

		
		

		HBox searchFilters = new HBox(10);
		
		Text text3 = new Text("Ricerca per: ");
		text3.setStyle(
				"-fx-font-family: 'Product Sans';" + 
				"-fx-font-size: 13;" + 
				"-fx-fill: black;"
		);
		searchFilters.setMargin(text3, new Insets(3, 0, 0, 5));
		searchFilters.getChildren().add(text3);	


		ToggleButton searchByName = new ToggleButton("nome");
		searchByName.setStyle(
				"-fx-font-family: 'Product Sans';" + 
				"-fx-font-size: 11;" + 
				"-fx-fill: black;" +
				"-fx-background-radius: 20;" +
				"-fx-border-color: #dbdbdb;" +
				"-fx-border-radius: 20;" +
				"-fx-background-color: white;"
		);
//		AnchorPane.setTopAnchor(searchByName, 190.0);
//		AnchorPane.setLeftAnchor(searchByName, 50.0);
		searchFilters.getChildren().add(searchByName);
		
		ToggleButton searchByTopic = new ToggleButton("tema");
		searchByTopic.setStyle(
				"-fx-font-family: 'Product Sans';" + 
				"-fx-font-size: 11;" + 
				"-fx-fill: black;" +
				"-fx-background-radius: 20;" +
				"-fx-border-color: #dbdbdb;" +
				"-fx-border-radius: 20;" +
				"-fx-background-color: white;"
		);
//		AnchorPane.setTopAnchor(searchByName, 190.0);
//		AnchorPane.setLeftAnchor(searchByName, 50.0);
		searchFilters.getChildren().add(searchByTopic);

		topSection.getChildren().add(searchFilters);
		
		
		//CAMBIA COLORE QUANDO HOVER
		searchByName.setOnMouseEntered(e -> {
			searchByName.setStyle(				
					"-fx-font-family: 'Product Sans';" + 
					"-fx-font-size: 11;" + 
					"-fx-text-fill: white;" +
					"-fx-background-radius: 20;" +
					"-fx-border-color: #136472;" +
					"-fx-border-radius: 20;" +
					"-fx-background-color: #0e5460;");
		});
		
		searchByTopic.setOnMouseEntered(e -> {
			searchByTopic.setStyle(				
					"-fx-font-family: 'Product Sans';" + 
					"-fx-font-size: 11;" + 
					"-fx-text-fill: white;" +
					"-fx-background-radius: 20;" +
					"-fx-border-color: #136472;" +
					"-fx-border-radius: 20;" +
					"-fx-background-color: #0e5460;"
			);
		});
		
		searchByName.setOnMouseExited(e -> {
			if(!searchByName.isSelected()) {
			searchByName.setStyle(				
					"-fx-font-family: 'Product Sans';" + 
					"-fx-font-size: 11;" + 
					"-fx-fill: black;" +
					"-fx-background-radius: 20;" +
					"-fx-border-color: #dbdbdb;" +
					"-fx-border-radius: 20;" +
					"-fx-background-color: white;"
			);}
		});
		
		searchByTopic.setOnMouseExited(e -> {
			if (!searchByTopic.isSelected()) {
			searchByTopic.setStyle(				
					"-fx-font-family: 'Product Sans';" + 
					"-fx-font-size: 11;" + 
					"-fx-fill: black;" +
					"-fx-background-radius: 20;" +
					"-fx-border-color: #dbdbdb;" +
					"-fx-border-radius: 20;" +
					"-fx-background-color: white;"
			);}
		});
		
		ToggleGroup g = new ToggleGroup();
		searchByName.setToggleGroup(g);
		searchByTopic.setToggleGroup(g);
		
		searchByName.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {searchByName.setStyle(
					"-fx-font-family: 'Product Sans';" + 
					"-fx-font-size: 11;" + 
					"-fx-text-fill: white;" +
					"-fx-background-radius: 20;" +
					"-fx-border-color: #136472;" +
					"-fx-border-radius: 20;" +
					"-fx-background-color: #0e5460;");
			
			}
			else {searchByName.setStyle(				
					"-fx-font-family: 'Product Sans';" + 
					"-fx-font-size: 11;" + 
					"-fx-fill: black;" +
					"-fx-background-radius: 20;" +
					"-fx-border-color: #dbdbdb;" +
					"-fx-border-radius: 20;" +
					"-fx-background-color: white;");}
		});
		
		searchByTopic.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {searchByTopic.setStyle(
					"-fx-font-family: 'Product Sans';" + 
					"-fx-font-size: 11;" + 
					"-fx-text-fill: white;" +
					"-fx-background-radius: 20;" +
					"-fx-border-color: #136472;" +
					"-fx-border-radius: 20;" +
					"-fx-background-color: #0e5460;");
			
			}
			else {searchByTopic.setStyle(				
					"-fx-font-family: 'Product Sans';" + 
					"-fx-font-size: 11;" + 
					"-fx-fill: black;" +
					"-fx-background-radius: 20;" +
					"-fx-border-color: #dbdbdb;" +
					"-fx-border-radius: 20;" +
					"-fx-background-color: white;");}
		});
		
//		Rectangle r = new Rectangle(640, 168);
//		r.setFill(Color.ORANGE);
//		AnchorPane.setTopAnchor(r, 0.0);
//		AnchorPane.setLeftAnchor(r, 0.0);
//		AnchorPane.setRightAnchor(r, 0.0);
//		basePane.getChildren().add(r);
//		r.setMouseTransparent(true);
		
		
		return basePane;

	}
	
}

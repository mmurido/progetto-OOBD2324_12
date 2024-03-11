package gui.searchPage;

import gui.commonComponents.IconUtils;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
import javafx.scene.text.Text;
import javafx.util.Duration;

public class SearchPageTopSection extends AnchorPane {

	VBox container;
	StackPane banner;
	Text title;
	Text subtitle;
	StackPane searchBar;
	TextField searchField;
	HBox filterBox;
	ToggleGroup filterButtonsToggleGroup;
	ToggleButton nameFilterToggleButton;
	ToggleButton topicFilterToggleButton;
	
	private static final String SEARCH_FIELD_STYLE= 
			"-fx-background-radius: 20; -fx-background-color: #fbfbfb; -fx-border-color: #dbdbdb;" +
			"-fx-border-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 8, 0, 0, 0);";
	
	private static final String DEFAULT_BUTTON_STYLE = 
			"-fx-font-family: 'Product Sans'; -fx-font-size: 11; -fx-text-fill: black;" +
			"-fx-background-color: white; -fx-background-radius: 20;" +
			"-fx-border-color: #dbdbdb; -fx-border-radius: 20;";
	
	private static final String HIGHLIGHTED_BUTTON_STYLE =  					
			"-fx-font-family: 'Product Sans'; -fx-font-size: 11; -fx-text-fill: white;" +
			"-fx-background-color: #0e5460; -fx-background-radius: 20;" +
			"-fx-border-color: #136472; -fx-border-radius: 20;";
	
	private static final String PULSATING_STYLE = 
			"-fx-font-family: 'Product Sans'; -fx-font-size: 11; -fx-text-fill: white;" +
			"-fx-background-color: red; -fx-background-radius: 20;" +
			"-fx-border-color: red; -fx-border-radius: 20;";
	
	public SearchPageTopSection() {
		initializeComponents();
		layoutComponents();
	}
	
	private void initializeComponents() {
		container = new VBox(10);
		setupBanner();
		setupTitle();
		setupSubtitle();
		setupSearchBar();
		setupSearchFilters();
	}
	
	private void layoutComponents() {
		this.getChildren().addAll(banner, container);
		container.getChildren().addAll(title, subtitle, searchBar, filterBox);		
		AnchorPane.setTopAnchor(container, 20.0);
		AnchorPane.setLeftAnchor(container, 130.0);		
		AnchorPane.setLeftAnchor(banner, 0.0);
		AnchorPane.setTopAnchor(banner, 0.0);
		AnchorPane.setRightAnchor(banner, 0.0);		
		VBox.setMargin(subtitle, new Insets(0, 0, 20, 5));		
		VBox.setMargin(searchBar, new Insets(0, 0, 5, 0));		
		VBox.setMargin(filterBox, new Insets(0, 0, 10, 0));
	}

	private void setupBanner() {
		banner = new StackPane();
		banner.setPrefSize(0, 155);
		banner.setStyle("-fx-background-color: #00958c");
	}
	 
	private void setupTitle() {
		title = new Text("Trova nuovi gruppi!");
		title.setStyle(
			 "-fx-font-family: 'Product Sans';" + 
			 "-fx-font-size: 30;" + 
			 "-fx-fill: white;" +
			 "-fx-font-weight: bold;"
		);
	}
	 
	private void setupSubtitle() {
		subtitle = new Text("Ricerca gruppi per nome o per tema.");
		subtitle.setStyle(
			 "-fx-font-family: 'Product Sans';" + 
             "-fx-font-size: 16;" + 
			 "-fx-fill: #f4f4f4;"
		);
	 }
	 
	private void setupSearchBar() {
		searchBar = new StackPane();
	
		searchField = new TextField();
		searchField.setMinSize(250, 30);
		searchField.setMaxWidth(520);
		searchField.setStyle(SEARCH_FIELD_STYLE);
		searchField.setPadding(new Insets(0, 5, 0, 35));
			
		searchField.prefWidthProperty().bind(Bindings.createDoubleBinding(
                () -> searchField.getText().length() * 7.5 + 5, 
                searchField.textProperty()  
        ));
		
		ImageView magnifyingGlassIcon = new ImageView(IconUtils.searchBarIcon);
		magnifyingGlassIcon.setFitWidth(15);
		magnifyingGlassIcon.setFitHeight(15);				
		StackPane.setAlignment(magnifyingGlassIcon, Pos.CENTER_LEFT);
		magnifyingGlassIcon.setTranslateX(10);

		searchBar.getChildren().addAll(searchField, magnifyingGlassIcon);		 
	 }

	private void setupSearchFilters() {
		filterBox = new HBox(10);
		
		Text text = new Text("Ricerca per: ");
		text.setStyle("-fx-font-family: 'Product Sans'; -fx-font-size: 13; -fx-fill: black;");
		HBox.setMargin(text, new Insets(3, 0, 0, 10));
		filterBox.getChildren().add(text);	
		
		filterButtonsToggleGroup = new ToggleGroup();		
		nameFilterToggleButton = this.createButton("nome");
		topicFilterToggleButton = this.createButton("tema");
	}
	
	private ToggleButton createButton(String label) {		
		ToggleButton filterToggleButton = new ToggleButton(label);
		filterToggleButton.setStyle(DEFAULT_BUTTON_STYLE);
		filterToggleButton.setToggleGroup(filterButtonsToggleGroup);
		filterBox.getChildren().add(filterToggleButton);
		
		filterToggleButton.setOnMouseEntered(e -> {
			filterToggleButton.setStyle(HIGHLIGHTED_BUTTON_STYLE);
		});
		
		filterToggleButton.setOnMouseExited(e -> {
			if(!filterToggleButton.isSelected()) {
				filterToggleButton.setStyle(DEFAULT_BUTTON_STYLE);
			}
		});
		
		filterToggleButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {filterToggleButton.setStyle(HIGHLIGHTED_BUTTON_STYLE);}
			else {filterToggleButton.setStyle(DEFAULT_BUTTON_STYLE);}
		});
		
		return filterToggleButton;
	}
	
	public void pulsateFilterButtons() {
		pulsateButton(nameFilterToggleButton);
		pulsateButton(topicFilterToggleButton);
	}
	
	private void pulsateButton(ToggleButton toggleButton) {	
		toggleButton.setStyle(PULSATING_STYLE);   
		
	    Timeline timeline = new Timeline(
	            new KeyFrame(Duration.ZERO, new KeyValue(toggleButton.opacityProperty(), 0.3)),
	            new KeyFrame(Duration.seconds(0.8), new KeyValue(toggleButton.opacityProperty(), 0.8)),
	            new KeyFrame(Duration.seconds(1.3), new KeyValue(toggleButton.opacityProperty(), 0.0))
	    );
	    
	    timeline.setCycleCount(1);                        
	    timeline.play();   
	    
	    timeline.setOnFinished(e -> {
	    	toggleButton.setOpacity(1);
	    	toggleButton.setStyle(DEFAULT_BUTTON_STYLE);} );
	}
}

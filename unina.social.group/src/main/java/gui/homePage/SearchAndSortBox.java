package gui.homePage;

import gui.IconUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class SearchAndSortBox extends HBox {
	
	public StackPane searchBar;
	public TextField searchField;
	public ToggleGroup toggleGroup;
	public ToggleButton sortAZButton;
	public ToggleButton sortZAButton;
	public Button highlightedText;
	public Button deleteButton;

	private static final String SEARCH_AND_SORT_BOX_STYLE = 
    		"-fx-background-color: #fbfbfb; -fx-background-radius: 5;" +
    		"-fx-effect: dropshadow(gaussian, gray, 5, 0, 0, 0);";
	
	private static final String SEARCH_FIELD_STYLE = 
            "-fx-background-radius: 20; -fx-background-color: white;" +
            "-fx-border-color: #dbdbdb; -fx-border-radius: 20;" +
            "-fx-effect: dropshadow(three-pass-box, gray, 2, 0, 0, 0);";
	
	private static final String HIGHLIGHTED_BUTTON_STYLE = 
			"-fx-background-color: #e1e1e1; -fx-background-radius: 10;";
	
	private static final String DEFAULT_BUTTON_STYLE = 
			"-fx-background-color: transparent; -fx-background-radius: 10;";
	
	public SearchAndSortBox() {
		initializeComponents();
	}
	
	private void initializeComponents() {
		this.setSpacing(10);
		this.setStyle(SEARCH_AND_SORT_BOX_STYLE);
		this.setPadding(new Insets(10, 20, 10, 20));
		
		setupSearchBar();
		
		toggleGroup = new ToggleGroup();
		sortAZButton = this.setupSortButton(new ImageView(IconUtils.sortAZ));
		sortZAButton = this.setupSortButton(new ImageView(IconUtils.sortZA));
		this.getChildren().addAll(searchBar, sortAZButton, sortZAButton);

		highlightedText = new Button();
		deleteButton = new Button();
	}

    private void setupSearchBar() {
        searchBar = new StackPane();
        
        searchField = new TextField();
        searchField.setMinSize(220, 30);
        searchField.setStyle(SEARCH_FIELD_STYLE);        
        searchField.setPadding(new Insets(0, 5, 0, 35));
        StackPane.setAlignment(searchField, Pos.CENTER_LEFT);
        
        ImageView magnifyingGlassIcon = new ImageView(IconUtils.searchBarIcon);
        magnifyingGlassIcon.setFitWidth(15);
        magnifyingGlassIcon.setFitHeight(15);
        StackPane.setAlignment(magnifyingGlassIcon, Pos.CENTER_LEFT);
        magnifyingGlassIcon.setTranslateX(10);
        
        searchBar.getChildren().addAll(searchField, magnifyingGlassIcon);
    }
   
    private ToggleButton setupSortButton(ImageView icon) {    	
		ToggleButton sortButton = new ToggleButton();
		IconUtils.setIcon(sortButton, icon);
		sortButton.setStyle(DEFAULT_BUTTON_STYLE);
		
		sortButton.setOnMouseEntered(e -> {
			sortButton.setStyle(HIGHLIGHTED_BUTTON_STYLE); 
		});
		
		sortButton.setOnMouseExited(e -> {
			if (!sortButton.isSelected()) {
				sortButton.setStyle(DEFAULT_BUTTON_STYLE);
			}
		});

		sortButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {sortButton.setStyle(HIGHLIGHTED_BUTTON_STYLE);}
			else {sortButton.setStyle(DEFAULT_BUTTON_STYLE);}
		});

		sortButton.setToggleGroup(toggleGroup);
		return sortButton;
    }
    
	public void highlightText(String textEntered) {
		highlightedText.setStyle(
				"-fx-background-color: #E3E6E8; -fx-background-radius: 5; -fx-font-family: 'Product Sans';" +
				"-fx-font-size: 14; -fx-fill: #444444; -fx-alignment: baseline-left;"
		);
		highlightedText.setPadding(new Insets(0, 3, 0, 3));
		highlightedText.setMaxHeight(8);		

		searchBar.getChildren().add(highlightedText);
		StackPane.setAlignment(highlightedText, Pos.CENTER_LEFT);
		highlightedText.setTranslateX(35);


		if (textEntered.length() > 13) {
			highlightedText.setText(textEntered.substring(0, 10) + "...");
		} else {
			highlightedText.setText(textEntered);
		}
	}

	public void addDeleteButton() {
		deleteButton.setText("✕");
		deleteButton.setStyle(
				"-fx-background-color: transparent; -fx-font-family: 'Comfortaa'; -fx-font-size: 14;" +
				"-fx-text-fill: #5b5b5b; -fx-font-weight: bold;"
		);

		searchBar.getChildren().add(deleteButton);
		StackPane.setAlignment(deleteButton, Pos.CENTER_RIGHT);
		deleteButton.setCursor(Cursor.HAND);
	}
}
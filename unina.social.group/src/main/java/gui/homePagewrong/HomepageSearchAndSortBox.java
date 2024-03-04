package gui;

import controllers.UserSession;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class HomepageSearchAndSortBox {
	
	private ToggleGroup sortButtons;

	private IconUtils iconUtils;
	private HomepageGroupDisplay hpGroupDisplay;
	private UserSession userSession;
	
	public HomepageSearchAndSortBox(UserSession userSession) throws Exception {
		this.iconUtils = new IconUtils();
		this.userSession = userSession;
		this.hpGroupDisplay = new HomepageGroupDisplay(userSession);
	}
	
	public HBox setupSearchAndSortBox() {
		HBox searchAndSortBox = new HBox(10);
		searchAndSortBox.setStyle(
        		"-fx-background-color: #fbfbfb;" + 
        		"-fx-background-radius: 5;" +
        		"-fx-effect: dropshadow(gaussian, gray, 5, 0, 0, 0);"
        );
		searchAndSortBox.setPadding(new Insets(10, 20, 10, 20));
		
		//ADD SEARCH BAR
        StackPane searchBar = this.setupSearchBar();
		searchAndSortBox.getChildren().add(searchBar);

		//ADD SORT BUTTONS
		ToggleButton sortAZButton = this.setupSortButton(new ImageView(iconUtils.sortAZ));
		ToggleButton sortZAButton = this.setupSortButton(new ImageView(iconUtils.sortZA));
		searchAndSortBox.getChildren().addAll(sortAZButton, sortZAButton);

		sortButtons = new ToggleGroup();		
		sortAZButton.setToggleGroup(sortButtons);
		sortZAButton.setToggleGroup(sortButtons);

		sortAZButton.setOnAction(event -> {
            if (sortAZButton.isSelected()) {
            	hpGroupDisplay.displaySortedAZ();
            } 
            else {
            	hpGroupDisplay.displayCurrentlyDisplayed();
            }
        });
		
		sortZAButton.setOnAction(event -> {
            if (sortZAButton.isSelected()) {
            	hpGroupDisplay.displaySortedZA();
            } 
            else {
            	hpGroupDisplay.displayCurrentlyDisplayed();
            }
        });

		return searchAndSortBox;
    }

    private StackPane setupSearchBar() {
        StackPane searchBar = new StackPane();
        
        TextField textField = new TextField();
        textField.setMinSize(220, 30);
        textField.setStyle(
                "-fx-background-radius: 20;" +
                "-fx-background-color: white;" +
                "-fx-border-color: #dbdbdb;" +
                "-fx-border-radius: 20;" +
                "-fx-effect: dropshadow(three-pass-box, gray, 2, 0, 0, 0);"
        );
        
        StackPane.setAlignment(textField, Pos.CENTER_LEFT);
        textField.setPadding(new Insets(0, 5, 0, 35));

        ImageView magnifyingGlassIcon = new ImageView(iconUtils.searchBarIcon);
        magnifyingGlassIcon.setFitWidth(15);
        magnifyingGlassIcon.setFitHeight(15);
        StackPane.setAlignment(magnifyingGlassIcon, Pos.CENTER_LEFT);
        magnifyingGlassIcon.setTranslateX(10);
        
        searchBar.getChildren().addAll(textField, magnifyingGlassIcon);

        setTextFieldEventHandlers(textField, searchBar);
        
        return searchBar;
    }
    
    private void setTextFieldEventHandlers(TextField textField, StackPane searchBar) {
        textField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleEnterKey(textField, searchBar);
            }
        });
    }

    private void handleEnterKey(TextField textField, StackPane searchBar) {
        String textEntered = textField.getText();

        if (!textEntered.isEmpty()) {
            textField.clear();
            textField.setEditable(false);
            sortButtons.getToggles().forEach(toggle -> ((ToggleButton) toggle).setSelected(false));

            hpGroupDisplay.displaySearchResults(textEntered);

            Button highlightedText = highlightText(searchBar, textEntered);
            addDeleteButton(searchBar, highlightedText, textField);
        }
    }

    private Button highlightText(StackPane searchBar, String textEntered) {
        Button highlightedText = new Button();
        highlightedText.setPadding(new Insets(0, 3, 0, 3));
        highlightedText.setMaxHeight(8);

        searchBar.getChildren().add(highlightedText);
        StackPane.setAlignment(highlightedText, Pos.CENTER_LEFT);
        highlightedText.setTranslateX(35);
        highlightedText.setStyle(
                "-fx-background-color: #E3E6E8; -fx-background-radius: 5; -fx-font-family: 'Product Sans'; " +
                "-fx-font-size: 14; -fx-fill: #444444; -fx-alignment: baseline-left;"
        );

        if (textEntered.length() > 13) {
            highlightedText.setText(textEntered.substring(0, 10) + "...");
        } else {
            highlightedText.setText(textEntered);
        }
        
        return highlightedText;
    }

    private void addDeleteButton(StackPane searchBar, Button highlightedText, TextField textField) {
        Button deleteButton = new Button("✕");
        deleteButton.setStyle(
                "-fx-background-color: transparent; -fx-font-family: 'Comfortaa'; -fx-font-size: 14; " +
                "-fx-text-fill: #5b5b5b; -fx-font-weight: bold;"
        );

        searchBar.getChildren().add(deleteButton);
        StackPane.setAlignment(deleteButton, Pos.CENTER_RIGHT);
        deleteButton.setCursor(Cursor.HAND);

        deleteButton.setOnAction(e -> {
            searchBar.getChildren().remove(highlightedText);
            searchBar.getChildren().remove(deleteButton);
            textField.setEditable(true);

            hpGroupDisplay.displayAll();
        });
    }
   
    private ToggleButton setupSortButton(ImageView icon) {
    	String HIGHLIGHTED_STYLE = "-fx-background-color: #e1e1e1; -fx-background-radius: 10;";
    	String UNHIGHLIGHTED_STYLE = "-fx-background-color: transparent; -fx-background-radius: 10;";
    	
		ToggleButton sortButton = new ToggleButton();
		iconUtils.setIcon(sortButton, icon);
		sortButton.setStyle(UNHIGHLIGHTED_STYLE);
		
		sortButton.setOnMouseEntered(e -> {sortButton.setStyle(HIGHLIGHTED_STYLE); });
		
		sortButton.setOnMouseExited(e -> {
			if (!sortButton.isSelected()) {
				sortButton.setStyle(UNHIGHLIGHTED_STYLE);
			}
		});

		sortButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {sortButton.setStyle(HIGHLIGHTED_STYLE);}
			else {sortButton.setStyle(UNHIGHLIGHTED_STYLE);}
		});

		return sortButton;
    }
}

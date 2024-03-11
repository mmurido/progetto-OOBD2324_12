package gui.homePage;

import java.util.List;

import controllers.HomepageController;
import gui.commonComponents.GroupCard;
import javafx.geometry.Insets;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Homepage extends BorderPane {
	
	private HomepageController controller;
	AnchorPane topSection;
	StackPane banner;
	Text title;
	BorderPane innerBorderPane;
	VBox rightSection;
	GroupDisplaySection groupDisplaySection;
	SearchAndSortBox searchAndSortBox;
	SuggestedGroupsSection suggestedGroupsSection;

	public Homepage() {
		this.controller = new HomepageController(this);
		initializeComponents();
		layoutComponents();
		controller.getAllUserGroupsGroupCards();
		controller.getSuggestedGroupsGroupCards();
		configureSearchBehavior();
		handleDeleteButtonClicked();
		handleSortAZButtonClicked();
		handleSortZAButtonClicked();		
		adjustContentToPageSize();
	}
	
	private void initializeComponents() {
		this.setStyle("-fx-background-color: #f9f9f9;");

		setupTopSection();

		innerBorderPane = new BorderPane();
		
		groupDisplaySection = new GroupDisplaySection(controller);
		
		rightSection = new VBox(30);
		
		searchAndSortBox = new SearchAndSortBox();
		
		suggestedGroupsSection = new SuggestedGroupsSection();
	}
	
	private void layoutComponents() {
		AnchorPane.setTopAnchor(this, 0.0);
		AnchorPane.setLeftAnchor(this, 0.0);
		AnchorPane.setRightAnchor(this, 0.0);
		AnchorPane.setBottomAnchor(this, 0.0);
		
		AnchorPane.setLeftAnchor(banner, 0.0);
		AnchorPane.setTopAnchor(banner, 0.0);
		AnchorPane.setRightAnchor(banner, 0.0);
		
		AnchorPane.setTopAnchor(title, 20.0);
		AnchorPane.setLeftAnchor(title, 130.0);
		
		BorderPane.setMargin(rightSection, new Insets(20, 30, 20, 30));
		
		topSection.getChildren().addAll(banner, title);
		this.setTop(topSection);
		
    	this.setCenter(innerBorderPane);
		innerBorderPane.setCenter(groupDisplaySection);
		
		rightSection.getChildren().addAll(searchAndSortBox, suggestedGroupsSection);
		innerBorderPane.setRight(rightSection);
	}
    
    private void setupTopSection() {
    	topSection = new AnchorPane();		
		setupBanner();
		setupTitle();
    }
    
    private void setupBanner() {
		banner = new StackPane();
		banner.setPrefSize(0, 80);
        banner.prefWidthProperty().bind(this.widthProperty());
		banner.setStyle("-fx-background-color: #00958c");
    }
    
    private void setupTitle() {
		title = new Text("I tuoi gruppi:");
		title.setStyle(
				"-fx-font-family: 'Product Sans'; -fx-font-size: 30;" + 
				"-fx-fill: white; -fx-font-weight: bold;"
		);
    }
    
    private void handleSortAZButtonClicked() {
		searchAndSortBox.sortAZButton.setOnAction(event -> {
            if (searchAndSortBox.sortAZButton.isSelected()) {
    			controller.onSortAZButtonSelected();
            } else {
            	controller.onSortButtonUnselected();
            }
		});
    }
    
    private void handleSortZAButtonClicked() {
		searchAndSortBox.sortZAButton.setOnAction(event -> {
            if (searchAndSortBox.sortZAButton.isSelected()) {
    			controller.onSortZAButtonSelected();
            } else {
            	controller.onSortButtonUnselected();
            }
		});
    }
    
    private void configureSearchBehavior() {
        searchAndSortBox.searchField.setOnAction(event -> {
    		String textEntered = searchAndSortBox.searchField.getText();
    		if (!textEntered.isEmpty()) {
    			searchAndSortBox.searchField.clear();
    			searchAndSortBox.searchField.setEditable(false);
    			searchAndSortBox.toggleGroup.getToggles().forEach(
    					toggle -> ((ToggleButton) toggle).setSelected(false));
    			searchAndSortBox.highlightText(textEntered);
    			searchAndSortBox.addDeleteButton();
    			
    			controller.handleSearch(textEntered);
    		}
        });
    }
    
    private void handleDeleteButtonClicked() {
		searchAndSortBox.deleteButton.setOnAction(e -> {
			searchAndSortBox.searchBar.getChildren().removeAll(
				searchAndSortBox.highlightedText, 
				searchAndSortBox.deleteButton
			);
				
			searchAndSortBox.searchField.setEditable(true);
			controller.getAllUserGroupsGroupCards();		
		});
    }
    
    private void adjustContentToPageSize() {
        groupDisplaySection.widthProperty().addListener((observable, oldValue, newValue) -> {
            double newWidth = newValue.doubleValue();
            if (newWidth == 304 || newWidth == 712) {
                innerBorderPane.setRight(null);
            }
            else {
            	innerBorderPane.setRight(rightSection);
            }
        });
    }
    
    public void displayUserGroups(List<GroupCard> groupCardsToDisplay) {
        for (GroupCard groupCard : groupCardsToDisplay) {
        	groupCard.removeJoinGroupButton();
        }
    	groupDisplaySection.displayUserGroups(groupCardsToDisplay);
    }
    
    public void displaySuggestedGroups(List<GroupCard> groupCardsToDisplay) {
    	suggestedGroupsSection.displaySuggestedGroups(groupCardsToDisplay);
    }

}

package gui.homepage;

import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import controllers.HomepageController;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Homepage extends BorderPane {
	
	private HomepageController controller;
	public AnchorPane topSection;
	public StackPane banner;
	public Text title;
	public BorderPane innerBorderPane;
	public VBox rightSection;
	public GroupDisplaySection groupDisplaySection;
	public SearchAndSortBox searchAndSortBox;
	public SuggestedGroupsSection suggestedGroupsSection;

	public Homepage() {
		this.controller = new HomepageController(this);
		initializeComponents();
		layoutComponents();
	}
	
	private void initializeComponents() {
		this.setStyle("-fx-background-color: #f9f9f9;");

		setupTopSection();

		innerBorderPane = new BorderPane();
		groupDisplaySection = new GroupDisplaySection();

		rightSection = new VBox(30);
		BorderPane.setMargin(rightSection, new Insets(20, 30, 20, 30));

		searchAndSortBox = new SearchAndSortBox();
		suggestedGroupsSection = new SuggestedGroupsSection();

		configureSearchBehavior();
		handleSortButtonClicked();
		handleDeleteButtonClicked();
		
		adjustContentToPageSize();
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
    
    private void handleSortButtonClicked() {
		searchAndSortBox.sortAZButton.setOnAction(event -> {
			controller.onSortAZButtonClicked();
		});
		
		searchAndSortBox.sortZAButton.setOnAction(event -> {
			controller.onSortZAButtonClicked();
		});
    }
    
    private void configureSearchBehavior() {
        searchAndSortBox.searchField.setOnAction(event -> {
            controller.handleSearch();
        });
    }
    
    private void handleDeleteButtonClicked() {
		searchAndSortBox.deleteButton.setOnAction(e -> {
			System.out.println("cliccato");
			controller.onDeleteButtonClicked();
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
}

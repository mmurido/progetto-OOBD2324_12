package gui.searchPage;


import controllers.SearchPageController;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class SearchPage extends BorderPane {
	
	private SearchPageController controller;
	public SearchPageTopSection topSection;
	public ResultsDisplaySection resultsDisplaySection;

    public SearchPage() {
		controller = new SearchPageController(this);
    	initializeComponents();
    }
  	
	public void initializeComponents() {
		this.setStyle("-fx-background-color: white;");
		AnchorPane.setTopAnchor(this, 0.0);
		AnchorPane.setLeftAnchor(this, 0.0);
		AnchorPane.setRightAnchor(this, 0.0);
		AnchorPane.setBottomAnchor(this, 0.0);
		
		topSection = new SearchPageTopSection();
		this.setTop(topSection);
		topSection.banner.prefWidthProperty().bind(this.widthProperty());
				
		resultsDisplaySection = new ResultsDisplaySection();
		this.setCenter(resultsDisplaySection);

		configureSearchBehavior();
	}
	
	private void configureSearchBehavior() {
		topSection.searchField.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				controller.onEnterKeyPressed();
			}
		});
	}
	
}

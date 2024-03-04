package gui.analyticsPage;

import controllers.AnalyticsPageController;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class AnalyticsPage extends BorderPane {
	
	private AnalyticsPageController controller;
	public AnchorPane topSection;
	public StackPane banner;
	public Text title;
	public AnalyticsFilterBox filterBox;
	public ReportDisplay reportDisplay;
	
	public AnalyticsPage() {
		this.controller = new AnalyticsPageController(this);
		initializeComponents();
		layoutComponents();
	}

	private void initializeComponents() {
		this.setStyle("-fx-background-color: #f6f6f6;");
    	topSection = new AnchorPane();
		setupBanner();	
		setupTitle();
		filterBox = new AnalyticsFilterBox(controller);
		reportDisplay = new ReportDisplay(controller);
		controller.setItemsIntoGroupChoiceBox();
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
		
		this.setTop(topSection);
		this.setCenter(reportDisplay);
		topSection.getChildren().addAll(banner, title, filterBox);
	}
	
    private void setupBanner() {
    	banner = new StackPane();
		banner.setPrefSize(0, 150);
        banner.prefWidthProperty().bind(this.widthProperty());
		banner.setStyle("-fx-background-color: #00958c");
    }
    
    private void setupTitle() {
		title = new Text("Report mensile.");
		title.setStyle(
				"-fx-font-family: 'Product Sans'; -fx-font-size: 30;" + 
				"-fx-fill: white; -fx-font-weight: bold;"
		);
    }

}

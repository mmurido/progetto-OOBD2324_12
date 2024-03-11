package gui.analyticsPage;

import controllers.AnalyticsPageController;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class AnalyticsPage extends BorderPane {
	
	private AnalyticsPageController controller;
	AnchorPane topSection;
	StackPane banner;
	Text title;
	AnalyticsFilterBox filterBox;
	ReportDisplay reportDisplay;
	
	public AnalyticsPage() {
		this.controller = new AnalyticsPageController(this);
		initializeComponents();
		layoutComponents();
		
		controller.getGroupOptions();
		setBehaviorForClearChoicesButton();
		setBehaviorForShowReportButton();
	}

	private void initializeComponents() {
		this.setStyle("-fx-background-color: #f6f6f6;");
    	topSection = new AnchorPane();
		setupBanner();	
		setupTitle();
		filterBox = new AnalyticsFilterBox(controller);
		reportDisplay = new ReportDisplay(controller);
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
    
    public void setGroupOptions(ObservableList<String> groupOptions) {
		filterBox.groupChoiceBox.setItems(groupOptions);
	}
    
    public void setYearOptions(ObservableList<String> yearOptions) {
		filterBox.yearChoiceBox.setItems(yearOptions);
	}
    
    public void setMonthOptions(ObservableList<String> monthOptions) {
		filterBox.monthChoiceBox.setItems(monthOptions);
	}
    
    private void setBehaviorForClearChoicesButton() {
    	filterBox.clearChoicesButton.setOnMouseClicked(event -> {
    		filterBox.reset();
    		reportDisplay.setVisible(false);
    	});
    }
    
	private void setBehaviorForShowReportButton() {
		filterBox.showReportButton.setOnMouseClicked(event -> {
			reportDisplay.displayReport();
			reportDisplay.setVisible(true);
		});
	}

}

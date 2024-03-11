package gui.analyticsPage;

import java.util.Arrays;
import java.util.List;

import controllers.AnalyticsPageController;
import gui.commonComponents.IconUtils;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class AnalyticsFilterBox extends HBox {

	private AnalyticsPageController controller;
	ChoiceBox<String> groupChoiceBox;
	ChoiceBox<String> yearChoiceBox;
	ChoiceBox<String> monthChoiceBox;
	Button clearChoicesButton;
	Button showReportButton;
	
	public AnalyticsFilterBox(AnalyticsPageController controller) {
		this.controller = controller;
		initializeComponents();
		layoutComponents();
	}
	
	private void initializeComponents() {
		this.setSpacing(20);
		this.setMinWidth(300);
		this.setPadding(new Insets(10, 15, 10, 15));
		this.setStyle(
        		"-fx-background-color: #fbfbfb; -fx-background-radius: 5;" +
        		"-fx-effect: dropshadow(gaussian, gray, 5, 0, 0, 0);"
		);
		
		setupLabeledChoiceBoxes();		
		createClearChoicesButton();		
		createShowReportButton();
	}
	
	private void layoutComponents() {
		AnchorPane.setTopAnchor(this, 70.0);
		AnchorPane.setLeftAnchor(this, 130.0);
	}

	private void setupLabeledChoiceBoxes() {		
		groupChoiceBox = createChoiceBox(150.0);
		setupLabeledChoiceBox("Gruppo:", groupChoiceBox);
		handleGroupSelection();
		
		yearChoiceBox = createChoiceBox(60.0);
		setupLabeledChoiceBox("Anno:", yearChoiceBox);
		yearChoiceBox.setDisable(true);
		handleYearSelection();

		monthChoiceBox = createChoiceBox(90.0);
		setupLabeledChoiceBox("Mese:", monthChoiceBox);
		monthChoiceBox.setDisable(true);
		handleMonthSelection();
	}
	
	private ChoiceBox<String> createChoiceBox(double width) {
        ChoiceBox<String> choiceBox = new ChoiceBox<>();        
        choiceBox.setPrefWidth(width);   		
		choiceBox.getStylesheets().add(
				AnalyticsFilterBox.class.getResource("/css/choiceBoxStyle.css").toExternalForm());
		return choiceBox;
	}
	
	private void setupLabeledChoiceBox(String text, ChoiceBox<String> choiceBox) {
		Label label = createLabel(text);
		StackPane choiceBoxPane = new StackPane(choiceBox);	
		VBox vbox = new VBox(5, label, choiceBoxPane);
		this.getChildren().add(vbox);
	}
	
	private Label createLabel(String text) {
		Label label = new Label(text);		
		label.setStyle(
				"-fx-text-fill: #0e5460; -fx-font-family: 'Product Sans'; -fx-font-size: 13;" + 
				"-fx-alignment: center-left; -fx-font-weight: bold;"
		);		
		return label;
	}
	
	private void handleGroupSelection() {
		groupChoiceBox.getSelectionModel().selectedItemProperty()
		.addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				yearChoiceBox.setDisable(false);	
				blockSelection(groupChoiceBox);
				String selectedGroupName = groupChoiceBox.getValue();				
				controller.onGroupSelected(selectedGroupName);				
			}
		});
	}
	
	private void handleYearSelection() {
		yearChoiceBox.getSelectionModel().selectedItemProperty()
		.addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
		        monthChoiceBox.setDisable(false);
		        blockSelection(yearChoiceBox);
		        int selectedYear = Integer.parseInt(yearChoiceBox.getValue());
				controller.onYearSelected(selectedYear);
			}
		});
	}

	private void handleMonthSelection() {
		monthChoiceBox.getSelectionModel().selectedItemProperty()
		.addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				blockSelection(monthChoiceBox);    			
				addClearAndShowButtons();
				
		        List<String> allMonths = Arrays.asList(
		                "Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno",
		                "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre");
		        
				int selectedMonth = allMonths.indexOf(monthChoiceBox.getValue()) +1;
				controller.onMonthSelected(selectedMonth);
			}
		});
	}
	
	public void addClearAndShowButtons() {
		this.getChildren().addAll(clearChoicesButton, showReportButton);
	}
		
	private void createClearChoicesButton() {
		clearChoicesButton = new Button("✕");
		clearChoicesButton.setCursor(Cursor.HAND);
		clearChoicesButton.setPrefHeight(40);
		clearChoicesButton.setTranslateY(13);
		clearChoicesButton.setStyle(
				"-fx-background-color: transparent; -fx-font-family: 'Comfortaa'; -fx-font-size: 20;" + 
				"-fx-fill: black; -fx-alignment: CENTER; -fx-font-weight: bold;"
		);
	}
	
	private void createShowReportButton() {
		showReportButton = new Button();
		ImageView showIcon = new ImageView(IconUtils.searchBarIcon);
		showIcon.setTranslateY(13);
		IconUtils.setIcon(showReportButton, showIcon);
		showReportButton.setCursor(Cursor.HAND);
		showReportButton.setPrefHeight(40);
		showReportButton.setStyle("-fx-background-color: transparent;");
	}
	
	public void reset() {
		getChildren().removeAll(clearChoicesButton, showReportButton);
		clearChoices();
	}
	
	private void clearChoices() {
		unblockSelection(monthChoiceBox);
		unblockSelection(groupChoiceBox);
		unblockSelection(yearChoiceBox);
		
		groupChoiceBox.setValue(null);
		yearChoiceBox.setValue(null);
		monthChoiceBox.setValue(null);

		yearChoiceBox.getItems().clear();
		monthChoiceBox.getItems().clear();

		yearChoiceBox.setDisable(true);
		monthChoiceBox.setDisable(true);
	}
	
    public void blockSelection(ChoiceBox<String> choiceBox) {
    	choiceBox.setVisible(false);
    	
	   	Button selectedFilter = new Button();
	   	StackPane stackPane = (StackPane) choiceBox.getParent();  
	   	stackPane.getChildren().add(selectedFilter);

    	selectedFilter.setPrefWidth(choiceBox.getPrefWidth());
		selectedFilter.setStyle(
				"-fx-background-color: white; -fx-background-radius: 10;" +
				"-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 8, 0, 0, 0);" +
				"-fx-text-fill: black; -fx-font-family: 'Product Sans';" +
				"-fx-font-size: 13; -fx-alignment: center-left;"
		);
		
		choiceBox.setVisible(false);    	
		selectedFilter.setVisible(true);
		selectedFilter.setText(choiceBox.getValue());
    }
    
    public void unblockSelection(ChoiceBox<String> choiceBox) {
        StackPane stackPane = (StackPane) choiceBox.getParent();
        stackPane.getChildren().removeIf(node -> node instanceof Button);
        choiceBox.setVisible(true);
    }
}

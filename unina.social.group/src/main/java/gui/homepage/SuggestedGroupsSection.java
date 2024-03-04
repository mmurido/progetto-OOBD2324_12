package gui.homepage;

import java.util.List;
import controllers.GroupController;
import controllers.UserSession;
import gui.searchPage.GroupCard;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.Gruppo;

public class SuggestedGroupsSection extends BorderPane {
	
	private GroupController controller;
	public StackPane header;
	public Label label;	
	public ScrollPane scrollPane;
	public List<Gruppo> suggestedGroups;
	public VBox suggestionsBox;
		
	public SuggestedGroupsSection() {
		this.controller = new GroupController();
		initializeComponents();
		layoutComponents();
	}
	
	private void initializeComponents() {
        this.setMinWidth(300);
        this.setStyle(
        		"-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, white , #f9f9ff);" + 
        		"-fx-background-radius: 5; -fx-effect: dropshadow(gaussian, gray, 5, 0, 0, 0);"
        );
        
        setupHeader();
        setupScrollPane();
        setupSuggestionsDisplay();
	}
	
	private void layoutComponents() {
		header.getChildren().add(label);
		this.setTop(header);
        scrollPane.setContent(suggestionsBox);
        this.setCenter(scrollPane);
	}

	private void setupHeader() {
		header = new StackPane();
		header.setPrefHeight(30);
		header.setStyle(
				"-fx-background-color: #fbfbfb; -fx-background-radius: 5 5 0 0;" +
				"-fx-border-color: #cecece; -fx-border-width: 0 0 1 0;"
		);

		label = new Label("Suggerimenti");
		label.setStyle(
				"-fx-font-family: 'Product Sans'; -fx-font-size: 15;" + 
				"-fx-text-fill: #c1c1c1; -fx-font-weight: bold;"
		);
	}
	
	private void setupScrollPane() {
		scrollPane = new ScrollPane();
		scrollPane.setStyle(
				"-fx-background: transparent; -fx-background-color: transparent;"
		);
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		String cssPath = SuggestedGroupsSection.class.getResource("/css/scrollPaneStyle.css").toExternalForm();
        scrollPane.getStylesheets().add(cssPath);
	}
	
	private void setupSuggestionsDisplay() {
        suggestedGroups = controller.getSuggestedGroups(UserSession.getLoggedUser());
        
        suggestionsBox = new VBox(30);
        suggestionsBox.setPadding(new Insets(10, 20, 15, 20));
        
        for (Gruppo group : suggestedGroups) {
        	GroupCard groupCard = new GroupCard(group);
            suggestionsBox.getChildren().add(groupCard);
        }
	}
	
}

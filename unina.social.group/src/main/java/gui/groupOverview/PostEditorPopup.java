package gui.groupOverview;

import gui.commonComponents.ShadowPane;
import gui.commonComponents.WindowControls;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PostEditorPopup extends BorderPane {
	
	Pane parent;
	Stage popupStage;
	Scene popupScene;
	ShadowPane shadowPane;
	AnchorPane windowControlsBar;
	WindowControls windowControls;
	Text titleText;
	AnchorPane centerSection;
	TextArea textArea;	
	AnchorPane bottomSection;
	Button postButton;
	StackPane buttonPane;

	private static final String POST_EDITOR_STYLE = 
			"-fx-background-color: #f6f6f6; -fx-background-radius: 5;" +
			"-fx-effect: dropshadow(gaussian, gray, 10, 0, 0, 0);";
	
	private static final String TEXT_AREA_STYLE = 
			"-fx-background-color: -fx-text-box-border, -fx-control-inner-background;" +
			"-fx-focus-color: transparent; -fx-faint-focus-color: transparent;" +
    		"-fx-font-family: 'Product Sans'; -fx-font-size: 14; -fx-border-color: #f1f1f1;";
	
	private static final String POST_BUTTON_DEFAULT_STYLE =
			"-fx-background-color: #00958c; -fx-background-radius: 10; -fx-font-family: 'Product Sans';" + 
			"-fx-text-fill: white; -fx-font-size: 14; -fx-font-weight: bold;";
	
	private static final String POST_BUTTON_HIGHLIGHTED_STYLE =
			"-fx-background-color: #007972; -fx-background-radius: 10; -fx-font-family: 'Product Sans';" + 
			"-fx-text-fill: white; -fx-font-size: 14; -fx-font-weight: bold;";
	
	public PostEditorPopup(Pane parent) {
		this.parent = parent;
		initializeComponents();
		layoutComponents();
		setMaximumLengthOfPost();
	}
	
	private void initializeComponents() {
		setupStage();
		setupShadowPane();
		setupScene();
		setupPopupPage();
		setupTopSection();
		setupCenterSection();
		setupBottomSection();
	}
	
	private void layoutComponents() {
		StackPane.setMargin(this, new javafx.geometry.Insets(10, 10, 10, 10));

		this.setTop(windowControlsBar);
		this.setCenter(centerSection);
		this.setBottom(bottomSection);
		
		AnchorPane.setTopAnchor(textArea, 20.0);
		AnchorPane.setLeftAnchor(textArea, 20.0);
		AnchorPane.setRightAnchor(textArea, 20.0);
		
        AnchorPane.setRightAnchor(buttonPane, 20.0);
        AnchorPane.setBottomAnchor(buttonPane, 10.0);
        AnchorPane.setTopAnchor(buttonPane, 10.0);
	}
	
    private void setupPopupPage() {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setMaxSize(500, 320);
        this.setStyle(POST_EDITOR_STYLE);
    }
    
    private void setupStage() {
        popupStage = new Stage();
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initStyle(StageStyle.TRANSPARENT);
    }
    
    private void setupShadowPane() {
        shadowPane = new ShadowPane(this);
        shadowPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-background-radius: 5;");
        shadowPane.setLayoutX(58);
        shadowPane.setLayoutY(6);
        
        adjustShadowSizeBasedOnParentSize();
    }
    
    private void adjustShadowSizeBasedOnParentSize() {
        parent.boundsInLocalProperty().addListener((observable, oldValue, newValue) -> {
            double newWidth = newValue.getWidth();
            double newHeight = newValue.getHeight();
        	shadowPane.setPrefSize(newWidth+2, newHeight+35);   
        });
    }
    
    private void setupScene() {
        popupScene = new Scene(shadowPane);
        popupScene.setFill(Color.TRANSPARENT);
        popupStage.setScene(popupScene);
    }
    
    public void show() {
        popupStage.show();   
    }
    
    private void setupTopSection() {
		titleText = new Text("Crea un nuovo post");
		titleText.setStyle(
				"-fx-fill: #5b5b5b; -fx-font-family: 'Product Sans';" + 
				"-fx-font-size: 14; -fx-font-weight: bold;"
		);
		
		windowControls = new WindowControls(popupStage, this);
		windowControls.setAlignment(Pos.CENTER);
		windowControls.setSpacing(150);
		windowControls.getChildren().removeAll(windowControls.minimizeButton, windowControls.maximizeButton);
		windowControls.getChildren().add(0, titleText);

		windowControlsBar = new AnchorPane(windowControls);
		windowControlsBar.setPadding(new Insets(5, 0, 5, 0));
		windowControlsBar.setStyle("-fx-border-color: white; -fx-border-width: 0 0 2 0;");
    }
    
    private void setupCenterSection() {
		textArea = new TextArea();
		textArea.setPrefHeight(170);
		textArea.setWrapText(true);
		textArea.setPromptText("Scrivi cosa pensi...");
		textArea.setStyle(TEXT_AREA_STYLE);
		
    	centerSection = new AnchorPane(textArea);
    }
    
    private void setupBottomSection() {
    	setupPostButton();
		
		buttonPane = new StackPane(postButton);
		StackPane.setAlignment(buttonPane, Pos.CENTER_RIGHT);
		
		bottomSection = new AnchorPane(buttonPane);
		bottomSection.setStyle("-fx-border-color: white; -fx-border-width: 2 0 0 0;");
    }
    
    private void setupPostButton() {
        postButton = new Button("Pubblica");
        postButton.setCursor(Cursor.HAND);
		postButton.setStyle(POST_BUTTON_DEFAULT_STYLE);
		
		postButton.setOnMouseEntered(e -> {
			postButton.setStyle(POST_BUTTON_HIGHLIGHTED_STYLE);
		});
		
		postButton.setOnMouseExited(e -> {
			postButton.setStyle(POST_BUTTON_DEFAULT_STYLE);
		});
    }
    
    private void setMaximumLengthOfPost() {
    	int maximumLength = 1000;
		textArea.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.length() > maximumLength) {
				textArea.setText(oldValue);
			}
		});
    }
}

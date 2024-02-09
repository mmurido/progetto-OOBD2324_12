package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUIHelper {

	private Stage stage;
	
	public GUIHelper(Stage stage) {
		this.stage = stage;
	}
	
	public Button createCloseButton() {
		Button closeButton = new Button("✕");
        closeButton.setFont(new Font("Comfortaa", 12));
        closeButton.setStyle("-fx-background-color: transparent; " +
        					 "-fx-alignment: CENTER; " + 
        					 "-fx-min-width: 0px; " +
        					 "-fx-min-height: 0px;");
        StackPane.setAlignment(closeButton, Pos.TOP_RIGHT);        
        closeButton.setOnAction(e -> {stage.close();});
        closeButton.setOnMouseEntered(e -> closeButton.setStyle("-fx-background-color: #df0404; -fx-alignment: CENTER; -fx-min-width: 0px; -fx-min-height: 0px; -fx-text-fill: white;"));
        closeButton.setOnMouseExited(e -> closeButton.setStyle("-fx-background-color: transparent; -fx-alignment: CENTER; -fx-min-width: 0px; -fx-min-height: 0px;"));
	
        return closeButton;
	}
	
	public Button createMinimizeButton() {
        Button minimizeButton = new Button("─");
        minimizeButton.setFont(new Font("Comfortaa", 12));
        minimizeButton.setStyle("-fx-background-color: transparent;");
        StackPane.setAlignment(minimizeButton, Pos.TOP_RIGHT);
        StackPane.setMargin(minimizeButton, new Insets(0, 25, 0, 0));
        minimizeButton.setOnMouseEntered(e -> minimizeButton.setStyle("-fx-background-color: #eeeeee; -fx-alignment: CENTER; -fx-min-width: 0px; -fx-min-height: 0px;"));
        minimizeButton.setOnMouseExited(e -> minimizeButton.setStyle("-fx-background-color: transparent; -fx-alignment: CENTER; -fx-min-width: 0px; -fx-min-height: 0px;"));
        minimizeButton.setOnAction(e -> {stage.setIconified(true);});
        return minimizeButton;
	}
	
	public StackPane createTemplatePane(double width, double height) {
        StackPane pane = new StackPane();
        pane.setStyle("-fx-background-color: white; " +
        			  "-fx-background-radius: 5;" +
        			  "-fx-effect: dropshadow(gaussian, black, 5, 0, 3, 3);");
        pane.setMinSize(width, height);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(2));
        return pane;		
	}
}

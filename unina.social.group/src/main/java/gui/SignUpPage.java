package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SignUpPage {
    
    public Scene getScene(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: white; " +
        			  "-fx-background-radius: 5;" +
        			  "-fx-effect: dropshadow(gaussian, black, 5, 0, 3, 3);");
        root.setMinSize(400, 400);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(2));
    	Scene scene = new Scene(root);
    	return scene;
    }
}

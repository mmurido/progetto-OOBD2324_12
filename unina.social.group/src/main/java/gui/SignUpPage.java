package gui;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

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

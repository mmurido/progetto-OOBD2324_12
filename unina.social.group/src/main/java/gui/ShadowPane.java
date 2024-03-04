package gui;

import javafx.scene.layout.StackPane;

public class ShadowPane extends StackPane{

	public ShadowPane() {
		this.setStyle("-fx-background-color: transparent;");
		this.setPrefSize(500, 500);
	}
}

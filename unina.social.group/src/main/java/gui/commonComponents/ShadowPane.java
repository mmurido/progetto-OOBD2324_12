package gui.commonComponents;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class ShadowPane extends StackPane{

	public ShadowPane() {
		this.setStyle("-fx-background-color: transparent;");
		this.setPrefSize(500, 500);
	}
	
	public ShadowPane(Node node) {
		this.setStyle("-fx-background-color: transparent;");
		this.setPrefSize(500, 500);
		this.getChildren().add(node);
	}
}

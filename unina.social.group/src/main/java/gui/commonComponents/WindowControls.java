package gui.commonComponents;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class WindowControls extends HBox {
	
	public Button minimizeButton;
	public Button maximizeButton;
	public Button closeButton;
	private Stage stage;
	private Pane window;
	private double oldHeight;
	private double oldWidth;
		
	public WindowControls(Stage stage) {
		this.stage = stage;
		initializeComponents();
		layoutComponents();
	}

	public WindowControls(Stage stage, Pane window) {
		this.stage = stage;
		this.window = window;
		initializeComponents();
		layoutComponents();
	}
	
	private void initializeComponents() {
		createMinimizeButton();
		createMaximizeButton();
		createCloseButton();
	}
	
	private void layoutComponents() {
		AnchorPane.setRightAnchor(this, 0.0);
		this.setPadding(new Insets(1));
		this.getChildren().addAll(minimizeButton, maximizeButton, closeButton);
	}

	private void createMinimizeButton() {
		minimizeButton = new Button("─");
		minimizeButton.setStyle(
				"-fx-background-color: transparent; -fx-font-family: 'Comfortaa';" +
				"-fx-font-size: 12; -fx-fill: black;"
		);

		minimizeButton.setOnMouseEntered(e -> 
			minimizeButton.setStyle("-fx-background-color: #eeeeee; -fx-alignment: CENTER;")
		);

		minimizeButton.setOnMouseExited(e -> 
			minimizeButton.setStyle("-fx-background-color: transparent; -fx-alignment: CENTER;")
		);

		minimizeButton.setOnAction(e -> {
			stage.setIconified(true);
		});
	}

	private void createMaximizeButton() {
		ImageView maximizeButtonIconView = new ImageView(IconUtils.maximizeButtonIcon);
		maximizeButtonIconView.setFitHeight(12);
		maximizeButtonIconView.setFitWidth(12);

		maximizeButton = new Button("", maximizeButtonIconView);
		maximizeButton.setStyle("-fx-background-color: transparent;");
		maximizeButton.setPrefSize(28, 25);

		maximizeButton.setOnMouseEntered(e -> 
			maximizeButton.setStyle("-fx-background-color: #eeeeee;"));
		
		maximizeButton.setOnMouseExited(e -> 
			maximizeButton.setStyle("-fx-background-color: transparent;"));
		
		maximizeButton.setOnAction(e -> {
			if (!stage.isMaximized()) {
				maximizeWindow();
			} else {
				restoreWindow();
			}
		});
	}

	private void maximizeWindow() {
		oldHeight = stage.getHeight();
		oldWidth = stage.getWidth();
		StackPane.setMargin(window, new Insets(0, 0, 48, 0));
		stage.setMaximized(true);
	}

	private void restoreWindow() {
		StackPane.setMargin(window, new Insets(10, 10, 10, 10));
		stage.setHeight(oldHeight);
		stage.setWidth(oldWidth);
		stage.setMaximized(false);
		stage.centerOnScreen();
	}

	private void createCloseButton() {
		closeButton = new Button("✕");
		closeButton.setStyle(
				"-fx-background-color: transparent; -fx-font-family: 'Comfortaa';" +
				"-fx-font-size: 12; -fx-fill: black; -fx-alignment: CENTER;"
		);

		closeButton.setOnAction(e -> {
			stage.close();
		});
		
		closeButton.setOnMouseEntered(e ->
			closeButton.setStyle(
					"-fx-background-color: #df0404; -fx-alignment: CENTER; -fx-text-fill: white;")
		);
		
		closeButton.setOnMouseExited(e -> 
			closeButton.setStyle(
					"-fx-background-color: transparent; -fx-alignment: CENTER;")
		);
	}

}

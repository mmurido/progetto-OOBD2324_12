package gui.homepage;

import gui.IconUtils;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class WindowControls {

	private Stage primaryStage;
	private Pane window;
	private HBox windowControls;
	private double oldHeight;
	private double oldWidth;
	
	private IconUtils iconUtils = new IconUtils();
	
	public void setStage(Stage stage) {
		this.primaryStage = stage;
	}
	
	public void setWindow(Pane pane) {
		this.window = pane;
	}
	
	public HBox setUpWindowControls() {
		windowControls = new HBox();
		AnchorPane.setTopAnchor(windowControls, 1.0);
		AnchorPane.setRightAnchor(windowControls, 1.0);

		addMinimizeButton();
		addMaximizeButton();
		addCloseButton();
		
		return windowControls;
	}

	private void addMinimizeButton() {
		Button minimizeButton = new Button("─");
		minimizeButton.setStyle("-fx-background-color: transparent;" + "-fx-font-family: 'Comfortaa';"
				+ "-fx-font-size: 12;" + "-fx-fill: black;");

		minimizeButton.setOnMouseEntered(
				e -> minimizeButton.setStyle("-fx-background-color: #eeeeee; -fx-alignment: CENTER;"));
		minimizeButton.setOnMouseExited(
				e -> minimizeButton.setStyle("-fx-background-color: transparent; -fx-alignment: CENTER;"));
		minimizeButton.setOnAction(e -> {
			primaryStage.setIconified(true);
		});

		windowControls.getChildren().add(minimizeButton);
	}

	private void addMaximizeButton() {
		ImageView maximizeButtonIconView = new ImageView(iconUtils.maximizeButtonIcon);
		maximizeButtonIconView.setFitHeight(12);
		maximizeButtonIconView.setFitWidth(12);

		Button maximizeButton = new Button("", maximizeButtonIconView);
		maximizeButton.setStyle("-fx-background-color: transparent;");
		maximizeButton.setPrefSize(28, 25);

		maximizeButton.setOnMouseEntered(e -> maximizeButton.setStyle("-fx-background-color: #eeeeee;"));
		maximizeButton.setOnMouseExited(e -> maximizeButton.setStyle("-fx-background-color: transparent;"));
		maximizeButton.setOnAction(e -> {
			if (!primaryStage.isMaximized()) {
				maximizeWindow();
			} else {
				restoreWindow();
			}
		});

		windowControls.getChildren().add(maximizeButton);
	}

	private void maximizeWindow() {
		oldHeight = primaryStage.getHeight();
		oldWidth = primaryStage.getWidth();
		StackPane.setMargin(window, new Insets(0, 0, 48, 0));
		primaryStage.setMaximized(true);
	}

	private void restoreWindow() {
		StackPane.setMargin(window, new Insets(10, 10, 10, 10));
		primaryStage.setHeight(oldHeight);
		primaryStage.setWidth(oldWidth);
		primaryStage.setMaximized(false);
		primaryStage.centerOnScreen();
	}

	private void addCloseButton() {
		Button closeButton = new Button("✕");
		closeButton.setFont(new Font("Comfortaa", 12));
		closeButton.setStyle("-fx-background-color: transparent;" + "-fx-font-family: 'Comfortaa';"
				+ "-fx-font-size: 12;" + "-fx-fill: black;" + "-fx-alignment: CENTER;");

		closeButton.setOnAction(e -> {
			primaryStage.close();
		});
		closeButton.setOnMouseEntered(e -> closeButton
				.setStyle("-fx-background-color: #df0404; -fx-alignment: CENTER; -fx-text-fill: white;"));
		closeButton.setOnMouseExited(
				e -> closeButton.setStyle("-fx-background-color: transparent; -fx-alignment: CENTER;"));

		windowControls.getChildren().add(closeButton);
	}

}

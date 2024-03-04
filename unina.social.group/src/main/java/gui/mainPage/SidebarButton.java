package gui.mainpage;

import gui.IconUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class SidebarButton extends ToggleButton {

	public ImageView icon;
	
	static final String HIGHLIGHTED_BUTTON_STYLE = 
			"-fx-background-color: #136472; -fx-background-radius: 10";
	
	static final String DEFAULT_BUTTON_STYLE = 
			"-fx-background-color: #0e5460; -fx-background-radius: 10";
	
	static final String LABEL_STYLE =
			"-fx-font-family: 'Product Sans'; -fx-font-size: 14; -fx-text-fill: white;";
	
	public SidebarButton (Image image, String text) {
		this.icon = new ImageView(image);
		this.setPrefSize(210, 40);
		setContent(icon, text);
		initializeButton();
	}
	
	public SidebarButton(Image image) {
		this.icon = new ImageView(image);
		this.setPrefSize(40, 40);		
		setContent(icon);
		initializeButton();
	}
	
	private void setContent(ImageView icon) {
		icon.setFitWidth(18);
		icon.setFitHeight(18);
		this.setGraphic(icon);
	}
	
	private void setContent(ImageView icon, String text) {		
		HBox hbox = new HBox(30);
		hbox.setAlignment(Pos.CENTER_LEFT);
		
		icon.setFitWidth(18);
		icon.setFitHeight(18);
		icon.setTranslateX(1);
				
		Label label = new Label(text);
		label.setStyle(LABEL_STYLE);
		
		hbox.getChildren().addAll(icon, label);
		this.setGraphic(hbox);
	}
	
	private void initializeButton() {
		this.setStyle(DEFAULT_BUTTON_STYLE);		
		this.setOnMouseEntered(e -> { highlight(); });		
		this.setOnMouseExited(e -> { unhighlight(); });
	}
	
	public void highlight() {
		IconUtils.zoomInIcon(icon);
		this.setStyle(HIGHLIGHTED_BUTTON_STYLE);
	}
	
	public void unhighlight() {
		IconUtils.zoomOutIcon(icon);
		this.setStyle(DEFAULT_BUTTON_STYLE);
	}

}

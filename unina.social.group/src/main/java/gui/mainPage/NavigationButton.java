package gui.mainPage;

import gui.IconUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class NavigationButton extends SidebarButton {
	
	public ToggleGroup toggleGroup;
	public Label label;
	
	static final String LABEL_STYLE =
			"-fx-font-family: 'Product Sans'; -fx-font-size: 14; -fx-text-fill: white;";
	
	public NavigationButton(Image image, ToggleGroup toggleGroup) {
		super(image);
		this.toggleGroup = toggleGroup;
		addToToggleGroup();
		highlightIfSelected();
		keepHighlightedIfSelected();
	}
	
	public NavigationButton(Image image, String text, ToggleGroup toggleGroup) {
		super(image, text);		
		this.toggleGroup = toggleGroup;
		addToToggleGroup();
		highlightIfSelected();
		keepHighlightedIfSelected();
	}
	
	private void addToToggleGroup() {
		this.setToggleGroup(toggleGroup);
	}	
	
	private void highlightIfSelected() {
		this.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				this.setStyle(HIGHLIGHTED_BUTTON_STYLE);
				this.setMouseTransparent(true);
			}
			else {
				this.setStyle(DEFAULT_BUTTON_STYLE);
				this.setMouseTransparent(false);
			}
		});
	}
	
	private void keepHighlightedIfSelected() {
		this.setOnMouseExited(e -> {
			if (!this.isSelected()) {
				IconUtils.zoomOutIcon(icon);
				this.setStyle(DEFAULT_BUTTON_STYLE);
			}
		});
	}

}

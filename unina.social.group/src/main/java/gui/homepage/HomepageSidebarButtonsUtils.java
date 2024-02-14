package gui.homepage;

import gui.IconUtils;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;

public class HomepageSidebarButtonsUtils {

	public static final String HIGHLIGHTED_STYLE = "-fx-background-color: #136472; -fx-background-radius: 10";
	public static final String UNHIGHLIGHTED_STYLE = "-fx-background-color: #0e5460; -fx-background-radius: 10";

	private IconUtils iconUtils = new IconUtils();
	
	public void highlightButton(ButtonBase button) {
		iconUtils.zoomInIcon((ImageView) button.getGraphic());
		button.setStyle(HIGHLIGHTED_STYLE);
	}
	public void unhighlightButton(ToggleButton button) {
		iconUtils.zoomOutIcon((ImageView) button.getGraphic());
		button.setStyle(UNHIGHLIGHTED_STYLE);
	}

	public void unhighlightButtonIfNotSelected(ToggleButton button) {
		if (!button.isSelected()) {
			iconUtils.zoomOutIcon((ImageView) button.getGraphic());
			button.setStyle(UNHIGHLIGHTED_STYLE);
		}
	}
	
	public void setBehaviorForNavigationButton(ToggleButton button, ImageView icon) {
		button.setOnMouseEntered(e -> {this.highlightButton(button);});

		button.setOnMouseExited(e -> {this.unhighlightButtonIfNotSelected(button);});

		this.addToggleButtonHighlightListener(button);
	}
	
	public void addToggleButtonHighlightListener(ToggleButton button) {
		button.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				button.setStyle(HIGHLIGHTED_STYLE);
			} else {
				button.setStyle(UNHIGHLIGHTED_STYLE);
			}
		});
	}
}

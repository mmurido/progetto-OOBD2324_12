package gui;

import javafx.animation.ScaleTransition;
import javafx.scene.control.ButtonBase;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class IconUtils {

	public Image closedEye = new Image(getClass().getResourceAsStream("a.png"));
	public Image openEye = new Image(getClass().getResourceAsStream("b.png"));
	public Image homeIcon = new Image(getClass().getResourceAsStream("homeIcon.png"));
	public Image searchIcon = new Image(getClass().getResourceAsStream("searchIcon.png"));
	public Image notificationIcon = new Image(getClass().getResourceAsStream("notificationIcon.png"));
	public Image messageIcon = new Image(getClass().getResourceAsStream("messageIcon.png"));
	public Image settingsIcon = new Image(getClass().getResourceAsStream("settingsIcon.png"));
	public Image analyticsIcon = new Image(getClass().getResourceAsStream("analyticsIcon.png"));
	public Image logoutIcon = new Image(getClass().getResourceAsStream("logoutIcon.png"));
	public Image navigationIcon = new Image(getClass().getResourceAsStream("navigationIcon.png"));
	public Image logo = new Image(getClass().getResourceAsStream("logo.png"));
	public Image maximizeButtonIcon = new Image(getClass().getResourceAsStream("c.png"));
	public Image profilePicture = new Image(getClass().getResourceAsStream("pfp.png"));
	public Image searchBarIcon = new Image(getClass().getResourceAsStream("searchBarIcon.png"));
	public Image sortAZ = new Image(getClass().getResourceAsStream("sortAZ.png"));
	public Image sortZA = new Image(getClass().getResourceAsStream("sortZA.png"));
	public Image crown = new Image(getClass().getResourceAsStream("crown.png"));
	
	public void setIcon(ButtonBase button, ImageView icon) {
		double iconWidth = 18;
		double iconHeight = 18;
		icon.setFitWidth(iconWidth);
		icon.setFitHeight(iconHeight);
		button.setGraphic(icon);
	}
	
	public void zoomInIcon(ImageView icon) {
		ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), icon);
		scaleTransition.setFromX(1.0);
		scaleTransition.setFromY(1.0);
		scaleTransition.setToX(1.2);
		scaleTransition.setToY(1.2);
		scaleTransition.playFromStart();
	}

	public void zoomOutIcon(ImageView icon) {
		ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), icon);
		scaleTransition.setFromX(1.2);
		scaleTransition.setFromY(1.2);
		scaleTransition.setToX(1.0);
		scaleTransition.setToY(1.0);
		scaleTransition.playFromStart();
	}
}

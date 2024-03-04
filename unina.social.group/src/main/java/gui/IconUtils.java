package gui;

import javafx.animation.ScaleTransition;
import javafx.scene.control.ButtonBase;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class IconUtils {

	public static final Image maximizeButtonIcon = loadImage("/images/square.png");
	public static final Image closedEye = loadImage("/images/closedEye.png");
	public static final Image openedEye = loadImage("/images/openedEye.png");
	public static final Image homeIcon = loadImage("/images/homeIcon.png");
	public static final Image searchIcon = loadImage("/images/searchIcon.png");
	public static final Image bellIcon = loadImage("/images/bell.png");
	public static final Image messageIcon = loadImage("/images/messagesIcon.png");
	public static final Image settingsIcon = loadImage("/images/cogIcon.png");
	public static final Image analyticsIcon = loadImage("/images/analyticsIcon.png");
	public static final Image logoutIcon = loadImage("/images/logoutIcon.png");
	public static final Image navigationIcon = loadImage("/images/navigationIcon.png");
	public static final Image logo = loadImage("/images/logo.png");
	public static final Image profilePicture = loadImage("/images/pfp.png");
	public static final Image searchBarIcon = loadImage("/images/searchBarIcon.png");
	public static final Image sortAZ = loadImage("/images/sortAZ.png");
	public static final Image sortZA = loadImage("/images/sortZA.png");
	public static final Image crown = loadImage("/images/crown.png");
	public static final Image highlightedLikeIcon = loadImage("/images/likeIcon.png");
	public static final Image seeThroughCommentIcon = loadImage("/images/seeThroughCommentIcon.png");
	public static final Image highlightedCommentIcon = loadImage("/images/orangeCommentIcon.png");
	public static final Image seeThroughLikeIcon = loadImage("/images/seeThroughLikeIcon.png");
	public static final Image membersIcon = loadImage("/images/membersIcon.png");
	public static final Image postIcon = loadImage("/images/postIcon.png");
	public static final Image noResultsFound = loadImage("/images/noResultsFound.png");
	public static final Image solidLikeIcon = loadImage("/images/solidLike.png");
	public static final Image solidCommentIcon = loadImage("/images/solidComment.png");
	public static final Image underConstructionImage = loadImage("/images/underConstruction.png");

    private static Image loadImage(String filename) {
        return new Image(IconUtils.class.getResourceAsStream(filename));
    }
	
	public static void setIcon(ButtonBase button, ImageView icon) {
		double iconWidth = 18;
		double iconHeight = 18;
		icon.setFitWidth(iconWidth);
		icon.setFitHeight(iconHeight);
		button.setGraphic(icon);
	}
	
	public static void zoomInIcon(ImageView icon) {
		ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), icon);
		scaleTransition.setFromX(1.0);
		scaleTransition.setFromY(1.0);
		scaleTransition.setToX(1.2);
		scaleTransition.setToY(1.2);
		scaleTransition.playFromStart();
	}

	public static void zoomOutIcon(ImageView icon) {
		ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), icon);
		scaleTransition.setFromX(1.2);
		scaleTransition.setFromY(1.2);
		scaleTransition.setToX(1.0);
		scaleTransition.setToY(1.0);
		scaleTransition.playFromStart();
	}
}

package gui.mainpage;

import controllers.UserSession;
import gui.IconUtils;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class SidebarBottomSection extends HBox {
	
	public HBox profileBox;
	public SidebarButton logoutButton;
	
	static final String PROFILE_BOX_STYLE = 
			"-fx-background-color: #136472; -fx-background-radius: 10";
	
	static final String TEXT_STYLE = 
			"-fx-font-family: 'Product Sans'; -fx-font-size: 12; -fx-fill: white;";
	
	public SidebarBottomSection() {
        initializeComponents();
        layoutComponents();
    }

    private void initializeComponents() {
    	this.setSpacing(3);
    	createProfileBox();
    	createLogoutButton();
    }
    
    private void layoutComponents() {		
		AnchorPane.setBottomAnchor(this, 10.0);
		AnchorPane.setLeftAnchor(this, 7.0);
		
		this.getChildren().addAll(profileBox, logoutButton);
    }
	
	private void createProfileBox() {
		profileBox = new HBox();
		profileBox.setStyle(PROFILE_BOX_STYLE);
		profileBox.setPrefSize(173, 45);
		profileBox.setPadding(new Insets(5, 8, 4, 10));
		profileBox.setSpacing(8);
		profileBox.setCursor(Cursor.HAND);
		
		addProfilePicture();
		addUserInfo();
	}
	
	private void addProfilePicture() {
		ImageView profilePicture = new ImageView(IconUtils.profilePicture);
		double profilePictureWidth = 35;
		double profilePictureHeight = 35;
		profilePicture.setFitHeight(profilePictureHeight);
		profilePicture.setFitWidth(profilePictureWidth);

		Rectangle profilePictureShape = new Rectangle(profilePictureWidth, profilePictureHeight);
		profilePictureShape.setArcHeight(10);
		profilePictureShape.setArcWidth(10);
		profilePicture.setClip(profilePictureShape);
		
		profileBox.getChildren().add(profilePicture);
	}
	
	private void addUserInfo() {
		VBox userInfoBox = new VBox();
		userInfoBox.setPadding(new Insets(3, 0, 0, 0));

		Text firstAndLastName = new Text(
				UserSession.getLoggedUser().getNome() + " " + 
				UserSession.getLoggedUser().getCognome());
		firstAndLastName.setStyle(TEXT_STYLE + " -fx-font-weight: bold;");

		Text username = new Text(UserSession.getLoggedUser().getUsername());
		username.setStyle(TEXT_STYLE);

		firstAndLastName.setMouseTransparent(true);
		username.setMouseTransparent(true);

		userInfoBox.getChildren().addAll(firstAndLastName, username);
		profileBox.getChildren().add(userInfoBox);
	}
	
	private void createLogoutButton() {
		logoutButton = new SidebarButton(IconUtils.logoutIcon);
		logoutButton.icon.setTranslateX(-3);
		logoutButton.setOnMouseEntered(e -> {IconUtils.zoomInIcon(logoutButton.icon);});
		logoutButton.setOnMouseExited(e -> {IconUtils.zoomOutIcon(logoutButton.icon);});	
	}
}

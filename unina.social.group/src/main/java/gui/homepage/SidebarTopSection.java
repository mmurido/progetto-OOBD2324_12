package gui.mainpage;

import gui.IconUtils;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SidebarTopSection extends HBox {
	
	public Text logo;
	public SidebarButton collapseButton;
	
	 public SidebarTopSection() {
        initializeComponents();
        layoutComponents();
    }

    private void initializeComponents() {
    	this.setSpacing(35);
    	createLogo();
    	createCollapseButton();
    }
    
    private void layoutComponents() {		
		AnchorPane.setTopAnchor(this, 10.0);
		AnchorPane.setLeftAnchor(this, 20.0);
		this.getChildren().addAll(logo, collapseButton);	
    }

	private void createLogo() {
		logo = new Text("SocialGroup");
		logo.setStyle("-fx-fill: white; -fx-font-size: 20; -fx-font-weight: bold;");
		logo.setFont(Font.loadFont(SidebarTopSection.class.getResourceAsStream("/fonts/Gopher-Bold.ttf"), 20));
		HBox.setMargin(logo, new Insets(10, 0, 0, 0));
	}
	
	private void createCollapseButton() {
		collapseButton = new SidebarButton(IconUtils.navigationIcon);
		collapseButton.setOnMouseEntered(e -> {IconUtils.zoomInIcon(collapseButton.icon);});
		collapseButton.setOnMouseExited(e -> {IconUtils.zoomOutIcon(collapseButton.icon);});	
	}
	
}

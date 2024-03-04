package gui.loginPage;

import javafx.scene.control.Button;

public class LoginButton extends Button {
	
	String defaultStyle = 
			"-fx-font-weight: bold; -fx-background-color: #0e6f80; -fx-background-radius: 5;" + 
			"-fx-text-fill: white; -fx-effect: dropshadow(gaussian, gray, 5, 0, 0, 1);";
	
	String highlightedStyle = 
			"-fx-font-weight: bold; -fx-background-color: #0e5460; -fx-background-radius: 5;" + 
			"-fx-text-fill: white; -fx-effect: dropshadow(gaussian, gray, 5, 0, 0, 1)";
	  
	public LoginButton() {
        initialize();
    }

    private void initialize() {
    	this.setText("LOGIN");       
    	this.setStyle(defaultStyle);
        this.setMaxWidth(205);
        this.setDisable(true);
        
        this.setOnMouseEntered(e -> {
        	if(!this.isDisabled())
        		this.setStyle(highlightedStyle);
    	});
    	
        this.setOnMouseExited(e -> {
        	if(!this.isDisabled())
        		this.setStyle(defaultStyle);
    	});
    }

}

package gui.loginPage;

import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SignUpPrompt extends VBox {
	
	Text promptText;
	Hyperlink signUpHyperlink;

    public SignUpPrompt() {
        initializeComponents();
        layoutComponents();
    }

    private void initializeComponents() {
    	createPromptText();    	
    	createSignUpHyperlink();    	
    }
    
    private void layoutComponents() {
    	this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(promptText, signUpHyperlink);
    }

    private void createPromptText() {
    	promptText = new Text("Non sei ancora registrato?");
    	promptText.setFont(Font.font("Product Sans", 11.5));
    }
    
    private void createSignUpHyperlink() {
    	signUpHyperlink = new Hyperlink("Crea un account.");
    	signUpHyperlink.setStyle("-fx-text-fill: #faa905; -fx-font: bold 11.8 'Product Sans';");

        signUpHyperlink.setOnMouseEntered(e -> {
        	signUpHyperlink.setStyle("-fx-underline: true; -fx-text-fill: #faa905; -fx-font: bold 11.8 'Product Sans';");
        });
        
        signUpHyperlink.setOnMouseExited(e -> {
        	signUpHyperlink.setStyle("-fx-underline: false; -fx-text-fill: #faa905; -fx-font: bold 11.8 'Product Sans';");
        });
        
        signUpHyperlink.setOnMouseClicked(e -> {

        });
    }
}

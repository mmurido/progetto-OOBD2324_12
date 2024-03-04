package gui.loginPage;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LoginPageHeading extends VBox{

	public Label title;
	public Button errorMessage;
	
	private static final String errorMessageStyle = 
			"-fx-background-color: b61f1f;" +
			"-fx-alignment: CENTER_LEFT;" + 
			"-fx-text-fill: white; " +
	        "-fx-font-family: 'Product Sans';" + 
			"-fx-font-size: 12;";
	
    public LoginPageHeading() {
        initializeComponents();
        layoutComponents();
    }

    private void initializeComponents() {
    	title = new Label("LOGIN");
    	title.setFont(Font.font("Product Sans", FontWeight.BOLD, 16));
		
    	errorMessage = new Button("Credenziali non corrette.");
    	errorMessage.setStyle(errorMessageStyle);    	
    	errorMessage.setMaxSize(205, 40);
        errorMessage.setVisible(false);
    }
    
    private void layoutComponents() {
        this.getChildren().addAll(title, errorMessage);
        this.setSpacing(3);
    }
}

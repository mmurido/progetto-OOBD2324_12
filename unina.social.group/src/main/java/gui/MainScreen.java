package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainScreen extends Application {

	private Stage primaryStage;
	private StackPane pane;
	
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    	GUIHelper guiHelper = new GUIHelper(primaryStage);

    	this.primaryStage = primaryStage;
    	primaryStage.initStyle(StageStyle.TRANSPARENT);

    	StackPane root = guiHelper.createTemplatePane(250, 370);
    	this.pane = root;

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        
        Button closeButton = guiHelper.createCloseButton();
        
        Button minimizeButton = guiHelper.createMinimizeButton();
       
        Label loginPageLabel = new Label("LOGIN");
        loginPageLabel.setFont(Font.font("Product Sans", FontWeight.BOLD, 16));
        loginPageLabel.setTranslateX(-76);
        loginPageLabel.setTranslateY(-125);
        
        TextField usernameTextField = this.createUsernameTextField();
        Label usernameTextFieldLabel = new Label("Username");
        usernameTextFieldLabel.setFont(Font.font("Product Sans", 12));
        usernameTextFieldLabel.setTranslateY(-75);
        usernameTextFieldLabel.setTranslateX(-76.3);
        
        TextField passwordTextField = this.createPasswordTextField();
        Label passwordTextFieldLabel = new Label("Password");
        passwordTextFieldLabel.setFont(Font.font("Product Sans", 12));
        passwordTextFieldLabel.setTranslateY(-9);
        passwordTextFieldLabel.setTranslateX(-76.3);
        
        //questo dovrebbe essere messo nella pagina di registrazione
    	Button warning = new Button("Username troppo lungo!");
    	warning.setMaxHeight(23);
    	warning.setMaxWidth(205);
    	warning.setTranslateY(-100);
        warning.setFont(Font.font("Product Sans", 12));
        warning.setStyle("-fx-background-color: b61f1f; -fx-alignment: CENTER_LEFT; -fx-min-width: 0px; -fx-min-height: 0px; -fx-text-fill: white;");
        warning.setVisible(false);
        usernameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 15) {
            	warning.setVisible(true);
            	usernameTextField.setStyle("-fx-focus-color: #b61f1f; -fx-faint-focus-color: rgba(182, 31, 31, 0.3);");
            }
            if (newValue.length() <= 15) {
            	warning.setVisible(false);
            	usernameTextField.setStyle("-fx-focus-color: #faa905; -fx-faint-focus-color: rgba(246, 200, 107, 0.3);");
            }
        });

        Button loginButton = this.createLoginButton();
        Button signUpButton = this.createSignUpButton();

        Text notSignedUpLabel = new Text("Non sei ancora registrato?");
        notSignedUpLabel.setFont(Font.font("Product Sans", 11.5));
        notSignedUpLabel.setTranslateY(130);
      
        
        root.getChildren().addAll(
        		closeButton, 
        		minimizeButton, 
        		loginPageLabel, 
        		usernameTextField, 
        		usernameTextFieldLabel, 
        		passwordTextField, 
        		passwordTextFieldLabel, 
        		loginButton, 
        		notSignedUpLabel, 
        		signUpButton,
        		warning);

        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    private TextField createUsernameTextField() {
        TextField usernameField = new TextField();
        usernameField.setStyle("-fx-focus-color: #faa905; -fx-faint-focus-color: rgba(246, 200, 107, 0.3);");
        usernameField.setMaxWidth(205);
        usernameField.setTranslateY(-50);
                
        this.setTextFieldBehavior(usernameField);
        
        return usernameField;
    }
    
    private void setTextFieldBehavior(TextField textField) {
        pane.setOnMouseClicked(e -> {
        	if (!textField.getBoundsInParent().contains(e.getX(), e.getY())) {
        				pane.requestFocus();
        	}
        });
    }
    
    private TextField createPasswordTextField() {
        TextField passwordTextField = new TextField();
        passwordTextField.setStyle("-fx-focus-color: #faa905; -fx-faint-focus-color: rgba(246, 200, 107, 0.3);");
        passwordTextField.setMaxWidth(205);
        passwordTextField.setTranslateY(15);
        this.setTextFieldBehavior(passwordTextField);
        return passwordTextField;
    }
    
    private Button createLoginButton() {
    	Button loginButton = new Button("LOGIN");
        loginButton.setStyle("-fx-font-weight: bold; -fx-background-color: #0e6f80; -fx-text-fill: white; -fx-effect: dropshadow(gaussian, gray, 3, 0, 3, 3);");
        loginButton.setMaxWidth(205);
        loginButton.setTranslateY(73);        
        loginButton.setOnMouseEntered(e -> {loginButton.setStyle("-fx-font-weight: bold; -fx-background-color: #0e5460; -fx-text-fill: white; -fx-effect: dropshadow(gaussian, gray, 3, 0, 3, 3)");});
        loginButton.setOnMouseExited(e -> {loginButton.setStyle("-fx-font-weight: bold; -fx-background-color: #0e6f80; -fx-text-fill: white; -fx-effect: dropshadow(gaussian, gray, 3, 0, 3, 3);");});
        return loginButton;
    }
    
    private Button createSignUpButton() {
        Button signUpButton = new Button("Crea un account.");
        signUpButton.setFont(Font.font("Product Sans", FontWeight.BOLD, 11.8));
        signUpButton.setTranslateY(145);
        signUpButton.setStyle("-fx-text-fill: #faa905; -fx-background-color: transparent;");        
        signUpButton.setOnMouseEntered(e -> {signUpButton.setStyle("-fx-underline: true; -fx-text-fill: #faa905; -fx-background-color: transparent;");});
        signUpButton.setOnMouseExited(e -> {signUpButton.setStyle("-fx-underline: false; -fx-text-fill: #faa905; -fx-background-color: transparent;");});
        
        signUpButton.setOnMouseClicked(e -> {
            SignUpPage signUpPage = new SignUpPage();
            Scene nextScene;
			try {
				nextScene = signUpPage.getScene(primaryStage);
				primaryStage.setScene(nextScene);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
        });
        return signUpButton;
    }


}
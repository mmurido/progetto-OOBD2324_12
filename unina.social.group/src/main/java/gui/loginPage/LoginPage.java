package gui.loginPage;

import controllers.LoginPageController;
import controllers.Navigation;

import gui.commonComponents.ShadowPane;
import gui.commonComponents.WindowControls;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginPage extends Application {

	private LoginPageController controller;
	Stage primaryStage;
	Scene scene;
	WindowControls windowControls;
	ShadowPane shadowPane;
	AnchorPane basePane;
	LoginPageHeading heading;
	LoginCredentialsSection credentialsSection;
	LoginButton loginButton;
	SignUpPrompt signUpPrompt;	
    double xOffset;
    double yOffset;
	
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	controller = new LoginPageController(this);
        shadowPane = new ShadowPane();
        initializePrimaryStage(primaryStage);
        setupBasePane();
		setupWindowControls();
		setupContent();
		makeStageDraggable();
    }
    
    private void initializePrimaryStage(Stage primaryStage) {
       	this.primaryStage = primaryStage;
    	primaryStage.centerOnScreen();
    	primaryStage.initStyle(StageStyle.TRANSPARENT);
    	
        scene = new Scene(shadowPane);
        scene.setFill(Color.TRANSPARENT);
        
		primaryStage.setScene(scene);
		primaryStage.show();
		
		Navigation.setPrimaryStage(primaryStage);
    }

    private void setupBasePane() {
        basePane = new AnchorPane();
		shadowPane.getChildren().add(basePane);
    	String style = 
				"-fx-background-color: white; -fx-background-radius: 5;" +
				"-fx-effect: dropshadow(gaussian, gray, 10, 0, 0, 0);";
		
        basePane.setStyle(style);
        basePane.setMaxSize(250, 370);
        basePane.setOnMouseClicked(e -> {basePane.requestFocus(); });        
    }
    
    private void setupWindowControls() {
        windowControls = new WindowControls(primaryStage);
        windowControls.getChildren().remove(windowControls.maximizeButton);
		basePane.getChildren().add(windowControls);
    }
    
    private void setupContent() {
    	VBox container = new VBox(20);
		basePane.getChildren().add(container);
		AnchorPane.setTopAnchor(container, 50.0);
		AnchorPane.setLeftAnchor(container, 30.0);
		AnchorPane.setRightAnchor(container, 30.0);
		
		heading = new LoginPageHeading();	
		credentialsSection = new LoginCredentialsSection();
		loginButton = new LoginButton();
		handleLoginButtonClick();
		bindLoginButtonToFieldCompletion();
		signUpPrompt = new SignUpPrompt();
		
		container.getChildren().addAll(heading, credentialsSection, loginButton, signUpPrompt);
    }

    private void handleLoginButtonClick() {
        loginButton.setOnAction(e -> {		
            String insertedUsername = credentialsSection.usernameField.getText();
            String insertedPassword = credentialsSection.hashedPasswordField.getText();
            controller.onLoginButtonClicked(insertedUsername, insertedPassword);
         });
    }
    
    private void bindLoginButtonToFieldCompletion() {
        BooleanBinding allFieldsFilled = Bindings.createBooleanBinding(
                () -> !credentialsSection.usernameField.getText().isEmpty() && 
                	  !credentialsSection.passwordField.getText().isEmpty() && 
                	  !credentialsSection.hashedPasswordField.getText().isEmpty(),
                credentialsSection.usernameField.textProperty(), 
                credentialsSection.passwordField.textProperty(), 
                credentialsSection.hashedPasswordField.textProperty());

        loginButton.disableProperty().bind(allFieldsFilled.not());
    }
    
    public void handleAuthenticationError() {
    	heading.errorMessage.setVisible(true);
    	
    	String ERROR_STYLE = 
    			"-fx-border-color: #b61f1f;" + 
    			"-fx-faint-focus-color: rgba(182, 31, 31, 0.3);" + 
    			"-fx-border-radius: 5;";
    	
    	credentialsSection.usernameField.setStyle(ERROR_STYLE);
    	credentialsSection.passwordField.setStyle(ERROR_STYLE);
    	credentialsSection.hashedPasswordField.setStyle(ERROR_STYLE);
    }
    
    private void makeStageDraggable() {
        basePane.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        basePane.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
    }
    
}
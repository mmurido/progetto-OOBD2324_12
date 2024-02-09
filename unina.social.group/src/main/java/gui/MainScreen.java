package gui;

import java.sql.SQLException;

import DAO.UtenteDAO;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class MainScreen extends Application {

	private Stage primaryStage;
	private StackPane pane;
	private UtenteDAO utenteDAO;
	
    private double xOffset = 0;
    private double yOffset = 0;
	
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    	GUIHelper guiHelper = new GUIHelper(primaryStage);

    	this.primaryStage = primaryStage;
    	primaryStage.centerOnScreen();
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
        
        //TextField passwordTextField = this.createPasswordTextField();
        Label passwordTextFieldLabel = new Label("Password");
        passwordTextFieldLabel.setFont(Font.font("Product Sans", 12));
        passwordTextFieldLabel.setTranslateY(-9);
        passwordTextFieldLabel.setTranslateX(-76.3);
        
        //questo dovrebbe essere messo nella pagina di registrazione
//    	Button warning = new Button("Username troppo lungo!");
//    	warning.setMaxHeight(23);
//    	warning.setMaxWidth(205);
//    	warning.setTranslateY(-100);
//        warning.setFont(Font.font("Product Sans", 12));
//        warning.setStyle("-fx-background-color: b61f1f; -fx-alignment: CENTER_LEFT; -fx-min-width: 0px; -fx-min-height: 0px; -fx-text-fill: white;");
//        warning.setVisible(false);
//        usernameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue.length() > 15) {
//            	warning.setVisible(true);
//            	usernameTextField.setStyle("-fx-focus-color: #b61f1f; -fx-faint-focus-color: rgba(182, 31, 31, 0.3);");
//            }
//            if (newValue.length() <= 15) {
//            	warning.setVisible(false);
//            	usernameTextField.setStyle("-fx-focus-color: #faa905; -fx-faint-focus-color: rgba(246, 200, 107, 0.3);");
//            }
//        });

        usernameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
        	if (newValue.contains(" ")) {
        		usernameTextField.setText(oldValue);
        	}
	    });
        

        
        
        
        PasswordField passwordTextField = new PasswordField();
        passwordTextField.setStyle("-fx-focus-color: #faa905; -fx-faint-focus-color: rgba(246, 200, 107, 0.3);");
        passwordTextField.setMaxWidth(205);
        passwordTextField.setTranslateY(15);
        this.setTextFieldBehavior(passwordTextField);
        
        TextField textField = new TextField();
        textField.setStyle("-fx-focus-color: #faa905; -fx-faint-focus-color: rgba(246, 200, 107, 0.3);");
        textField.setMaxWidth(205);
        textField.setTranslateY(15);
        this.setTextFieldBehavior(textField);
        



        passwordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
        	if (newValue.contains(" ")) {
        		passwordTextField.setText(oldValue);
        	}
	    });
        
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
        	if (newValue.contains(" ")) {
        		textField.setText(oldValue);
        	}
	    });
        
        
        ToggleButton toggleButton = new ToggleButton();
        toggleButton.setStyle("-fx-background-color: transparent");
        
        Image iconImage = new Image(getClass().getResourceAsStream("a.png"));

        ImageView iconView = new ImageView(iconImage);
        iconView.setSmooth(true);
        toggleButton.setTranslateY(15);
        toggleButton.setTranslateX(85);
                
        toggleButton.setGraphic(iconView);        
        toggleButton.setPrefSize(25, 25);
        
        Image iconImage2 = new Image(getClass().getResourceAsStream("b.png"));

        ImageView iconView2 = new ImageView(iconImage2);
        iconView2.setSmooth(true);
        toggleButton.setTranslateY(15);
        toggleButton.setTranslateX(85);
        
        toggleButton.setOnAction(e -> {
        	if(toggleButton.isSelected()) {
        		toggleButton.setGraphic(iconView2); 
        	}
        	else {
        		toggleButton.setGraphic(iconView);
        	}


        });
        
        textField.managedProperty().bind(toggleButton.selectedProperty());
        textField.visibleProperty().bind(toggleButton.selectedProperty());

        passwordTextField.managedProperty().bind(toggleButton.selectedProperty().not());
        passwordTextField.visibleProperty().bind(toggleButton.selectedProperty().not());

        textField.textProperty().bindBidirectional(passwordTextField.textProperty());
        
        
        
        
        

        
        Button loginButton = this.createLoginButton();
        Button signUpButton = this.createSignUpButton();

        Text notSignedUpLabel = new Text("Non sei ancora registrato?");
        notSignedUpLabel.setFont(Font.font("Product Sans", 11.5));
        notSignedUpLabel.setTranslateY(130);
      

        
        
        
    	Button warning = new Button("Credenziali non corrette.");
    	warning.setMaxHeight(23);
    	warning.setMaxWidth(205);
    	warning.setTranslateY(-100);
        warning.setFont(Font.font("Product Sans", 12));
        warning.setStyle("-fx-background-color: b61f1f; -fx-alignment: CENTER_LEFT; -fx-min-width: 0px; -fx-min-height: 0px; -fx-text-fill: white;");
        warning.setVisible(false);
        loginButton.setOnAction(e -> {
        	Homepage homepage = new Homepage();
        	Scene nextScene;
        	nextScene = homepage.getScene(primaryStage);
			primaryStage.setScene(nextScene);
			primaryStage.centerOnScreen();
			primaryStage.setResizable(true);

//            String username = usernameTextField.getText();
//            String password = passwordTextField.getText();
//           
//            try {
//				utenteDAO = new UtenteDAO();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//            boolean credentialsAreCorrect = utenteDAO.checkIfCredentialsAreCorrect(username, password);
//            System.out.println(credentialsAreCorrect);
//            if (!credentialsAreCorrect) {
//            	warning.setVisible(true);
//            	usernameTextField.setStyle("-fx-border-color: #b61f1f; -fx-faint-focus-color: rgba(182, 31, 31, 0.3); -fx-border-radius: 2;");
//            	passwordTextField.setStyle("-fx-border-color: #b61f1f; -fx-faint-focus-color: rgba(182, 31, 31, 0.3); -fx-border-radius: 2;");
//            }
//            else
//            {
//                Homepage homepage = new Homepage();
//                Scene nextScene;
//    			try {
//    				nextScene = homepage.getScene(primaryStage);
//    				primaryStage.setScene(nextScene);
//    				primaryStage.show();
//    			} catch (Exception exception) {
//    				exception.printStackTrace();
//    			}
//            }
         });       
        
        

        // Handle mouse pressed event to start dragging
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        // Handle mouse dragged event to move the stage
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
        
        usernameTextField.textProperty().addListener((obs, oldText, newText) -> updateButtonStyle(loginButton, usernameTextField, passwordTextField));
        passwordTextField.textProperty().addListener((obs, oldText, newText) -> updateButtonStyle(loginButton, usernameTextField, passwordTextField));

        
        
        
        
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
        		signUpButton, textField, toggleButton, warning);

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
        loginButton.setStyle("-fx-font-weight: bold; -fx-background-color: #0e6f80; -fx-text-fill: white; -fx-effect: dropshadow(gaussian, gray, 5, 0, 0, 1);");
        loginButton.setMaxWidth(205);
        loginButton.setTranslateY(73); 
        loginButton.setDisable(true);
        loginButton.setOnMouseEntered(e -> {
        	if(!loginButton.isDisabled()) {
        		loginButton.setStyle("-fx-font-weight: bold; -fx-background-color: #0e5460; -fx-text-fill: white; -fx-effect: dropshadow(gaussian, gray, 5, 0, 0, 1)");
        	}
    	});
    	
        loginButton.setOnMouseExited(e -> {
        	if(!loginButton.isDisabled()) {
        		loginButton.setStyle("-fx-font-weight: bold; -fx-background-color: #0e6f80; -fx-text-fill: white; -fx-effect: dropshadow(gaussian, gray, 5, 0, 0, 1);");
        	}
    	});
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
				primaryStage.centerOnScreen();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
        });
        return signUpButton;
    }
    
    
    private void updateButtonStyle(Button button, TextField textField1, TextField textField2) {
        // Check if both text fields have content
        if (!textField1.getText().isEmpty() && !textField2.getText().isEmpty()) {
            button.setDisable(false);
        } else {
            button.setDisable(true);
        }
    }
    
    
    
    


}
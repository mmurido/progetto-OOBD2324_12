package gui.loginPage;

import gui.IconUtils;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class LoginCredentialsSection extends VBox {
	
    public Label usernameLabel;
    public Label passwordLabel;
    public TextField usernameField;
    public TextField passwordField;
    public PasswordField hashedPasswordField;
    public VBox labeledUsernameField;
    public VBox labeledPasswordField;
    
    private String FIELD_STYLE = 
    		"-fx-focus-color: #faa905; -fx-faint-focus-color: rgba(246, 200, 107, 0.5);" + 
    	    "-fx-background-radius: 5; -fx-border-radius: 5; -fx-border-color: #eeeeee;";

    public LoginCredentialsSection() {
        initializeComponents();
        layoutComponents();
    }

    private void initializeComponents() {
    	usernameLabel = createLabel("Username");		
		usernameField = createTextField();		
		labeledUsernameField = createLabeledField(usernameLabel, usernameField);
		
	    passwordLabel = createLabel("Password");
	    StackPane passwordTogglePane = createPasswordTogglePane();
	    labeledPasswordField = createLabeledField(passwordLabel, passwordTogglePane);
    }
    
    private Label createLabel(String content) {
    	Label label = new Label(content);
		label.setFont(Font.font("Product Sans", 12));
		return label;
    }
    
    private TextField createTextField() {
    	TextField field = new TextField();
		field.setStyle(FIELD_STYLE);
		preventSpaces(field);
		return field;
    }
    
    private PasswordField createPasswordField() {
    	PasswordField field = new PasswordField();
		field.setStyle(FIELD_STYLE);
		preventSpaces(field);
		return field;
    }
    
    private void preventSpaces(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.contains(" ")) {
                textField.setText(oldValue);
            }
        });
    }
    
    private VBox createLabeledField(Label label, Node field) {
        VBox container = new VBox(3);
        container.getChildren().addAll(label, field);
        return container;
    }
    
    private StackPane createPasswordTogglePane() {
        StackPane passwordPane = new StackPane();

	    passwordField = createTextField();
        passwordPane.getChildren().add(passwordField);

	    hashedPasswordField = createPasswordField();
        passwordPane.getChildren().add(hashedPasswordField);

        ToggleButton passwordToggleButton = createPasswordToggleButton();
        passwordPane.getChildren().add(passwordToggleButton);
        StackPane.setAlignment(passwordToggleButton, Pos.CENTER_RIGHT);

        return passwordPane;
    }
    
    private ToggleButton createPasswordToggleButton() {
        ToggleButton toggleButton = new ToggleButton();
        toggleButton.setStyle("-fx-background-color: transparent");
        toggleButton.setPrefSize(25, 25);

	    ImageView closedEyeIcon = new ImageView(IconUtils.closedEye);
	    closedEyeIcon.setSmooth(true);
	    closedEyeIcon.setFitHeight(20);
	    closedEyeIcon.setFitWidth(20);
      
	    ImageView openedEyeIcon = new ImageView(IconUtils.openedEye);
	    openedEyeIcon.setSmooth(true);
	    openedEyeIcon.setFitHeight(20);
	    openedEyeIcon.setFitWidth(20);

        toggleButton.setGraphic(closedEyeIcon);

        toggleButton.setOnAction(e -> {
            if (toggleButton.isSelected()) {
                toggleButton.setGraphic(openedEyeIcon);
            } else {
                toggleButton.setGraphic(closedEyeIcon);
            }
        });

        passwordField.visibleProperty().bind(toggleButton.selectedProperty());
        hashedPasswordField.visibleProperty().bind(toggleButton.selectedProperty().not());
        passwordField.textProperty().bindBidirectional(hashedPasswordField.textProperty());

        return toggleButton;
    }

    private void layoutComponents() {
        this.getChildren().addAll(labeledUsernameField, labeledPasswordField);
        this.setSpacing(20);
    }

}

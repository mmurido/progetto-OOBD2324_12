package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SignUpPage {
    
    public Scene getScene(Stage primaryStage) throws Exception {
        // Creazione di un'istanza di GUIHelper passando lo Stage principale
        GUIHelper guiHelper = new GUIHelper(primaryStage);
        
        // Creazione del pannello template
        StackPane centerPane = guiHelper.createTemplatePane(400, 500);
        
        // Creazione della scritta SIGNUP sopra il GridPane
        Label signUpLabel = new Label("SIGNUP");
        signUpLabel.setFont(Font.font("Product Sans", 35)); // Imposta il font in stampatello
        centerPane.getChildren().add(signUpLabel);
        StackPane.setAlignment(signUpLabel, Pos.TOP_CENTER); // Imposta l'allineamento al centro in alto
        StackPane.setMargin(signUpLabel, new Insets(30, 0, 0, 0)); // Imposta il margine sopra
        
        // Creazione dei controlli per raccogliere i dati
        TextField nameField = new TextField();
        nameField.setPromptText("Inserisci il tuo nome");
        TextField surnameField = new TextField();
        surnameField.setPromptText("Inserisci il tuo cognome");
        TextField emailField = new TextField();
        emailField.setPromptText("Inserisci la tua email");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Inserisci la password");
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Conferma la password");
        DatePicker birthDatePicker = new DatePicker();
        birthDatePicker.setPromptText("Seleziona la data di nascita");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Scegli un username");
        
        // Creazione dei label per i controlli
        Label nameLabel = new Label("Nome:");
        Label surnameLabel = new Label("Cognome:");
        Label emailLabel = new Label("Email:");
        Label passwordLabel = new Label("Password:");
        Label confirmPasswordLabel = new Label("Conferma Password:");
        Label birthDateLabel = new Label("Data di Nascita:");
        Label usernameLabel = new Label("Username:");
        
        // Creazione del layout BorderPane
        BorderPane root = new BorderPane();
        root.setCenter(centerPane);
        
        // Creazione del GridPane per i controlli
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(15));
        gridPane.setAlignment(Pos.CENTER); // Imposta l'allineamento del GridPane al centro
        
        // Aggiunta dei controlli al GridPane
        gridPane.addColumn(0, nameLabel, surnameLabel, emailLabel, passwordLabel, confirmPasswordLabel, birthDateLabel, usernameLabel);
        gridPane.addColumn(1, nameField, surnameField, emailField, passwordField, confirmPasswordField, birthDatePicker, usernameField);
        
        // Aggiunta del GridPane al centro del pannello
        centerPane.getChildren().add(gridPane);
        
        // Creazione dei bottoni di chiusura e minimizzazione
        Button closeButton = guiHelper.createCloseButton();
        Button minimizeButton = guiHelper.createMinimizeButton();
        
        // Creazione di un HBox per contenere i bottoni nella parte superiore destra
        HBox buttonBox = new HBox(minimizeButton, closeButton); // Qui ho invertito l'ordine dei bottoni
        buttonBox.setAlignment(Pos.TOP_RIGHT); // Posizionamento a destra
        buttonBox.setPadding(new Insets(10)); // Padding per allontanare dai bordi
        buttonBox.setStyle("-fx-background-color: white;"); // Impostazione dello sfondo bianco
        
        // Aggiunta dell'HBox al top del BorderPane
        root.setTop(buttonBox);
        
        // Creazione della scena
        Scene scene = new Scene(root, 400, 500); // Imposta le dimensioni della finestra come preferisci

        // Imposta la scena sullo stage
        primaryStage.setScene(scene);

        // Centra la finestra sullo schermo
        primaryStage.centerOnScreen();

        // Mostra la finestra
        primaryStage.show();

        return scene;
    }
}



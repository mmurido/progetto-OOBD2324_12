package gui;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SignUpPage {

    public Scene getScene(Stage primaryStage) {
        // Creazione di un'istanza di GUIHelper passando lo Stage principale
        GUIHelper guiHelper = new GUIHelper(primaryStage);

        // Creazione del pannello template
        Pane centerPane = new Pane();

        // Creazione della scritta SIGNUP sopra il GridPane
        Label signUpLabel = new Label("SIGNUP");
        signUpLabel.setFont(Font.font("Product Sans", 40)); // Imposta il font in stampatello
        signUpLabel.setStyle("-fx-text-fill: #18344a;"); // Imposta il colore del testo
        signUpLabel.setLayoutX(90);
        signUpLabel.setLayoutY(0);

        // Creazione dei controlli per raccogliere i dati
        TextField nameField = new TextField();
        nameField.setPromptText("Inserisci il tuo nome");
        nameField.setLayoutX(80);
        nameField.setLayoutY(70);

        TextField surnameField = new TextField();
        surnameField.setPromptText("Inserisci il tuo cognome");
        surnameField.setLayoutX(80);
        surnameField.setLayoutY(110);

        TextField emailField = new TextField();
        emailField.setPromptText("Inserisci la tua email");
        emailField.setLayoutX(80);
        emailField.setLayoutY(150);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Inserisci la password");
        passwordField.setLayoutX(80);
        passwordField.setLayoutY(190);

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Conferma la password");
        confirmPasswordField.setLayoutX(80);
        confirmPasswordField.setLayoutY(230);

        DatePicker birthDatePicker = new DatePicker();
        birthDatePicker.setPromptText("Seleziona la data di nascita");
        birthDatePicker.setLayoutX(80);
        birthDatePicker.setLayoutY(270);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Scegli un username");
        usernameField.setLayoutX(80);
        usernameField.setLayoutY(310);

        // Creazione dell'etichetta per il messaggio di registrazione effettuata
        Label successLabel = new Label("Registrazione effettuata");
        successLabel.setTextFill(Color.GREEN); // Imposta il colore del testo a verde
        successLabel.setFont(Font.font("Arial", 14));
        successLabel.setLayoutX(80);
        successLabel.setLayoutY(400);
        successLabel.setVisible(false); // Imposta l'etichetta non visibile all'inizio

        // Aggiunta dei controlli al pannello
        centerPane.getChildren().addAll(signUpLabel, nameField, surnameField, emailField, passwordField,
                confirmPasswordField, birthDatePicker, usernameField, successLabel);

        // Creazione del bottone "Registrati"
        Button signUpButton = new Button("Registrati");
        signUpButton.setStyle("-fx-background-color: #18344a; -fx-text-fill: white;");
        signUpButton.setPrefWidth(160); // Imposta la larghezza preferita del bottone
        signUpButton.setLayoutX(75);
        signUpButton.setLayoutY(370);

        // Aggiunta del gestore degli eventi al bottone "Registrati"
        signUpButton.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Recupera i dati inseriti dall'utente
                String name = nameField.getText();
                String surname = surnameField.getText();
                String email = emailField.getText();
                String password = passwordField.getText();
                String confirmPassword = confirmPasswordField.getText();
                String birthDate = birthDatePicker.getEditor().getText(); // Ottieni la data di nascita come stringa
                String username = usernameField.getText();

                // Verifica il formato dell'email usando un'espressione regolare
                String emailPattern = "^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$";
                if (!email.matches(emailPattern)) {
                    // Se l'email non corrisponde al pattern, mostra un messaggio di errore
                    emailField.setText("");
                    emailField.setPromptText("Email non valida");
                    emailField.setStyle("-fx-text-inner-color: red;");
                    return; // Esci dal metodo senza proseguire con la registrazione
                }

                // Rimetti il colore del testo a normale se l'email è nel formato corretto
                emailField.setStyle("-fx-text-inner-color: black;");

                // Verifica se la conferma della password corrisponde alla password
                if (!password.equals(confirmPassword)) {
                    // Se non corrispondono, mostra un messaggio di errore e cambia il colore del testo
                    confirmPasswordField.setText("");
                    confirmPasswordField.setPromptText("Le password non corrispondono");
                    confirmPasswordField.setStyle("-fx-text-inner-color: red;");
                    return; // Esci dal metodo senza proseguire con la registrazione
                }

                // Rimetti il colore del testo a normale se le password corrispondono
                confirmPasswordField.setStyle("-fx-text-inner-color: black;");

                // Verifica il formato dell'username usando un'espressione regolare
                String usernamePattern = "^[A-Za-z0-9_]{4,15}$";
                if (!username.matches(usernamePattern)) {
                    // Se l'username non corrisponde al pattern, mostra un messaggio di errore
                    usernameField.setText("");
                    usernameField.setPromptText("Formato username non valido");
                    usernameField.setStyle("-fx-text-inner-color: red;");
                    return; // Esci dal metodo senza proseguire con la registrazione
                }

                // Rimetti il colore del testo a normale se l'username è nel formato corretto
                usernameField.setStyle("-fx-text-inner-color: black;");

                // Inserisci qui la logica per la registrazione
                // Puoi eseguire la validazione dei campi qui e gestire la registrazione

                // Ad esempio, mostriamo un messaggio di registrazione avvenuta con successo
                successLabel.setVisible(true);
                System.out.println("Registrazione completata:");
                System.out.println("Nome: " + name);
                System.out.println("Cognome: " + surname);
                System.out.println("Email: " + email);
                System.out.println("Password: " + password);
                System.out.println("Data di nascita: " + birthDate);
                System.out.println("Username: " + username);
            }
        });

        // Aggiunta del bottone "Registrati" al pannello
        centerPane.getChildren().add(signUpButton);

        // Creazione dei bottoni di chiusura e minimizzazione
        Button closeButton = guiHelper.createCloseButton();
        Button minimizeButton = guiHelper.createMinimizeButton();

        // Creazione di un HBox per contenere i bottoni nella parte superiore destra
        HBox buttonBox = new HBox(minimizeButton, closeButton); // Qui ho invertito l'ordine dei bottoni
        buttonBox.setAlignment(Pos.TOP_RIGHT); // Posizionamento a destra
        buttonBox.setPadding(new Insets(10)); // Padding per allontanare dai bordi
        buttonBox.setStyle("-fx-background-color: white;"); // Impostazione dello sfondo bianco

        // Creazione del layout BorderPane
        BorderPane root = new BorderPane();
        root.setCenter(centerPane);
        root.setTop(buttonBox);

        // Creazione della scena
        Scene scene = new Scene(root, 300, 500); // Imposta le dimensioni della finestra come preferisci

        // Imposta la scena sullo stage
        primaryStage.setScene(scene);

        // Centra la finestra sullo schermo
        primaryStage.centerOnScreen();

        // Mostra la finestra
        primaryStage.show();

        return scene;
    }
}

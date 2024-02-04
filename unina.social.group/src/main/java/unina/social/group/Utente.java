package unina.social.group;

import java.time.*;

public class Utente {

    private String email;
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private LocalDate dataIscrizione;
    private String tipo;
    
    private static UtenteValidator validator = new UtenteValidator();

    // Costruttori
    public Utente(String email, String username, String password, String nome, String cognome, LocalDate dataNascita, LocalDate dataIscrizione, String tipo) {
    	validator.validateAllValues(email, username, password, nome, cognome, dataNascita, dataIscrizione, tipo);
    	
    	this.email = email;
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.dataIscrizione = dataIscrizione;
        this.tipo = tipo;
    }
    

	// Metodi getter    
    public String getEmail() {
        return email;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getCognome() {
        return cognome;
    }
    
    public LocalDate getDataNascita() {
        return dataNascita;
    }
    
    public LocalDate getDataIscrizione() {
        return dataIscrizione;
    }
    
    public String getTipo() {
        return tipo;
    }

    // Metodi setter    
    public void setEmail(String email) {
    	validator.validateEmail(email);
        this.email = email;
    }
    
    public void setUsername(String username) {
    	validator.validateUsername(username);
        this.username = username;
    }
    
    public void setPassword(String password) {
    	validator.validatePassword(password);
        this.password = password;
    }
    
    public void setNome(String nome) {
    	validator.validateNome(nome);
        this.nome = nome;
    }
    
    public void setCognome(String cognome) {
    	validator.validateCognome(cognome);
        this.cognome = cognome;
    }
    
    public void setDataNascita(LocalDate dataNascita) {
    	validator.validateDataNascita(dataNascita);
        this.dataNascita = dataNascita;
    }
  
    public void setDataIscrizione(LocalDate dataIscrizione) {
    	validator.validateDataIscrizione(dataIscrizione);
        this.dataIscrizione = dataIscrizione;
    }
    
    public void setTipo(String tipo) {
    	validator.validateTipo(tipo);
        this.tipo = tipo;    
    }
 
 }
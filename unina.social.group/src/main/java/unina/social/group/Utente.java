package unina.social.group;
import java.time.*;

public class Utente {
	
	private int idUtente;
    private String email;
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private LocalDate dataIscrizione;
    private String tipo;

    // Costruttori di java
    public Utente() { }
    
    public Utente(String email, String username, String password, String nome, String cognome, LocalDate dataNascita, LocalDate dataIscrizione, String tipo) {
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
    public int getIdUtente() {
        return idUtente;
    }
    
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
    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    
    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }
  
    public void setDataIscrizione(LocalDate dataIscrizione) {
        this.dataIscrizione = dataIscrizione;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;    
    }
 
 }
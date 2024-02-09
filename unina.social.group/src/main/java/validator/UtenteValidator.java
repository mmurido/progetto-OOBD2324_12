package validator;

import java.time.LocalDate;
import java.time.Period;

import org.apache.commons.validator.routines.EmailValidator;

public class UtenteValidator {
	
	public void validateAllValues(String email, String username, String password, String nome, String cognome, LocalDate dataNascita, LocalDate dataIscrizione, String tipo) {
		this.validateEmail(email);
		this.validateUsername(username);
		this.validatePassword(password);
		this.validateNome(nome);
		this.validateCognome(cognome);
		this.validateDataNascita(dataNascita);
		this.validateDataIscrizione(dataIscrizione);
		this.validateTipo(tipo);
	}
	
	public void validateEmail(String email) {
	    if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("L'indirizzo email non pu� essere nullo o vuoto.");
        }
	    
	    if (email.length() > 254) {
	    	throw new IllegalArgumentException("L'indirizzo email � troppo lungo. Il massimo numero di caratteri permessi � 254");
	    }

        if (!EmailValidator.getInstance().isValid(email)) {
            throw new IllegalArgumentException("L'indirizzo email non � valido.");
        }
	}
	
	public void validateUsername(String username) {
	    if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("L'username non pu� essere nullo o vuoto.");
        }
	    
	    if (username.length() > 15) {
	    	throw new IllegalArgumentException("L'username � troppo lungo. Il massimo numero di caratteri permessi � 15");
	    }
	    
		String USERNAME_PATTERN = "^[A-Za-z0-9._]{4,15}$";
		if (!username.matches(USERNAME_PATTERN)) {
            throw new IllegalArgumentException("L'username non � valido.");	
		}
	}
	
	public void validatePassword(String password) {
	    if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("La password non pu� essere nulla o vuota.");
        }
	    
	    if (password.length() > 20) {
	    	throw new IllegalArgumentException("La password � troppo lunga. Il massimo numero di caratteri permessi � 20");
	    }	
	}
	
	public void validateNome(String nome) {
		if (nome == null || nome.isEmpty()) {
			throw new IllegalArgumentException("Il nome non pu� essere nullo o vuoto.");
		}
		
		if (nome.length() > 30) {
			throw new IllegalArgumentException("Il nome � troppo lungo. Il massimo numero di caratteri permessi � 30");
		}
	}
	
	public void validateCognome(String cognome) {
		if (cognome == null || cognome.isEmpty()) {
			throw new IllegalArgumentException("Il cognome non pu� essere nullo o vuoto.");
		}
		
		if (cognome.length() > 30) {
			throw new IllegalArgumentException("Il cognome � troppo lungo. Il massimo numero di caratteri permessi � 30");
		}
	}
	
	public void validateDataNascita(LocalDate dataNascita) {
	    if (dataNascita == null) {
            throw new IllegalArgumentException("La data di nascita non pu� essere nulla.");
        }
	    
        LocalDate oggi = LocalDate.now();
        Period periodo = Period.between(dataNascita, oggi);      
        if (periodo.getYears() < 18) {
            throw new IllegalArgumentException("Data di nascita non valida: l'utente deve essere maggiorenne.");	
        }
	}
		
	public void validateDataIscrizione(LocalDate dataIscrizione) {
	    if (dataIscrizione == null) {
            throw new IllegalArgumentException("La data di iscrizione non pu� essere nulla.");
        }
	}
	
	public void validateTipo(String tipo) {
	    if (tipo == null || tipo.isEmpty()) {
            throw new IllegalArgumentException("Il tipo non pu� essere nullo o vuoto.");
        }
	    
		if (!(tipo.equalsIgnoreCase("semplice_utente")
			  || tipo.equalsIgnoreCase("owner_gruppo")
			  || tipo.equalsIgnoreCase("admin_gruppo")
			  || tipo.equalsIgnoreCase("owner_e_admin_gruppo"))) {
            
			throw new IllegalArgumentException("Il tipo dell'utente deve essere 'semplice_utente', 'owner_gruppo', 'admin_gruppo', oppure 'owner_e_admin_gruppo'.");
		}
	}

}

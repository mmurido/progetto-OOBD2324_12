package unina.social.group;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RichiestaValidator {

	public void validateAllValues(LocalDateTime dataOraInvio, String stato, LocalDate dataAccettazione, Utente mittente, Utente destinatario) {
		this.validateDataOraInvio(dataOraInvio);
		this.validateStato(stato);
		this.validateDataAccettazione(dataAccettazione, dataOraInvio, stato);
		this.validateUtente(mittente);
		this.validateUtente(destinatario);
	}
	
	public void validateDataOraInvio(LocalDateTime dataOraInvio) {
	    if (dataOraInvio == null) {
            throw new IllegalArgumentException("Data e ora di invio della richiesta non possono essere nulli.");
        }
	}
	
	public void validateStato(String stato) {
	    if (stato == null || stato.isEmpty()) {
            throw new IllegalArgumentException("Lo stato della richiesta non può essere nullo o vuoto.");
        }
	    
		if (!(stato.equalsIgnoreCase("in_attesa")
			  || stato.equalsIgnoreCase("accettata")
			  || stato.equalsIgnoreCase("rifiutata"))) {
            
			throw new IllegalArgumentException("Lo stato della richiesta deve essere 'in_attesa', 'accettata', oppure 'rifiutata'.");	
		}
	}
	
	public void validateDataAccettazione(LocalDate dataAccettazione, LocalDateTime dataOraInvio, String stato) {
		if (dataAccettazione == null) {
			if(stato.equalsIgnoreCase("accettata")) {
				throw new IllegalArgumentException("La data di accettazione della richiesta non può essere nulla se la richiesta è accettata.");
			}
		}
		
		if (dataAccettazione != null) {
			
			if (dataAccettazione.isBefore(dataOraInvio.toLocalDate())) {
				throw new IllegalArgumentException("La data di accettazione della richiesta non può essere precedente alla data di invio della richiesta stessa.");
			}
			
			if (!stato.equalsIgnoreCase("accettata")) {
				throw new IllegalArgumentException("La data di accettazione della richiesta deve essere nulla se la richiesta non è stata accettata.");
			}
		}
	}
	
	public void validateUtente(Utente utente) {
		if (utente == null) {
			throw new IllegalArgumentException("Il mittente/destinatario della richiesta non può essere nullo.");
		}
		
	}
}

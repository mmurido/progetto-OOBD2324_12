package unina.social.group;

import java.time.LocalDateTime;

public class MessaggioValidator {

	public void validateAllValues(String testo, LocalDateTime dataOraInvio, Utente mittente, Utente destinatario) {
		this.validateTesto(testo);
		this.validateDataOraInvio(dataOraInvio);
		this.validateUtente(mittente);
		this.validateUtente(destinatario);
	}
	
	public void validateTesto(String testo) {
		if (testo == null || testo.isEmpty()) {
			throw new IllegalArgumentException("Il testo del messaggio non può essere nullo o vuoto.");
		}
		
		if (testo.length() > 3000) {
			throw new IllegalArgumentException("Il testo del messaggio è troppo lungo: il numero massimo di caratteri permessi è 3000.");
		}
	}
	
	public void validateDataOraInvio(LocalDateTime dataOraInvio) {
		if (dataOraInvio == null) {
			throw new IllegalArgumentException("Data e ora di invio del messaggio non possono essere nulli.");
		}
	}
	
	public void validateUtente(Utente utente) {
		if (utente == null) {
			throw new IllegalArgumentException("Il mittente/destinatario della richiesta non può essere nullo.");
		}
	}
}

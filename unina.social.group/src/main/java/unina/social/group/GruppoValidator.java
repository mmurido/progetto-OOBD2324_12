package unina.social.group;

import java.time.LocalDateTime;

public class GruppoValidator {
	
	public void validateAllValues(String nome, LocalDateTime dataOraCreazione, String descrizione, Tema tema, Utente owner) {
		this.validateNome(nome);
		this.validateDataOraCreazione(dataOraCreazione);
		this.validateDescrizione(descrizione);
		this.validateTema(tema);
		this.validateOwner(owner);
	}

	public void validateNome(String nome) {
		if (nome == null || nome.isEmpty()) {
			throw new IllegalArgumentException("Il nome del gruppo non può essere nullo o vuoto.");
		}
		
		if (nome.length() > 30) {
			throw new IllegalArgumentException("Il nome del gruppo è troppo lungo: il massimo numero di caratteri permesso è 30.");
		}
	}

	public void validateDataOraCreazione(LocalDateTime dataOraCreazione) {
		if (dataOraCreazione == null) {
			throw new IllegalArgumentException("Data e ora di creazione del gruppo non possono essere nulli.");
		}
	}
	
	public void validateDescrizione(String descrizione) {
		if (descrizione.length() > 3000) {
			throw new IllegalArgumentException("La descrizione del gruppo è troppo lunga: il massimo numero di caratteri permesso è 3000.");
		}
	}
	
	public void validateTema(Tema tema) {
		if (tema == null) {
			throw new IllegalArgumentException("Il tema del gruppo non può essere nullo.");
		}
	}
	
	public void validateOwner(Utente owner) {
		if (owner == null) {
			throw new IllegalArgumentException("L'owner del gruppo non può essere nullo.");
		}
	}
}

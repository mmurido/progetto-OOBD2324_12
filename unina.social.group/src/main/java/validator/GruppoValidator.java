package validator;

import java.time.LocalDateTime;

import model.Tema;
import model.Utente;

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
			throw new IllegalArgumentException("Il nome del gruppo non pu� essere nullo o vuoto.");
		}
		
		if (nome.length() > 30) {
			throw new IllegalArgumentException("Il nome del gruppo � troppo lungo: il massimo numero di caratteri permesso � 30.");
		}
	}

	public void validateDataOraCreazione(LocalDateTime dataOraCreazione) {
		if (dataOraCreazione == null) {
			throw new IllegalArgumentException("Data e ora di creazione del gruppo non possono essere nulli.");
		}
	}
	
	public void validateDescrizione(String descrizione) {
		if (descrizione.length() > 3000) {
			throw new IllegalArgumentException("La descrizione del gruppo � troppo lunga: il massimo numero di caratteri permesso � 3000.");
		}
	}
	
	public void validateTema(Tema tema) {
		if (tema == null) {
			throw new IllegalArgumentException("Il tema del gruppo non pu� essere nullo.");
		}
	}
	
	public void validateOwner(Utente owner) {
		if (owner == null) {
			throw new IllegalArgumentException("L'owner del gruppo non pu� essere nullo.");
		}
	}
}

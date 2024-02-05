package validator;

public class TemaValidator {

	public void validateTema(String tema) {
		if (tema == null || tema.isEmpty()) {
			throw new IllegalArgumentException("Il tema non pu� essere nullo o vuoto.");
		}
		
		if (tema.length() > 20) {
			throw new IllegalArgumentException("Il tema � troppo lungo: il massimo numero di caratteri permesso � 20.");
		}
	}
}

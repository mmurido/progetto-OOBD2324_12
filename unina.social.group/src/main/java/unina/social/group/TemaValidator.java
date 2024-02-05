package unina.social.group;

public class TemaValidator {

	public void validateTema(String tema) {
		if (tema == null || tema.isEmpty()) {
			throw new IllegalArgumentException("Il tema non può essere nullo o vuoto.");
		}
		
		if (tema.length() > 20) {
			throw new IllegalArgumentException("Il tema è troppo lungo: il massimo numero di caratteri permesso è 20.");
		}
	}
}

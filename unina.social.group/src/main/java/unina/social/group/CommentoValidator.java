package unina.social.group;

import java.time.LocalDateTime;

public class CommentoValidator {

    public void validateAllValues(Post post, Utente autore, String testo, LocalDateTime dataOra) {
    	
        validatePost(post);
        validateAutore(autore);
        validateTesto(testo);
        validateDataOra(dataOra);
    }

    public void validatePost(Post post) {
        if (post == null) {
            throw new IllegalArgumentException("Il post non può essere nullo.");
        }
        
    }

    public void validateAutore(Utente autore) {
        if (autore == null) {
            throw new IllegalArgumentException("L'autore del commento non può essere nullo.");
        }
      
    }

    public void validateTesto(String testo) {
        if (testo == null || testo.isEmpty()) {
            throw new IllegalArgumentException("Il testo del commento non può essere nullo o vuoto.");
        }
        if (testo.length() > 800) {
            throw new IllegalArgumentException("Il testo del commento non può superare i 800 caratteri.");
        }
    }
         

    public void validateDataOra(LocalDateTime dataOra) {
        if (dataOra == null) {
            throw new IllegalArgumentException("La data e ora del commento non possono essere nulle.");
        }
        
}


}

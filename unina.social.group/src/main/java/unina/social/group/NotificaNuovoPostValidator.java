package unina.social.group;

import java.time.LocalDateTime;

public class NotificaValidator {

    public void validateAllValues(Post post, Utente destinatario, String testo, LocalDateTime dataOra) {
        validatePost(post);
        validateDestinatario(destinatario);
        validateTesto(testo);
        validateDataOra(dataOra);
    }

    public void validatePost(Post post) {
        if (post == null) {
            throw new IllegalArgumentException("Il post non può essere nullo.");
        }
        
    }

    public void validateDestinatario(Utente destinatario) {
        if (destinatario == null) {
            throw new IllegalArgumentException("Il destinatario della notifica non può essere nullo.");
        }
        
    }

    public void validateTesto(String testo) {
        if (testo == null || testo.isEmpty()) {
            throw new IllegalArgumentException("Il testo della notifica non può essere nullo o vuoto.");
        }
        if (testo.length() > 100) {
            throw new IllegalArgumentException("Il testo della notifica non può superare i 100 caratteri.");
        }
    }

    public void validateDataOra(LocalDateTime dataOra) {
        if (dataOra == null) {
            throw new IllegalArgumentException("La data e ora della notifica non possono essere nulle.");
        }
       
    }
}

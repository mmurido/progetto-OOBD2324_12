package model;

import java.time.LocalDateTime;
import java.util.List;

public class PostValidator {

    public void validateAllValues(Gruppo gruppo, Utente autore, LocalDateTime dataOraPubblicazione, String testo, List<String> urlFoto, String tipo) {
        validateGruppo(gruppo);
        validateAutore(autore);
        validateDataOraPubblicazione(dataOraPubblicazione);
        validateTipo(tipo);

        if ("post_con_testo".equalsIgnoreCase(tipo)) {
            if (testo == null) {
                throw new IllegalArgumentException("Il testo non può essere nullo per un post di tipo 'post_con_testo'.");
            }
        } else if ("post_con_foto".equalsIgnoreCase(tipo)) {
            if (urlFoto == null || urlFoto.isEmpty()) {
                throw new IllegalArgumentException("È richiesta almeno una URL di foto per un post di tipo 'post_con_foto'.");
            }
        } else if ("post_con_testo_e_foto".equalsIgnoreCase(tipo)) {
            if (testo == null) {
                throw new IllegalArgumentException("Il testo non può essere nullo per un post di tipo 'post_con_testo_e_foto'.");
            }
            if (urlFoto == null || urlFoto.isEmpty()) {
                throw new IllegalArgumentException("È richiesta almeno una URL di foto per un post di tipo 'post_con_testo_e_foto'.");
            }
        }
    }

    public void validateGruppo(Gruppo gruppo) {
        if (gruppo == null) {
            throw new IllegalArgumentException("Il gruppo non può essere nullo.");
        }
    }

    public void validateAutore(Utente autore) {
        if (autore == null) {
            throw new IllegalArgumentException("L'autore del post non può essere nullo.");
        }
    }

    public void validateDataOraPubblicazione(LocalDateTime dataOraPubblicazione) {
        if (dataOraPubblicazione == null) {
            throw new IllegalArgumentException("La data e ora di pubblicazione del post non possono essere nulle.");
        }
    }

    public void validateTipo(String tipo) {
        if (tipo == null || tipo.isEmpty()) {
            throw new IllegalArgumentException("Il tipo del post non può essere nullo o vuoto.");
        }
        if (!"post_con_testo".equalsIgnoreCase(tipo) && !"post_con_foto".equalsIgnoreCase(tipo) && !"post_con_testo_e_foto".equalsIgnoreCase(tipo)) {
            throw new IllegalArgumentException("Il tipo del post non è valido.");
        }
    }
}

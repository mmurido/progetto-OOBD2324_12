package model;

public class LikeValidator {

    public void validateAllValues(Post post, Utente utente) {
        validatePost(post);
        validateUtente(utente);
    }

    public void validatePost(Post post) {
        if (post == null) {
            throw new IllegalArgumentException("Il post non può essere nullo.");
        }
       
    }

    public void validateUtente(Utente utente) {
        if (utente == null) {
            throw new IllegalArgumentException("L'utente non può essere nullo.");
        }
        
    }
}

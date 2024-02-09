package model;

public class MembroGruppoValidator {

    public void validateAllValues(Gruppo gruppo, Utente partecipante) {
        validateGruppo(gruppo);
        validatePartecipante(partecipante);
    }

    public void validateGruppo(Gruppo gruppo) {
        if (gruppo == null) {
            throw new IllegalArgumentException("Il gruppo non può essere nullo.");
        }
       
    }

    public void validatePartecipante(Utente partecipante) {
        if (partecipante == null) {
            throw new IllegalArgumentException("Il partecipante non può essere nullo.");
        }
        
    }
}

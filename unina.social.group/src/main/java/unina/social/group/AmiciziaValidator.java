package unina.social.group;

public class AmiciziaValidator {

    public void validateAllValues(Utente amico1, Utente amico2) {
        validateAmici(amico1, amico2);
    }

    public void validateAmici(Utente amico1, Utente amico2) {
        if (amico1 == null || amico2 == null) {
            throw new IllegalArgumentException("Gli utenti coinvolti nell'amicizia non possono essere nulli.");
        }
        if (amico1.equals(amico2)) {
            throw new IllegalArgumentException("Gli utenti coinvolti nell'amicizia non possono essere la stessa persona.");
        }
    }
}

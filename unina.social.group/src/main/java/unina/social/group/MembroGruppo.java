package unina.social.group;

public class MembroGruppo {
    private Gruppo gruppo;
    private Utente partecipante;

    public MembroGruppo(Gruppo gruppo, Utente partecipante) {
        this.gruppo = gruppo;
        this.partecipante = partecipante;
    }

    public Gruppo getGruppo() {
        return gruppo;
    }

    public void setGruppo(Gruppo gruppo) {
        this.gruppo = gruppo;
    }

    public Utente getPartecipante() {
        return partecipante;
    }

    public void setPartecipante(Utente partecipante) {
        this.partecipante = partecipante;
    }
}

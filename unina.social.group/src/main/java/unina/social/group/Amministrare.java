package unina.social.group;
import java.util.Objects;

public class Amministrare {

    private Gruppo gruppo;
    private Utente admin;

    public Amministrare(Gruppo gruppo, Utente admin) {
        this.gruppo = gruppo;
        this.admin = admin;
    }

    public Gruppo getGruppo() {
        return gruppo;
    }

    public void setGruppo(Gruppo gruppo) {
        this.gruppo = gruppo;
    }

    public Utente getUtente() {
        return admin;
    }

    public void setUtente(Utente admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Amministrare{" +
                "gruppo=" + gruppo +
                ", admin=" + admin +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Amministrare that = (Amministrare) obj;
        return Objects.equals(gruppo, that.gruppo) &&
                Objects.equals(admin, that.admin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gruppo, admin);
    }
}

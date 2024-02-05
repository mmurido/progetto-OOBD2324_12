package unina.social.group;

import java.time.LocalDateTime;
import java.util.Objects;

public class Commento {

    private String idPost;
    private String idUtente;
    private String testo;
    private LocalDateTime dataOra;

    private static CommentoValidator validator = new CommentoValidator();

    public Commento(String idPost, String idUtente, String testo, LocalDateTime dataOra) {
        validator.validateAllValues(idPost, idUtente, testo, dataOra);

        this.idPost = idPost;
        this.idUtente = idUtente;
        this.testo = testo;
        this.dataOra = dataOra;
    }

    public String getIdPost() {
        return idPost;
    }

    public String getIdUtente() {
        return idUtente;
    }

    public String getTesto() {
        return testo;
    }

    public LocalDateTime getDataOra() {
        return dataOra;
    }

    public void setIdPost(String idPost) {
        validator.validateIdPost(idPost);
        this.idPost = idPost;
    }

    public void setIdUtente(String idUtente) {
        validator.validateIdUtente(idUtente);
        this.idUtente = idUtente;
    }

    public void setTesto(String testo) {
        validator.validateTesto(testo);
        this.testo = testo;
    }

    public void setDataOra(LocalDateTime dataOra) {
        validator.validateDataOra(dataOra);
        this.dataOra = dataOra;
    }

    @Override
    public String toString() {
        return "Commento{" +
                "idPost='" + idPost + '\'' +
                ", idUtente='" + idUtente + '\'' +
                ", testo='" + testo + '\'' +
                ", dataOra=" + dataOra +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Commento commento = (Commento) obj;
        return Objects.equals(idPost, commento.idPost) &&
                Objects.equals(idUtente, commento.idUtente) &&
                Objects.equals(testo, commento.testo) &&
                Objects.equals(dataOra, commento.dataOra);
    }
}


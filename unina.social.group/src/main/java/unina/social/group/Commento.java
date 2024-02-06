package unina.social.group;

import java.time.LocalDateTime;
import java.util.Objects;

public class Commento {

    private Post post;
    private Utente autore;
    private String testo;
    private LocalDateTime dataOra;

    private static CommentoValidator validator = new CommentoValidator();

    public Commento(String Post, Utente autore, String testo, LocalDateTime dataOra) {
        validator.validateAllValues(Post, autore, testo, dataOra);

        this.post = post;
        this.autore = autore;
        this.testo = testo;
        this.dataOra = dataOra;
    }

    public Post getPost() {
        return post;
    }

    public Utente getAutore() {
        return autore;
    }

    public String getTesto() {
        return testo;
    }

    public LocalDateTime getDataOra() {
        return dataOra;
    }

    public void setPost(Post post) {
        validator.validatePost(post);
        this.post = post;
    }

    public void setAutore(Utente autore) {
        validator.validateAutore(autore);
        this.autore = autore;
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
                "Post='" + post + '\'' +
                ", autore='" + autore.getUsername() + '\'' +
                ", testo='" + testo + '\'' +
                ", dataOra=" + dataOra +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Commento commento = (Commento) obj;
        return Objects.equals(post, commento.post) &&
                Objects.equals(autore, commento.autore) &&
                Objects.equals(testo, commento.testo) &&
                Objects.equals(dataOra, commento.dataOra);
    }
}


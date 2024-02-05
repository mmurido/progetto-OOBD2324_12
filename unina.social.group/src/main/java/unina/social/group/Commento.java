package unina.social.group;

import java.time.LocalDateTime;
import java.util.Objects;

public class Commento {

    private String Post;
    private String destinatario;
    private String testo;
    private LocalDateTime dataOra;

    private static CommentoValidator validator = new CommentoValidator();

    public Commento(String Post, String destinatario, String testo, LocalDateTime dataOra) {
        validator.validateAllValues(Post, destinatario, testo, dataOra);

        this.Post = Post;
        this.destinatario = destinatario;
        this.testo = testo;
        this.dataOra = dataOra;
    }

    public String getPost() {
        return Post;
    }

    public String getdestinatario() {
        return destinatario;
    }

    public String getTesto() {
        return testo;
    }

    public LocalDateTime getDataOra() {
        return dataOra;
    }

    public void setPost(String Post) {
        validator.validatePost(Post);
        this.Post = Post;
    }

    public void setIdUtente(String destinatario) {
        validator.validateIdUtente(destinatario);
        this.destinatario = destinatario;
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
                "Post='" + Post + '\'' +
                ", destinatario='" + destinatario + '\'' +
                ", testo='" + testo + '\'' +
                ", dataOra=" + dataOra +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Commento commento = (Commento) obj;
        return Objects.equals(Post, commento.Post) &&
                Objects.equals(destinatario, commento.destinatario) &&
                Objects.equals(testo, commento.testo) &&
                Objects.equals(dataOra, commento.dataOra);
    }
}


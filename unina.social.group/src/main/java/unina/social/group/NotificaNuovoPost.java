package unina.social.group;

import java.time.LocalDateTime;
import java.util.Objects;

public class NotificaNuovoPost {

	private Post post;
    private String destinatario;
    private String testo;
    private LocalDateTime dataOra;

    private static NotificaNuovoPostValidator validator = new NotificaNuovoPostValidator();
//Costruttori
    public NotificaNuovoPost(String destinatario, String testo, LocalDateTime dataOra) {
        validator.validateAllValues( destinatario, testo, dataOra);
    public NotificaNuovoPost()

        
        this.destinatario = destinatario;
        this.testo = testo;
        this.dataOra = dataOra;
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
        validator.validatedestinatario();destinatario
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
        return "NotificaNuovoPost{" +
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
        NotificaNuovoPost that = (NotificaNuovoPost) obj;
        return Objects.equals(Post, that.Post) &&
                Objects.equals(destinatario, that.destinatario) &&
                Objects.equals(testo, that.testo) &&
                Objects.equals(dataOra, that.dataOra);
    }
}

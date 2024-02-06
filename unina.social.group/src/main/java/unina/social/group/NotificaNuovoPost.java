package unina.social.group;

import java.time.LocalDateTime;
import java.util.Objects;

public class NotificaNuovoPost {

	private Post post;
    private Utente destinatario;
    private String testo;
    private LocalDateTime dataOra;

    private static NotificaNuovoPostValidator validator = new NotificaNuovoPostValidator();
//Costruttori
    public NotificaNuovoPost(Utente destinatario, String testo, LocalDateTime dataOra, Post post) {
        validator.validateAllValues( destinatario, testo, dataOra);
    

        this.post = post;
        this.destinatario = destinatario;
        this.testo = testo;
        this.dataOra = dataOra;
    }
    public Post getPost() {
    	return post;
    }

    public Utente getdestinatario() {
        return destinatario;
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

    public void setIdUtente(Utente destinatario) {
        validator.validateDestinatario(); 
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
                "Post='" +pPost + '\'' +
                ", destinatario='" + destinatario.getUsername() + '\'' +
                ", testo='" + testo + '\'' +
                ", dataOra=" + dataOra +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NotificaNuovoPost that = (NotificaNuovoPost) obj;
        return Objects.equals(post, that.post) &&
                Objects.equals(destinatario, that.destinatario) &&
                Objects.equals(testo, that.testo) &&
                Objects.equals(dataOra, that.dataOra);
    }
}

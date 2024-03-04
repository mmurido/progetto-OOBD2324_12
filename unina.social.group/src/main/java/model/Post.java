package model;

import java.time.LocalDateTime;
import java.util.List;

public class Post {
	private String idPost;
    private Gruppo gruppo;
    private Utente autore;
    private LocalDateTime dataOraPubblicazione;
    private String testo;
    private List<String> urlFoto;
    private String tipo;

    public Post(String idPost, Gruppo gruppo, Utente autore, LocalDateTime dataOraPubblicazione, String testo, List<String> urlFoto, String tipo) {
        this.idPost = idPost;
    	this.gruppo = gruppo;
        this.autore = autore;
        this.dataOraPubblicazione = dataOraPubblicazione;
        this.testo = testo;
        this.urlFoto = urlFoto;
        this.tipo = tipo;
    }

    public String getIdPost() {
        return idPost;
    }
    
    public Gruppo getGruppo() {
        return gruppo;
    }

    public void setGruppo(Gruppo gruppo) {
        this.gruppo = gruppo;
    }

    public Utente getAutore() {
        return autore;
    }

    public void setAutore(Utente autore) {
        this.autore = autore;
    }

    public LocalDateTime getDataOraPubblicazione() {
        return dataOraPubblicazione;
    }

    public void setDataOraPubblicazione(LocalDateTime dataOraPubblicazione) {
        this.dataOraPubblicazione = dataOraPubblicazione;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public List<String> getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(List<String> urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }

}

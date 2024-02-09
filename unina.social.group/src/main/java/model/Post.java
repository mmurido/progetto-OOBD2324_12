package model;

import java.time.LocalDateTime;
import java.util.List;

public class Post {
    private Gruppo gruppo;
    private Utente autore;
    private LocalDateTime dataOraPubblicazione;
    private String testo;
    private List<String> urlFoto;
    private TipoPost tipo;

    public Post(Gruppo gruppo, Utente autore, LocalDateTime dataOraPubblicazione, String testo, List<String> urlFoto, TipoPost tipo) {
        this.gruppo = gruppo;
        this.autore = autore;
        this.dataOraPubblicazione = dataOraPubblicazione;
        this.testo = testo;
        this.urlFoto = urlFoto;
        this.tipo = tipo;
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

    public TipoPost getTipo() {
        return tipo;
    }

    public void setTipo(TipoPost tipo) {
        this.tipo = tipo;
    }
    public enum TipoPost {
        testo,
        foto,
        testo_e_foto,
        post_con_testo_e_foto
    }

}

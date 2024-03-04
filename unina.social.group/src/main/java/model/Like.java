package model;

import java.time.LocalDate;

public class Like {
    private Post post;
    private Utente utente;
    private LocalDate data;

    public Like(Post post, Utente utente) {
        this.post = post;
        this.utente = utente;
    }

    public Post getPost() {
        return post;
    }

    public Utente getUtente() {
        return utente;
    }
    
    public LocalDate getData() {
    	return data;
    }

    public void setPost(Post post) {
        this.post = post;
    }
    
    public void setUtente(Utente utente) {
        this.utente = utente;
    }
    
    public void setLike(LocalDate data) {
    	this.data = data;
    }
}

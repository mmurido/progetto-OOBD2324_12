package unina.social.group;

public class Like {
    private Post post;
    private Utente utente;

    public Like(Post post, Utente utente) {
        this.post = post;
        this.utente = utente;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}

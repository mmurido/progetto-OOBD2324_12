package unina.social.group;

import java.util.Objects;

public class Profilo {

	private String urlFotoProfilo;
	private String indirizzo;
	private String descrizione;
	private String genere;
	private Utente utente;
	
	private static ProfiloValidator validator = new ProfiloValidator();
	
	public Profilo(Utente utente) {
		this.utente = utente;
	}
	

	public Profilo(String urlFotoProfilo, String indirizzo, String descrizione, String genere, Utente utente) {
		validator.validateAllValues(urlFotoProfilo, indirizzo, descrizione, genere);
		
		this.urlFotoProfilo = urlFotoProfilo;
		this.indirizzo = indirizzo;
		this.descrizione = descrizione;
		this.genere = genere;
		this.utente = utente;
	}


	public String getUrlFotoProfilo() {
		return urlFotoProfilo;
	}
	
	
	public String getIndirizzo() {
		return indirizzo;
	}
	
	
	public String getDescrizione() {
		return descrizione;
	}
	
	
	public String getGenere() {
		return genere;
	}
	
	
	public Utente getUtente() {
		return utente;
	}
	
	
	public void setUrlFotoProfilo(String urlFotoProfilo) {
		validator.validateUrlFotoProfilo(urlFotoProfilo);
		this.urlFotoProfilo = urlFotoProfilo;
	}
	
	
	public void setIndirizzo(String indirizzo) {
		validator.validateIndirizzo(indirizzo);
		this.indirizzo = indirizzo;
	}
	
	
	public void setDescrizione(String descrizione) {
		validator.validateDescrizione(descrizione);
		this.descrizione = descrizione;
	}
	
	
	public void setGenere(String genere) {
		validator.validateGenere(genere);
		this.genere = genere;
	}
	
	
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	
	@Override
	public String toString() {
		return "Profilo [urlFotoProfilo=" + urlFotoProfilo + 
			   ", indirizzo=" + indirizzo + 
			   ", descrizione=" + descrizione +
			   ", genere=" + genere + 
			   ", utente=" + utente.getUsername() + 
			   "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profilo other = (Profilo) obj;
		return Objects.equals(descrizione, other.descrizione) 
			   && Objects.equals(genere, other.genere)
			   && Objects.equals(indirizzo, other.indirizzo) 
			   && Objects.equals(urlFotoProfilo, other.urlFotoProfilo)
			   && Objects.equals(utente.getUsername(), other.utente.getUsername());
	}
	
	
}

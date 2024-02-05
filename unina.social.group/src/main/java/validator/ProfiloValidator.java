package validator;

import org.apache.commons.validator.routines.UrlValidator;

public class ProfiloValidator {
	
	public void validateAllValues(String urlFotoProfilo, String indirizzo, String descrizione, String genere) {
		this.validateUrlFotoProfilo(urlFotoProfilo);
		this.validateIndirizzo(indirizzo);
		this.validateDescrizione(descrizione);
		this.validateGenere(genere);
	}
	
	
	public void validateUrlFotoProfilo(String urlFotoProfilo) {
		if (!UrlValidator.getInstance().isValid(urlFotoProfilo)) {
			throw new IllegalArgumentException("L'url della foto profilo non � valido.");
		}
	}
	
	
	public void validateIndirizzo(String indirizzo) {
		//incompleto
	}
	
	
	public void validateDescrizione(String descrizione) {
		if (descrizione.length() > 3000) {
			throw new IllegalArgumentException("La desrizione � troppo lunga: il numero massimo di caratteri permessi � 3000.");
		}
	}
	
	
	public void validateGenere(String genere) {
		if (!(genere.equalsIgnoreCase("femmina")
			  || genere.equalsIgnoreCase("maschio")
			  || genere.equalsIgnoreCase("altro"))) {
			throw new IllegalArgumentException("Il genere pu� essere 'femmina', 'maschio' oppure 'altro'.");
		}
	}
}

package unina.social.group;

import java.time.LocalDateTime;
import java.util.Objects;

public class Messaggio {
	
	private String testo;
	private LocalDateTime dataOraInvio;
	private Utente mittente;
	private Utente destinatario;
	
	private static MessaggioValidator validator = new MessaggioValidator();

	public Messaggio(String testo, LocalDateTime dataOraInvio, Utente mittente, Utente destinatario) {
		validator.validateAllValues(testo, dataOraInvio, mittente, destinatario);
		
		this.testo = testo;
		this.dataOraInvio = dataOraInvio;
		this.mittente = mittente;
		this.destinatario = destinatario;
	}
		
	public String getTesto() {
		return testo;
	}
	
	public LocalDateTime getDataOraInvio() {
		return dataOraInvio;
	}	

	public Utente getMittente() {
		return mittente;
	}
	

	public Utente getDestinaratio() {
		return destinatario;
	}	

	public void setTesto(String testo) {
		validator.validateTesto(testo);
		this.testo = testo;
	}	

	public void setDataOraInvio(LocalDateTime dataOraInvio) {
		validator.validateDataOraInvio(dataOraInvio);
		this.dataOraInvio = dataOraInvio;
	}	

	public void setMittente(Utente mittente) {
		validator.validateUtente(mittente);
		this.mittente = mittente;
	}	

	public void setDestinaratio(Utente destinaratio) {
		validator.validateUtente(destinaratio);
		this.destinatario = destinaratio;
	}

	@Override
	public String toString() {
		return "Messaggio [testo=" + testo + 
			   ", dataOraInvio=" + dataOraInvio + 
			   ", mittente=" + mittente.getUsername() + 
			   ", destinaratio=" + destinatario.getUsername() + 
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
		Messaggio other = (Messaggio) obj;
		return Objects.equals(dataOraInvio, other.dataOraInvio) 
			   && Objects.equals(destinatario, other.destinatario)
			   && Objects.equals(mittente, other.mittente) 
			   && Objects.equals(testo, other.testo);
	}	
	
}

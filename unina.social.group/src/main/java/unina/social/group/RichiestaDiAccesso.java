package unina.social.group;

import java.time.LocalDateTime;
import java.util.Objects;
import java.time.LocalDate;

public class RichiestaDiAccesso {

	private LocalDateTime dataOraInvio;
	private String stato;
	private LocalDate dataAccettazione;
	private Utente mittente;
	private Utente destinatario;
	
	private static RichiestaValidator validator = new RichiestaValidator();
	
	public RichiestaDiAccesso(LocalDateTime dataOraInvio, String stato, LocalDate dataAccettazione, Utente mittente, Utente destinatario) {
		validator.validateAllValues(dataOraInvio, stato, dataAccettazione, mittente, destinatario);
		
		this.dataOraInvio = dataOraInvio;
		this.stato = stato;
		this.dataAccettazione = dataAccettazione;
		this.mittente = mittente;
		this.destinatario = destinatario;
	}

	public LocalDateTime getDataOraInvio() {
		return dataOraInvio;
	}
	
	public String getStato() {
		return stato;
	}
	
	public LocalDate getDataAccettazione() {
		return dataAccettazione;
	}
	
	public Utente getMittente() {
		return mittente;
	}
	
	public Utente getDestinatario() {
		return destinatario;
	}
	
	public void setDataOraInvio(LocalDateTime dataOraInvio) {
		validator.validateDataOraInvio(dataOraInvio);
		this.dataOraInvio = dataOraInvio;
	}
	
	public void setStato(String stato) {
		validator.validateStato(stato);
		this.stato = stato;
		
		if (stato.equalsIgnoreCase("accettata") && this.dataAccettazione == null) {
			this.setDataAccettazione(LocalDate.now());
		}
	}
	
	public void setDataAccettazione(LocalDate dataAccettazione) {
		validator.validateDataAccettazione(dataAccettazione, this.dataOraInvio, this.stato);
		this.dataAccettazione = dataAccettazione;
	}
	
	public void setMittente(Utente mittente) {
		this.mittente = mittente;
	}

	public void setDestinatario(Utente destinatario) {
		this.destinatario = destinatario;
	}

	@Override
	public String toString() {
		return "RichiestaDiAccesso [dataOraInvio=" + dataOraInvio + 
			   ", stato=" + stato + 
			   ", dataAccettazione=" + dataAccettazione + 
			   ", mittente=" + mittente.getUsername() + 
			   ", destinatario=" + destinatario.getUsername() + 
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
		RichiestaDiAccesso other = (RichiestaDiAccesso) obj;
		return Objects.equals(dataAccettazione, other.dataAccettazione)
			   && Objects.equals(dataOraInvio, other.dataOraInvio) 
			   && Objects.equals(destinatario, other.destinatario)
			   && Objects.equals(mittente, other.mittente) 
			   && Objects.equals(stato, other.stato);
	}

}
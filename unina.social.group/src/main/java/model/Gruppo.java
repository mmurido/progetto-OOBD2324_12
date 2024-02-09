package model;

import java.time.LocalDateTime;
import java.util.Objects;

import validator.GruppoValidator;

public class Gruppo {

	private String idGruppo;
	private String nome;
	private LocalDateTime dataOraCreazione;
	private String descrizione;
	private Tema tema;
	private Utente owner;
	
	private static GruppoValidator validator = new GruppoValidator();

	public Gruppo(String nome, LocalDateTime dataOraCreazione, String descrizione, Tema tema, Utente owner) {
		validator.validateAllValues(nome, dataOraCreazione, descrizione, tema, owner);
		
		this.nome = nome;
		this.dataOraCreazione = dataOraCreazione;
		this.descrizione = descrizione;
		this.tema = tema;
		this.owner = owner;
	}

	public String getIdGruppo() {
		return idGruppo;
	}
	
	public String getNome() {
		return nome;
	}

	public LocalDateTime getDataOraCreazione() {
		return dataOraCreazione;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public Tema getTema() {
		return tema;
	}

	public Utente getOwner() {
		return owner;
	}

	public void setIdGruppo(String idGruppo) {
		this.idGruppo = idGruppo;
	}
	
	public void setNome(String nome) {
		validator.validateNome(nome);
		this.nome = nome;
	}

	public void setDataOraCreazione(LocalDateTime dataOraCreazione) {
		validator.validateDataOraCreazione(dataOraCreazione);
		this.dataOraCreazione = dataOraCreazione;
	}

	public void setDescrizione(String descrizione) {
		validator.validateDescrizione(descrizione);
		this.descrizione = descrizione;
	}

	public void setTema(Tema tema) {
		validator.validateTema(tema);
		this.tema = tema;
	}

	public void setOwner(Utente owner) {
		validator.validateOwner(owner);
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Gruppo [idGruppo =" + idGruppo +
			   ", nome=" + nome + 
			   ", dataOraCreazione=" + dataOraCreazione + 
			   ", descrizione=" + descrizione + 
			   ", tema=" + tema.getTema() + 
			   ", owner=" + owner.getUsername() + 
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
		Gruppo other = (Gruppo) obj;
		return Objects.equals(idGruppo, other.idGruppo)
			   && Objects.equals(dataOraCreazione, other.dataOraCreazione)
			   && Objects.equals(descrizione, other.descrizione) 
			   && Objects.equals(nome, other.nome)
			   && Objects.equals(owner, other.owner) 
			   && Objects.equals(tema, other.tema);
	}
	
}

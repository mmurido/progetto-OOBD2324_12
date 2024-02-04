package unina.social.group;
import java.time.*;

public class Starter {

	public static void main(String[] args) {
		
		Utente utente = new Utente("teresadee002@gmail.com", "aaaa", "password", "teresa", "de santis", LocalDate.of(2002, 11, 28), LocalDate.of(2024, 1, 1), "semplice_utente");
		System.out.println(utente.toString());
		
		
	}

}

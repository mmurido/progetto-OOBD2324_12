package unina.social.group;

import java.util.Objects;

public class Tema {

	private String tema;
	
	private static TemaValidator validator = new TemaValidator();

	public Tema(String tema) {
		validator.validateTema(tema);
		this.tema = tema;
	}
	
	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		validator.validateTema(tema);
		this.tema = tema;
	}

	@Override
	public String toString() {
		return "Tema [tema=" + tema + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tema other = (Tema) obj;
		return Objects.equals(tema, other.tema);
	}
	
	
}

package unina.social.group;

public class AmministrareValidator {

    public void validateAmministrazione(Amministrare amministrare) {
        if (amministrare == null) {
            throw new IllegalArgumentException("Amministrare non può essere nullo.");
        }

        validateGruppo(amministrare.getGruppo());
        validateAdmin(amministrare.getAdmin());
    }

    public void validateGruppo(Gruppo gruppo) {
        if (gruppo == null) {
            throw new IllegalArgumentException("Il gruppo non può essere nullo.");
        }
        
    }

    public void validateAdmin(Admin admin) {
        if (admin == null) {
            throw new IllegalArgumentException("L'admin non può essere nullo.");
        }
       
}

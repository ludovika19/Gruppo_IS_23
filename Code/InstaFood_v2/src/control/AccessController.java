package control;
import DTO.UtenteDTO;
import entity.*;

public class AccessController {
    private static AccessController instance;
    private Utente utenteCorrente;
    private static Sistema sistema;
    private Amministratore amministratore;

    public AccessController() {
        try {
            sistema = Sistema.getInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static AccessController getInstance() {
        if(instance==null) {
            try {
                instance=new AccessController();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public Utente getUtenteCorrente() {
        return utenteCorrente;
    }

    public void setUtenteCorrente(Utente utenteCorrente) {
        this.utenteCorrente = utenteCorrente;
    }

    public void autenticaUtente(String email, String password){
        Utente utente=null;

        utente = sistema.autenticaUtente(email, password);
        if(utente ==null) {
            this.utenteCorrente=null;
            this.amministratore = null;
        }else{
            this.amministratore = null;
            this.utenteCorrente = utente;
        }

    }

    public void autenticaAmministratore(String email, String password){
        int idAmministratore = sistema.autenticaAmministratore(email, password);
        if(idAmministratore == -1) {
            this.amministratore = null;
            this.utenteCorrente = null;
        } else {
            this.utenteCorrente = null;
            this.amministratore = new Amministratore(idAmministratore, email, password);
        }
        
    }

    public boolean registraUtente(UtenteDTO utenteDTO) {
        if (utenteDTO != null) {
            return AccessController.sistema.registraUtente(utenteDTO);
        }
        return false; // Registrazione fallita
    }
    


}

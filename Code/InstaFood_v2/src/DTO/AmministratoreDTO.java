package DTO;

public class AmministratoreDTO {
    private int idAmministratore;
    private String username;
    private String ruolo; // campo aggiuntivo per specificare il tipo di amministratore
    private boolean attivo; // campo per indicare se l'amministratore è attivo

    public AmministratoreDTO() {
        super();
    }

    public AmministratoreDTO(int idAmministratore, String username) {
        this.idAmministratore = idAmministratore;
        this.username = username;
        this.attivo = true; // di default attivo
    }

    public AmministratoreDTO(int idAmministratore, String username, String ruolo) {
        this.idAmministratore = idAmministratore;
        this.username = username;
        this.ruolo = ruolo;
        this.attivo = true;
    }

    public int getIdAmministratore() {
        return idAmministratore;
    }

    public void setIdAmministratore(int idAmministratore) {
        this.idAmministratore = idAmministratore;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }
}

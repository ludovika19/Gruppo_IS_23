package DTO;

public class IngredienteRicettaDTO {
    private int ricettaId;
    private int raccoltaId;
    private int utenteId;
    private int ingredienteId;
    private String nomeIngrediente;
    private int quantita;
    private String unitaMisura; // campo aggiuntivo per specificare l'unità di misura

    public IngredienteRicettaDTO() {
        super();
    }

    public IngredienteRicettaDTO(int ricettaId, int ingredienteId, int quantita) {
        this.ricettaId = ricettaId;
        this.ingredienteId = ingredienteId;
        this.quantita = quantita;
    }

    public IngredienteRicettaDTO(int ricettaId, int raccoltaId, int utenteId, 
                                 int ingredienteId, String nomeIngrediente, int quantita) {
        this.ricettaId = ricettaId;
        this.raccoltaId = raccoltaId;
        this.utenteId = utenteId;
        this.ingredienteId = ingredienteId;
        this.nomeIngrediente = nomeIngrediente;
        this.quantita = quantita;
    }

    public int getRicettaId() {
        return ricettaId;
    }

    public void setRicettaId(int ricettaId) {
        this.ricettaId = ricettaId;
    }

    public int getRaccoltaId() {
        return raccoltaId;
    }

    public void setRaccoltaId(int raccoltaId) {
        this.raccoltaId = raccoltaId;
    }

    public int getUtenteId() {
        return utenteId;
    }

    public void setUtenteId(int utenteId) {
        this.utenteId = utenteId;
    }

    public int getIngredienteId() {
        return ingredienteId;
    }

    public void setIngredienteId(int ingredienteId) {
        this.ingredienteId = ingredienteId;
    }

    public String getNomeIngrediente() {
        return nomeIngrediente;
    }

    public void setNomeIngrediente(String nomeIngrediente) {
        this.nomeIngrediente = nomeIngrediente;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public String getUnitaMisura() {
        return unitaMisura;
    }

    public void setUnitaMisura(String unitaMisura) {
        this.unitaMisura = unitaMisura;
    }
}

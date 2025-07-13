package DTO;

import java.util.List;

public class RaccoltaDTO {
    private int idRaccolta;
    private String titolo;
    private String descrizione;
    private int utenteId;
    private String nomeAutore; // per visualizzazione
    private int numeroRicette;
    private List<RicettaDTO> ricette; // lista delle ricette nella raccolta

    public RaccoltaDTO() {
        super();
    }

    public RaccoltaDTO(int idRaccolta, String titolo, String descrizione, int utenteId) {
        this.idRaccolta = idRaccolta;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.utenteId = utenteId;
    }

    public RaccoltaDTO(int idRaccolta, String titolo, String descrizione, 
                       int utenteId, String nomeAutore) {
        this.idRaccolta = idRaccolta;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.utenteId = utenteId;
        this.nomeAutore = nomeAutore;
    }

    public int getIdRaccolta() {
        return idRaccolta;
    }

    public void setIdRaccolta(int idRaccolta) {
        this.idRaccolta = idRaccolta;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getUtenteId() {
        return utenteId;
    }

    public void setUtenteId(int utenteId) {
        this.utenteId = utenteId;
    }

    public String getNomeAutore() {
        return nomeAutore;
    }

    public void setNomeAutore(String nomeAutore) {
        this.nomeAutore = nomeAutore;
    }

    public int getNumeroRicette() {
        return numeroRicette;
    }

    public void setNumeroRicette(int numeroRicette) {
        this.numeroRicette = numeroRicette;
    }

    public List<RicettaDTO> getRicette() {
        return ricette;
    }

    public void setRicette(List<RicettaDTO> ricette) {
        this.ricette = ricette;
        if (ricette != null) {
            this.numeroRicette = ricette.size();
        }
    }
}

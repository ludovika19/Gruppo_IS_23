package DTO;

import entity.Utente;
import java.time.LocalDateTime;

public class CommentoDTO {

    private int idCommento;
    private String testo;
    private Utente autore;
    private LocalDateTime data;
    private int utenteId;
    private int ricettaId;

    public CommentoDTO(int idCommento, String testo, Utente autore, LocalDateTime data) {
        this.idCommento = idCommento;
        this.testo = testo;
        this.autore = autore;
        this.data = data;
    }

    public CommentoDTO(int idCommento, String testo, LocalDateTime data, int utenteId, int ricettaId) {
        this.idCommento = idCommento;
        this.testo = testo;
        this.data = data;
        this.utenteId = utenteId;
        this.ricettaId = ricettaId;
    }

    public int getIdCommento() {
        return this.idCommento;
    }

    public void setIdCommento(int idCommento) {
        this.idCommento = idCommento;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Utente getAutore() {
        return autore;
    }

    public void setAutore(Utente autore) {
        this.autore = autore;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public int getUtenteId() {
        return utenteId;
    }

    public void setUtenteId(int utenteId) {
        this.utenteId = utenteId;
    }

    public int getRicettaId() {
        return ricettaId;
    }

    public void setRicettaId(int ricettaId) {
        this.ricettaId = ricettaId;
    }
}

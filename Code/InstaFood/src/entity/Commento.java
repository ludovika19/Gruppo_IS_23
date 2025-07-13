package entity;

import java.time.LocalDateTime;

public class  Commento {
    private int id;
    private String testo;
    private LocalDateTime dataOra;
    private Utente autore;
    private Ricetta ricetta;

    public Commento(int id, String testo, LocalDateTime dataOra, Utente autore, Ricetta ricetta) {
        this.id = id;
        this.testo = testo;
        this.dataOra = dataOra;
        this.autore = autore;
        this.ricetta = ricetta;
    }

    // Getters e setters
}

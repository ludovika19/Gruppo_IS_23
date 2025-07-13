package entity;

import database.CommentoDAO;
import database.RicettaDAO;
import database.UtenteDAO;
import java.util.Date;

public class Commento {
    private int id;
    private String testo;
    private Date data;
    private Utente autore;
    private Ricetta ricetta;

    public Commento(int id, String testo, Date data, Utente autore, Ricetta ricetta) {
        this.id = id;
        this.testo = testo;
        this.data = data;
        this.autore = autore;
        this.ricetta = ricetta;
    }

    public Commento(CommentoDAO commentoDAO) {
        this.id = commentoDAO.getIdCommento();
        this.testo = commentoDAO.getTesto();
        // Conversione da java.util.Date a java.sql.Date
        if (commentoDAO.getData() != null) {
            this.data = new Date(commentoDAO.getData().getTime());
        }
        
        // Carico l'autore del commento
        int idUtente = commentoDAO.getUtente_idUtente();
        UtenteDAO utenteDAO = new UtenteDAO(idUtente);
        this.autore = new Utente(utenteDAO);
        
        // Carico la ricetta commentata
        int idRicetta = commentoDAO.getRicetta_idRicetta();
        RicettaDAO ricettaDAO = new RicettaDAO(idRicetta);
        this.ricetta = new Ricetta(ricettaDAO);
    }

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getTesto() { return testo; }
    public void setTesto(String testo) { this.testo = testo; }
    
    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }
    
    public Utente getAutore() { return autore; }
    public void setAutore(Utente autore) { this.autore = autore; }
    
    public Ricetta getRicetta() { return ricetta; }
    public void setRicetta(Ricetta ricetta) { this.ricetta = ricetta; }
}

package entity;

import database.*;
import java.util.ArrayList;
import java.util.List;

public class Raccolta {

    private int idRaccolta;
    private String titolo;
    private String descrizione;
    private Utente proprietario;
    private List<Ricetta> ricette;

    public Raccolta(String titolo) {
        this.titolo = titolo;
        this.ricette = new ArrayList<>();
    }

    public Raccolta(int idRaccolta, String titolo, String descrizione, Utente proprietario) {
        this.idRaccolta = idRaccolta;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.proprietario = proprietario;
        this.ricette = new ArrayList<>();
    }

    public Raccolta(RaccoltaDAO raccoltaDAO) {
        this.idRaccolta = raccoltaDAO.getIdRaccolta();
        this.titolo = raccoltaDAO.getTitolo();
        // Conversione da Blob a String per la descrizione
        try {
            if (raccoltaDAO.getDescrizione() != null) {
                this.descrizione = new String(raccoltaDAO.getDescrizione().getBytes(1, (int) raccoltaDAO.getDescrizione().length()));
            }
        } catch (Exception e) {
            this.descrizione = "";
        }
        
        // Carico il proprietario della raccolta
        int idUtente = raccoltaDAO.getUtente_idUtente();
        database.UtenteDAO utenteDAO = new database.UtenteDAO(idUtente);
        this.proprietario = new Utente(utenteDAO);
        
        // Inizializzo la lista ricette (il caricamento lazy può essere fatto in un secondo momento)
        this.ricette = new ArrayList<>();
    }

    public boolean creaRaccolta() {
        RaccoltaDAO dao = new RaccoltaDAO();
        int idGenerato = dao.createRaccolta(this.idRaccolta);

        if (idGenerato > 0) {
            this.idRaccolta = idGenerato;
            return true;
        } else {
            return false;
        }
    }

    public boolean aggiungiRicetta(int idRicetta) {
        RaccoltaDAO dao = new RaccoltaDAO(this.idRaccolta);
        return dao.aggiungiRicettaAllaRaccolta(idRicetta, idRaccolta);
    }

    public boolean rimuoviRicetta(int idRicetta) {
        RicettaDAO dao = new RicettaDAO();
        dao.setId(idRicetta);

        int ritorno = dao.deleteRicetta();
        if (ritorno > 0) return true;
        else return false; 
    }

    // Getters e setters
    public int getIdRaccolta() { return idRaccolta; }
    public void setIdRaccolta(int idRaccolta) { this.idRaccolta = idRaccolta; }
    
    public String getTitolo() { return titolo; }
    public void setTitolo(String titolo) { this.titolo = titolo; }
    
    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
    
    public Utente getProprietario() { return proprietario; }
    public void setProprietario(Utente proprietario) { this.proprietario = proprietario; }
    
    public List<Ricetta> getRicette() { return ricette; }
    public void setRicette(List<Ricetta> ricette) { this.ricette = ricette; }

}

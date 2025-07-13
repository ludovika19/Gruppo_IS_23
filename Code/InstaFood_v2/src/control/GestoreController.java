package control;

import DTO.*;
import entity.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller singleton per gestire le operazioni tra entity e GUI
 */
public class GestoreController {
    private static GestoreController instance;
    private AccessController accessController;
    private Sistema sistema;
    
    // Costruttore privato per singleton
    private GestoreController() {
        try {
            this.accessController = AccessController.getInstance();
            this.sistema = Sistema.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Metodo singleton per ottenere l'istanza
     */
    public static GestoreController getInstance() {
        if (instance == null) {
            instance = new GestoreController();
        }
        return instance;
    }
    
    /**
     * Metodo per pubblicare una ricetta
     * ricettaDTO i dati della ricetta da pubblicare
     * true se la pubblicazione è avvenuta con successo, false altrimenti
     */
    public boolean pubblicaRicetta(RicettaDTO ricettaDTO) {
        // Verifico che ci sia un utente autenticato
        Utente utenteCorrente = accessController.getUtenteCorrente();
        if (utenteCorrente == null) {
            System.err.println("Errore: Nessun utente autenticato");
            return false;
        }
        
        // Validazione dei dati della ricetta
        if (ricettaDTO == null) {
            System.err.println("Errore: Dati ricetta non validi");
            return false;
        }
        
        if (ricettaDTO.getTitolo() == null || ricettaDTO.getTitolo().trim().isEmpty()) {
            System.err.println("Errore: Titolo ricetta obbligatorio");
            return false;
        }
        
        if (ricettaDTO.getDescrizioneCompleta() == null || ricettaDTO.getDescrizioneCompleta().trim().isEmpty()) {
            System.err.println("Errore: Descrizione ricetta obbligatoria");
            return false;
        }
        
        if (ricettaDTO.getTempo() <= 0) {
            System.err.println("Errore: Tempo di preparazione deve essere maggiore di 0");
            return false;
        }
        
        // Imposto l'ID dell'utente nel DTO
        ricettaDTO.setUtenteId(utenteCorrente.getId());
        
        // Delego la pubblicazione all'entity Utente
        boolean risultato = utenteCorrente.pubblicaRicetta(ricettaDTO);
        
        if (risultato) {
            System.out.println("Ricetta pubblicata con successo: " + ricettaDTO.getTitolo());
        } else {
            System.err.println("Errore nella pubblicazione della ricetta");
        }
        
        return risultato;
    }
    
    /*
     * Metodo per modificare una ricetta esistente
     */
    public boolean modificaRicetta(RicettaDTO ricettaDTO) {
        Utente utenteCorrente = accessController.getUtenteCorrente();
        if (utenteCorrente == null) {
            System.err.println("Errore: Nessun utente autenticato");
            return false;
        }
        
        return utenteCorrente.modificaRicetta(ricettaDTO);
    }
    
    /**
     * Metodo per ottenere il feed delle ricette
     */
    public ArrayList<RicettaDTO> getFeed() {
        return sistema.getFeed();
    }
    
    /**
     * Metodo per aggiungere un commento a una ricetta
     */
    public boolean aggiungiCommento(int idRicetta, String testo) {
        Utente utenteCorrente = accessController.getUtenteCorrente();
        if (utenteCorrente == null) {
            System.err.println("Errore: Nessun utente autenticato");
            return false;
        }
        
        return utenteCorrente.aggiungiCommento(idRicetta, testo);
    }
    
    /**
     * Metodo per creare una nuova raccolta
     */
    public boolean creaRaccolta(String titolo, String descrizione) {
        Utente utenteCorrente = accessController.getUtenteCorrente();
        if (utenteCorrente == null) {
            System.err.println("Errore: Nessun utente autenticato");
            return false;
        }
        
        return utenteCorrente.creaRaccolta(titolo, descrizione);
    }
    
    /**
     * Metodo per ottenere le raccolte dell'utente corrente
     */
    public List<Raccolta> getRaccolteUtente() {
        Utente utenteCorrente = accessController.getUtenteCorrente();
        if (utenteCorrente == null) {
            return new ArrayList<>();
        }
        
        return utenteCorrente.getRaccolte();
    }
    
    /**
     * Metodo per ottenere le ricette pubblicate dall'utente corrente
     */
    public ArrayList<Ricetta> getRicetteUtente() {
        Utente utenteCorrente = accessController.getUtenteCorrente();
        if (utenteCorrente == null) {
            return new ArrayList<>();
        }
        
        return utenteCorrente.getRicettePubblicate();
    }
    
    /**
     * Metodo per visualizzare il profilo dell'utente corrente
     */
    public UtenteDTO visualizzaProfilo() {
        Utente utenteCorrente = accessController.getUtenteCorrente();
        if (utenteCorrente == null) {
            return null;
        }
        
        return utenteCorrente.visualizzaProfilo();
    }
    
    /**
     * Metodo per modificare il profilo dell'utente corrente
     */
    public void modificaProfilo(UtenteDTO utenteDTO) {
        Utente utenteCorrente = accessController.getUtenteCorrente();
        if (utenteCorrente != null) {
            utenteCorrente.modificaProfilo(utenteDTO);
        }
    }
    
    /**
     * Metodo per ottenere le statistiche dell'utente corrente
     */
    public Object[] getStatisticheUtente() {
        Utente utenteCorrente = accessController.getUtenteCorrente();
        if (utenteCorrente == null) {
            return new Object[0];
        }
        
        return utenteCorrente.generaStatistiche();
    }
    
    /**
     * Metodo per ottenere i tag più usati
     */
    public ArrayList<TagDTO> getTagMostUsed() {
        return sistema.getTagMostUsed();
    }
    
    /**
     * Metodo per generare report (solo per amministratori)
     */
    public ArrayList<Object> generaReport(java.util.Date dataInizio, java.util.Date dataFine) {
        return sistema.generaReport(dataInizio, dataFine);
    }
    
    // Getter per accedere ai controller
    public AccessController getAccessController() {
        return accessController;
    }
    
    public Sistema getSistema() {
        return sistema;
    }
    
}

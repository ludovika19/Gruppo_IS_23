package entity;

import database.RicettaDAO;
import database.UtenteDAO;

import java.util.List;

public class Ricetta {
    private int id;
    private String titolo;
    private String descrizione;
    private int tempoPreparazione; // in minuti
    private int numLike;
    private boolean pubblica;
    private List<Ingrediente> ingredienti;
    private List<String> tag;
    private Raccolta raccolta;
    private Utente autore;

    public Ricetta(int id, String titolo, String descrizione, int tempoPreparazione, List<Ingrediente> ingredienti, List<String> tag, boolean pubblica, Raccolta raccolta, Utente autore) {
        this.id = id;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.tempoPreparazione = tempoPreparazione;
        this.ingredienti = ingredienti;
        this.tag = tag;
        this.pubblica = pubblica;
        this.raccolta = raccolta;
        this.autore = autore;
    }

    public Ricetta(int id){
        this.id = id;
    }

    public Ricetta(RicettaDAO ricettaDB){
        this.titolo = ricettaDB.getTitolo();
        this.descrizione = ricettaDB.getDescrizione();
        this.tempoPreparazione = ricettaDB.getTempo();

        this.pubblica = ricettaDB.isPubblica();

//da finire------------------------------------------------

        int idUtente = ricettaDB.getUtente_idUtente();
        UtenteDAO utenteDB = new UtenteDAO(idUtente);
        Utente utenteTemp = new Utente(utenteDB);
        this.autore = utenteTemp;
    }


    public void aggiungiIngrediente(Ingrediente ingrediente) {
        this.ingredienti.add(ingrediente);
    }

    public void rimuoviIngrediente(Ingrediente ingrediente) {
        this.ingredienti.remove(ingrediente);
    }

    // metodo per verificare se una ricetta è pubblica
    public boolean isPubblica(){
        return pubblica;
    }




}

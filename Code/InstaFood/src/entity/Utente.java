package entity;

import database.RicettaDAO;
import database.UtenteDAO;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

public class Utente {
    private int id;
    private String nome;
    private String cognome;
    private String password;
    private String email;
    private Date dataN;
    private String luogoN;
    private Blob biografia;
    private Blob immagine;
    private List<Ricetta> ricettePubblicate;
    private List<Raccolta> raccolte;

    public Utente(int id, String nome, String cognome, String password, String email, Date dataN, String luogoN, Blob biografia, Blob immagine) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.password = password;
        this.email = email;
        this.dataN = dataN;
        this.luogoN = luogoN;
        this.biografia = biografia;
        this.immagine = immagine;
        this.raccolte = raccolte;
        this.ricettePubblicate = ricettePubblicate;
    }

    public Utente(int id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public Utente(UtenteDAO utentedb){
        this.id = utentedb.getId();
        this.nome = utentedb.getNome();
        this.cognome = utentedb.getCognome();
        this.password = utentedb.getPassword();
        this.email = utentedb.getEmail();
        this.dataN = utentedb.getDataN();
        this.luogoN = utentedb.getLuogoN();
        this.biografia = utentedb.getBiografia();
        this.immagine = utentedb.getImmagine();

        //carico la lista di ricette di un autore
        RicettaDAO ricettaDB = new RicettaDAO();

        //liste di raccolte di un utente
    }

    // Getters e setters

    public void aggiungiRicetta(Ricetta ricetta) {
        this.ricettePubblicate.add(ricetta);
    }

    public void aggiungiRaccolta(Raccolta raccolta) {
        this.raccolte.add(raccolta);
    }


}

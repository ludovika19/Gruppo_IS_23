package entity;

import DTO.*;
import database.CommentoDAO;
import database.RicettaDAO;
import database.UtenteDAO;
import java.sql.Blob;
import java.util.ArrayList;
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
    private String notifica; // Nuovo campo per le notifiche
    private ArrayList<Ricetta> ricettePubblicate;
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
        this.raccolte = new ArrayList<>();
        this.ricettePubblicate = new ArrayList<>();
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
        RicettaDAO ricettaDAO = new RicettaDAO();
        ricettaDAO.setUtente_idUtente(this.id);
        this.ricettePubblicate = ricettaDAO.getListaRicetteAsEntity();

        //liste di raccolte di un utente
        database.RaccoltaDAO raccoltaDAO = new database.RaccoltaDAO();
        this.raccolte = raccoltaDAO.getRaccolteByUtente(this.id);
    }

    public void aggiungiRicetta(Ricetta ricetta) {
        this.ricettePubblicate.add(ricetta);
    }

    public void aggiungiRaccolta(Raccolta raccolta) {
        this.raccolte.add(raccolta);
    }

    // Metodi di business logic
    public boolean creaRicetta(Ricetta ricetta) {
        database.RicettaDAO ricettaDAO = new database.RicettaDAO();
        int idGenerato = ricettaDAO.createRicetta(ricetta);
        if (idGenerato > 0) {
            ricetta.setId(idGenerato);
            if (this.ricettePubblicate == null) {
                this.ricettePubblicate = new java.util.ArrayList<>();
            }
            this.ricettePubblicate.add(ricetta);
            return true;
        }
        return false;
    }

    public boolean creaRaccolta(String titolo, String descrizione) {
        database.RaccoltaDAO raccoltaDAO = new database.RaccoltaDAO();
        Raccolta nuovaRaccolta = new Raccolta(titolo);
        nuovaRaccolta.setDescrizione(descrizione);
        nuovaRaccolta.setProprietario(this);
        
        int idGenerato = raccoltaDAO.createRaccolta(nuovaRaccolta.getIdRaccolta());
        if (idGenerato > 0) {
            nuovaRaccolta.setIdRaccolta(idGenerato);
            if (this.raccolte == null) {
                this.raccolte = new java.util.ArrayList<>();
            }
            this.raccolte.add(nuovaRaccolta);
            return true;
        }
        return false;
    }

    public boolean aggiungiCommento(int idRicetta, String testo) {
        database.CommentoDAO commentoDAO = new database.CommentoDAO();
        return commentoDAO.createCommento(this.id, idRicetta, testo);
    }

    public List<Commento> getCommentiUtente() {
        database.CommentoDAO commentoDAO = new database.CommentoDAO();
        return commentoDAO.getCommentiByUtente(this.id);
    }

    public void modificaProfilo(DTO.UtenteDTO utenteDTO) {

        database.UtenteDAO utenteDAO = new database.UtenteDAO();
        utenteDAO.setId(utenteDTO.getIdUtente());
        utenteDAO.setNome(utenteDTO.getNome());
        utenteDAO.setCognome(utenteDTO.getCognome());
        utenteDAO.setEmail(utenteDTO.getEmail());
        utenteDAO.setPassword(utenteDTO.getPassword());
        utenteDAO.setDataN(utenteDTO.getDataNascita());
        utenteDAO.setLuogoN(utenteDTO.getLuogoNascita());
        utenteDAO.setBiografia(utenteDTO.getBiografia());
        utenteDAO.setImmagine(utenteDTO.getImmagine());        
        utenteDAO.updateUtente();
    }

    /**
     * Metodo per visualizzare i dati dell'utente come DTO
     */
    public UtenteDTO visualizzaProfilo(){
        // Creo un nuovo UtenteDTO
        UtenteDTO utenteDTO = new UtenteDTO();
        
        // Copio i dati di base
        utenteDTO.setIdUtente(this.id);
        utenteDTO.setNome(this.nome);
        utenteDTO.setCognome(this.cognome);
        utenteDTO.setEmail(this.email);
        utenteDTO.setPassword(this.password);
        utenteDTO.setDataNascita(this.dataN);
        utenteDTO.setLuogoNascita(this.luogoN);
        utenteDTO.setBiografia(this.biografia);
        utenteDTO.setImmagine(this.immagine);
        
        // Calcolo delle statistiche
        // Numero di ricette pubblicate
        if (this.ricettePubblicate != null) {
            utenteDTO.setNumeroRicette(this.ricettePubblicate.size());
        } else {
            utenteDTO.setNumeroRicette(0);
        }
        
        // Calcolo del totale dei like su tutte le ricette
        int likeTotali = 0;
        if (this.ricettePubblicate != null && !this.ricettePubblicate.isEmpty()) {
            for (Ricetta ricetta : this.ricettePubblicate) {
                likeTotali += ricetta.getLike();
            }
        }
        utenteDTO.setLikeTotali(likeTotali);

        return utenteDTO;
    }
    
    public int generaStatisticaLikeTotali(){
        int likeTotali = 0;
        if (this.ricettePubblicate != null && !this.ricettePubblicate.isEmpty()) {
            UtenteDAO utenteDAO = new UtenteDAO(this.id);
            likeTotali = utenteDAO.getLikeTotali();
        }
        return likeTotali;
    }

    public int generaStatisticaCommentiTotali(){
        int commentiTotali = 0;
        if (this.ricettePubblicate != null && !this.ricettePubblicate.isEmpty()) {
            CommentoDAO commentoDAO = new CommentoDAO();
            commentoDAO.setUtente_idUtente(this.id);
            commentiTotali = commentoDAO.getNumCommentiPerRicetta();
        }
        return commentiTotali;
    }

    public RicettaDAO generaStatisticaRicettaMigliore(){
        RicettaDAO miglioreRicetta = new RicettaDAO();
        if (this.ricettePubblicate != null && !this.ricettePubblicate.isEmpty()) {
            RicettaDAO ricettaDAO = new RicettaDAO();
            ricettaDAO.setUtente_idUtente(this.id);

            miglioreRicetta = ricettaDAO.getMiglioreRicetta();
            
        }
        return miglioreRicetta;
    }

    public Object[] generaStatistiche(){
        Object[] statistiche = new Object[3];
        statistiche[0] = this.generaStatisticaLikeTotali();
        statistiche[1] = this.generaStatisticaCommentiTotali();
        statistiche[2] = this.generaStatisticaRicettaMigliore();
        
        return statistiche;
    }
    
    /**
     * Metodo per pubblicare (creare e inserire) una nuova ricetta nel database
     */
    public boolean pubblicaRicetta(RicettaDTO ricettaDTO) {
        // Validazione parametri
        if (ricettaDTO == null) {
            return false;
        }
        
        // Validazione raccolta - deve essere specificata
        if (ricettaDTO.getRaccoltaId() <= 0) {
            return false; // Una ricetta deve appartenere a una raccolta
        }
        
        // Verifico che la raccolta appartenga a questo utente
        boolean raccoltaDelUtente = false;
        if (this.raccolte != null) {
            for (Raccolta raccolta : this.raccolte) {
                if (raccolta.getIdRaccolta() == ricettaDTO.getRaccoltaId()) {
                    raccoltaDelUtente = true;
                    break;
                }
            }
        }
        
        if (!raccoltaDelUtente) {
            return false; // L'utente può pubblicare solo in raccolte proprie
        }
        
        // Carico la raccolta dal database
        database.RaccoltaDAO raccoltaDAO = new database.RaccoltaDAO(ricettaDTO.getRaccoltaId());
        Raccolta raccolta = new Raccolta(raccoltaDAO);
        
        // Creo l'oggetto Ricetta dai dati del DTO
        Ricetta nuovaRicetta = new Ricetta(0); // ID temporaneo
        nuovaRicetta.setTitolo(ricettaDTO.getTitolo());
        nuovaRicetta.setDescrizione(ricettaDTO.getDescrizioneCompleta());
        nuovaRicetta.setTempoPreparazione(ricettaDTO.getTempo());
        nuovaRicetta.setLike(0); // Nuova ricetta inizia con 0 like
        nuovaRicetta.setPubblica(true); // Imposta come pubblica
        nuovaRicetta.setDataPubblicazione(new java.util.Date()); // Data corrente
        nuovaRicetta.setAutore(this);
        nuovaRicetta.setRaccolta(raccolta); // Imposto sempre la raccolta
        
        // Creo un RicettaDAO e inserisco la ricetta nel database
        database.RicettaDAO ricettaDAO = new database.RicettaDAO();
        int idGenerato = ricettaDAO.createRicetta(nuovaRicetta);
        
        // Se l'inserimento è andato a buon fine, aggiungo la ricetta anche alla lista locale
        if (idGenerato > 0) {
            // Aggiorno l'ID della ricetta con quello generato dal database
            nuovaRicetta.setId(idGenerato);
            
            // Aggiungo alla lista delle ricette pubblicate
            if (this.ricettePubblicate == null) {
                this.ricettePubblicate = new java.util.ArrayList<>();
            }
            this.ricettePubblicate.add(nuovaRicetta);
            
            return true;
        }
        
        return false;
    }

    /**
     * Metodo per modificare una ricetta esistente
     */
    public boolean modificaRicetta(RicettaDTO ricettaDTO) {
        // Validazione parametri
        if (ricettaDTO == null || ricettaDTO.getId() <= 0) {
            return false;
        }
        
        // Verifica che la ricetta appartenga a questo utente
        boolean ricettaDelUtente = false;
        if (this.ricettePubblicate != null) {
            for (Ricetta ricetta : this.ricettePubblicate) {
                if (ricetta.getId() == ricettaDTO.getId()) {
                    ricettaDelUtente = true;
                    break;
                }
            }
        }
        
        if (!ricettaDelUtente) {
            return false; // L'utente non può modificare ricette che non sono sue
        }
        
        // Aggiorna la ricetta nel database con i dati del DTO
        database.RicettaDAO ricettaDAO = new database.RicettaDAO(ricettaDTO.getId());
        
        // Imposta tutti i dati dal DTO
        ricettaDAO.setTitolo(ricettaDTO.getTitolo());
        ricettaDAO.setDescrizione(ricettaDTO.getDescrizioneCompleta());
        ricettaDAO.setTempo(ricettaDTO.getTempo());
        ricettaDAO.setLike(ricettaDTO.getNumeroLike());
        ricettaDAO.setPubblica(ricettaDTO.isPubblica()); // Usa il valore dal DTO
        ricettaDAO.setData(ricettaDTO.getDataPubblicazione());
        ricettaDAO.setUtente_idUtente(this.id);
        
        // Gestisco la raccolta se specificata nel DTO
        if (ricettaDTO.getRaccoltaId() > 0) {
            ricettaDAO.setRaccolta_idRaccolta(ricettaDTO.getRaccoltaId());
            ricettaDAO.setRaccolta_Utente_idUtente(this.id);
        }
        
        int risultato = ricettaDAO.updateRicetta();
        
        // Se l'aggiornamento è andato a buon fine, aggiorna anche l'oggetto locale
        if (risultato > 0) {
            for (Ricetta ricetta : this.ricettePubblicate) {
                if (ricetta.getId() == ricettaDTO.getId()) {
                    ricetta.setTitolo(ricettaDTO.getTitolo());
                    ricetta.setDescrizione(ricettaDTO.getDescrizioneCompleta());
                    ricetta.setTempoPreparazione(ricettaDTO.getTempo());
                    ricetta.setLike(ricettaDTO.getNumeroLike());
                    ricetta.setPubblica(ricettaDTO.isPubblica());
                    ricetta.setDataPubblicazione(ricettaDTO.getDataPubblicazione());
                    break;
                }
            }
            return true;
        }
        
        return false;
    }

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public Date getDataN() { return dataN; }
    public void setDataN(Date dataN) { this.dataN = dataN; }
    
    public String getLuogoN() { return luogoN; }
    public void setLuogoN(String luogoN) { this.luogoN = luogoN; }
    
    public Blob getBiografia() { return biografia; }
    public void setBiografia(Blob biografia) { this.biografia = biografia; }
    
    public Blob getImmagine() { return immagine; }
    public void setImmagine(Blob immagine) { this.immagine = immagine; }
    
    public ArrayList<Ricetta> getRicettePubblicate() { return ricettePubblicate; }
    public void setRicettePubblicate(ArrayList<Ricetta> ricettePubblicate) { this.ricettePubblicate = ricettePubblicate; }
    
    public List<Raccolta> getRaccolte() { return raccolte; }
    public void setRaccolte(List<Raccolta> raccolte) { this.raccolte = raccolte; }

    public String getNotifica() { return notifica; }
    public void setNotifica(String notifica) { this.notifica = notifica; }
}

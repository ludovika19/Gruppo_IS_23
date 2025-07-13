package entity;

import DTO.*;
import database.RicettaDAO;
import database.UtenteDAO;
import java.util.Date;
import java.util.List;

public class Ricetta {
    private int id;
    private String titolo;
    private String descrizione;
    private int tempoPreparazione; // in minuti
    private int Like;
    private boolean pubblica;
    private Date dataPubblicazione;
    private List<Ingrediente> ingredienti;
    private List<String> tag;
    private Raccolta raccolta;
    private Utente autore;

    public Ricetta(int id, String titolo, String descrizione, int tempoPreparazione, List<Ingrediente> ingredienti, List<String> tag, boolean pubblica, Date dataPubblicazione, Raccolta raccolta, Utente autore) {
        this.id = id;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.tempoPreparazione = tempoPreparazione;
        this.ingredienti = ingredienti;
        this.tag = tag;
        this.pubblica = pubblica;
        this.dataPubblicazione = dataPubblicazione;
        this.raccolta = raccolta;
        this.autore = autore;
    }

    public Ricetta(int id){
        this.id = id;
    }

    public Ricetta(RicettaDAO ricettaDB){
        this.id = ricettaDB.getId();
        this.titolo = ricettaDB.getTitolo();
        this.descrizione = ricettaDB.getDescrizione();
        this.tempoPreparazione = ricettaDB.getTempo();
        this.Like = ricettaDB.getLike();
        this.pubblica = ricettaDB.isPubblica();
        
        // Assegno la data dal database
        if (ricettaDB.getData() != null) {
            this.dataPubblicazione = ricettaDB.getData();
        } else {
            this.dataPubblicazione = new Date();
        }

        // Carico l'autore della ricetta
        int idUtente = ricettaDB.getUtente_idUtente();
        UtenteDAO utenteDB = new UtenteDAO(idUtente);
        this.autore = new Utente(utenteDB);

        // Carico la raccolta
        int idRaccolta = ricettaDB.getRaccolta_idRaccolta();
        database.RaccoltaDAO raccoltaDAO = new database.RaccoltaDAO(idRaccolta);
        this.raccolta = new Raccolta(raccoltaDAO);

        // Carico gli ingredienti
        database.IngredienteRicettaDAO ingredienteRicettaDAO = new database.IngredienteRicettaDAO();
        this.ingredienti = ingredienteRicettaDAO.getIngredientiByRicetta(this.id);

        // Carico i tag
        database.TagRicettaDAO tagRicettaDAO = new database.TagRicettaDAO();
        this.tag = tagRicettaDAO.getTagByRicetta(this.id);
    }

    public void aggiungiIngrediente(Ingrediente ingrediente) {
        this.ingredienti.add(ingrediente);
    }

    public void rimuoviIngrediente(Ingrediente ingrediente) {
        this.ingredienti.remove(ingrediente);
    }

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getTitolo() { return titolo; }
    public void setTitolo(String titolo) { this.titolo = titolo; }
    
    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
    
    public int getTempoPreparazione() { return tempoPreparazione; }
    public void setTempoPreparazione(int tempoPreparazione) { this.tempoPreparazione = tempoPreparazione; }
    
    public int getLike() { return Like; }
    public void setLike(int Like) { this.Like = Like; }
    
    public boolean isPubblica() { return pubblica; }
    public void setPubblica(boolean pubblica) { this.pubblica = pubblica; }
    
    public Date getDataPubblicazione() { return dataPubblicazione; }
    public void setDataPubblicazione(Date dataPubblicazione) { this.dataPubblicazione = dataPubblicazione; }
    
    public List<Ingrediente> getIngredienti() { return ingredienti; }
    public void setIngredienti(List<Ingrediente> ingredienti) { this.ingredienti = ingredienti; }
    
    public List<String> getTag() { return tag; }
    public void setTag(List<String> tag) { this.tag = tag; }
    
    public Raccolta getRaccolta() { return raccolta; }
    public void setRaccolta(Raccolta raccolta) { this.raccolta = raccolta; }
    
    public Utente getAutore() { return autore; }
    public void setAutore(Utente autore) { this.autore = autore; }

    // metodo per aggiungere Like
    public void incrementaLike() {
        this.Like++;
    }

    // Metodi di business logic
    public boolean aggiungiLike() {
        database.RicettaDAO ricettaDAO = new database.RicettaDAO();
        this.Like++;
        return ricettaDAO.updateLike(this.id, this.Like);
    }

    public List<Commento> getCommenti() {
        database.CommentoDAO commentoDAO = new database.CommentoDAO();
        return commentoDAO.getCommentiByRicetta(this.id);
    }

    public boolean aggiungiIngredienteRicetta(Ingrediente ingrediente, int quantita) {
        database.IngredienteRicettaDAO ingredienteRicettaDAO = new database.IngredienteRicettaDAO();
        boolean successo = ingredienteRicettaDAO.addIngredienteToRicetta(this.id, ingrediente.getId(), quantita);
        if (successo) {
            ingrediente.setQta(quantita);
            if (this.ingredienti == null) {
                this.ingredienti = new java.util.ArrayList<>();
            }
            this.ingredienti.add(ingrediente);
        }
        return successo;
    }

    public boolean aggiungiTag(String nomeTag) {
        database.TagDAO tagDAO = new database.TagDAO();
        database.TagRicettaDAO tagRicettaDAO = new database.TagRicettaDAO();
        
        // Prima cerco se il tag esiste, altrimenti lo creo
        int idTag = tagDAO.getIdByNome(nomeTag);
        if (idTag == -1) {
            idTag = tagDAO.createTag(nomeTag);
        }
        
        if (idTag > 0) {
            boolean successo = tagRicettaDAO.addTagToRicetta(this.id, idTag);
            if (successo) {
                if (this.tag == null) {
                    this.tag = new java.util.ArrayList<>();
                }
                this.tag.add(nomeTag);
            }
            return successo;
        }
        return false;
    }

    /**
     * Metodo per visualizzare i dati della ricetta come DTO
     */
    public RicettaDTO visualizzaRicetta() {
        // Creo un nuovo RicettaDTO
        RicettaDTO ricettaDTO = new RicettaDTO();
        
        // Copio i dati di base
        ricettaDTO.setId(this.id);
        ricettaDTO.setTitolo(this.titolo);
        ricettaDTO.setDescrizioneCompleta(this.descrizione);
        ricettaDTO.setTempo(this.tempoPreparazione);
        ricettaDTO.setNumeroLike(this.Like);
        ricettaDTO.setPubblica(this.pubblica);
        ricettaDTO.setDataPubblicazione(this.dataPubblicazione);
        
        // Gestisco l'autore
        if (this.autore != null) {
            ricettaDTO.setAutore(this.autore.getNome() + " " + this.autore.getCognome());
            ricettaDTO.setUtenteId(this.autore.getId());
        }
        
        // Gestisco la raccolta
        if (this.raccolta != null) {
            ricettaDTO.setRaccoltaId(this.raccolta.getIdRaccolta());
        }
        
        // Converto gli ingredienti in IngredienteDTO
        if (this.ingredienti != null && !this.ingredienti.isEmpty()) {
            java.util.List<IngredienteDTO> ingredientiDTO = new java.util.ArrayList<>();
            for (Ingrediente ingrediente : this.ingredienti) {
                IngredienteDTO ingredienteDTO = new IngredienteDTO(
                    ingrediente.getId(), 
                    ingrediente.getNome(), 
                    ingrediente.getQta()
                );
                ingredientiDTO.add(ingredienteDTO);
            }
            ricettaDTO.setIngredienti(ingredientiDTO);
        }
        
        // Converto i tag in TagDTO
        if (this.tag != null && !this.tag.isEmpty()) {
            java.util.List<TagDTO> tagsDTO = new java.util.ArrayList<>();
            for (int i = 0; i < this.tag.size(); i++) {
                String nomeTag = this.tag.get(i);
                TagDTO tagDTO = new TagDTO(i + 1, nomeTag); // ID temporaneo
                tagsDTO.add(tagDTO);
            }
            ricettaDTO.setTags(tagsDTO);
        }
        
        // Carico i commenti se necessario
        java.util.List<Commento> commenti = this.getCommenti();
        if (commenti != null) {
            ricettaDTO.setNumCommenti(commenti.size());
        }
        
        return ricettaDTO;
    }

    /**
     * Metodo per aggiungere un commento alla ricetta
     */
    public boolean aggiungiCommento(String testo, Utente autore) {
        // Validazione dei parametri
        if (testo == null || testo.trim().isEmpty()) {
            return false; // Testo del commento non valido
        }
        
        if (autore == null) {
            return false; // Autore non valido
        }
        
        database.CommentoDAO commentoDAO = new database.CommentoDAO();
        boolean successo = commentoDAO.createCommento(autore.getId(), this.id, testo);
        
        return successo;
    }

    public boolean aggiungiCommento(String testo, int idUtente) {
        // Validazione dei parametri
        if (testo == null || testo.trim().isEmpty()) {
            return false; // Testo del commento non valido
        }
        
        if (idUtente <= 0) {
            return false; // ID utente non valido
        }
        
        // Uso CommentoDAO per salvare nel database
        database.CommentoDAO commentoDAO = new database.CommentoDAO();
        boolean successo = commentoDAO.createCommento(idUtente, this.id, testo);
        
        return successo;
    }

}
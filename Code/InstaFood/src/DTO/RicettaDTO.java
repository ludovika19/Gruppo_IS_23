package DTO;

import java.time.LocalDate;
import java.util.List;

public class RicettaDTO {
    private int idRicetta;
    private String titolo;
    private String autore;// username o nome+cognome
    private String descrizioneAnteprima;
    private String descrizioneCompleta;
    private int tempo;
    private int like;
    private int numCommenti;
    private boolean pubblica;
    private LocalDate dataPubblicazione;
    private int raccoltaId;
    private int utenteId;
    private List<CommentoDTO> ultimiCommenti;
    private List<IngredienteDTO> ingredienti;
    private List<TagDTO> tags;

    public RicettaDTO() {
        super();
    }

    public RicettaDTO(String titolo, String descrizioneAnteprima, int tempo, 
                      List<IngredienteDTO> ingredienti, List<TagDTO> tags) {
        this.titolo = titolo;
        this.descrizioneAnteprima = descrizioneAnteprima;
        this.tempo = tempo;
        this.ingredienti = ingredienti;
        this.tags = tags;
    }

    public RicettaDTO(int idRicetta, String titolo, String descrizioneCompleta, 
                      int tempo, int like, boolean pubblica, LocalDate dataPubblicazione) {
        this.idRicetta = idRicetta;
        this.titolo = titolo;
        this.descrizioneCompleta = descrizioneCompleta;
        this.tempo = tempo;
        this.like = like;
        this.pubblica = pubblica;
        this.dataPubblicazione = dataPubblicazione;
    }

    public int getId() {
        return this.idRicetta;
    }
    
    public void setId(int id) {
        this.idRicetta = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getDescrizioneAnteprima() {
        return descrizioneAnteprima;
    }

    public void setDescrizioneAnteprima(String descrizioneAnteprima) {
        this.descrizioneAnteprima = descrizioneAnteprima;
    }

    public String getDescrizioneCompleta() {
        return descrizioneCompleta;
    }

    public void setDescrizioneCompleta(String descrizioneCompleta) {
        this.descrizioneCompleta = descrizioneCompleta;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getNumeroLike() {
        return like;
    }

    public void setNumeroLike(int like) {
        this.like = like;
    }

    public void setNumCommenti(int numCommenti) {
        this.numCommenti = numCommenti;
    }

    public int getNumCommenti() {
        return numCommenti;
    }

    public boolean isPubblica() {
        return pubblica;
    }

    public void setPubblica(boolean pubblica) {
        this.pubblica = pubblica;
    }

    public LocalDate getDataPubblicazione() {
        return dataPubblicazione;
    }

    public void setDataPubblicazione(LocalDate dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }

    public int getRaccoltaId() {
        return raccoltaId;
    }

    public void setRaccoltaId(int raccoltaId) {
        this.raccoltaId = raccoltaId;
    }

    public int getUtenteId() {
        return utenteId;
    }

    public void setUtenteId(int utenteId) {
        this.utenteId = utenteId;
    }

    public List<CommentoDTO> getUltimiCommenti() {
        return ultimiCommenti;
    }

    public void setUltimiCommenti(List<CommentoDTO> ultimiCommenti) {
        this.ultimiCommenti = ultimiCommenti;
    }

    public List<IngredienteDTO> getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(List<IngredienteDTO> ingredienti) {
        this.ingredienti = ingredienti;
    }

    public List<TagDTO> getTags() {
        return tags;
    }

    public void setTags(List<TagDTO> tags) {
        this.tags = tags;
    }
}



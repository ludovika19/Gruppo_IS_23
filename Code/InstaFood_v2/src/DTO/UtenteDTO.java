package DTO;

import java.sql.Blob;
import java.util.Date;


public class UtenteDTO {
    private int idUtente;
    private String nome;
    private String cognome;
    private String email;
    private Date dataNascita;
    private String luogoNascita;
    private Blob biografia;
    private Blob immagine;
    private String password;
    private String nomeCompleto; // nome + cognome per visualizzazione
    private int numeroRicette;
    private int likeTotali;

    public UtenteDTO() {
        super();
    }

    public UtenteDTO(int idUtente, String nome, String cognome, String email) {
        this.idUtente = idUtente;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.nomeCompleto = nome + " " + cognome;
    }

    public UtenteDTO(int idUtente, String nome, String cognome, String email, 
                     Date dataNascita, String luogoNascita, Blob biografia) {
        this.idUtente = idUtente;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataNascita = dataNascita;
        this.luogoNascita = luogoNascita;
        this.biografia = biografia;
        this.nomeCompleto = nome + " " + cognome;
    }

    public UtenteDTO(int idUtente, String nome, String cognome, String email, 
                     Date dataNascita, String luogoNascita, 
                     Blob biografia, Blob immagine, String password, 
                     int numeroRicette, int likeTotali) {
        this.idUtente = idUtente;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataNascita = dataNascita;
        this.luogoNascita = luogoNascita;
        this.biografia = biografia;
        this.immagine = immagine;
        this.password = password;
        this.numeroRicette = numeroRicette;
        this.likeTotali = likeTotali;
        this.nomeCompleto = nome + " " + cognome;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        updateNomeCompleto();
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
        updateNomeCompleto();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getLuogoNascita() {
        return luogoNascita;
    }

    public void setLuogoNascita(String luogoNascita) {
        this.luogoNascita = luogoNascita;
    }

    public Blob getBiografia() {
        return biografia;
    }

    public void setBiografia(Blob biografia) {
        this.biografia = biografia;
    }

    public Blob getImmagine() {
        return immagine;
    }

    public void setImmagine(Blob immagine) {
        this.immagine = immagine;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public int getNumeroRicette() {
        return numeroRicette;
    }

    public void setNumeroRicette(int numeroRicette) {
        this.numeroRicette = numeroRicette;
    }

    public int getLikeTotali() {
        return likeTotali;
    }

    public void setLikeTotali(int likeTotali) {
        this.likeTotali = likeTotali;
    }

    private void updateNomeCompleto() {
        if (nome != null && cognome != null) {
            this.nomeCompleto = nome + " " + cognome;
        }
    }
}

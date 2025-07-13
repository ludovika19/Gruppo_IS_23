package DTO;

import java.time.LocalDate;

public class UtenteDTO {
    private int idUtente;
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataNascita;
    private String luogoNascita;
    private String biografia;
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
                     LocalDate dataNascita, String luogoNascita, String biografia) {
        this.idUtente = idUtente;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataNascita = dataNascita;
        this.luogoNascita = luogoNascita;
        this.biografia = biografia;
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

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getLuogoNascita() {
        return luogoNascita;
    }

    public void setLuogoNascita(String luogoNascita) {
        this.luogoNascita = luogoNascita;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
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

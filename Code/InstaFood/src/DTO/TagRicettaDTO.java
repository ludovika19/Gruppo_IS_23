package DTO;

public class TagRicettaDTO {
    private int ricettaId;
    private int raccoltaId;
    private int utenteId;
    private int tagId;
    private String nomeTag;
    private String titoloRicetta;

    public TagRicettaDTO() {
        super();
    }

    public TagRicettaDTO(int ricettaId, int tagId) {
        this.ricettaId = ricettaId;
        this.tagId = tagId;
    }

    public TagRicettaDTO(int ricettaId, int raccoltaId, int utenteId, int tagId, 
                         String nomeTag, String titoloRicetta) {
        this.ricettaId = ricettaId;
        this.raccoltaId = raccoltaId;
        this.utenteId = utenteId;
        this.tagId = tagId;
        this.nomeTag = nomeTag;
        this.titoloRicetta = titoloRicetta;
    }

    public int getRicettaId() {
        return ricettaId;
    }

    public void setRicettaId(int ricettaId) {
        this.ricettaId = ricettaId;
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

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getNomeTag() {
        return nomeTag;
    }

    public void setNomeTag(String nomeTag) {
        this.nomeTag = nomeTag;
    }

    public String getTitoloRicetta() {
        return titoloRicetta;
    }

    public void setTitoloRicetta(String titoloRicetta) {
        this.titoloRicetta = titoloRicetta;
    }
}

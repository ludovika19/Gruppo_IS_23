package DTO;

public class TagDTO {
    private int idTag;
    private String nome;

    public TagDTO() {
        super();
    }

    public TagDTO(int idTag, String nome) {
        this.idTag = idTag;
        this.nome = nome;
    }

    public int getIdTag() {
        return idTag;
    }
    
    public void setIdTag(int idTag) {
        this.idTag = idTag;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

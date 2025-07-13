package entity;

import database.TagDAO;

public class Tag {
    private int id;
    private String nome;

    public Tag() {
        super();
    }

    public Tag(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Tag(TagDAO tagDAO) {
        this.id = tagDAO.getId();
        this.nome = tagDAO.getNome();
    }

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    @Override
    public String toString() {
        return nome;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tag tag = (Tag) obj;
        return id == tag.id;
    }
}

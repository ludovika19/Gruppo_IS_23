package entity;

import database.IngredienteDAO;

public class Ingrediente {
    private int id;
    private String nome;
    private int qta;

    public Ingrediente() {
        super();
    }

    public Ingrediente(int id, String nome, int qta) {
        this.id = id;
        this.nome = nome;
        this.qta = qta;
    }

    public Ingrediente(IngredienteDAO ingredienteDAO) {
        this.id = ingredienteDAO.getId();
        this.nome = ingredienteDAO.getNome();
        // La quantità viene gestita nella relazione Ricetta_has_Ingrediente
    }

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public int getQta() { return qta; }
    public void setQta(int qta) { this.qta = qta; }
}

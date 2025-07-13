package entity;

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
}

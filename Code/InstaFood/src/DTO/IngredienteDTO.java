package DTO;

public class IngredienteDTO {
    private int idIngrediente;
    private String nome;
    private int quantita; // per quando l'ingrediente è associato a una ricetta

    public IngredienteDTO() {
        super();
    }

    public IngredienteDTO(int idIngrediente, String nome) {
        this.idIngrediente = idIngrediente;
        this.nome = nome;
    }

    public IngredienteDTO(int idIngrediente, String nome, int quantita) {
        this.idIngrediente = idIngrediente;
        this.nome = nome;
        this.quantita = quantita;
    }

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}

package entity;

import database.RaccoltaDAO;

import java.util.List;

public class Raccolta {

    private int idRaccolta;
    private String titolo;
    private List<Ricetta> ricette;

    public Raccolta(String titolo) {
        this.titolo = titolo;
    }

    public boolean creaRaccolta() {
        RaccoltaDAO dao = new RaccoltaDAO();
        int idGenerato = dao.createRaccolta(this.idRaccolta);

        if (idGenerato > 0) {
            this.idRaccolta = idGenerato;
            return true;
        } else {
            return false;
        }
    }

    // Getters e setters

    public boolean aggiungiRicetta(int idRicetta) {
        RaccoltaDAO dao = new RaccoltaDAO(this.idRaccolta);
        return dao.aggiungiRicettaAllaRaccolta(idRicetta, idRaccolta);
    }

}

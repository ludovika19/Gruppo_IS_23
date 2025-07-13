package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredienteRicettaDAO {
    private int ricettaId;
    private int raccoltaId;
    private int utenteId;
    private int ingredienteId;
    private int quantita;

    public IngredienteRicettaDAO() {
        super();
    }

    public IngredienteRicettaDAO(int ricettaId, int raccoltaId, int utenteId, int ingredienteId) {
        this.ricettaId = ricettaId;
        this.raccoltaId = raccoltaId;
        this.utenteId = utenteId;
        this.ingredienteId = ingredienteId;
        readIngredienteRicetta();
    }

    // CREATE
    public int createIngredienteRicetta() {
        int ret = 0;
        String query = "INSERT INTO instafood.Ricetta_has_Ingrediente " +
                "(Ricetta_idRicetta, Ricetta_Raccolta_idRaccolta, " +
                "Ricetta_Raccolta_Utente_idUtente, Ingrediente_idIngrediente, Qta) " +
                "VALUES ('" + ricettaId + "', '" + raccoltaId + "', '" +
                utenteId + "', '" + ingredienteId + "', '" + quantita + "')";

        try {
            ret = DBConnectionManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            ret = -1;
        }
        return ret;
    }

    // READ
    public void readIngredienteRicetta() {
        String query = "SELECT * FROM instafood.Ricetta_has_Ingrediente " +
                "WHERE Ricetta_idRicetta = '" + ricettaId + "' " +
                "AND Ricetta_Raccolta_idRaccolta = '" + raccoltaId + "' " +
                "AND Ricetta_Raccolta_Utente_idUtente = '" + utenteId + "' " +
                "AND Ingrediente_idIngrediente = '" + ingredienteId+"';";

        try {
            ResultSet rs = DBConnectionManager.selectQuery(query);
            if(rs.next()) {
                this.quantita = rs.getInt("Qta");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public int updateIngredienteRicetta() {
        String query = "UPDATE instafood.Ricetta_has_Ingrediente " +
                "SET Qta = '" + quantita + "' " +
                "WHERE Ricetta_idRicetta = '" + ricettaId + "' " +
                "AND Ricetta_Raccolta_idRaccolta = '" + raccoltaId + "' " +
                "AND Ricetta_Raccolta_Utente_idUtente = '" + utenteId + "' " +
                "AND Ingrediente_idIngrediente = '" + ingredienteId+"';";

        try {
            return DBConnectionManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // DELETE
    public int deleteIngredienteRicetta() {
        String query = "DELETE FROM instafood.Ricetta_has_Ingrediente " +
                "WHERE Ricetta_idRicetta = '" + ricettaId + "' " +
                "AND Ricetta_Raccolta_idRaccolta = '" + raccoltaId + "' " +
                "AND Ricetta_Raccolta_Utente_idUtente = '" + utenteId + "' " +
                "AND Ingrediente_idIngrediente = '" + ingredienteId+"';";

        try {
            return DBConnectionManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 3. Ottieni ingredienti con quantità per una ricetta
    public List<IngredienteConQuantita> getIngredientiPerRicetta(int ricettaId) {
        List<IngredienteConQuantita> risultati = new ArrayList<>();
        String query = "SELECT i.idIngrediente, i.Nome, rhi.Qta " +
                "FROM instafood.ricetta_has_ingrediente rhi " +
                "JOIN instafood.Ingrediente i ON rhi.Ingrediente_idIngrediente = i.idIngrediente " +
                "WHERE rhi.Ricetta_idRicetta = '" + ricettaId+"';";

        try {
            ResultSet rs = DBConnectionManager.selectQuery(query);
            while (rs.next()) {
                IngredienteDAO ingrediente = new IngredienteDAO();
                ingrediente.setId(rs.getInt("idIngrediente"));
                ingrediente.setNome(rs.getString("Nome"));

                risultati.add(new IngredienteConQuantita(
                        ingrediente,
                        rs.getInt("Qta")
                ));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return risultati;
    }

    public List<entity.Ingrediente> getIngredientiByRicetta(int idRicetta) {
        List<entity.Ingrediente> ingredienti = new ArrayList<>();
        String query = "SELECT i.idIngrediente, i.Nome, ri.Qta FROM instafood.Ingrediente i " +
                      "INNER JOIN instafood.Ricetta_has_Ingrediente ri ON i.idIngrediente = ri.Ingrediente_idIngrediente " +
                      "WHERE ri.Ricetta_idRicetta = '" + idRicetta + "'";
        
        try {
            ResultSet rs = DBConnectionManager.selectQuery(query);
            
            while (rs.next()) {
                entity.Ingrediente ingrediente = new entity.Ingrediente();
                ingrediente.setId(rs.getInt("idIngrediente"));
                ingrediente.setNome(rs.getString("Nome"));
                ingrediente.setQta(rs.getInt("Qta"));
                ingredienti.add(ingrediente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return ingredienti;
    }

    public boolean addIngredienteToRicetta(int idRicetta, int idIngrediente, int quantita) {
        // Prima devo ottenere le informazioni della ricetta per raccolta e utente
        String queryRicetta = "SELECT Raccolta_idRaccolta, Raccolta_Utente_idUtente FROM instafood.Ricetta WHERE idRicetta = '" + idRicetta + "'";
        
        try {
            ResultSet rs = DBConnectionManager.selectQuery(queryRicetta);
            if (rs.next()) {
                int raccoltaId = rs.getInt("Raccolta_idRaccolta");
                int utenteId = rs.getInt("Raccolta_Utente_idUtente");
                
                String insertQuery = "INSERT INTO instafood.Ricetta_has_Ingrediente " +
                                   "(Ricetta_idRicetta, Ricetta_Raccolta_idRaccolta, " +
                                   "Ricetta_Raccolta_Utente_idUtente, Ingrediente_idIngrediente, Qta) " +
                                   "VALUES ('" + idRicetta + "', '" + raccoltaId + "', '" +
                                   utenteId + "', '" + idIngrediente + "', '" + quantita + "')";
                
                int rowsAffected = DBConnectionManager.updateQuery(insertQuery);
                return rowsAffected > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }

    // Classe interna per restituire risultati
    public static class IngredienteConQuantita {
        private final IngredienteDAO ingrediente;
        private final int quantita;

        public IngredienteConQuantita(IngredienteDAO ingrediente, int quantita) {
            this.ingrediente = ingrediente;
            this.quantita = quantita;
        }

        public IngredienteDAO getIngrediente() {
            return ingrediente;
        }

        public int getQuantita() {
            return quantita;
        }
    }

    // Getters e Setters
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

    public int getIngredienteId() {
        return ingredienteId;
    }

    public void setIngredienteId(int ingredienteId) {
        this.ingredienteId = ingredienteId;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
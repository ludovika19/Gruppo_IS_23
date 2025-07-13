package database;

import DTO.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagRicettaDAO {
    private int ricettaId;
    private int raccoltaId;
    private int utenteId;
    private int tagId;

    public TagRicettaDAO() {
        super();
    }

    public TagRicettaDAO(int ricettaId, int raccoltaId, int utenteId, int tagId) {
        this.ricettaId = ricettaId;
        this.raccoltaId = raccoltaId;
        this.utenteId = utenteId;
        this.tagId = tagId;
        readTagRicetta();
    }

    // CREATE
    public int createTagRicetta() {
        int ret = 0;
        String query = "INSERT INTO instafood.Ricetta_has_Tag " +
                "(Ricetta_idRicetta, Ricetta_Raccolta_idRaccolta, " +
                "Ricetta_Raccolta_Utente_idUtente, Tag_idTag) " +
                "VALUES ('" + ricettaId + "', '" + raccoltaId + "', '" +
                utenteId + "', '" + tagId + "')";

        try {
            ret = DBConnectionManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            ret = -1;
        }
        return ret;
    }

    // READ
    public void readTagRicetta() {
        String query = "SELECT * FROM instafood.Ricetta_has_Tag " +
                "WHERE Ricetta_idRicetta = '" + ricettaId + "' " +
                "AND Ricetta_Raccolta_idRaccolta = '" + raccoltaId + "' " +
                "AND Ricetta_Raccolta_Utente_idUtente = '" + utenteId + "' " +
                "AND Tag_idTag = '" + tagId+"';";

        try {
            ResultSet rs = DBConnectionManager.selectQuery(query);
            if(rs.next()) {
                // I valori sono già impostati nel costruttore
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE (non necessario in quanto la chiave è composta)
    public int updateTagRicetta() {
        // Non implementato perché la chiave primaria è composta
        // e non ci sono altri campi da aggiornare
        return -1;
    }

    // DELETE
    public int deleteTagRicetta() {
        String query = "DELETE FROM instafood.Ricetta_has_Tag " +
                "WHERE Ricetta_idRicetta = '" + ricettaId + "' " +
                "AND Ricetta_Raccolta_idRaccolta = '" + raccoltaId + "' " +
                "AND Ricetta_Raccolta_Utente_idUtente = '" + utenteId + "' " +
                "AND Tag_idTag = '" + tagId+"';";

        try {
            return DBConnectionManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 1. Ottieni i tag più usati (top 5)
    public ArrayList<TagDTO> getTagPiuUsati() {
        ArrayList<TagDTO> tagList = new ArrayList<>();
        String query = "SELECT t.idTag, t.Nome, COUNT(*) AS conteggio " +
                "FROM instafood.Ricetta_has_Tag rht " +
                "INNER JOIN instafood.Tag t ON rht.Tag_idTag = t.idTag " +
                "GROUP BY t.idTag, t.Nome " +
                "LIMIT 5";

        try {
            ResultSet rs = DBConnectionManager.selectQuery(query);
            while (rs.next()) {
                TagDTO tag = new TagDTO(rs.getInt("idTag"), rs.getString("Nome"));
                tagList.add(tag);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return tagList;
    }

    // 2. Ottieni tutti i tag associati a una ricetta
    public List<TagDAO> getTagPerRicetta(int ricettaId) {
        List<TagDAO> tagList = new ArrayList<>();
        String query = "SELECT idTag " +
                "FROM instafood.Ricetta_has_Tag " +
                "WHERE Ricetta_idRicetta = '" + ricettaId+"';";

        try {
            ResultSet rs = DBConnectionManager.selectQuery(query);
            while (rs.next()) {
                TagDAO tag = new TagDAO(rs.getInt("Tag_idTag"));
                tagList.add(tag);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return tagList;
    }

    public List<String> getTagByRicetta(int idRicetta) {
        List<String> tags = new ArrayList<>();
        String query = "SELECT t.Nome FROM instafood.Tag t " +
                      "INNER JOIN instafood.Ricetta_has_Tag rt ON t.idTag = rt.Tag_idTag " +
                      "WHERE rt.Ricetta_idRicetta = '" + idRicetta + "'";

        try {
            ResultSet rs = DBConnectionManager.selectQuery(query);

            while (rs.next()) {
                tags.add(rs.getString("Nome"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tags;
    }

    public boolean addTagToRicetta(int idRicetta, int idTag) {
        // Prima devo ottenere le informazioni della ricetta per raccolta e utente
        String queryRicetta = "SELECT Raccolta_idRaccolta, Raccolta_Utente_idUtente FROM instafood.Ricetta WHERE idRicetta = '" + idRicetta + "'";

        try {
            ResultSet rs = DBConnectionManager.selectQuery(queryRicetta);
            if (rs.next()) {
                int raccoltaId = rs.getInt("Raccolta_idRaccolta");
                int utenteId = rs.getInt("Raccolta_Utente_idUtente");

                String insertQuery = "INSERT INTO instafood.Ricetta_has_Tag " +
                                   "(Ricetta_idRicetta, Ricetta_Raccolta_idRaccolta, " +
                                   "Ricetta_Raccolta_Utente_idUtente, Tag_idTag) " +
                                   "VALUES ('" + idRicetta + "', '" + raccoltaId + "', '" +
                                   utenteId + "', '" + idTag + "')";

                int rowsAffected = DBConnectionManager.updateQuery(insertQuery);
                return rowsAffected > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
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

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}
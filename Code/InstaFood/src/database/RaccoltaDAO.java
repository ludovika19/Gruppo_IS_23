package database;

import DTO.RaccoltaDTO;

import java.sql.*;

public class RaccoltaDAO {
    private int idRaccolta;
    private String titolo;
    private Blob descrizione;
    private int Utente_idUtente;
    public RaccoltaDAO() {
        super();
    }

    public RaccoltaDAO(int id){
        this.idRaccolta = id;
        getRaccolta();
    }

    public int createRaccolta(int id){
        int ret = 0;

        String query = "INSERT INTO instafood.Raccolta (idRaccolta, Titolo, Descrizione, Utente_idUtente) VALUES ( \'"+id+"\',"+"\'"+this.titolo+"\','"+this.descrizione+"\','"+this.Utente_idUtente+"')";
        System.out.println(query);
        try{
            ret = DBConnectionManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            ret = -1;
        }
        return ret;
    }

    public void getRaccolta(){
        //1. definisco la query
        String query = "SELECT * FROM instafood.Raccolta WHERE idRaccolta='"+this.idRaccolta+"';";

        System.out.println(query); //per debug

        try {
            //2 faccio di query di select
            // - crea la connessione
            // - statement
            ResultSet rs = DBConnectionManager.selectQuery(query);

            if(rs.next()) { //se ho un risultato

                //mi vado a prendere i dati, accedendo tramite il nome dell'attributo-colonna
                //this.setNome(rs.getString("nome"));
                this.setTitolo(rs.getString("Titolo"));
                this.setDescrizione(rs.getBlob("Descrizione"));
                this.setUtente_idUtente(rs.getInt("Utente_idUtente"));
            }

        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int deleteRaccolta(){
        String query = "DELETE FROM instafood.Raccolta WHERE idRaccolta='"+this.idRaccolta+"';";

        System.out.println(query); //per debug

        int ritorno=0;
        try {
            //2 faccio di query di delete
            // - crea la connessione
            // - statement
            ritorno = DBConnectionManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            ritorno=-1;
        }
        return ritorno;
    }

    public int updateRaccolta(){
        int ret = 0;

        String query = "UPDATE instafood.Raccolta SET Titolo='"+this.titolo+"', Descrizione='"+ this.descrizione +"', Utente_idUtente='"+ this.Utente_idUtente +"' WHERE idRaccolta='"+this.idRaccolta+"';" ;
        System.out.println(query);
        try{
            ret = DBConnectionManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            ret = -1;
        }
        return ret;
    }

    public boolean aggiungiRicettaAllaRaccolta(int idRicetta, int idRaccolta) {
        String query = "UPDATE Ricette SET idRaccolta = ? WHERE idRicetta = ?";

        try (Connection conn = DBConnectionManager.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idRaccolta);
            stmt.setInt(2, idRicetta);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("[DAO] Errore durante l'aggiornamento della ricetta: " + e.getMessage());
            return false;
        }
    }


    // Metodi per restituire DTO
    public RaccoltaDTO toDTO() {
        return DTOConverter.convertToDTO(this);
    }

    public int getIdRaccolta() {
        return idRaccolta;
    }
    public void setIdRaccolta(int idRaccolta) {
        this.idRaccolta = idRaccolta;
    }
    public String getTitolo() {
        return titolo;
    }
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
    public Blob getDescrizione() {
        return descrizione;
    }
    public void setDescrizione(Blob descrizione) {
        this.descrizione = descrizione;
    }
    public int getUtente_idUtente() {
        return Utente_idUtente;
    }
    public void setUtente_idUtente(int utente_idUtente) {
        Utente_idUtente = utente_idUtente;
    }
}

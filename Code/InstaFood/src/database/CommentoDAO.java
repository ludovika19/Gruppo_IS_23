package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class CommentoDAO {
    private int idCommento;
    private String testo;
    private Date data;
    private int utente_idUtente;
    private int ricetta_idRicetta;

    public CommentoDAO(){
        super();
    }

    public CommentoDAO(int id){
        this.idCommento = id;
        readCommento();
    }

    public int createCommento(int id){
        int ret = 0;

        String query = "INSERT INTO instafood.Commento (idCommento, Testo, Data, Utente_idUtente, Ricetta_idRicetta) VALUES" +
                        " ( \'"+id+"\',"+"\'"+this.testo+"\','"+this.data+"\','"+this.utente_idUtente+"\','"+this.ricetta_idRicetta+"')";
        System.out.println(query);
        try{
            ret = DBConnectionManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            ret = -1;
        }
        return ret;
    }

    public void readCommento(){
        //1. definisco la query
        String query = "SELECT * FROM instafood.Commento WHERE idCommento='"+this.idCommento+"';";

        System.out.println(query); //per debug

        try {

            //2 faccio di query di select
            // - crea la connessione
            // - statement
            ResultSet rs = DBConnectionManager.selectQuery(query);

            if(rs.next()) { //se ho un risultato
                //INSERT INTO Utente (idUtente, Nome, Cognome, Password, Email, DataNascita, LuogoNascita, Biografia, Immagine)

                //mi vado a prendere i dati, accedendo tramite il nome dell'attributo-colonna
                //this.setNome(rs.getString("nome"));
                this.setTesto(rs.getString("Testo"));
                this.setData(rs.getDate("Data"));
                this.setUtente_idUtente(rs.getInt("Utente_idUtente"));
                this.setRicetta_idRicetta(rs.getInt("Ricetta_idRicetta"));
            }

        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //ottengo il numero di commenti per ogni ricetta
    public int getNumCommentiPerRicetta(){
        int commentiTot = 0;

        String query = "SELECT COUNT(*) AS NumeroCommentiRicevuti\n" +
                        "FROM instafood.Commento as c\n" +
                        "WHERE c.Ricetta_idRicetta='"+this.ricetta_idRicetta+"';";

        try (Connection conn = DBConnectionManager.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                commentiTot = rs.getInt("TotaleLike");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return commentiTot;
    }

    //ottengo le ricette con più commenti
    public ArrayList<RicettaDAO> getRicettaWithMostComment(){
        ArrayList<RicettaDAO> listaRicetta = new ArrayList<>();

        String query = "SELECT r.idRicetta, COUNT(c.idCommento) AS NumeroCommenti\n" +
                        "FROM instafood.Ricetta r\n" +
                        "LEFT JOIN instafood.Commento c ON r.idRicetta = c.Ricetta_idRicetta\n" +
                        "GROUP BY r.idRicetta\n" +
                        "ORDER BY NumeroCommenti DESC\n" +
                        "LIMIT 3;";
        try{
            ResultSet rs = DBConnectionManager.selectQuery(query);

            while ((rs.next())){
                RicettaDAO ricetta_temp = new RicettaDAO(rs.getInt("idRicetta"));
                listaRicetta.add(ricetta_temp);
            }
        }catch ( ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return   listaRicetta;
    }

    //ottengo gli ultimi 3 commenti associati a una ricetta
    public ArrayList<CommentoDAO> getLast3Comments(){
        ArrayList<CommentoDAO> listaCommento = new ArrayList<>();

        String query = "SELECT *\n" +
                        "FROM instafood.Commento\n" +
                        "WHERE Ricetta_idRicetta = '"+ this.ricetta_idRicetta +"'\n" +
                        "ORDER BY Data DESC\n" +
                        "LIMIT 3;";
        try{
            ResultSet rs = DBConnectionManager.selectQuery(query);

            while ((rs.next())){
                CommentoDAO commento_temp = new CommentoDAO(rs.getInt("idCommento"));
                listaCommento.add(commento_temp);
            }
        }catch ( ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return   listaCommento;
    }

    //tutti i commenti associasti a una ricetta
    public ArrayList<CommentoDAO> getCommentiRicetta(){
        ArrayList<CommentoDAO> listaCommento = new ArrayList<>();

        String query = "SELECT *\n" +
                "FROM instafood.Commento\n" +
                "WHERE Ricetta_idRicetta = '"+ this.ricetta_idRicetta +"';";
        try{
            ResultSet rs = DBConnectionManager.selectQuery(query);

            while ((rs.next())){
                CommentoDAO commento_temp = new CommentoDAO();
                //listaCommento.add(commento_temp);

                commento_temp.setTesto(rs.getString("Testo"));
                commento_temp.setData(rs.getDate("Data"));
                commento_temp.setUtente_idUtente(rs.getInt("Utente_idUtente"));
                commento_temp.setRicetta_idRicetta(rs.getInt("Ricetta_idRicetta"));
                listaCommento.add(commento_temp);
            }
        }catch ( ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return   listaCommento;
    }

    public int getIdCommento() {
        return idCommento;
    }
    public void setIdCommento(int idCommento) {
        this.idCommento = idCommento;
    }
    public String getTesto() {
        return testo;
    }
    public void setTesto(String testo) {
        this.testo = testo;
    }
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
    public int getUtente_idUtente() {
        return utente_idUtente;
    }
    public void setUtente_idUtente(int utente_idUtente) {
        this.utente_idUtente = utente_idUtente;
    }
    public int getRicetta_idRicetta() {
        return ricetta_idRicetta;
    }
    public void setRicetta_idRicetta(int ricetta_idRicetta) {
        this.ricetta_idRicetta = ricetta_idRicetta;
    }
}

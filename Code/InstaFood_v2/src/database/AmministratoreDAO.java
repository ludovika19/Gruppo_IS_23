package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AmministratoreDAO {
    private int idAmministratore;
    private String username;
    private String password;

    public AmministratoreDAO() {
        super();
    }

    public int autenticazione(String username, String password) {
        int autenticazione=0;
        //1. definisco la query
        String query = "SELECT * FROM instafood.Amministratore WHERE Username='"+username+"' AND Password='"+password+"';";

        System.out.println(query); //per debug

        try {
            //2 faccio di query di select
            // - crea la connessione
            // - statement
            ResultSet rs = DBConnectionManager.selectQuery(query);

            if(rs.next()) { //se ho un risultato
                //mi vado a prendere i dati, accedendo tramite il nome dell'attributo-colonna
                //this.setNome(rs.getString("nome"));
                this.setIdAmministratore(rs.getInt("idAmministratore"));
            } else{
                autenticazione = -1;
            }

        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return autenticazione;
    }

    public int getIdAmministratore() {
        return idAmministratore;
    }
    public void setIdAmministratore(int idAmministratore) {
        this.idAmministratore = idAmministratore;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

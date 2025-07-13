package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagDAO {
    private int id;
    private String nome;

    public TagDAO(){
        super();
    }

    public TagDAO(int id){
        this.id = id;
        getTag();
    }

    public int createTag(int id){
        int ret = 0;

        String query = "INSERT INTO instafood.Tag (idTag, Nome) VALUES ( \'"+id+"\',"+"\'"+this.nome+"')";
        System.out.println(query);
        try{
            ret = DBConnectionManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            ret = -1;
        }
        return ret;
    }

    public void getTag(){
        //1. definisco la query
        String query = "SELECT * FROM instafood.Tag WHERE idTag='"+this.id+"';";

        System.out.println(query); //per debug

        try {
            //2 faccio di query di select
            // - crea la connessione
            // - statement
            ResultSet rs = DBConnectionManager.selectQuery(query);

            if(rs.next()) { //se ho un risultato
                //mi vado a prendere i dati, accedendo tramite il nome dell'attributo-colonna
                //this.setNome(rs.getString("nome"));
                this.setNome(rs.getString("Nome"));
            }

        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int deleteTag(){
        String query = "DELETE FROM instafood.Tag WHERE idTag='"+this.id+"';";

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

    public int updateTag(){
        int ret = 0;

        String query = "UPDATE instafood.Tag SET Nome='"+this.nome+"' WHERE idTag='"+this.id+"';" ;
        System.out.println(query);
        try{
            ret = DBConnectionManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            ret = -1;
        }
        return ret;
    }

    public int getIdByNome(String nomeTag) {
        String query = "SELECT idTag FROM instafood.Tag WHERE Nome = '" + nomeTag + "'";
        
        try {
            ResultSet rs = DBConnectionManager.selectQuery(query);
            if (rs.next()) {
                return rs.getInt("idTag");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return -1; // Tag non trovato
    }

    public int createTag(String nomeTag) {
        // Genero un ID univoco
        int nuovoId = (int) (System.currentTimeMillis() % 1000000);
        
        String query = "INSERT INTO instafood.Tag (idTag, Nome) VALUES ('" + nuovoId + "', '" + nomeTag + "')";
        
        try {
            int rowsAffected = DBConnectionManager.updateQuery(query);
            if (rowsAffected > 0) {
                return nuovoId;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        return -1;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}

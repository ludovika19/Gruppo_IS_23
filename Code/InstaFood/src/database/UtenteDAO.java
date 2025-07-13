package database;

import DTO.UtenteDTO;
import java.sql.*;
import java.util.ArrayList;

public class UtenteDAO {
    private int id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private Date dataN;
    private String luogoN;
    private Blob biografia;
    private Blob immagine;

    public  UtenteDAO(){
        super();
    }

    public UtenteDAO(int id){
        this.id = id;
        readUtente();
    }

    public void readUtente(){
        //1. definisco la query
        String query = "SELECT * FROM instafood.Utente WHERE idUtente='"+this.id+"';";

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
                this.setNome(rs.getString("Nome"));
                this.setCognome(rs.getString("Cognome"));
                this.setPassword(rs.getString("Password"));
                this.setEmail(rs.getString("Email"));
                this.setDataN(rs.getDate("DataNascita"));
                this.setLuogoN(rs.getString("LuogoNascita"));
                this.setBiografia(rs.getBlob("Biografia"));
                this.setImmagine(rs.getBlob("Immagine"));
            }

        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int createUtente(int id){
        int ret = 0;

        String query = "INSERT INTO instafood.Utente (idUtente, Nome, Cognome, Password, Email, DataNascita, LuogoNascita, Biografia, Immagine) VALUES ( \'"+id+"\',"+"\'"+this.nome+"\','"+this.cognome+"\','"+this.password+"\','"+this.email+"\','"+this.dataN+"\','"+this.luogoN+"\','"+this.biografia+"\','"+this.immagine+"')";
        System.out.println(query);
        try{
            ret = DBConnectionManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            ret = -1;
        }
        return ret;
    }

    public ArrayList<UtenteDAO> getListaUtenti(){
        ArrayList<UtenteDAO> listaUtenti = new ArrayList<>();
        String query = "SELECT * FROM instafood.Utente;";
        try{
            ResultSet rs = DBConnectionManager.selectQuery(query);

            while ((rs.next())){
                UtenteDAO utente_temp = new UtenteDAO();

                utente_temp.setId(rs.getInt("idUtente"));
                utente_temp.setNome(rs.getString("Nome"));
                utente_temp.setCognome(rs.getString("Cognome"));
                utente_temp.setPassword(rs.getString("Password"));
                utente_temp.setEmail(rs.getString("Email"));
                utente_temp.setDataN(rs.getDate("DataNascita"));
                utente_temp.setLuogoN(rs.getString("LuogoNascita"));
                utente_temp.setBiografia(rs.getBlob("Biografia"));
                utente_temp.setImmagine(rs.getBlob("Immagine"));
                listaUtenti.add(utente_temp);
            }
        }catch ( ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return  listaUtenti;
    }

    public int deleteUtente(){
        String query = "DELETE FROM instafood.Utente WHERE idUtente='"+this.id+"';";

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

    public int updateUtente(){
        int ret = 0;

        String query = "UPDATE instafood.Utente SET Nome='"+this.nome+"', Cognome='"+ this.cognome +"', Password='"+ this.password +"', Email='"+ this.email +"', DataNascita='"+ this.dataN +"', LuogoNascita='"+ this.luogoN +"', Biografia='"+ this.biografia +"', Immagine='"+ this.immagine+"' WHERE idUtente='"+this.id+"';" ;
        System.out.println(query);
        try{
            ret = DBConnectionManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            ret = -1;
        }
        return ret;
    }

    public int getLikeTotali() {
        int likeTotali=0;

        // esegui query e ritorna lista di oggetti Ricetta
        String query = "SELECT SUM(r.`Like`) AS TotaleLike FROM instafood.Utente as u JOIN instafood.Ricetta as r ON u.idUtente = r.Utente_idUtente WHERE u.idUtente='"+this.id+"';";
        try (Connection conn = DBConnectionManager.GetConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                likeTotali = rs.getInt("TotaleLike");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return likeTotali;
    }

    // Metodi per restituire DTO
    public UtenteDTO toDTO() {
        return DTOConverter.convertToDTO(this);
    }

    public ArrayList<UtenteDTO> getListaUtentiDTO(){
        ArrayList<UtenteDTO> listaUtentiDTO = new ArrayList<>();
        ArrayList<UtenteDAO> listaUtenti = getListaUtenti();
        
        for (UtenteDAO utente : listaUtenti) {
            listaUtentiDTO.add(DTOConverter.convertToDTO(utente));
        }
        
        return listaUtentiDTO;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Date getDataN() {
        return dataN;
    }
    public void setDataN(Date dataN) {
        this.dataN = dataN;
    }
    public String getLuogoN() {
        return luogoN;
    }
    public void setLuogoN(String luogoN) {
        this.luogoN = luogoN;
    }
    public Blob getBiografia() {
        return biografia;
    }
    public void setBiografia(Blob biografia) {
        this.biografia = biografia;
    }
    public Blob getImmagine() {
        return immagine;
    }
    public void setImmagine(Blob immagine) {
        this.immagine = immagine;
    }
}

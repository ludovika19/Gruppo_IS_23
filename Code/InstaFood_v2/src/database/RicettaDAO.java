package database;

import DTO.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


public class RicettaDAO {

    private int id;
    private String titolo;
    private String descrizione;
    private int tempo;
    private int like;
    private boolean pubblica;
    private Date data;
    private int raccolta_idRaccolta;
    private int raccolta_Utente_idUtente;
    private int Utente_idUtente;

    public ArrayList<RicettaDTO> getLastRicette() {
        ArrayList<RicettaDTO> ricette = new ArrayList<>();

        // esegui query e ritorna lista di oggetti Ricetta
        String sql = "SELECT " +
                "r.idRicetta, " +
                "r.Titolo, " +
                "r.Descrizione, " +
                "r.Tempo, " +
                "r.`Like` AS NumeroLike, " +
                "r.Data AS DataPubb, " +
                "u.Nome AS AutoreNome, " +
                "u.Cognome AS AutoreCognome " +
                "FROM instafood.ricetta r " +
                "INNER JOIN instafood.utente u ON r.Utente_idUtente = u.idUtente " +
                "WHERE r.Visibilita = 1 " +
                "ORDER BY r.Data DESC " +
                "LIMIT 5";

        try {
            ResultSet rs = DBConnectionManager.selectQuery(sql);

            while (rs.next()) {
                RicettaDTO dto = new RicettaDTO();
                dto.setTitolo(rs.getString("Titolo"));
                dto.setDescrizioneCompleta(rs.getString("Descrizione"));
                dto.setTempo(rs.getInt("Tempo"));
                dto.setId(rs.getInt("idRicetta"));
                dto.setAutore(rs.getString("AutoreNome") + " " + rs.getString("AutoreCognome"));
                dto.setNumeroLike(rs.getInt("NumeroLike"));
                dto.setDataPubblicazione(rs.getDate("DataPubb"));

                ricette.add(dto);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return ricette;
    }

    public RicettaDAO(){
        super();
    }

    public RicettaDAO(int id){
        this.id = id;
        readRicetta();
    }

    public int creaRicetta(int id){
        int ret = 0;

        String query = "INSERT INTO instafood.ricetta (idRicetta, Titolo, Descrizione, Tempo, `Like`, Visibilita, Data, Raccolta_idRaccolta, Raccolta_Utente_idUtente, Utente_idUtente) VALUES ( \'"+id+"\',"+"\'"+this.titolo+"\','"+this.descrizione+"\','"+this.tempo+"\','"+this.like+"\','"+this.pubblica+"\','"+this.data+"\','"+this.raccolta_idRaccolta+"\','"+this.raccolta_Utente_idUtente+"\','"+this.Utente_idUtente+"')";

        System.out.println(query);
        try{
            ret = DBConnectionManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            ret = -1;
        }
        return ret;
    }

    public void readRicetta(){
        //1. definisco la query
        String query = "SELECT * FROM instafood.ricetta WHERE idRicetta='"+this.id+"';";

        System.out.println(query); //per debug

        try {
            //2 faccio di query di select
            // - crea la connessione
            // - statement
            ResultSet rs = DBConnectionManager.selectQuery(query);

            if(rs.next()) { //se ho un risultato
                //idRicetta, Titolo, Descrizione, Tempo, `Like`, Visibilita, Data, Raccolta_idRaccolta, Raccolta_Utente_idUtente, Utente_idUtente
                //mi vado a prendere i dati, accedendo tramite il nome dell'attributo-colonna
                //this.setNome(rs.getString("nome"));
                this.setTitolo(rs.getString("Titolo"));
                this.setDescrizione(rs.getString("Descrizione"));
                this.setTempo(rs.getInt("Tempo"));
                this.setLike(rs.getInt("`Like`"));
                this.setPubblica(rs.getBoolean("Visibilita"));
                this.setData(rs.getDate("Data"));
                this.setRaccolta_idRaccolta(rs.getInt("Raccolta_idRaccolta"));
                this.setRaccolta_Utente_idUtente(rs.getInt("Raccolta_Utente_idUtente"));
                this.setUtente_idUtente(rs.getInt("Utente_idUtente"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ArrayList<RicettaDAO> getListaRicette(){
        ArrayList<RicettaDAO> listaRicette = new ArrayList<>();
        String query = "SELECT * FROM instafood.ricetta WHERE Utente_idUtente='"+ this.Utente_idUtente+"';";
        try{
            ResultSet rs = DBConnectionManager.selectQuery(query);

            while ((rs.next())){
                RicettaDAO ricetta_temp = new RicettaDAO();

                ricetta_temp.setTitolo(rs.getString("Titolo"));
                ricetta_temp.setDescrizione(rs.getString("Descrizione"));
                ricetta_temp.setTempo(rs.getInt("Tempo"));
                ricetta_temp.setLike(rs.getInt("`Like`"));
                ricetta_temp.setPubblica(rs.getBoolean("Visibilita"));
                ricetta_temp.setData(rs.getDate("Data"));
                ricetta_temp.setRaccolta_idRaccolta(rs.getInt("Raccolta_idRaccolta"));
                ricetta_temp.setRaccolta_Utente_idUtente(rs.getInt("Raccolta_Utente_idUtente"));
                ricetta_temp.setUtente_idUtente(rs.getInt("Utente_idUtente"));
                listaRicette.add(ricetta_temp);
            }
        }catch ( ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return  listaRicette;
    }

    public int deleteRicetta(){
        String query = "DELETE FROM instafood.ricetta WHERE idRicetta='"+this.id+"';";

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

    public int updateRicetta(){
        int ret = 0;
        //idRicetta, Titolo, Descrizione, Tempo, `Like`, Visibilita, Data, Raccolta_idRaccolta, Raccolta_Utente_idUtente, Utente_idUtente
        String query = "UPDATE instafood.ricetta SET Titolo='"+this.titolo+"', Descrizione='"+ this.descrizione +"', Tempo='"+ this.tempo +"', `Like`='"+ this.like +"', Visibilita='"+ this.pubblica +"', Data='"+ this.data +"', Raccolta_idRaccolta='"+ this.raccolta_idRaccolta +"', Raccolta_Utente_idUtente='"+ this.raccolta_Utente_idUtente +"', Utente_idUtente='"+ this.Utente_idUtente +"' WHERE idRicetta='"+this.id+"';" ;
        System.out.println(query);
        try{
            ret = DBConnectionManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            ret = -1;
        }
        return ret;
    }

    public RicettaDAO getMiglioreRicetta(){
        RicettaDAO ricettamigliore = null;
        String query = "SELECT r.idRicetta FROM instafood.ricetta as r JOIN instafood.utente as u ON r.Utente_idUtente = u.idUtente ORDER BY r.`Like` DESC LIMIT 1;";
        try{
            ResultSet rs = DBConnectionManager.selectQuery(query);

            while ((rs.next())){
                ricettamigliore = new RicettaDAO(rs.getInt("idRicetta"));
            }
        }catch ( ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return  ricettamigliore;
    }

    public ArrayList<UtenteDTO> autoriConPiuRicette(){
        ArrayList<UtenteDTO> autoriConPiuRicette = null;

        String query = "SELECT u.idUtente AS ID, CONCAT(u.Nome, ' ', u.Cognome) AS Autore, COUNT(r.idRicetta) AS RicetteInserite" +
                        "FROM instafood.utente u" +
                        "INNER JOIN instafood.ricetta r ON u.idUtente = r.Utente_idUtente" +
                        "GROUP BY u.idUtente, u.Nome, u.Cognome" +
                        "ORDER BY RicetteInserite DESC;";
        try{
            ResultSet rs = DBConnectionManager.selectQuery(query);
            while ((rs.next())){
                UtenteDAO utente_temp = new UtenteDAO(rs.getInt("idUtente"));                
                autoriConPiuRicette.add(DTOConverter.convertToDTO(utente_temp));
            }

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return  autoriConPiuRicette;
    }

    public int createRicetta(entity.Ricetta ricetta) {
        int ret = 0;
        
        // Genero un ID univoco (in un'applicazione reale useresti auto-increment)
        int nuovoId = (int) (System.currentTimeMillis() % 1000000);
        
        String query = "INSERT INTO instafood.Ricetta (idRicetta, Titolo, Descrizione, Tempo, `Like`, Visibilita, Data, " +
                      "Raccolta_idRaccolta, Raccolta_Utente_idUtente, Utente_idUtente) VALUES " +
                      "('" + nuovoId + "', '" + ricetta.getTitolo() + "', '" + ricetta.getDescrizione() + "', " +
                      "'" + ricetta.getTempoPreparazione() + "', '" + ricetta.getLike() + "', " +
                      "'" + (ricetta.isPubblica() ? 1 : 0) + "', CURDATE(), " +
                      "'" + ricetta.getRaccolta().getIdRaccolta() + "', '" + ricetta.getAutore().getId() + "', " +
                      "'" + ricetta.getAutore().getId() + "')";
        
        System.out.println(query);
        try {
            int rowsAffected = DBConnectionManager.updateQuery(query);
            if (rowsAffected > 0) {
                ret = nuovoId;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            ret = -1;
        }
        return ret;
    }

    public ArrayList<entity.Ricetta> getListaRicetteAsEntity() {
        ArrayList<entity.Ricetta> listaRicette = new ArrayList<>();
        String query = "SELECT * FROM instafood.ricetta WHERE Utente_idUtente='" + this.Utente_idUtente + "';";
        
        try {
            ResultSet rs = DBConnectionManager.selectQuery(query);
            
            while (rs.next()) {
                RicettaDAO ricettaDAO = new RicettaDAO();
                ricettaDAO.setId(rs.getInt("idRicetta"));
                ricettaDAO.setTitolo(rs.getString("Titolo"));
                ricettaDAO.setDescrizione(rs.getString("Descrizione"));
                ricettaDAO.setTempo(rs.getInt("Tempo"));
                ricettaDAO.setLike(rs.getInt("Like"));
                ricettaDAO.setPubblica(rs.getBoolean("Visibilita"));
                ricettaDAO.setData(rs.getDate("Data"));
                ricettaDAO.setRaccolta_idRaccolta(rs.getInt("Raccolta_idRaccolta"));
                ricettaDAO.setRaccolta_Utente_idUtente(rs.getInt("Raccolta_Utente_idUtente"));
                ricettaDAO.setUtente_idUtente(rs.getInt("Utente_idUtente"));
                
                entity.Ricetta ricetta = new entity.Ricetta(ricettaDAO);
                listaRicette.add(ricetta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listaRicette;
    }

    public boolean updateLike(int idRicetta, int nuovoNumeroLike) {
        String query = "UPDATE instafood.Ricetta SET `Like` = '" + nuovoNumeroLike + "' WHERE idRicetta = '" + idRicetta + "'";
        
        try {
            int rowsAffected = DBConnectionManager.updateQuery(query);
            return rowsAffected > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int ricettePubblicatePerIntervallo(Date dataInizio, Date dataFine) {
        String query = "SELECT COUNT(*) AS numero_ricette" +
                        "FROM instafood.Ricetta" +
                        "WHERE Data BETWEEN '" + dataInizio + "' AND '" + dataFine + "';";
        
        try {
            ResultSet rs = DBConnectionManager.selectQuery(query);
            if (rs.next()) {
                return rs.getInt("NumeroRicette");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitolo() {
        return titolo;
    }
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
    public String getDescrizione() {
        return descrizione;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public int getTempo() {
        return tempo;
    }
    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
    public int getLike() {
        return like;
    }
    public void setLike(int like) {
        this.like = like;
    }
    public boolean isPubblica() {
        return pubblica;
    }
    public void setPubblica(boolean pubblica) {
        this.pubblica = pubblica;
    }
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
    public int getRaccolta_idRaccolta() {
        return raccolta_idRaccolta;
    }
    public void setRaccolta_idRaccolta(int raccolta_idRaccolta) {
        this.raccolta_idRaccolta = raccolta_idRaccolta;
    }
    public int getRaccolta_Utente_idUtente() {
        return raccolta_Utente_idUtente;
    }
    public void setRaccolta_Utente_idUtente(int raccolta_Utente_idUtente) {
        this.raccolta_Utente_idUtente = raccolta_Utente_idUtente;
    }
    public int getUtente_idUtente() {
        return Utente_idUtente;
    }
    public void setUtente_idUtente(int utente_idUtente) {
        this.Utente_idUtente = utente_idUtente;
    }
}

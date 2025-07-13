package database;

import java.sql.*;
import java.lang.String;

public class DBConnectionManager {

    public static String url = "jdbc:mysql://localhost:3306/";
    public static String dbName = "instafood";
    public static String driver = "com.mysql.cj.jdbc.Driver";
    public static String userName = "super_admin";
    public static String password = "Str0ngP@ss!";

    public static Connection GetConnection()throws ClassNotFoundException, SQLException{
        Connection conn = null;
        Class.forName(driver);

        conn = DriverManager.getConnection(url.concat(dbName), userName, password);

        return conn;
    }

    public static void closeConnection(Connection conn)throws SQLException{
        conn.close();
    }

    public static ResultSet selectQuery(String query) throws ClassNotFoundException, SQLException {


        //.1 creo la connessione

        Connection conn = GetConnection();


        //2. creo lo statement
        Statement statment = conn.createStatement();

        //eseguo la query che ho fornito come input
        ResultSet ret = statment.executeQuery(query); //"SELECT * from STUDENTI where .... "


        //ci ritorna il result set
        return ret;
    }

    public static int updateQuery(String query) throws ClassNotFoundException, SQLException {

        Connection conn = GetConnection();
        Statement statement = conn.createStatement();
        int ret = statement.executeUpdate(query);
        conn.close();
        return ret;
    }
}

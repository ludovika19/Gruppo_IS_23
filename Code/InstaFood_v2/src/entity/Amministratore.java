package entity;

import database.AmministratoreDAO;

public class Amministratore {
    private int id;
    private String username;
    private String password;

    public Amministratore() {
        super();
    }

    public Amministratore(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Amministratore(AmministratoreDAO amministratoreDAO) {
        this.id = amministratoreDAO.getIdAmministratore();
        this.username = amministratoreDAO.getUsername();
        this.password = amministratoreDAO.getPassword();
    }

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

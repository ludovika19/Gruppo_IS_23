package entity;

import DTO.*;
import database.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Sistema {
    private static Sistema instance;
    // Metodo per ottenere le ricette per il feed
    public ArrayList<RicettaDTO> getFeed() {
        RicettaDAO ricettaDAO = new RicettaDAO();
        return ricettaDAO.getLastRicette();
    }

    public static Sistema getInstance() throws ClassNotFoundException, SQLException {
		if(instance==null) {
			instance=new Sistema();
		}
		return instance;
	}
    
    /*
    // Metodo per la ricerca di ricette (versione semplificata)
    public List<RicettaDTO> ricercaRicette(String termineRicerca) {
        RicettaDAO ricettaDAO = new RicettaDAO();
        // Implementazione semplificata - normalmente si userebbe un metodo specifico
        return ricettaDAO.getLastRicette(); // Placeholder
    }
    
    // Metodo per ottenere le ricette più popolari (versione semplificata)
    public List<RicettaDTO> getRicettePopolari() {
        RicettaDAO ricettaDAO = new RicettaDAO();
        // Implementazione semplificata - normalmente si ordinerebbe per numero di like
        return ricettaDAO.getLastRicette(); // Placeholder
    }
    */

    // Metodo per ottenere report per un amministratore
    public ArrayList<Object> generaReport(Date dataInizio, Date dataFine){
        ArrayList<Object> report = new ArrayList<>();

        //numero di ricette pubblicate in un dato intervallo temporale
        int numRicette = new RicettaDAO().ricettePubblicatePerIntervallo(dataInizio, dataFine);
        report.add(numRicette);

        //autori con più ricette
        RicettaDAO autoriWithMostRecepie = new RicettaDAO();
        ArrayList<UtenteDTO> utente = autoriWithMostRecepie.autoriConPiuRicette();
        report.add(utente);

        //tag più usati
        TagRicettaDAO tagMostUsed = new TagRicettaDAO();
        ArrayList<TagDTO> tag = tagMostUsed.getTagPiuUsati();
        report.add(tag);

        //ricette con più commenti
        CommentoDAO commentiDAO = new CommentoDAO();
        ArrayList<RicettaDTO> ricetteWithMostComments = commentiDAO.getRicettaWithMostComment();
        report.add(ricetteWithMostComments);

        return report;
    }
    
    // Metodo per registrare un nuovo utente (versione semplificata)
    public boolean registraUtente(UtenteDTO nuovoUtente) {
        database.UtenteDAO utenteDAO = new database.UtenteDAO();
        utenteDAO.setId(nuovoUtente.getIdUtente());
        utenteDAO.setNome(nuovoUtente.getNome());
        utenteDAO.setCognome(nuovoUtente.getCognome());
        utenteDAO.setEmail(nuovoUtente.getEmail());
        utenteDAO.setPassword(nuovoUtente.getPassword());
        utenteDAO.setDataN(nuovoUtente.getDataNascita());
        utenteDAO.setLuogoN(nuovoUtente.getLuogoNascita());
        utenteDAO.setBiografia(nuovoUtente.getBiografia());
        utenteDAO.setImmagine(nuovoUtente.getImmagine()); 

        return utenteDAO.createUtente(0) > 0; // Placeholder
    }
    /*
    // Metodo per ottenere tutti gli ingredienti (versione semplificata)
    public List<IngredienteDTO> getAllIngredienti() {
        IngredienteDAO ingredienteDAO = new IngredienteDAO();
        // Implementazione semplificata
        return new java.util.ArrayList<>(); // Placeholder
    }
    */

    // Metodo per ottenere tutti i tag più usati
    public ArrayList<TagDTO> getTagMostUsed() {
        TagRicettaDAO tagDTO = new TagRicettaDAO();        
        return tagDTO.getTagPiuUsati(); 
    }
    
    public int autenticaAmministratore(String username, String password) {
        AmministratoreDAO amministratoreDAO = new AmministratoreDAO();
        return amministratoreDAO.autenticazione(username, password);
    }
    
    // Metodo per autenticare un utente
    public Utente autenticaUtente(String email, String password) {
        UtenteDAO utenteDAO = new UtenteDAO();
        UtenteDAO utenteAutenticato = utenteDAO.autenticazione(email, password);
        
        if (utenteAutenticato != null) {
            return new Utente(utenteDAO);
        }
        
        return null; // Autenticazione fallita
    }

    public void inviaNotifica(CommentoDTO commento) {
        // Metodo per inviare una notifica all'utente
        if (commento.getUtenteId() != 0  && commento.getTesto() != null && commento.getRicettaId() != 0) {
            int idRicetta = commento.getRicettaId();
            
            UtenteDAO utenteDAO = new UtenteDAO();
            utenteDAO.inviaNotificaDB(idRicetta);
            
            System.out.println("Notifica inviata a " + commento.getAutore() + ": " + commento.getTesto());
        }
    }

    public ArrayList<String> getNotificheUtente(int idUtente) {
        ArrayList<String> notifiche = new ArrayList<>();

        UtenteDAO utenteDAO = new UtenteDAO();
        utenteDAO.setId(idUtente);
        String notifica = utenteDAO.getNotificaDB();

        if (notifica == null || notifica.isEmpty()) {
            return new ArrayList<>(); // Nessuna notifica
        }
        else{
            String[] parole = notifica.split("-");
            for (int i = 0; i < parole.length; i++) {
                notifiche.add(parole[i]);
            }
            return notifiche;
        }

         
    }
}

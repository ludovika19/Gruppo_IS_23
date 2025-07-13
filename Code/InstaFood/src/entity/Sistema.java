package entity;

import database.RicettaDAO;
import DTO.RicettaDTO;
import java.util.List;




public class Sistema {
    public List<RicettaDTO> getRicettePerFeed() {
        RicettaDAO ricettaDAO = new RicettaDAO();
        return ricettaDAO.getLastRicette();
    }
}

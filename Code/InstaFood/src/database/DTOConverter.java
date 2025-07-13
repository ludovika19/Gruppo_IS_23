package database;

import DTO.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Utility class per la conversione tra oggetti DAO e DTO
 */
public class DTOConverter {

    // Conversioni per UtenteDAO -> UtenteDTO
    public static UtenteDTO convertToDTO(UtenteDAO dao) {
        if (dao == null) return null;
        
        UtenteDTO dto = new UtenteDTO(
            dao.getId(),
            dao.getNome(),
            dao.getCognome(),
            dao.getEmail()
        );
        
        // Conversione Date -> LocalDate
        if (dao.getDataN() != null) {
            dto.setDataNascita(convertToLocalDate(dao.getDataN()));
        }
        
        dto.setLuogoNascita(dao.getLuogoN());
        
        // Conversione Blob -> String
        if (dao.getBiografia() != null) {
            dto.setBiografia(convertBlobToString(dao.getBiografia()));
        }
        
        return dto;
    }

    // Conversioni per RaccoltaDAO -> RaccoltaDTO
    public static RaccoltaDTO convertToDTO(RaccoltaDAO dao) {
        if (dao == null) return null;
        
        RaccoltaDTO dto = new RaccoltaDTO(
            dao.getIdRaccolta(),
            dao.getTitolo(),
            dao.getDescrizione() != null ? convertBlobToString(dao.getDescrizione()) : null,
            dao.getUtente_idUtente()
        );
        
        return dto;
    }

    // Conversioni per CommentoDAO -> CommentoDTO
    public static CommentoDTO convertToDTO(CommentoDAO dao) {
        if (dao == null) return null;
        
        CommentoDTO dto = new CommentoDTO(
            dao.getIdCommento(),
            dao.getTesto(),
            dao.getData() != null ? convertToLocalDateTime(dao.getData()) : null,
            dao.getUtente_idUtente(),
            dao.getRicetta_idRicetta()
        );
        
        return dto;
    }

    // Conversioni per IngredienteDAO -> IngredienteDTO
    public static IngredienteDTO convertToDTO(IngredienteDAO dao) {
        if (dao == null) return null;
        
        return new IngredienteDTO(
            dao.getId(),
            dao.getNome()
        );
    }

    // Conversioni per TagDAO -> TagDTO
    public static TagDTO convertToDTO(TagDAO dao) {
        if (dao == null) return null;
        
        return new TagDTO(
            dao.getId(),
            dao.getNome()
        );
    }

    // Conversioni per AmministratoreDAO -> AmministratoreDTO
    public static AmministratoreDTO convertToDTO(AmministratoreDAO dao) {
        if (dao == null) return null;
        
        return new AmministratoreDTO(
            dao.getIdAmministratore(),
            dao.getUsername()
        );
    }

    // Metodi di utilità per conversione tipi

    /**
     * Converte java.util.Date a LocalDate
     */
    public static LocalDate convertToLocalDate(Date date) {
        if (date == null) return null;
        if (date instanceof java.sql.Date) {
            return ((java.sql.Date) date).toLocalDate();
        }
        return new java.sql.Date(date.getTime()).toLocalDate();
    }

    /**
     * Converte java.util.Date a LocalDateTime
     */
    public static LocalDateTime convertToLocalDateTime(Date date) {
        if (date == null) return null;
        if (date instanceof java.sql.Timestamp) {
            return ((java.sql.Timestamp) date).toLocalDateTime();
        }
        return new java.sql.Timestamp(date.getTime()).toLocalDateTime();
    }

    /**
     * Converte LocalDate a java.sql.Date
     */
    public static java.sql.Date convertToSqlDate(LocalDate localDate) {
        if (localDate == null) return null;
        return java.sql.Date.valueOf(localDate);
    }

    /**
     * Converte LocalDateTime a java.sql.Timestamp
     */
    public static java.sql.Timestamp convertToSqlTimestamp(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        return java.sql.Timestamp.valueOf(localDateTime);
    }

    /**
     * Converte Blob a String
     */
    public static String convertBlobToString(Blob blob) {
        if (blob == null) return null;
        try {
            byte[] bytes = blob.getBytes(1, (int) blob.length());
            return new String(bytes);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Converte String a byte array per Blob
     */
    public static byte[] convertStringToBytes(String str) {
        if (str == null) return null;
        return str.getBytes();
    }
}

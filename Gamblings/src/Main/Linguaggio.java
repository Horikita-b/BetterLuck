package Main;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;

public class Linguaggio {
	private static String CREATE_TABLE_LINGUAGGIO = "CRATE TABLE IF NOT EXISTS Linguaggio("
			+ "LinguaggioID INT NOT NULL AUTO_INCREMENT, \r\n"
			+ "Nome VARCHAR(20) NOT NULL, \r\n"
			+ "PRIMARY KEY (LinguaggioID));";
	

	
	private static String CREATE_TABLE_SVILUPPATORE_LINGUAGGIO = "CREATE TABLE IF NOT EXISTS Sviluppatore_Linguaggio("
			+ "Sviluppatore_LinguaggioID INT NOT NULL AUTO_INCREMENT, \r\n"
			+ "SviluppatoreID INT NOT NULL,"
			+ "FOREIGN KEY(SviluppatoreID) REFERENCES sviluppatore(SviluppatoreID) ON DELETE CASCADE ON UPDATE CASCADE, \r\n"
			+ "LinguaggioID INT NOT NULL,\r\n"
			+ "FOREIGN KEY(LinguaggioID) REFERENCES Linguaggio(LinguaggioID) ON DELETE CASCADE ON UPDATE CASCADE,"
			+ "PRIMARY KEY (Sviluppatore_LinguaggioID));";

	public static void createTableLinguaggio(Connection connection) {
        try (
             Statement stmt = connection.createStatement()) {

            // Creazione tabella CLIENTS
            String createTableLinguaggio = "CRATE TABLE IF NOT EXISTS Linguaggio("
        			+ "LinguaggioID INT NOT NULL AUTO_INCREMENT, \r\n"
        			+ "Nome VARCHAR(20) NOT NULL, \r\n"
        			+ "PRIMARY KEY (LinguaggioID));";
            
            stmt.execute(createTableLinguaggio);
	
            System.out.println("Tabella creata/verificata correttamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
	public static void createTableSviluppatoreLinguaggio(Connection connection) {
        try (
             Statement stmt = connection.createStatement()) {

            // Creazione tabella CLIENTS
            String createTableSviluppatoreLinguaggio = "CREATE TABLE IF NOT EXISTS Sviluppatore_Linguaggio("
        			+ "Sviluppatore_LinguaggioID INT NOT NULL AUTO_INCREMENT, \r\n"
        			+ "SviluppatoreID INT NOT NULL,"
        			+ "FOREIGN KEY(SviluppatoreID) REFERENCES sviluppatore(SviluppatoreID) ON DELETE CASCADE ON UPDATE CASCADE, \r\n"
        			+ "LinguaggioID INT NOT NULL,\r\n"
        			+ "FOREIGN KEY(LinguaggioID) REFERENCES Linguaggio(LinguaggioID) ON DELETE CASCADE ON UPDATE CASCADE,"
        			+ "PRIMARY KEY (Sviluppatore_LinguaggioID));";
            
            stmt.execute(createTableSviluppatoreLinguaggio);
	
            System.out.println("Tabella creata/verificata correttamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
	public static void deleteLinguaggio(int LinguaggioID,Connection connection) {
        String sql = "DELETE FROM Linguaggio WHERE LinguaggioID = ?";
        try (
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, LinguaggioID);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Linguaggio con ID " + LinguaggioID + " eliminato correttamente.");
            } else {
                System.out.println("Nessun linguaggio eliminato. Verificare l'ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


	private static String CREATE_TABLE_SVILUPPATORE_PROGETTO = "CREATE TABLE IF NOT EXISTS Sviluppatore_Progetto(\r\n"
			+ "Sviluppatore_ProgettoID INT NOT NULL AUTO_INCREMENT,\r\n"
			+ "SviluppatoreID INT NOT NULL,\r\n"
			+ "ProgettoID INT NOT NULL,\r\n"
			+ "FOREIGN KEY(SviluppatoreID) REFERENCES Sviluppatore(SviluppatoreID) ON DELETE CASCADE ON UPDATE CASCADE,\r\n"
			+ "FOREIGN KEY(ProgettoID) REFERENCES Progetto(ProgettoID) ON DELETE CASCADE ON UPDATE CASCADE,\r\n"
			+ "PRIMARY KEY(Sviluppatore_ProgettoID));";



// 11) Read Tutti Linguaggi 

	public static void readAllLinguaggi(Connection connection) {
		String query = "SELECT * FROM Linguaggio";
			
		try (
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(query))
		{
		while (rs.next()) {
	                
		int LinguaggioID = rs.getInt("LinguaggioID");
		String Nome = rs.getString("Nome");
				

	    System.out.printf("LinguaggioID: %d | Nome: %s",
	    		LinguaggioID, Nome);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//metodo per aggiungere un linguaggio ad uno sviluppatore
	
	public void inserisciLinguaggioSviluppatore(Connection connection, int SviluppatoreID, int LinguaggioID) {
       

        String query = "INSERT INTO sviluppatore_linguaggio (SviluppatoreID, LinguaggioID) VALUES (?, ?)";

        try ( 
        		PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, SviluppatoreID);
            pstmt.setInt(2, LinguaggioID);

            pstmt.executeUpdate();  // Esegui l'inserimento
            
            int rowsAffected = pstmt.executeUpdate();  // Esegui l'inserimento
            if (rowsAffected > 0) {
                System.out.println("Linguaggio inserito correttamente.");
            } else {
                System.out.println("Errore: Nessun linguaggio inserito.");  //controlli
            }
        } catch (SQLException e) {
            System.out.println("Errore durante l'inserimento: " + e.getMessage());
        }
    }
	
	public void rimuoviLinguaggioSviluppatore(Connection connection, int SviluppatoreID, int LinguaggioID) {
		
	    String query = "DELETE FROM sviluppatore_linguaggio WHERE SviluppatoreID = ? AND LinguaggioID = ?";

	    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	        pstmt.setInt(1, SviluppatoreID);  // Imposta l'ID dello sviluppatore
	        pstmt.setInt(2, LinguaggioID);    // Imposta l'ID del linguaggio

	        int rowsAffected = pstmt.executeUpdate();  // Esegui il delete

	        if (rowsAffected > 0) {
	            System.out.println("Linguaggio rimosso correttamente dallo sviluppatore.");
	        } else {
	            System.out.println("Nessun linguaggio trovato per lo sviluppatore con questi ID.");   //controlli
	        }
	    } catch (SQLException e) {
	        System.out.println("Errore durante la rimozione: " + e.getMessage());
	    }
	}
	
	
}

package Main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
}

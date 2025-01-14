package Main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Progetto {
	private static String CREATE_TABLE_PROGETTO = "CREATE TABLE IF NOT EXISTS Progetto(\r\n"
			+ "ProgettoID INT NOT NULL AUTO_INCREMENT,\r\n"
			+ "NomeProgetto VARCHAR(30) NOT NULL,\r\n"
			+ "PRIMARY KEY (ProgettoID));";
	
	private static String CREATE_TABLE_SVILUPPATORE_PROGETTO = "CREATE TABLE IF NOT EXISTS Sviluppatore_Progetto(\r\n"
			+ "Sviluppatore_ProgettoID INT NOT NULL AUTO_INCREMENT,\r\n"
			+ "SviluppatoreID INT NOT NULL,\r\n"
			+ "ProgettoID INT NOT NULL,\r\n"
			+ "FOREIGN KEY(SviluppatoreID) REFERENCES Sviluppatore(SviluppatoreID) ON DELETE CASCADE ON UPDATE CASCADE,\r\n"
			+ "FOREIGN KEY(ProgettoID) REFERENCES Progetto(ProgettoID) ON DELETE CASCADE ON UPDATE CASCADE,\r\n"
			+ "PRIMARY KEY(Sviluppatore_ProgettoID));";
	
	public static void createTableProgetto(Connection connection) {
        try (
             Statement stmt = connection.createStatement()) {

            // Creazione tabella CLIENTS
            String createTableProgetto = "CREATE TABLE IF NOT EXISTS Progetto(\r\n"
        			+ "ProgettoID INT NOT NULL AUTO_INCREMENT,\r\n"
        			+ "NomeProgetto VARCHAR(30) NOT NULL,\r\n"
        			+ "PRIMARY KEY (ProgettoID));";
            
            stmt.execute(createTableProgetto);
	
            System.out.println("Tabella creata/verificata correttamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	public static void createTableSviluppatoreProgetto(Connection connection) {
        try (
             Statement stmt = connection.createStatement()) {

            // Creazione tabella CLIENTS
            String createTableSviluppatoreProgetto = "CREATE TABLE IF NOT EXISTS Sviluppatore_Progetto(\r\n"
        			+ "Sviluppatore_ProgettoID INT NOT NULL AUTO_INCREMENT,\r\n"
        			+ "SviluppatoreID INT NOT NULL,\r\n"
        			+ "ProgettoID INT NOT NULL,\r\n"
        			+ "FOREIGN KEY(SviluppatoreID) REFERENCES Sviluppatore(SviluppatoreID) ON DELETE CASCADE ON UPDATE CASCADE,\r\n"
        			+ "FOREIGN KEY(ProgettoID) REFERENCES Progetto(ProgettoID) ON DELETE CASCADE ON UPDATE CASCADE,\r\n"
        			+ "PRIMARY KEY(Sviluppatore_ProgettoID));";
            
            stmt.execute(createTableSviluppatoreProgetto);
	
            System.out.println("Tabella creata/verificata correttamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

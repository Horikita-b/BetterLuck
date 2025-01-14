package Main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Team {
	
	private static String CREATE_TABLE_TEAM = "CREATE TABLE IF NOT EXISTS Team(\r\n"
			+ "TeamID INT NOT NULL AUTO_INCREMENT,\r\n"
			+ "NomeTeam VARCHAR(30)NOT NULL,\r\n"
			+ "ManagerID INT NOT NULL,\r\n"
			+ "FOREIGN KEY(ManagerID) REFERENCES Manager(ManagerID) ON UPDATE CASCADE ON DELETE SET NULL,\r\n"
			+ "PRIMARY KEY(TeamID));";
	
	public static void createTableTeam(Connection connection) {
        try (
             Statement stmt = connection.createStatement()) {

            // Creazione tabella CLIENTS
            String createTableTeam = "CREATE TABLE IF NOT EXISTS Team(\r\n"
        			+ "TeamID INT NOT NULL AUTO_INCREMENT,\r\n"
        			+ "NomeTeam VARCHAR(30)NOT NULL,\r\n"
        			+ "ManagerID INT NOT NULL,\r\n"
        			+ "FOREIGN KEY(ManagerID) REFERENCES Manager(ManagerID) ON UPDATE CASCADE ON DELETE SET NULL,\r\n"
        			+ "PRIMARY KEY(TeamID));";
            
            stmt.execute(createTableTeam);
	
            System.out.println("Tabella creata/verificata correttamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}

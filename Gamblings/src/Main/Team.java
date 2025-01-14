package Main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Team {
	
	private static String CREATE_TABLE_TEAM = "CREATE TABLE IF NOT EXISTS Team(\r\n"
			+ "TeamID INT NOT NULL AUTO_INCREMENT,\r\n"
			+ "NomeTeam VARCHAR(30)NOT NULL,\r\n"
			+ "ManagerID INT NOT NULL,\r\n"
			+ "FOREIGN KEY(ManagerID) REFERENCES Manager(ManagerID) ON UPDATE CASCADE ON DELETE SET NULL,\r\n"
			+ "PRIMARY KEY(TeamID));";


//16) Read tutti i team

public static void readAllTeams(Connection connection) {
	String query = "SELECT team.TeamID, dipendente.Nome, dipendente.Cognome, team.NomeTeam FROM Team FULL OUTER JOIN Manager"
			+ "ON team.ManagerID = manager.ManagerID INNER JOIN dipendente ON manager.DipendenteID = dipendente.DipendenteID";
			
		try (
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(query))
		{
			while (rs.next()) {
	                
				int TeamID = rs.getInt("TeamID");
				String Nome = rs.getString("Nome");
				String Cognome = rs.getString("Cognome");
				String NomeTeam = rs.getString("NomeTeam");
				
	    System.out.printf("TeamID: %d | Nome: %s | Cognome: %s | NomeTeam: %s",
	    		TeamID, Nome, Cognome, NomeTeam);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
}
}
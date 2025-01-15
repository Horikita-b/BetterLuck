package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Team {
	
	public static void createTableTeam(Connection connection) {
		try (Statement stmt = connection.createStatement()) {
			final String CREATE_TABLE_TEAM = "CREATE TABLE IF NOT EXISTS Team(\r\n"
					+ "TeamID INT NOT NULL AUTO_INCREMENT,\r\n" + "NomeTeam VARCHAR(30)NOT NULL,\r\n"
					+ "ManagerID INT,\r\n"
					+ "FOREIGN KEY(ManagerID) REFERENCES Manager(ManagerID) ON UPDATE CASCADE ON DELETE SET NULL,\r\n"
					+ "PRIMARY KEY(TeamID));";

			stmt.execute(CREATE_TABLE_TEAM);

			System.out.println("Tabella creata/verificata correttamente.");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void deleteTeam(Connection connection, int TeamID) {
		String sql = "DELETE FROM Team WHERE TeamID = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setInt(1, TeamID);
			int affectedRows = pstmt.executeUpdate();
			if (affectedRows > 0) {
				System.out.println("Team con ID " + TeamID + " eliminato correttamente.");
			} else {
				System.out.println("Nessun Team eliminato. Verificare l'ID.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void readAllTeams(Connection connection) {
		String query = "SELECT team.TeamID, dipendente.Nome, dipendente.Cognome, team.NomeTeam FROM Team LEFT OUTER JOIN Manager "
				+ "ON team.ManagerID = manager.ManagerID LEFT OUTER JOIN dipendente ON manager.DipendenteID = dipendente.DipendenteID";

		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {

				int TeamID = rs.getInt("TeamID");
				String Nome = rs.getString("Nome");
				String Cognome = rs.getString("Cognome");
				String NomeTeam = rs.getString("NomeTeam");

				System.out.printf("TeamID: %d | Nome: %s | Cognome: %s | NomeTeam: %s\n", TeamID, Nome, Cognome,
						NomeTeam);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
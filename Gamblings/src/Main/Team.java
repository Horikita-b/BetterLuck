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

	public static int insertTeam(Connection connection, String nome) {
		String sql = "INSERT INTO Team (NomeTeam) VALUES (?)";
		try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstmt.setString(1, nome);

			int affectedRows = pstmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("Creazione team fallita, nessuna riga aggiunta.");
			}

			// Recupero la chiave generata (ID auto-increment)
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					return generatedKeys.getInt(1);
				} else {
					throw new SQLException("Creazione team fallita, ID non recuperato.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; // In caso di errore
	}
	public static void updateManagerID(Connection connection, int TeamID, int ManagerID) {
	    
		String query = "UPDATE Team SET ManagerID = ? WHERE TeamID = ?";

	    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	        pstmt.setInt(1, ManagerID);  // Imposta il nuovo ID del manager
	        pstmt.setInt(2, TeamID);     // Imposta l'ID del team da aggiornare

	        int affectedRows = pstmt.executeUpdate();  // Esegui l'update

	        if (affectedRows > 0) {
	            System.out.println("ManagerID aggiornato correttamente per il team con ID " + TeamID);
	        } else {
	            System.out.println("Nessun team trovato con ID " + TeamID + ". Verificare l'ID.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	

	// Visualizzare Dipendenti di un Team/Manager
	public static void readDipendentiTeam(Connection connection, int TeamID) {
		String query = "SELECT dipendente.Nome, dipendente.Cognome FROM sviluppatore LEFT JOIN dipendente ON sviluppatore.DipendenteID = dipendente.DipendenteID WHERE TeamID = ?";

		try (PreparedStatement pstmt = connection.prepareStatement(query)) {

			pstmt.setInt(1, TeamID);
			
			try (ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				
				String Nome = rs.getString("Nome");
				String Cognome = rs.getString("Cognome");
				
				System.out.printf("| Nome: %s | Cognome: %s", Nome, Cognome);
			}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
	
	



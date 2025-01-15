package Main;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;

public class Linguaggio {
	
	public static void createTableLinguaggio(Connection connection) {
		try (Statement stmt = connection.createStatement()) {

			// Creazione tabella CLIENTS
			final String CREATE_TABLE_LINGUAGGIO = "CREATE TABLE IF NOT EXISTS Linguaggio("
					+ "LinguaggioID INT NOT NULL AUTO_INCREMENT, \r\n" + "Nome VARCHAR(20) NOT NULL, \r\n"
					+ "PRIMARY KEY (LinguaggioID));";

			stmt.execute(CREATE_TABLE_LINGUAGGIO);

			System.out.println("Tabella creata/verificata correttamente.");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void createTableSviluppatoreLinguaggio(Connection connection) {
		try (Statement stmt = connection.createStatement()) {

			// Creazione tabella CLIENTS
			final String CREATE_TABLE_SVILUPPATORE_LINGUAGGIO = "CREATE TABLE IF NOT EXISTS Sviluppatore_Linguaggio("
					+ "Sviluppatore_LinguaggioID INT NOT NULL AUTO_INCREMENT, \r\n" + "SviluppatoreID INT NOT NULL,"
					+ "FOREIGN KEY(SviluppatoreID) REFERENCES sviluppatore(SviluppatoreID) ON DELETE CASCADE ON UPDATE CASCADE, \r\n"
					+ "LinguaggioID INT NOT NULL,\r\n"
					+ "FOREIGN KEY(LinguaggioID) REFERENCES Linguaggio(LinguaggioID) ON DELETE CASCADE ON UPDATE CASCADE,"
					+ "PRIMARY KEY (Sviluppatore_LinguaggioID));";

			stmt.execute(CREATE_TABLE_SVILUPPATORE_LINGUAGGIO);

			System.out.println("Tabella creata/verificata correttamente.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteLinguaggio(Connection connection,int LinguaggioID) {
		String sql = "DELETE FROM Linguaggio WHERE LinguaggioID = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

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

// 11) Read Tutti Linguaggi 

	public static void readAllLinguaggi(Connection connection) {
		String query = "SELECT * FROM Linguaggio";

		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {

				int LinguaggioID = rs.getInt("LinguaggioID");
				String Nome = rs.getString("Nome");

				System.out.printf("LinguaggioID: %d | Nome: %s\n", LinguaggioID, Nome);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	 public static int insertLinguaggio(Connection connection,String nome) {
	        String sql = "INSERT INTO Linguaggio (Nome) VALUES (?)";
	        try (
	             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	            pstmt.setString(1, nome);

	            int affectedRows = pstmt.executeUpdate();
	            if (affectedRows == 0) {
	                throw new SQLException("Creazione linguaggio fallita, nessuna riga aggiunta.");
	            }

	            // Recupero la chiave generata (ID auto-increment)
	            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    return generatedKeys.getInt(1);
	                } else {
	                    throw new SQLException("Creazione linguaggio fallita, ID non recuperato.");
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	       return -1; // In caso di errore
	 }
	 
}

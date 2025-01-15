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
	
	// prova visualizzare linguaggi conosciuti di uno sviluppatore
	public static void readAllLinguaggiSviluppatore(Connection connection,int SviluppatoreID) {
		String query = "SELECT dipendente.Nome, linguaggio.Nome FROM Sviluppatore_Linguaggio "
				+ "LEFT JOIN sviluppatore ON sviluppatore.SviluppatoreID = Sviluppatore_Linguaggio.SviluppatoreID"
				+ " LEFT JOIN dipendente ON dipendente.DipendenteID = Sviluppatore.DipendenteID"
				+ " LEFT JOIN linguaggio ON linguaggio.LinguaggioID = Sviluppatore_Linguaggio.LinguaggioID"
				+ " WHERE dipendente.DipendenteID = ?";

		try (PreparedStatement pstmt = connection.prepareStatement(query))  {
			
			System.out.println("ciao");
			pstmt.setInt(1, SviluppatoreID);
			System.out.println(pstmt.toString());
			System.out.println("ciao caro");
			try (ResultSet rs2 = pstmt.executeQuery()) {
				while (rs2.next()) {
					int Sviluppatore_LinguaggioID = rs2.getInt("Sviluppatore_LinguaggioID");
					String NomeDipendente = rs2.getString("dipendente.Nome");
					String NomeLinguaggio= rs2.getString("linguaggio.Nome");
					System.out.printf("Sviluppatore_LinguaggioID: %d | Nome Sviluppatore: %s| Nome Linguaggio: %s\n", Sviluppatore_LinguaggioID,NomeDipendente,NomeLinguaggio);
				}
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

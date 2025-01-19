package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Progetto {
	
	final static String[] CRUD_PROGETTO = { "1) Vedi progetti", "2) Aggiungi progetto", "3) Elimina progetto" };
	
	
	/*
	 * createTableProgetto: Crea la tabella Progetto nel database, se non esiste già.
	 *
	 * @param connection: Connessione al database.
	 *
	 */
	public static void createTableProgetto(Connection connection) {
		try (Statement stmt = connection.createStatement()) {

			// Creazione tabella CLIENTS
			final String CREATE_TABLE_PROGETTO = "CREATE TABLE IF NOT EXISTS Progetto(\r\n"
					+ "ProgettoID INT NOT NULL AUTO_INCREMENT,\r\n" + "NomeProgetto VARCHAR(30) NOT NULL,\r\n"
					+ "PRIMARY KEY (ProgettoID));";

			stmt.execute(CREATE_TABLE_PROGETTO);

			System.out.println("Tabella creata/verificata correttamente.");

		} catch (SQLException e) {
			System.out.println("Errore durante la creazione: " + e.getMessage());
		}
	}
	
	/*
	 * checkProgetto: Verifica se un progetto con il dato ProgettoID esiste nel database.
	 *
	 * @param connection: Connessione al database.
	 * @param ProgettoID: L'ID del progetto da verificare.
	 *
	 * @return boolean: Restituisce true se il progetto esiste, false altrimenti.
	 *
	 * @throws SQLException
	 */
	public static boolean checkProgetto(Connection connection, int ProgettoID) throws SQLException {
		String checkQuery = "SELECT COUNT(*) FROM progetto WHERE ProgettoID = ?";

		try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
			checkStmt.setInt(1, ProgettoID);
			try (ResultSet rsCheck = checkStmt.executeQuery()) {
				if (rsCheck.next() && rsCheck.getInt(1) == 0) {
					System.out.println("Progetto con ID " + ProgettoID + " non trovato.");
					return false;
				}
			}
		}
		return true;
	}
	
	
	/*
	 * createTableSviluppatoreProgetto: Crea la tabella Sviluppatore_Progetto nel database
	 *
	 * @param connection: Connessione al database.
	 *
	 */
	public static void createTableSviluppatoreProgetto(Connection connection) {
		try (Statement stmt = connection.createStatement()) {

			// Creazione tabella CLIENTS
			final String CREATE_TABLE_SVILUPPATORE_PROGETTO = "CREATE TABLE IF NOT EXISTS Sviluppatore_Progetto(\r\n"
					+ "Sviluppatore_ProgettoID INT NOT NULL AUTO_INCREMENT,\r\n" + "SviluppatoreID INT NOT NULL,\r\n"
					+ "ProgettoID INT NOT NULL,\r\n"
					+ "FOREIGN KEY(SviluppatoreID) REFERENCES Sviluppatore(SviluppatoreID) ON DELETE CASCADE ON UPDATE CASCADE,\r\n"
					+ "FOREIGN KEY(ProgettoID) REFERENCES Progetto(ProgettoID) ON DELETE CASCADE ON UPDATE CASCADE,\r\n"
					+ "PRIMARY KEY(Sviluppatore_ProgettoID));";

			stmt.execute(CREATE_TABLE_SVILUPPATORE_PROGETTO);

			System.out.println("Tabella creata/verificata correttamente.");

		} catch (SQLException e) {
			System.out.println("Errore durante la creazione: " + e.getMessage());
		}
	}

	/*
	 * checkSviluppatore_Progetto: Verifica se esiste una relazione tra uno sviluppatore e un progetto nella tabella Sviluppatore_Progetto.
	 *
	 * @param connection: Connessione al database.
	 * @param SviluppatoreID: ID dello sviluppatore da verificare.
	 * @param ProgettoID: ID del progetto da verificare.
	 *
	 * @return boolean: true se la relazione esiste, false altrimenti.
	 *
	 * @throws SQLException
	 */
	public static boolean checkSviluppatore_Progetto(Connection connection, int SviluppatoreID, int ProgettoID) throws SQLException {
		String checkQuery = "SELECT COUNT(*) FROM sviluppatore_progetto WHERE ProgettoID = ? AND SviluppatoreID = ?";

		try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
			checkStmt.setInt(1, ProgettoID);
			checkStmt.setInt(2, SviluppatoreID);
			try (ResultSet rsCheck = checkStmt.executeQuery()) {
				if (rsCheck.next() && rsCheck.getInt(1) == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	/*
	 * deleteProgetto: Elimina un progetto dalla tabella Progetto in base al suo ID.
	 *
	 * @param connection: Connessione al database.
	 * @param ProgettoID: ID del progetto da eliminare.
	 */
	public static void deleteProgetto(Connection connection, int ProgettoID) {
		String sql = "DELETE FROM Progetto WHERE ProgettoID = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setInt(1, ProgettoID);
			int affectedRows = pstmt.executeUpdate();
			if (affectedRows > 0) {
				System.out.println("Progetto con ID " + ProgettoID + " eliminato correttamente.");
			} else {
				System.out.println("Nessun Progetto eliminato. Verificare l'ID.");
			}

		} catch (SQLException e) {
			System.out.println("Errore durante la rimozione: " + e.getMessage());
		}
	}

	/*
	 * readAllProgetti: Legge e stampa tutti i progetti presenti nella tabella Progetto.
	 *
	 * @param connection: Connessione al database.
	 *
	 */
	public static void readAllProgetti(Connection connection) {
		String query = "SELECT * FROM Progetto";

		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {

				int ProgettoID = rs.getInt("ProgettoID");
				String NomeProgetto = rs.getString("NomeProgetto");

				System.out.printf("ProgettoID: %d | NomeProgetto: %s\n", ProgettoID, NomeProgetto);
			}

		} catch (SQLException e) {
			System.out.println("Errore durante la lettura: " + e.getMessage());
		}

	}

	/*
	 * insertProgetto: Inserisce un nuovo progetto nel database.
	 *
	 * @param connection: Connessione al database.
	 * @param nome: Nome del progetto da inserire.
	 *
	 * @return: Restituisce l'ID del progetto appena inserito, o -1 in caso di errore.
	 *
	 */
	public static int insertProgetto(Connection connection, String nome) {
		String sql = "INSERT INTO Progetto (NomeProgetto) VALUES (?)";
		try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstmt.setString(1, nome);

			int affectedRows = pstmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("Creazione progetto fallita, nessuna riga aggiunta.");
			}

			// Recupero la chiave generata (ID auto-increment)
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					System.out.println("Creazione nuovo progetto riuscita");
					return generatedKeys.getInt(1);
				} else {
					throw new SQLException("Creazione progetto fallita, ID non recuperato.");
				}
			}
		} catch (SQLException e) {
			System.out.println("Errore durante l'inserimento: " + e.getMessage());
		}
		return -1; // In caso di errore
	}

	/*
	 * readAllProgettiSviluppatore: Legge tutti i progetti associati a uno sviluppatore dal database.
	 *
	 * @param connection: Connessione al database.
	 * @param SviluppatoreID: ID dello sviluppatore per cui leggere i progetti.
	 *
	 */
	public static void readAllProgettiSviluppatore(Connection connection, int SviluppatoreID) {
		String query = "SELECT dipendente.Nome, progetto.NomeProgetto FROM Sviluppatore_Progetto "
				+ "LEFT JOIN sviluppatore ON sviluppatore.SviluppatoreID = Sviluppatore_Progetto.SviluppatoreID"
				+ " LEFT JOIN dipendente ON dipendente.DipendenteID = Sviluppatore.DipendenteID"
				+ " LEFT JOIN progetto ON progetto.ProgettoID = Sviluppatore_Progetto.ProgettoID"
				+ " WHERE sviluppatore.SviluppatoreID = ?";
		
		try (PreparedStatement pstmt = connection.prepareStatement(query))  {
			if (!Sviluppatore.checkSviluppatore(connection, SviluppatoreID)) {
				return;
			}
			
			pstmt.setInt(1, SviluppatoreID);
			try (ResultSet rs2 = pstmt.executeQuery()) {
				while (rs2.next()) {
					String NomeDipendente = rs2.getString("dipendente.Nome");
					String NomeProgetto= rs2.getString("progetto.NomeProgetto");
					System.out.printf("Nome Sviluppatore: %s| Nome Progetto: %s\n", NomeDipendente, NomeProgetto);
				}
			}
		} catch (SQLException e) {
			System.out.println("Errore durante la lettura: " + e.getMessage());
		}
	}
	
	/*
	 * inserisciProgettoSviluppatore: Inserisce un progetto associato a uno sviluppatore nel database.
	 *
	 * @param connection: Connessione al database.
	 * @param SviluppatoreID: ID dello sviluppatore a cui associare il progetto.
	 * @param ProgettoID: ID del progetto da associare allo sviluppatore.
	 *
	 */
	public static void inserisciProgettoSviluppatore(Connection connection, int SviluppatoreID, int ProgettoID) {
        String query = "INSERT INTO sviluppatore_progetto (SviluppatoreID, ProgettoID) VALUES (?, ?)";

        try ( 
        		PreparedStatement pstmt = connection.prepareStatement(query)) {
        	
			if (!Sviluppatore.checkSviluppatore(connection, SviluppatoreID) || !Progetto.checkProgetto(connection, ProgettoID)) {
				return;
			}
			
			if (Progetto.checkSviluppatore_Progetto(connection, SviluppatoreID, ProgettoID)) {
				System.out.println("Lo sviluppatore ha gia' questo progetto");
				return;
			}
            pstmt.setInt(1, SviluppatoreID);
            pstmt.setInt(2, ProgettoID);

            int rowsAffected = pstmt.executeUpdate();  // Esegui l'inserimento
            if (rowsAffected > 0) {
                System.out.println("Progetto inserito correttamente.");
            } else {
                System.out.println("Errore: Nessun progetto inserito.");  //controlli
            }
        } catch (SQLException e) {
            System.out.println("Errore durante l'inserimento: " + e.getMessage());
        }
	}
	
	/*
	 * rimuoviProgettoSviluppatore: Rimuove un progetto associato a uno sviluppatore dal database.
	 *
	 * @param connection: Connessione al database.
	 * @param SviluppatoreID: ID dello sviluppatore da cui rimuovere il progetto.
	 * @param ProgettoID: ID del progetto da rimuovere dallo sviluppatore.
	 */
	public static void rimuoviProgettoSviluppatore(Connection connection, int SviluppatoreID, int ProgettoID) {
		
	    String query = "DELETE FROM sviluppatore_progetto WHERE SviluppatoreID = ? AND ProgettoID = ?";

	    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			if (!Sviluppatore.checkSviluppatore(connection, SviluppatoreID) || !Progetto.checkProgetto(connection, ProgettoID)) {
				return;
			}
			
			if (!Progetto.checkSviluppatore_Progetto(connection, SviluppatoreID, ProgettoID)) {
				System.out.println("Lo sviluppatore non ha questo progetto");
				return;
			}
	        pstmt.setInt(1, SviluppatoreID);  // Imposta l'ID dello sviluppatore
	        pstmt.setInt(2, ProgettoID);    // Imposta l'ID del linguaggio

	        int rowsAffected = pstmt.executeUpdate();  // Esegui il delete

	        if (rowsAffected > 0) {
	            System.out.println("Progetto rimosso correttamente dallo sviluppatore.");
	        } else {
	            System.out.println("Nessun progetto trovato per lo sviluppatore con questi ID.");   //controlli
	        }
	    } catch (SQLException e) {
	        System.out.println("Errore durante la rimozione: " + e.getMessage());
	    }
	
	}
	
	
	int id;
	String nome;


	public Progetto(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
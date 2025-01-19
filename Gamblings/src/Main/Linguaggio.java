package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Linguaggio {

	final static String[] CRUD_LINGUAGGIO = { "1) Vedi linguaggi", "2) Aggiungi linguaggio", "3) Elimina linguaggio" };

	/**
	 * createTableLinguaggio: Metodo che crea la tabella Linguaggio nel database.
	 * 
	 * @param connection: Oggetto Connection per gestire la connessione al database.
	 */
	public static void createTableLinguaggio(Connection connection) {
		try (Statement stmt = connection.createStatement()) {

			// Creazione tabella CLIENTS
			final String CREATE_TABLE_LINGUAGGIO = "CREATE TABLE IF NOT EXISTS Linguaggio("
					+ "LinguaggioID INT NOT NULL AUTO_INCREMENT, \r\n" + "Nome VARCHAR(20) NOT NULL, \r\n"
					+ "PRIMARY KEY (LinguaggioID));";

			stmt.execute(CREATE_TABLE_LINGUAGGIO);

			System.out.println("Tabella creata/verificata correttamente.");

		} catch (SQLException e) {
			System.out.println("Errore durante la creazione: " + e.getMessage());
		}

	}

	
	/*
	 * checkLinguaggio: Metodo che verifica se un linguaggio esiste nel database.
	 * 
	 * @param connection: Oggetto Connection per gestire la connessione al database.
	 * @param LinguaggioID: ID del linguaggio da verificare.
	 * 
	 * @return true se il linguaggio esiste, false altrimenti.
	 * @throws SQLException 
	 */
	public static boolean checkLinguaggio(Connection connection, int LinguaggioID) throws SQLException {
		String checkQuery = "SELECT COUNT(*) FROM linguaggio WHERE LinguaggioID = ?";

		try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
			checkStmt.setInt(1, LinguaggioID);
			try (ResultSet rsCheck = checkStmt.executeQuery()) {
				if (rsCheck.next() && rsCheck.getInt(1) == 0) {
					System.out.println("Linguaggio con ID " + LinguaggioID + " non trovato.");
					return false;
				}
			}
		}
		return true;
	}

	
	/*
	 * createTableSviluppatoreLinguaggio: Metodo che crea la tabella Sviluppatore_Linguaggio nel database.
	 * 
	 * @param connection: Oggetto Connection per gestire la connessione al database.
	 */
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
			System.out.println("Errore durante la creazione: " + e.getMessage());
		}
	}

	
	/*
	 * checkSviluppatore_Linguaggio: Metodo che verifica se un'associazione tra uno sviluppatore e un linguaggio esiste nel database.
	 * 
	 * @param connection: Oggetto Connection per gestire la connessione al database.
	 * @param SviluppatoreID: ID dello sviluppatore.
	 * @param LinguaggioID: ID del linguaggio.
	 * @return: Ritorna true se l'associazione esiste, false altrimenti.
	 * @throws SQLException
	 */
	public static boolean checkSviluppatore_Linguaggio(Connection connection, int SviluppatoreID, int LinguaggioID)
			throws SQLException {
		String checkQuery = "SELECT COUNT(*) FROM sviluppatore_linguaggio WHERE LinguaggioID = ? AND SviluppatoreID = ?";

		try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
			checkStmt.setInt(1, LinguaggioID);
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
	 * deleteLinguaggio: Metodo che rimuove un linguaggio dal database utilizzando l'ID.
	 * 
	 * @param connection: Oggetto Connection per gestire la connessione al database.
	 * @param LinguaggioID: ID del linguaggio da rimuovere.
	 * @throws SQLException
	 */
	public static void deleteLinguaggio(Connection connection, int LinguaggioID) {
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
			System.out.println("Errore durante la rimozione: " + e.getMessage());
		}
	}

	/*
	 * readAllLinguaggi: Metodo che legge e visualizza tutti i linguaggi dal database.
	 * 
	 * @param connection: Oggetto Connection per gestire la connessione al database.
	 * @throws SQLException
	 */
	public static void readAllLinguaggi(Connection connection) {
		String query = "SELECT * FROM Linguaggio";

		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {

				int LinguaggioID = rs.getInt("LinguaggioID");
				String Nome = rs.getString("Nome");

				System.out.printf("LinguaggioID: %d | Nome: %s\n", LinguaggioID, Nome);
			}

		} catch (SQLException e) {
			System.out.println("Errore durante la lettura: " + e.getMessage());
		}
	}

	/*
	 * insertLinguaggio: Metodo che inserisce un nuovo linguaggio nel database.
	 * 
	 * @param connection: Oggetto Connection per gestire la connessione al database.
	 * @param nome: Nome del linguaggio da inserire.
	 * @return: Il ID del linguaggio inserito se l'operazione ha successo, -1 altrimenti.
	 * @throws SQLException
	 */
	public static int insertLinguaggio(Connection connection, String nome) {
		String sql = "INSERT INTO Linguaggio (Nome) VALUES (?)";
		try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstmt.setString(1, nome);

			int affectedRows = pstmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("Creazione linguaggio fallita, nessuna riga aggiunta.");
			}

			// Recupero la chiave generata (ID auto-increment)
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					System.out.println("Creazione linguaggio riuscita");
					return generatedKeys.getInt(1);
				} else {
					throw new SQLException("ID non recuperato.");
				}
			}
		} catch (SQLException e) {
			System.out.println("Errore durante l'inserimento : " + e.getMessage());
		}
		return -1; // In caso di errore
	}

	/*
	 * readAllLinguaggiSviluppatore: Metodo che legge tutti i linguaggi associati a uno sviluppatore specifico.
	 * 
	 * @param connection: Oggetto Connection per gestire la connessione al database.
	 * @param SviluppatoreID: ID dello sviluppatore di cui leggere i linguaggi.
	 * 
	 */
	public static void readAllLinguaggiSviluppatore(Connection connection, int SviluppatoreID) {

		String query = "SELECT dipendente.Nome, linguaggio.Nome FROM Sviluppatore_Linguaggio "
				+ "LEFT JOIN sviluppatore ON sviluppatore.SviluppatoreID = Sviluppatore_Linguaggio.SviluppatoreID"
				+ " LEFT JOIN dipendente ON dipendente.DipendenteID = Sviluppatore.DipendenteID"
				+ " LEFT JOIN linguaggio ON linguaggio.LinguaggioID = Sviluppatore_Linguaggio.LinguaggioID"
				+ " WHERE sviluppatore.SviluppatoreID = ?";

		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			if (!Sviluppatore.checkSviluppatore(connection, SviluppatoreID)) {
				return;
			}

			pstmt.setInt(1, SviluppatoreID);
			try (ResultSet rs2 = pstmt.executeQuery()) {
				while (rs2.next()) {
					String NomeDipendente = rs2.getString("dipendente.Nome");
					String NomeLinguaggio = rs2.getString("linguaggio.Nome");
					System.out.printf("Nome Sviluppatore: %s| Nome Linguaggio: %s\n", NomeDipendente, NomeLinguaggio);
				}
			}
		} catch (SQLException e) {
			System.out.println("Errore durante la lettura: " + e.getMessage());
		}
	}

	/*
	 * inserisciLinguaggioSviluppatore: Metodo per aggiungere un linguaggio a uno sviluppatore.
	 * 
	 * @param connection: Oggetto Connection per gestire la connessione al database.
	 * @param SviluppatoreID: ID dello sviluppatore a cui aggiungere il linguaggio.
	 * @param LinguaggioID: ID del linguaggio da aggiungere.
	 */
	public static void inserisciLinguaggioSviluppatore(Connection connection, int SviluppatoreID, int LinguaggioID) {
		String query = "INSERT INTO sviluppatore_linguaggio (SviluppatoreID, LinguaggioID) VALUES (?, ?)";

		try (PreparedStatement pstmt = connection.prepareStatement(query)) {

			if (!Sviluppatore.checkSviluppatore(connection, SviluppatoreID)
					|| !Linguaggio.checkLinguaggio(connection, LinguaggioID)) {
				return;
			}

			if (Linguaggio.checkSviluppatore_Linguaggio(connection, SviluppatoreID, LinguaggioID)) {
				System.out.println("Lo sviluppatore gia' conosce questo linguaggio");
				return;
			}
			pstmt.setInt(1, SviluppatoreID);
			pstmt.setInt(2, LinguaggioID);

			int rowsAffected = pstmt.executeUpdate(); // Esegui l'inserimento
			if (rowsAffected > 0) {
				System.out.println("Linguaggio inserito correttamente.");
			} else {
				System.out.println("Errore: Nessun linguaggio inserito."); // controlli
			}
		} catch (SQLException e) {
			System.out.println("Errore durante l'inserimento: " + e.getMessage());
		}
	}

	
	/*
	 * rimuoviLinguaggioSviluppatore: Metodo per rimuovere un linguaggio da uno sviluppatore.
	 * 
	 * @param connection: Oggetto Connection per gestire la connessione al database.
	 * @param SviluppatoreID: ID dello sviluppatore da cui rimuovere il linguaggio.
	 * @param LinguaggioID: ID del linguaggio da rimuovere.
	 */
	public static void rimuoviLinguaggioSviluppatore(Connection connection, int SviluppatoreID, int LinguaggioID) {

		String query = "DELETE FROM sviluppatore_linguaggio WHERE SviluppatoreID = ? AND LinguaggioID = ?";

		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			if (!Sviluppatore.checkSviluppatore(connection, SviluppatoreID)
					|| !Linguaggio.checkLinguaggio(connection, LinguaggioID)) {
				return;
			}

			if (!Linguaggio.checkSviluppatore_Linguaggio(connection, SviluppatoreID, LinguaggioID)) {
				System.out.println("Lo sviluppatore gia' non conosce questo linguaggio");
				return;
			}
			pstmt.setInt(1, SviluppatoreID); // Imposta l'ID dello sviluppatore
			pstmt.setInt(2, LinguaggioID); // Imposta l'ID del linguaggio

			int rowsAffected = pstmt.executeUpdate(); // Esegui il delete

			if (rowsAffected > 0) {
				System.out.println("Linguaggio rimosso correttamente dallo sviluppatore.");
			} else {
				System.out.println("Nessun linguaggio trovato per lo sviluppatore con questi ID."); // controlli
			}
		} catch (SQLException e) {
			System.out.println("Errore durante la rimozione: " + e.getMessage());
		}
	}
	
	
	private int id;
	private String nome;

	public Linguaggio(int id, String nome) {
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

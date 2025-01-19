package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Team {
	final static String[] CRUD_TEAM = { "1) Vedi team", "2) Vedi dipendenti team", "3) Aggiungi team",
			"4) Elimina team"};

	/*
	 * createTableTeam: Metodo per creare la tabella Team nel database.
	 * 
	 * @param connection: Oggetto Connection per gestire la connessione al database.
	 * 
	 */
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
			System.out.println("Errore durante la creazione: " + e.getMessage());
		}

	}

	/*
	 * checkTeam: Metodo per verificare se esiste un team con l'ID specificato nel database.
	 *
	 * @param connection: Oggetto Connection per gestire la connessione al database.
	 * @param TeamID: ID del team da verificare.
	 * 
	 * @return true se il team esiste, false altrimenti.
	 * @throw SQLException
	 */
	public static boolean checkTeam(Connection connection, int TeamID) throws SQLException {
	    String checkQuery = "SELECT COUNT(*) FROM Team WHERE TeamID = ?";
	    
	    try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
	        checkStmt.setInt(1, TeamID);
	        try (ResultSet rsCheck = checkStmt.executeQuery()) {
	            if (rsCheck.next() && rsCheck.getInt(1) == 0) {
	                System.out.println("Team con ID " + TeamID + " non trovato.");
	                return false;
	            }
	        }
	    }
	    return true;
	}
	
	/*
	 * readAllTeams: Metodo per leggere e stampare tutte le informazioni sui team dal database.
	 *
	 * @param connection: Oggetto Connection per gestire la connessione al database.
	 * 
	 */
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
			System.out.println("Errore durante la lettura: " + e.getMessage());
		}

	}

	/*
	 * readDipendentiTeam: Metodo per leggere e stampare i dipendenti associati a un team specifico.
	 *
	 * @param connection: Oggetto Connection per gestire la connessione al database.
	 * @param TeamID: ID del team di cui si vogliono leggere i dipendenti.
	 * 
	 */
	public static void readDipendentiTeam(Connection connection, int TeamID) {
		String query = "SELECT dipendente.Nome, dipendente.Cognome FROM sviluppatore LEFT JOIN dipendente ON sviluppatore.DipendenteID = dipendente.DipendenteID WHERE TeamID = ?";

		try (PreparedStatement pstmt = connection.prepareStatement(query)) {

			pstmt.setInt(1, TeamID);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {

					String Nome = rs.getString("Nome");
					String Cognome = rs.getString("Cognome");

					System.out.printf("Nome: %s | Cognome: %s\n", Nome, Cognome);
				}

			}
		} catch (SQLException e) {
			System.out.println("Errore durante la lettura: " + e.getMessage());
		}
	}

	/*
	 * insertTeam: Metodo per inserire un nuovo team nel database.
	 *
	 * @param connection: Oggetto Connection per gestire la connessione al database.
	 * @param nome: Nome del team da inserire.
	 * 
	 * @return int: Restituisce l'ID del team appena creato se l'operazione ha successo, 
	 *              altrimenti restituisce -1 in caso di errore.
	 *
	 */
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
					System.out.println("Creazione nuovo team riuscita");
					return generatedKeys.getInt(1);
				} else {
					throw new SQLException("Creazione team fallita, ID non recuperato.");
				}
			}
		} catch (SQLException e) {
			System.out.println("Errore durante l'inserimento: " + e.getMessage());
		}
		return -1; // In caso di errore
	}

	/*
	 * deleteTeam: Elimina un team specificato dal database.
	 *
	 * @param connection: Connessione al database.
	 * @param TeamID: ID del team da eliminare.
	 */
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
			System.out.println("Errore durante la rimozione: " + e.getMessage());
		}
	}

	/*
	 * updateManagerID: Aggiorna l'ID del manager assegnato a un team specificato.
	 *
	 * @param connection: Connessione al database.
	 * @param TeamID: ID del team da aggiornare.
	 * @param ManagerID: Nuovo ID del manager da assegnare al team.
	 *
	 */
	public static void updateManagerID(Connection connection, int TeamID, int ManagerID) {

		String query = "UPDATE Team SET ManagerID = ? WHERE TeamID = ?";

		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			if (!Manager.checkManager(connection, ManagerID)) {
				return;
			}
			pstmt.setInt(1, ManagerID); // Imposta il nuovo ID del manager
			pstmt.setInt(2, TeamID); // Imposta l'ID del team da aggiornare

			int affectedRows = pstmt.executeUpdate(); // Esegui l'update

			if (affectedRows > 0) {
				System.out.println("ManagerID aggiornato correttamente per il team con ID " + TeamID);
			} else {
				System.out.println("Nessun team trovato con ID " + TeamID + ". Verificare l'ID.");
			}

		} catch (SQLException e) {
			System.out.println("Errore durante l'aggiornamento: " + e.getMessage());
		}
	}

	int id;
	int id_manager;
	String nome;

	public Team(int id, int id_manager, String nome) {
		this.id = id;
		this.id_manager = id_manager;
		this.nome = nome;
	}



	public int getId_manager() {
		return id_manager;
	}

	public void setId_manager(int id_manager) {
		this.id_manager = id_manager;
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

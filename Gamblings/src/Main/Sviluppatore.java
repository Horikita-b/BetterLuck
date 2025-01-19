package Main;

import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Sviluppatore extends Dipendente {

	final static String[] CRUD_SVILUPPATORE = { "1) Vedi sviluppatori", "2) Vedi linguaggi sviluppatore",
			"3) Vedi progetti sviluppatore", "4) Aggiungi linguaggio", "5) Rimuovi linguaggio", "6) Aggiungi progetto",
			"7) Rimuovi progetto", "8) Modifica team" };

	/*
	 * createTableSviluppatore: Metodo che crea la tabella Sviluppatore nel database.
	 * 
	 * @param connection: Oggetto Connection per gestire la connessione al database.
	 * 
	 */
	public static void createTableSviluppatore(Connection connection) {
		try (Statement stmt = connection.createStatement()) {

			// Creazione tabella CLIENTS
			final String CREATE_TABLE_SVILUPPATORE = "CREATE TABLE IF NOT EXISTS Sviluppatore(\r\n"
					+ "SviluppatoreID INT NOT NULL AUTO_INCREMENT,\r\n" + "DipendenteID INT NOT NULL,\r\n"
					+ "TeamID INT, \r\n"
					+ "FOREIGN KEY(DipendenteID) REFERENCES dipendente(DipendenteID) ON UPDATE CASCADE ON DELETE CASCADE,\r\n"
					+ "FOREIGN KEY(TeamID) REFERENCES team(TeamID) ON DELETE SET NULL,\r\n"
					+ "PRIMARY KEY (SviluppatoreID));";

			stmt.execute(CREATE_TABLE_SVILUPPATORE);

			// 6) Read Tutti Sviluppatori

			System.out.println("Tabella creata/verificata correttamente.");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	
	/*
	 * readAllSviluppatori: Metodo che legge e stampa tutti gli sviluppatori dal database.
	 * 
	 * @param connection: Oggetto Connection per gestire la connessione al database.
	 * 
	 */
	public static void readAllSviluppatori(Connection connection) {
		String query = "SELECT sviluppatore.SviluppatoreID, dipendente.Nome, dipendente.Cognome, team.NomeTeam FROM Sviluppatore INNER JOIN dipendente ON Sviluppatore.DipendenteID = dipendente.DipendenteID LEFT JOIN team on Sviluppatore.TeamID = team.TeamID";
		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {

				int SviluppatoreID = rs.getInt("SviluppatoreID");
				String Nome = rs.getString("Nome");
				String Cognome = rs.getString("Cognome");
				String Team = rs.getString("team.NomeTeam");

				System.out.printf("SviluppatoreID: %d | Nome: %s | Cognome: %s | Team: %s\n", SviluppatoreID, Nome, Cognome, Team);
			}
		} catch (SQLException e) {
			System.out.println("Errore durante la lettura: " + e.getMessage());
		}
	}

	/*
	 * checkSviluppatore: Metodo che verifica se un sviluppatore esiste nel database.
	 * 
	 * @param connection: Oggetto Connection per gestire la connessione al database.
	 * @param SviluppatoreID: ID dello sviluppatore da verificare.
	 * 
	 * @return true se lo sviluppatore esiste, false altrimenti.
	 * 
	 * @throws SQLException
	 */
	public static boolean checkSviluppatore(Connection connection, int SviluppatoreID) throws SQLException {
		String checkQuery = "SELECT COUNT(*) FROM sviluppatore WHERE SviluppatoreID = ?";

		try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
			checkStmt.setInt(1, SviluppatoreID);
			try (ResultSet rsCheck = checkStmt.executeQuery()) {
				if (rsCheck.next() && rsCheck.getInt(1) == 0) {
					System.out.println("Sviluppatore con ID " + SviluppatoreID + " non trovato.");
					return false;
				}
			}
		}
		return true;
	}

	
	/*
	 * insertSviluppatore: Metodo che inserisce un nuovo sviluppatore nel database.
	 * 
	 * @param DipendenteID: ID del dipendente associato allo sviluppatore.
	 * @param TeamID: ID del team dello sviluppatore (può essere NULL).
	 * @param connection: Oggetto Connection per gestire la connessione al database.
	 * 
	 * @return ID del nuovo sviluppatore se l'inserimento è riuscito, -1 altrimenti.
	 * 
	 * @throws SQLException
	 */
	public static int insertSviluppatore(int DipendenteID, int TeamID, Connection connection) {

		String sql = "INSERT INTO Sviluppatore(DipendenteID, TeamID) VALUES (?,?)";
		try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstmt.setInt(1, DipendenteID);
			
			if (!Team.checkTeam(connection, TeamID)) {
				pstmt.setNull(2, java.sql.Types.INTEGER);
			} else {
				pstmt.setInt(2, TeamID);
			}

			int affectedRows = pstmt.executeUpdate();

			if (affectedRows > 0) {

				try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						return generatedKeys.getInt(1);
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Errore durante l'inserimento: " + e.getMessage());
		}
		return -1;
	}
	

	/*
	 * aggiornaTeam: Metodo che aggiorna il team associato a uno sviluppatore nel database.
	 * 
	 * @param connection: Oggetto Connection per gestire la connessione al database.
	 * @param SviluppatoreID: ID dello sviluppatore il cui team deve essere aggiornato.
	 * @param TeamID: Nuovo ID del team.
	 * 
	 * @throws SQLException
	 */
	public static void aggiornaTeam(Connection connection, int SviluppatoreID, int TeamID) {

		final String query = "UPDATE Sviluppatore SET TeamID = ? WHERE SviluppatoreID = ?";

		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			
			if (!Team.checkTeam(connection, TeamID)) {
				return;
			}
			pstmt.setInt(1, TeamID);
			pstmt.setInt(2, SviluppatoreID);

			int righeAggiornate = pstmt.executeUpdate();

			// Verifica se l'aggiornamento è riuscito
			if (righeAggiornate > 0) {
				System.out.println("Team aggiornato con successo per lo Sviluppatore con ID: " + SviluppatoreID);
			} else {
				System.out.println("Nessun Sviluppatore trovato con ID: " + SviluppatoreID);
			}

		} catch (

		SQLException e) {
			System.err.println("Errore durante l'aggiornamento del team: " + e.getMessage());
		}
	}

	private ArrayList<String> linguaggiConosciuti;
	private ArrayList<Integer> progettiAssegnati;

	public Sviluppatore(int id, String nome, String cognome, double stipendioBase,
			ArrayList<String> linguaggiConosciuti, ArrayList<Integer> progettiAssegnati) {
		super(id, nome, cognome, stipendioBase);
		this.linguaggiConosciuti = linguaggiConosciuti;
		this.progettiAssegnati = progettiAssegnati;
	}

	public ArrayList<String> getLinguaggiConosciuti() {
		return linguaggiConosciuti;
	}

	public void setLinguaggiConosciuti(ArrayList<String> linguaggiConosciuti) {
		this.linguaggiConosciuti = linguaggiConosciuti;
	}

	public ArrayList<Integer> getProgettiAssegnati() {
		return progettiAssegnati;
	}

	public void setProgettiAssegnati(ArrayList<Integer> progettiAssegnati) {
		this.progettiAssegnati = progettiAssegnati;
	}
}

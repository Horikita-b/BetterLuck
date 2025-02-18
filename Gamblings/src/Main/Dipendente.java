package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Dipendente {
	// 5
	final static String[] CRUD_DIPENDENTE = { "1) Aggiungi Dipendente", "2) Vedi Dipendenti", "3) Elimina Dipendente",
			"4) Modifica stipendio", "5) Calcola stipendi" };
	
	/*
	 * createTableDipendente: Metodo che crea la tabella Dipendente nel database.
	 * 
	 * @param connection: Oggetto Connection per gestire la connessione al database.
	 * 
	 */
	public static void createTableDipendente(Connection connection) {
		try (Statement stmt = connection.createStatement()) {

			// Creazione tabella CLIENTS
			final String CREATE_TABLE_DIPENDENTE = "CREATE TABLE IF NOT EXISTS Dipendente(\r\n"
					+ "DipendenteID INT NOT NULL AUTO_INCREMENT,\r\n" + "Nome VARCHAR(30) NOT NULL,\r\n"
					+ "Cognome VARCHAR(30) NOT NULL,\r\n" + "StipendioBase DOUBLE NOT NULL,\r\n"
					+ "Ruolo VARCHAR(30) NOT NULL,\r\n" + "PRIMARY KEY (DipendenteID));";

			stmt.execute(CREATE_TABLE_DIPENDENTE);

			System.out.println("Tabella creata/verificata correttamente.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/*
	 * insertDipendente: Metodo che inserisce un nuovo Dipendente nel database.
	 * 
	 * @param Nome: Nome del dipendente.
	 * @param Cognome: Cognome del dipendente.
	 * @param StipendioBase: Stipendio base del dipendente.
	 * @param Ruolo: Ruolo del dipendente.
	 * @param connection: Oggetto Connection per gestire la connessione al database.
	 * 
	 * @return l'ID generato per il nuovo Dipendente.
	 * 
	 * @throws SQLException
	 */
	public static int insertDipendente(String Nome, String Cognome, double StipendioBase, String Ruolo,
			Connection connection) throws SQLException{
		String sql = "INSERT INTO Dipendente(Nome, Cognome, StipendioBase, Ruolo) VALUES (?,?,?,?)";
		try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstmt.setString(1, Nome);
			pstmt.setString(2, Cognome);
			pstmt.setDouble(3, StipendioBase);
			pstmt.setString(4, Ruolo);

			int affectedRows = pstmt.executeUpdate();

			if (affectedRows > 0) {

				try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						return generatedKeys.getInt(1);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new SQLException("Creazione Dipendente fallita");
	}
	
	
	
	/*
	 * readAllDipendenti: Metodo che legge e stampa tutti i Dipendenti dal database.
	 * 
	 * @param connection: Oggetto Connection per gestire la connessione al database.
	 * 
	 */
	public static void readAllDipendenti(Connection connection) {
		String query = "SELECT * FROM Dipendente";

		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {

				int DipendenteID = rs.getInt("DipendenteID");
				String Nome = rs.getString("Nome");
				String Cognome = rs.getString("Cognome");
				double StipendioBase = rs.getDouble("StipendioBase");
				String Ruolo = rs.getString("Ruolo");

				System.out.printf("DipendenteID: %d | Nome: %s | Cognome: %s | StipendioBase: %.2f | Ruolo: %s\n",
						DipendenteID, Nome, Cognome, StipendioBase, Ruolo);
			}

		} catch (SQLException e) {
			e.getMessage();
		}
	}

	
	/*
	 * deleteDipendente: Metodo che elimina un Dipendente dal database in base all'ID.
	 * 
	 * @param connection: Oggetto Connection per gestire la connessione al database.
	 * @param DipendenteID: ID del Dipendente da eliminare.
	 * 
	 */
	public static void deleteDipendente(Connection connection, int DipendenteID) {
		String sql = "DELETE FROM Dipendente WHERE DipendenteID = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setInt(1, DipendenteID);
			int affectedRows = pstmt.executeUpdate();
			if (affectedRows > 0) {
				System.out.println("Dipendente con ID " + DipendenteID + " eliminato correttamente.");
			} else {
				System.out.println("Nessun dipendente eliminato. Verificare l'ID.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/*
	 * aggiornaStipendioDipendente: Metodo che aggiorna lo stipendio di un dipendente nel database.
	 * 
	 * @param connection: Oggetto Connection per gestire la connessione al database.
	 * @param nuovoStipendio: Nuovo valore dello stipendio base da assegnare al dipendente.
	 * @param DipendenteID: ID del dipendente il cui stipendio deve essere aggiornato.
	 * 
	 */
	public static void aggiornaStipendioDipendente(Connection connection, double nuovoStipendio, int DipendenteID) {

		final String query = "UPDATE Dipendente SET StipendioBase = ? WHERE DipendenteID = ?";

		try (PreparedStatement pstmt = connection.prepareStatement(query)) {

			pstmt.setDouble(1, nuovoStipendio);
			pstmt.setInt(2, DipendenteID);

			int righeAggiornate = pstmt.executeUpdate();

			// Verifica se l'aggiornamento è riuscito
			if (righeAggiornate > 0) {
				System.out.println("Stipendio aggiornato con successo per il dipendente con ID: " + DipendenteID);
			} else {
				System.out.println("Nessun dipendente trovato con ID: " + DipendenteID);
			}

		} catch (

		SQLException e) {
			System.err.println("Errore durante l'aggiornamento dello stipendio: " + e.getMessage());
		}
	}

	
	/*
	 * calcolaStipendi: Metodo che calcola il totale degli stipendi dei dipendenti nel database.
	 * 
	 * @param connection: Oggetto Connection per gestire la connessione al database.
	 * 
	 */
	public static void calcolaStipendi(Connection connection) {
		String query = "SELECT sum(dipendente.StipendioBase +COALESCE(manager.Bonus, 0))\r\n"
				+ "FROM dipendente\r\n"
				+ "LEFT JOIN manager\r\n"
				+ "ON manager.DipendenteID = dipendente.DipendenteID;";
		
		try (Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			
			if (rs.next()) {
				System.out.println("Totale stipendio dei dipendenti: $" + rs.getInt(1));
			} else {
				System.out.println("Qualcosa e' andato storto, riprovare");
			}
			
		} catch (SQLException e) {
			System.out.println("Errore durante il calcolo: " + e.getMessage());
		}
	}
	
	protected int id;
	protected String nome;
	protected String cognome;
	protected double stipendioBase;

	public Dipendente(int id, String nome, String cognome, double stipendioBase) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.stipendioBase = stipendioBase;
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

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public double getStipendioBase() {
		return stipendioBase;
	}

	public void setStipendioBase(double stipendioBase) {
		this.stipendioBase = stipendioBase;
	}

	public void aggiornaStipendioBaseSviluppatore(double stipendioBaseSviluppatore) {
		this.stipendioBase = stipendioBaseSviluppatore;
	}

}

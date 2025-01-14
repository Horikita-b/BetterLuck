package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


/*Employee (classe base): rappresenta un dipendente generico, con attributi come id, nome, cognome, e stipendioBase.
Manager (classe derivata): rappresenta un manager, con attributi aggiuntivi come bonus e teamGestito.
Developer (classe derivata): rappresenta uno sviluppatore, con attributi come linguaggiConosciuti e progettiAssegnati.
Il database deve contenere una tabella per i dipendenti e tabelle correlate per i progetti e i team. Deve essere possibile aggiungere, modificare, eliminare dipendenti, assegnarli a progetti e calcolare gli stipendi (considerando eventuali bonus). */
public class Dipendente {
	private static String CREATE_TABLE_DIPENDENTE = "CREATE TABLE IF NOT EXISTS Dipendente(\r\n"
			+ "DipendenteID INT NOT NULL AUTO_INCREMENT,\r\n"
			+ "Nome VARCHAR(30) NOT NULL,\r\n"
			+ "Cognome VARCHAR(30) NOT NULL,\r\n"
			+ "StipendioBase DOUBLE NOT NULL,\r\n"
			+ "Ruolo VARCHAR(30) NOT NULL,\r\n"
			+ "PRIMARY KEY (DipendenteID));";
	
	public static void createTableDipendente(Connection connection) {
        try (
             Statement stmt = connection.createStatement()) {

            // Creazione tabella CLIENTS
            String createTableDipendente = "CREATE TABLE IF NOT EXISTS Dipendente(\r\n"
        			+ "DipendenteID INT NOT NULL AUTO_INCREMENT,\r\n"
        			+ "Nome VARCHAR(30) NOT NULL,\r\n"
        			+ "Cognome VARCHAR(30) NOT NULL,\r\n"
        			+ "StipendioBase DOUBLE NOT NULL,\r\n"
        			+ "Ruolo VARCHAR(30) NOT NULL,\r\n"
        			+ "PRIMARY KEY (DipendenteID));";
            
            stmt.execute(createTableDipendente);
	

            System.out.println("Tabella creata/verificata correttamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	 public static void deleteDipendente(int DipendenteID,Connection connection) {
	        String sql = "DELETE FROM Dipendente WHERE DipendenteID = ?";
	        try (
	             PreparedStatement pstmt = connection.prepareStatement(sql)) {

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

	

	

	//2) Read Tutti Dipendenti
	
	public static void readAllDipendenti(Connection connection) {
		String query = "SELECT * FROM Dipendente";
				
			try (
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query))
			{
				while (rs.next()) {
		                
					int DipendenteID = rs.getInt("DipendenteID");
					String Nome = rs.getString("Nome");
					String Cognome = rs.getString("Cognome");
					double StipendioBase= rs.getDouble("StipendioBase");
					String Ruolo = rs.getString("Ruolo");

		    System.out.printf("DipendenteID: %d | Nome: %s | Cognome: %s | StipendioBase: %.2f | Ruolo: %s\n",
		    		DipendenteID, Nome, Cognome, StipendioBase, Ruolo);
				}

			} catch (SQLException e) {
				e.printStackTrace();
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
	
} 

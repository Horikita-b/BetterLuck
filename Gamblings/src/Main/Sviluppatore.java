package Main;

import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Sviluppatore extends Dipendente {

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

	public static void readAllSviluppatori(Connection connection) {

		String query = "SELECT sviluppatore.SviluppatoreID, dipendente.Nome, dipendente.Cognome FROM Sviluppatore INNER JOIN dipendente ON Sviluppatore.DipendenteID = dipendente.DipendenteID ";
				
			try (
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query))
			{
				while (rs.next()) {
		                
					int SviluppatoreID = rs.getInt("SviluppatoreID");
					String Nome = rs.getString("Nome");
					String Cognome = rs.getString("Cognome");
					
		    System.out.printf("SviluppatoreID: %d | Nome: %s | Cognome: %s " ,
		    		SviluppatoreID, Nome, Cognome);
				}
			}catch (SQLException e) {
			e.printStackTrace();
		}
	}



	
	
	public static int insertSviluppatore (int DipendenteID, int TeamID, Connection connection) {
		 String sql = "INSERT INTO Sviluppatore(DipendenteID, TeamID) VALUES (?,?)";
		 try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))  {
			 
			 pstmt.setInt(1, DipendenteID);
			 pstmt.setInt(2, TeamID);
			 
			 int affectedRows = pstmt.executeUpdate();
		        
		        if (affectedRows > 0) {
		            
		            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
		                if (generatedKeys.next()) {
		                    return generatedKeys.getInt(1);
		                }
		            }
		        }
			 }	catch (SQLException e) {
				e.printStackTrace();
			 }return -1;
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

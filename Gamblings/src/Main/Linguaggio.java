package Main;

import java.sql.Statement;

import java.sql.SQLException;

import java.sql.ResultSet;
import java.sql.Connection;

public class Linguaggio {
	private static String CREATE_TABLE_LINGUAGGIO = "CRATE TABLE IF NOT EXISTS Linguaggio("
			+ "LinguaggioID INT NOT NULL AUTO_INCREMENT, \r\n"
			+ "Nome VARCHAR(20) NOT NULL, \r\n"
			+ "PRIMARY KEY (LinguaggioID));";
	
	private static String CREATE_TABLE_SVILUPPATORE_PROGETTO = "CREATE TABLE IF NOT EXISTS Sviluppatore_Progetto(\r\n"
			+ "Sviluppatore_ProgettoID INT NOT NULL AUTO_INCREMENT,\r\n"
			+ "SviluppatoreID INT NOT NULL,\r\n"
			+ "ProgettoID INT NOT NULL,\r\n"
			+ "FOREIGN KEY(SviluppatoreID) REFERENCES Sviluppatore(SviluppatoreID) ON DELETE CASCADE ON UPDATE CASCADE,\r\n"
			+ "FOREIGN KEY(ProgettoID) REFERENCES Progetto(ProgettoID) ON DELETE CASCADE ON UPDATE CASCADE,\r\n"
			+ "PRIMARY KEY(Sviluppatore_ProgettoID));";



// 11) Read Tutti Linguaggi 

	public static void readAllLinguaggi(Connection connection) {
		String query = "SELECT * FROM Linguaggio";
			
		try (
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(query))
		{
		while (rs.next()) {
	                
		int LinguaggioID = rs.getInt("LinguaggioID");
		String Nome = rs.getString("Nome");
				

	    System.out.printf("LinguaggioID: %d | Nome: %s",
	    		LinguaggioID, Nome);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
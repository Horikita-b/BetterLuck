package Main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Progetto {
	private static String CREATE_TABLE_PROGETTO = "CREATE TABLE IF NOT EXISTS Progetto(\r\n"
			+ "ProgettoID INT NOT NULL AUTO_INCREMENT,\r\n"
			+ "NomeProgetto VARCHAR(30) NOT NULL,\r\n"
			+ "PRIMARY KEY (ProgettoID));";
	
	private static String CREATE_TABLE_SVILUPPATORE_LINGUAGGIO = "CREATE TABLE IF NOT EXISTS Sviluppatore_Linguaggio("
			+ "Sviluppatore_LinguaggioID INT NOT NULL AUTO_INCREMENT, \r\n"
			+ "SviluppatoreID INT NOT NULL,"
			+ "FOREIGN KEY(SviluppatoreID) REFERENCES sviluppatore(SviluppatoreID) ON DELETE CASCADE ON UPDATE CASCADE, \r\n"
			+ "LinguaggioID INT NOT NULL,\r\n"
			+ "FOREIGN KEY(LinguaggioID) REFERENCES Linguaggio(LinguaggioID) ON DELETE CASCADE ON UPDATE CASCADE,"
			+ "PRIMARY KEY (Sviluppatore_LinguaggioID));";
	




//Read tutti i progetti
	public static void readAllProgetti(Connection connection) {
	String query = "SELECT * FROM Progetto";
	
	try (
	Statement stmt = connection.createStatement();
	ResultSet rs = stmt.executeQuery(query))
	{
		while (rs.next()) {
                
			int ProgettoID = rs.getInt("ProgettoID");
			String NomeProgetto= rs.getString("NomeProgetto");
			
    System.out.printf("ProgettoID: %d | NomeProgetto: %s\n",
    		ProgettoID, NomeProgetto);
		}

	} catch (SQLException e) {
		e.printStackTrace();
	}
}
	
}
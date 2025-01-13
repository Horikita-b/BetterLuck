package Main;

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
	

}

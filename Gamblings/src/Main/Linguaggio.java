package Main;

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
}

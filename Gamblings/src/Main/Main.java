package Main;

import java.sql.*;


public class Main {
    
    private static String Create_table_DIPENDENTE = "CREATE TABLE Dipendente("
    		+ "DipendentiID INT NOT NULL AUTO_INCREMENT,"
    		+ "Nome VARCHAR(30)NOT NULL,"
    		+ "Cognome VARCHAR(30)NOT NULL,"
    		+ "StipendioBase DOUBLE NOT NULL,"
    		+ "Ruolo VARCHAR(30) NOT NULL,"
    		+ "PRIMARY KEY (DipendentiID));";
    		
    		
    	private static String Create_table_SVILUPPATORE = "CREATE TABLE Sviluppatore("
    			+ "SvilupatoriID INT NOT NULL AUTO_INCREMENT,"
    			+ "DipendenteID INT NOT NULL,"
    			+ "TeamID INT NOT NULL,"
    			+ "FOREIGN KEY(DipendenteID) REFERENCES dipendente(DipendenteID)  ON DELETE CASCADE ON UPDATE CASCADE,"
    			+ "FOREIGN KEY(TeamID) REFERENCES Team(TeamID) ON DELETE SET NULL,"
    			+ "PRIMARY KEY (SviluppatoriID));";
    	
    	private static String Create_table_TEAM ="CREATE TABLE Team("
    			+ "TeamID INT NOT NULL AUTO_INCREMENT,"
    			+ "nomeTeam VARCHAR(30)NOT NULL,"
    			+ "ManagerID INT NOT NULL,"
    			+ "FOREIGN KEY(ManagerID) REFERENCES Manager(ManagerID) ON UPDATE CASCADE ON DELETE SET NULL,"
    			+ "PRIMARY KEY(TeamID));";
    	
    	private static String Create_table_Progetto = "CREATE TABLE Progetto("
    			+ "ProgettiID INT NOT NULL AUTO_INCREMENT,"
    			+ "nomeProgetto VARCHAR(30) NOT NULL,"
    			+ "PRIMARY KEY (ProgettoID));";
    	
    	private static String Create_table_Linguaggio = "CREATE TABLE Linguaggio("
    			+ "LinguaggioID INT NOT NULL AUTO_INCREMENT,\r\n"
    			+ "Nome VARCHAR(20) NOT NULL,\r\n"
    			+ "PRIMARY KEY (LinguaggioID));";
    	
    	private static String Create_table_MANAGER = "CREATE TABLE Manager("
    			+ "ManagerID INT NOT NULL AUTO_INCREMENT,"
    			+ "DipendenteID INT NOT NULL,"
    			+ "Bonus DOUBLE NOT NULL,"
    			+ "FOREIGN KEY(DipendenteID) REFERENCES dipendente(DipendenteID)" 
    			+ "ON DELETE CASCADE"
    			+ "ON UPDATE CASCADE,"
    			+ "PRIMARY KEY (ManagerID));";
    			
    	private static String Create_table_SVILUPPATORE_PROGETTO = "CREATE TABLE Sviluppatore_Progetto("
    			+ "Sviluppatore_ProgettoID INT NOT NULL AUTO_INCREMENT,"
    			+ "SviluppatoreID INT NOT NULL,"
    			+ "ProgettoID INT NOT NULL,"
    			+ "FOREIGN KEY(LinguaggioID) REFERENCES Linguaggio(LinguaggioID) ON DELETE CASCADE ON UPDATE CASCADE,"
    			+ "PRIMARY KEY (Sviluppatore_LinguaggioID));";
    	
    	private static String Create_table_SVILUPPATORE_LINGUAGGIO = "CREATE TABLE CREATE TABLE Sviluppatore_Linguaggio("
    			+ "Sviluppatore_LinguaggioID INT NOT NULL AUTO_INCREMENT, \r\n"
    			+ "SviluppatoreID INT NOT NULL, FOREIGN KEY(SviluppatoreID) "
    			+ "REFERENCES sviluppatore(SviluppatoreID), \r\n"
    			+ "LinguaggioID INT NOT NULL,\r\n"
    			+ "FOREIGN KEY(LinguaggioID) REFERENCES Linguaggio(LinguaggioID)ON DELETE CASCADE ON UPDATE CASCADE ,"
    			+ "PRIMARY KEY (Sviluppatore_LinguaggioID));";
    			

	public static void main(String[] args) {
	
	
	
	
	}
	
	
}

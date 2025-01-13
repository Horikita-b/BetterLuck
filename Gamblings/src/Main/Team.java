package Main;

public class Team {
	
	private static String CREATE_TABLE_TEAM = "CREATE TABLE IF NOT EXISTS Team(\r\n"
			+ "TeamID INT NOT NULL AUTO_INCREMENT,\r\n"
			+ "NomeTeam VARCHAR(30)NOT NULL,\r\n"
			+ "ManagerID INT NOT NULL,\r\n"
			+ "FOREIGN KEY(ManagerID) REFERENCES Manager(ManagerID) ON UPDATE CASCADE ON DELETE SET NULL,\r\n"
			+ "PRIMARY KEY(TeamID));";

}

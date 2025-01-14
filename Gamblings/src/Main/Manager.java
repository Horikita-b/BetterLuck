package Main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Manager extends Dipendente {  
	private static String CREATE_TABLE_MANAGER = "CREATE TABLE IF NOT EXISTS Manager(\r\n"
			+ "ManagerID INT NOT NULL AUTO_INCREMENT,\r\n"
			+ "DipendenteID INT NOT NULL,\r\n"
			+ "Bonus DOUBLE NOT NULL,\r\n"
			+ "FOREIGN KEY(DipendenteID) REFERENCES dipendente(DipendenteID)\r\n"
			+ "ON DELETE CASCADE\r\n"
			+ "ON UPDATE CASCADE,\r\n"
			+ "PRIMARY KEY (ManagerID));";
	
	
	public static void createTableManager(Connection connection) {
        try (
             Statement stmt = connection.createStatement()) {

            // Creazione tabella CLIENTS
            String createTableManager = "CREATE TABLE IF NOT EXISTS Manager(\r\n"
        			+ "ManagerID INT NOT NULL AUTO_INCREMENT,\r\n"
        			+ "DipendenteID INT NOT NULL,\r\n"
        			+ "Bonus DOUBLE NOT NULL,\r\n"
        			+ "FOREIGN KEY(DipendenteID) REFERENCES dipendente(DipendenteID)\r\n"
        			+ "ON DELETE CASCADE\r\n"
        			+ "ON UPDATE CASCADE,\r\n"
        			+ "PRIMARY KEY (ManagerID));";
            
            stmt.execute(createTableManager);
	
            System.out.println("Tabella creata/verificata correttamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	
	
	
	
	
	
	
	
	
	
	private double bonus;
	private ArrayList<Sviluppatore> teamGestito;
	public Manager(int id, String nome, String cognome, double stipendioBase, double bonus, ArrayList<Sviluppatore> teamGestito) {
		super(id, nome, cognome, stipendioBase);
		this.bonus = bonus;
		this.teamGestito = teamGestito;
	}
	public double getBonus() {
		return bonus;
	}
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	public ArrayList<Sviluppatore> getTeamGestito() {
		return teamGestito;
	}
	public void setTeamGestito(ArrayList<Sviluppatore> teamGestito) {
		this.teamGestito = teamGestito;
	}
	@Override
	public double getStipendioBase() {
		return stipendioBase + bonus;
	}
}

package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	 // 6) Read Tutti Manager 
	

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
	
	
	public static void readAllManager(Connection connection) {
		String query = "SELECT manager.ManagerID, dipendente.Nome, dipendente.Cognome, manager.Bonus FROM Manager INNER JOIN dipendente ON Manager.DipendenteID = dipendente.DipendenteID ";
				
			try (
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query))
			{
				while (rs.next()) {
		                
					int ManagerID = rs.getInt("ManagerID");
					String Nome = rs.getString("Nome");
					String Cognome = rs.getString("Cognome");
					double Bonus= rs.getDouble("Bonus");
					
		    System.out.printf("ManagerID: %d | Nome: %s | Cognome: %s | Bonus: %.2f\n" ,
		    		ManagerID, Nome, Cognome, Bonus);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	
	public static int insertManager (int DipendenteID, double Bonus, Connection connection) {
	String sql = "INSERT INTO Manager(DipendenteID, Bonus) VALUES (?,?)";
	try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))  {
		 
		 
		 pstmt.setInt(1, DipendenteID);
		 pstmt.setDouble(2, Bonus);
		 
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

package Main;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class Sviluppatore extends Dipendente {
	private static String CREATE_TABLE_SVILUPPATORE = "CREATE TABLE IF NOT EXISTS Sviluppatore(\r\n"
			+ "SviluppatoreID INT NOT NULL AUTO_INCREMENT,\r\n"
			+ "DipendenteID INT NOT NULL,\r\n"
			+ "TeamID INT NOT NULL, \r\n"
			+ "FOREIGN KEY(DipendenteID) REFERENCES dipendente(DipendenteID) ON UPDATE CASCADE ON DELETE CASCADE,\r\n"
			+ "FOREIGN KEY(TeamID) REFERENCES Team(TeamID) ON DELETE SET NULL\r\n"
			+ "PRIMARY KEY (SviluppatoreID));";
	
	
	public static void createTableSviluppatore(Connection connection) {
        try (
             Statement stmt = connection.createStatement()) {

            // Creazione tabella CLIENTS
            String createTableSviluppatore = "CREATE TABLE IF NOT EXISTS Sviluppatore(\r\n"
        			+ "SviluppatoreID INT NOT NULL AUTO_INCREMENT,\r\n"
        			+ "DipendenteID INT NOT NULL,\r\n"
        			+ "TeamID INT NOT NULL, \r\n"
        			+ "FOREIGN KEY(DipendenteID) REFERENCES dipendente(DipendenteID) ON UPDATE CASCADE ON DELETE CASCADE,\r\n"
        			+ "FOREIGN KEY(TeamID) REFERENCES Team(TeamID) ON DELETE SET NULL\r\n"
        			+ "PRIMARY KEY (SviluppatoreID));";
            
            stmt.execute(createTableSviluppatore);
	
            System.out.println("Tabella creata/verificata correttamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private ArrayList<String> linguaggiConosciuti;
	private ArrayList<Integer> progettiAssegnati;

	public Sviluppatore(int id, String nome, String cognome, double stipendioBase, ArrayList<String> linguaggiConosciuti, ArrayList<Integer> progettiAssegnati) {
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

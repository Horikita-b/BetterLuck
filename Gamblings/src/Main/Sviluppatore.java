package Main;
import java.util.ArrayList;
public class Sviluppatore extends Dipendente {
	private static String CREATE_TABLE_SVILUPPATORE = "CREATE TABLE IF NOT EXISTS Sviluppatore(\r\n"
			+ "SviluppatoreID INT NOT NULL AUTO_INCREMENT,\r\n"
			+ "DipendenteID INT NOT NULL,\r\n"
			+ "TeamID INT NOT NULL, \r\n"
			+ "FOREIGN KEY(DipendenteID) REFERENCES dipendente(DipendenteID) ON UPDATE CASCADE ON DELETE CASCADE,\r\n"
			+ "FOREIGN KEY(TeamID) REFERENCES Team(TeamID) ON DELETE SET NULL\r\n"
			+ "PRIMARY KEY (SviluppatoreID));";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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

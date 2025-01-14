package Main;

import java.util.ArrayList;

public class Managers extends Employers {  
		private static String CREATE_TABLE_MANAGERS = "CREATE TABLE IF NOT EXISTS Managers (\r\n"
				+ "ManagerID INT NOT NULL AUTO_INCREMENT,\r\n"
				+ "DipendenteID INT NOT NULL,\r\n"
				+ "Bonus DOUBLE NOT NULL,\r\n"
				+ "FOREIGN KEY(DipendenteID) REFERENCES dipendente(DipendenteID)\r\n"
				+ "ON DELETE CASCADE\r\n"
				+ "ON UPDATE CASCADE,\r\n"
				+ "PRIMARY KEY (ManagerID));";
		
		
	private double bonus;
	private ArrayList<Developers> teamGestito;
	public Managers(int id, String nome, String cognome, double stipendioBase, double bonus, ArrayList<Developers> teamGestito) {
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
	public ArrayList<Developers> getTeamGestito() {
		return teamGestito;
	}
	public void setTeamGestito(ArrayList<Developers> teamGestito) {
		this.teamGestito = teamGestito;
	}
	@Override
	public double getStipendioBase() {
		return stipendioBase + bonus;
	}
}

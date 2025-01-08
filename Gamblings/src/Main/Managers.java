package Main;

import java.util.ArrayList;

public class Managers extends Employers {  
	
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

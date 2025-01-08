package Main;

/*Employee (classe base): rappresenta un dipendente generico, con attributi come id, nome, cognome, e stipendioBase.
Manager (classe derivata): rappresenta un manager, con attributi aggiuntivi come bonus e teamGestito.
Developer (classe derivata): rappresenta uno sviluppatore, con attributi come linguaggiConosciuti e progettiAssegnati.
Il database deve contenere una tabella per i dipendenti e tabelle correlate per i progetti e i team. Deve essere possibile aggiungere, modificare, eliminare dipendenti, assegnarli a progetti e calcolare gli stipendi (considerando eventuali bonus). */
public class Employers {

	protected int id;
	protected String nome;
	protected String cognome;
	protected double stipendioBase;

	public Employers(int id, String nome, String cognome, double stipendioBase) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.stipendioBase = stipendioBase;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public double getStipendioBase() {
		return stipendioBase;
	}

	public void setStipendioBase(double stipendioBase) {
		this.stipendioBase = stipendioBase;
	}
	
} 

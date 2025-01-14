package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*Employee (classe base): rappresenta un dipendente generico, con attributi come id, nome, cognome, e stipendioBase.
Manager (classe derivata): rappresenta un manager, con attributi aggiuntivi come bonus e teamGestito.
Developer (classe derivata): rappresenta uno sviluppatore, con attributi come linguaggiConosciuti e progettiAssegnati.
Il database deve contenere una tabella per i dipendenti e tabelle correlate per i progetti e i team. Deve essere possibile aggiungere, modificare, eliminare dipendenti, assegnarli a progetti e calcolare gli stipendi (considerando eventuali bonus). */
public class Dipendente {
	private static String CREATE_TABLE_DIPENDENTE = "CREATE TABLE IF NOT EXISTS Dipendente(\r\n"
			+ "DipendenteID INT NOT NULL AUTO_INCREMENT,\r\n"
			+ "Nome VARCHAR(30) NOT NULL,\r\n"
			+ "Cognome VARCHAR(30) NOT NULL,\r\n"
			+ "StipendioBase DOUBLE NOT NULL,\r\n"
			+ "Ruolo VARCHAR(30) NOT NULL,\r\n"
			+ "PRIMARY KEY (DipendenteID));";
	
	
	public static  void createDipendente(Connection connection, String nome, String cognome, double stipendioBase, String ruolo) throws SQLException{
		String query = "INSERT INTO dipendente VALUES (NULL, ?, ?, ?, ?)";
		PreparedStatement pstmt = connection.prepareStatement(query);
	}
	
	public static  void readStipendioTotale(Connection connection) {
		String query = "SELECT SUM(dipendente.StipendioBase + manager.bonus) FROM dipendente INNER JOIN manager ON dipendente.DipendenteID = manager.";
	}
	
	
	
	
	
	
	
	

	protected int id;
	protected String nome;
	protected String cognome;
	protected double stipendioBase;

	public Dipendente(int id, String nome, String cognome, double stipendioBase) {
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

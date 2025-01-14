package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	// Static Attributes
	private static final String URL = "jdbc:mysql://localhost:3306/";
	private static final String USER = "root";
	private static final String PASSWORD = "_____!";
	
	private Connection connection;
	
	
	DBManager(String nomeDatabase) throws SQLException{
		this.connection = DriverManager.getConnection(URL + nomeDatabase, USER, PASSWORD);
	}


	public Connection getConnection() {
		return connection;
	}
	
	public void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			System.out.println("Chiusura connection non e' andato a buon fine");
		}
	}
	
}

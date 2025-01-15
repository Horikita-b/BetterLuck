package Main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	// CORREZIONI
	// non si puo usare NOT NULL quando c'e ON DELETE SET NULL --
	// Attenzione con JOIN multipli

	// DA FARE
	// Aggiustare readAllSviluppatori
	
	// Create Dipendenti (Sviluppatori e Manager)
	// Aggiungere Dipendente(Sviluppatore/Manager)
	// Aggiornare Sviluppatore (Aggiungere/Togliere un linguaggioConosciuto, Cambiare Team, Aggiungere/Togliere Progetti)
	// Aggiornare Manager (Cambiare Bonus)
	// Aggiornare Team -> aggiornare il suo ManagerID
	// Visualizzare Dipendenti di un Team/Manager ->
	// Visualizzare Linguaggi conosciuti di uno Sviluppatore ->
	// Visualizzare Tutti i Progetti di uno Sviluppatore ->
	// Visualizzare Tutti gli Sviluppatore di un Progetto ->
	// Calcolare gli stipendi
	
	
	// Commenti
	public static void printMenu() {
		System.out.println("1) Visualizzare tutti i Dipendenti");
		System.out.println("2) Visualizzare tutti gli Sviluppatori");
		System.out.println("3) Visualizzare tutti i Manager");
		System.out.println("4) Visualizzare tutti i Team");
		System.out.println("5) Visualizzare tutti i Progetti");
		System.out.println("6) Visualizzare tutti i Linguaggi");
		System.out.println("40) Eliminare un Dipendente");
		System.out.println("41) Eliminare un Team");
		System.out.println("42) Eliminare un Progetto");
		System.out.println("43) Eliminare un Linguaggio");
		System.out.println("55) visualizzare sviluppatore linguaggi");
		System.out.println("50) Cambiare stipendio di un Dipendente");
		System.out.println("99) Uscire");
	}

	// Fare commento -> Funziona
	public static void createTables(Connection conn) {
		Dipendente.createTableDipendente(conn);
		Manager.createTableManager(conn);
		Team.createTableTeam(conn);
		Linguaggio.createTableLinguaggio(conn);
		Progetto.createTableProgetto(conn);
		Sviluppatore.createTableSviluppatore(conn);
		Linguaggio.createTableSviluppatoreLinguaggio(conn);
		Progetto.createTableSviluppatoreProgetto(conn);
	}

	// Fare commento -> Funziona
	public static DBManager getDBManager(Scanner scanner) {
		DBManager db = null;
		String dbNome = null;
		do {
			try {
				System.out.println("Inserire nome database: ");
				dbNome = scanner.nextLine();
				if (dbNome.equalsIgnoreCase("EXIT")) {
					return null;
				}
				db = new DBManager(dbNome);
				return db;
			} catch (SQLException e) {
				System.out.println("Errore: " + e.getMessage());
			} catch (Exception e) {
				System.out.println("Errore: inserire un input valido");
			}
		} while (true);
	}

	public static void main(String[] args) throws SQLException {
		Scanner scanner = new Scanner(System.in);
		DBManager db = getDBManager(scanner);
		if (db == null) {
			System.out.println("Chiusura sistema");
			System.exit(0);
		}
		Connection conn = db.getConnection();
		createTables(conn);
		int scelta = 0;
		do {
			try {
				printMenu();
				scelta = scanner.nextInt();
				scanner.nextLine();

				switch (scelta) {
				case 1:
					Dipendente.readAllDipendenti(conn);
					break;
				case 2:
					Sviluppatore.readAllSviluppatori(conn);
					break;
				case 3:
					Manager.readAllManager(conn);
					break;
				case 4:
					Team.readAllTeams(conn);
					break;
				case 5:
					Progetto.readAllProgetti(conn);
					break;
				case 6:
					Linguaggio.readAllLinguaggi(conn);
					break;
				case 40: {
					Dipendente.readAllDipendenti(conn);
					System.out.println("Scegli il dipendente che vuoi eliminare");
					int id_dipendente = scanner.nextInt();
					Dipendente.deleteDipendente(conn, id_dipendente);
				}
					break;
				case 41: {
					Team.readAllTeams(conn);
					System.out.println("Scegli il Team che vuoi eliminare");
					int id_team = scanner.nextInt();
					Team.deleteTeam(conn, id_team);
				}
					break;
				case 42: {
					Progetto.readAllProgetti(conn);
					System.out.println("Scegli il Progetto che vuoi eliminare");
					int id_progetto = scanner.nextInt();
					Progetto.deleteProgetto(conn, id_progetto);
				}
					break;
				case 43: {
					Linguaggio.readAllLinguaggi(conn);
					System.out.println("Scegli il Linguaggio che vuoi eliminare");
					int id_linguaggio = scanner.nextInt();
					Linguaggio.deleteLinguaggio(conn, id_linguaggio);
				}
					break;
				case 50: {
					System.out.println("Hai scelto di Cambiare lo stipendio di un dipendente");
					Dipendente.readAllDipendenti(conn);
					System.out.println("Scegli il dipendente a cui vuoi cambiare lo stipendio");
					int id_dipendente = scanner.nextInt();
					System.out.println("Inserire il nuovo stipendio");
					double nuovo_stipendio = scanner.nextDouble();
					Dipendente.aggiornaStipendioDipendente(conn, nuovo_stipendio, id_dipendente);
				}
					break;
				case 55: {
					Sviluppatore.readAllSviluppatori(conn);
					System.out.println("scegli lo sviluppatore che vuoi visualizzare i linguaggi");
					int DipendenteID = scanner.nextInt();
					Linguaggio.readAllLinguaggiSviluppatore(conn, DipendenteID);
				}break;
				
				case 99:
					System.out.println("Sei uscito dal programma");
				}
			} catch (InputMismatchException e) {
				System.out.println("Errore: Inserire un input valido");
				scanner.nextLine();
			}
			System.out.println();
		} while (scelta != 99);

		scanner.close();
		db.closeConnection();

		/*
		 * C R U D
		 * 
		 * TUTTI I DIPENDENTI (Sviluppatore - Manager) 1) Create Dipendente
		 * (Sviluppatore o Manager) // fai i metodi createSviluppatore e createManager
		 * 2) - Read Tutti Dipendenti !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 3) Read
		 * Dipendente by ID !!!!!!!! 4) UpdateDipendente -> Stipendio -> Connection
		 * connection, id , nuovo valore !!!!!! 5) Delete Dipendente by ID ->
		 * !!!!!!!!!!!!!!!!
		 * 
		 * TUTTI GLI SVILUPPATORI 6) - Read Tutti Sviluppatori !!!!!!!!!!!!!!!!!!!!!! 7)
		 * Read Sviluppatore by ID !!!!!!!! 8) Update Sviluppatore -> Cambiare i suoi
		 * linguaggiconosciuti e progettiAssegnati e team
		 * 
		 * TUTTI I MANAGER 6) - Read Tutti Manager !!!!!!!!!!!!!!!!!! 6.1) 7) Read
		 * Manager by ID !!!!!!!! 8.8) Update Manager -> Cambiare bonus e teamGestito ->
		 * IDMANAGER
		 * 
		 * 
		 * TUTTI I LINGUAGGI 10) Create Linguaggi 11) - Read Tutti Linguaggi
		 * !!!!!!!!!!!!!!!!! 12) ==== 13) (Update LinguaggiConosciuti di uno
		 * sviluppatore -> create/eliminare record sviluppatore_linguaggio) 14) Delete
		 * Linguaggio !!!!!!!!!!!!!!!!!!!!!!
		 * 
		 * Tutti i TEAM 15) Create Team 16) - Read tutti i team !!!!!!!!!!!!!!! 17) ----
		 * 18) Team -> cambiare il suo manager (se e solo se IDMANAGER NULL) 19) Delete
		 * Team !!!!!!!!!!!!
		 * 
		 * TUTTI I PROGETTI 20) Create un progetto 21) - Read tutti i progetti
		 * !!!!!!!!!!!!!!!!!!!!!!!!! 22) --- 24) Delete Progetto !!!!!!!!!!!!!!!!!!!!!
		 * 
		 * 25) Calcola Uscite stipendi sum(stipendiBase + bonus)
		 * 
		 */

	}
}

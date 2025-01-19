package Main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	final static String[] CATEGORIE = { "1) Dipendente", "2) Sviluppatore", "3) Manager", "4) Linguaggio", "5) Team",
			"6) Progetto" };

	final static String[][] SUBCATEGORIE = { Dipendente.CRUD_DIPENDENTE, Sviluppatore.CRUD_SVILUPPATORE,
			Manager.CRUD_MANAGER, Linguaggio.CRUD_LINGUAGGIO, Team.CRUD_TEAM, Progetto.CRUD_PROGETTO };

	/*
	 * crudDipendente : Metodo che gestisce le operazioni CRUD (Create, Read, Update, Delete) 
	 * relative ai Dipendenti. 
	 * 
	 * @param scanner : Oggetto scanner per l'input utente
	 * 
	 * @paran conn : Oggett connection per gestire la connessione tra il database e
	 * java application
	 * 
	 * @throws : SQLException, InputMismatchException
	 */
	public static void crudDipendente(Scanner scanner, Connection conn) throws SQLException {
		int scelta = 0;
		do {
			printCrud(Dipendente.CRUD_DIPENDENTE);
			scelta = scanner.nextInt();
			scanner.nextLine();

			switch (scelta) {
			case 1: {
				String ruolo;
				int id = -1;
				do {
					System.out.println("Inserire ruolo (Sviluppatore o Manager)");
					ruolo = scanner.nextLine();
					if (ruolo.equalsIgnoreCase("SVILUPPATORE")) {
						ruolo = "Sviluppatore";
					} else if (ruolo.equalsIgnoreCase("MANAGER")) {
						ruolo = "Manager";
					} else if (ruolo.equalsIgnoreCase("EXIT")) {
						break;
					} else {
						System.out.println("Inserire un ruolo valido");
						continue;
					}

					System.out.println("Inserire Nome");
					String nome = scanner.nextLine();
					System.out.println("Inserire Cognome");
					String cognome = scanner.nextLine();
					System.out.println("Inserire Stipendio");
					double stipendio = scanner.nextDouble();
					int id_dipendente = Dipendente.insertDipendente(nome, cognome, stipendio, ruolo, conn);

					if (ruolo.equalsIgnoreCase("SVILUPPATORE")) {
						Team.readAllTeams(conn);
						System.out.println("Inserire Team:");
						int id_team = scanner.nextInt();
						id = Sviluppatore.insertSviluppatore(id_dipendente, id_team, conn);
					} else {
						System.out.println("Inserire Bonus: ");
						double bonus = scanner.nextDouble();
						id = Manager.insertManager(id_dipendente, bonus, conn);
					}

					if (id != -1) {
						System.out.println("Creazione dipendente completata");
					} else {
						System.out.println("Creazione dipendente fallita");
					}
					break;
				} while (!ruolo.equalsIgnoreCase("EXIT"));

			}
				break;
			case 2:
				Dipendente.readAllDipendenti(conn);
				break;
			case 3: {
				Dipendente.readAllDipendenti(conn);
				System.out.println("Quale dipendente vuoi eliminare?");
				int id_dipendente = scanner.nextInt();
				Dipendente.deleteDipendente(conn, id_dipendente);
			}

				break;
			case 4: {
				Dipendente.readAllDipendenti(conn);
				System.out.println("Di quale dipendente vuoi cambiare lo stipendio?");
				int id_dipendente = scanner.nextInt();
				System.out.println("Inserire il nuovo valore: ");
				double nuovo_stipendio = scanner.nextDouble();
				Dipendente.aggiornaStipendioDipendente(conn, nuovo_stipendio, id_dipendente);
			}
				break;
			case 5:
				Dipendente.calcolaStipendi(conn);
				break;
			}
		} while (scelta != 0);
	}

	
	/*
	 * crudSviluppatore:  Metodo che gestisce le operazioni CRUD (Create, Read, Update, Delete) 
	 * relative ai Sviluppatori.
	 *
	 * @param scanner: Scanner per leggere l'input dell'utente.
	 * @param conn: Connessione al database.
	 *
	 * @throws SQLException
	 */
	public static void crudSviluppatore(Scanner scanner, Connection conn) throws SQLException {
		int scelta = 0;
		do {
			printCrud(Sviluppatore.CRUD_SVILUPPATORE);
			scelta = scanner.nextInt();
			scanner.nextLine();

			switch (scelta) {
			case 1:
				Sviluppatore.readAllSviluppatori(conn);
				break;
			case 2:
			{
				Sviluppatore.readAllSviluppatori(conn);
				System.out.println("Di quale sviluppatore vuoi vedere i linguaggi conosciuti? ");
				int id_sviluppatore = scanner.nextInt();
				Linguaggio.readAllLinguaggiSviluppatore(conn, id_sviluppatore);
			}
				break;
			case 3:
			{
				Sviluppatore.readAllSviluppatori(conn);
				System.out.println("Di quale sviluppatore vuoi vedere i progetti? ");
				int id_sviluppatore = scanner.nextInt();
				Progetto.readAllProgettiSviluppatore(conn, id_sviluppatore);
			}
				break;
			case 4:
			{
				Sviluppatore.readAllSviluppatori(conn);
				System.out.println("A quale sviluppatore vuoi aggiungere un linguaggio conosciuto? ");
				int id_sviluppatore = scanner.nextInt();
				Linguaggio.readAllLinguaggi(conn);
				System.out.println("Quale linguaggio vuoi aggiungere? ");
				int id_linguaggio =scanner.nextInt();
				Linguaggio.inserisciLinguaggioSviluppatore(conn, id_sviluppatore, id_linguaggio);
			}
				break;
			case 5:
			{
				Sviluppatore.readAllSviluppatori(conn);
				System.out.println("Da quale sviluppatore vuoi rimuovere un linguaggio conosciuto? ");
				int id_sviluppatore = scanner.nextInt();
				Linguaggio.readAllLinguaggi(conn);
				System.out.println("Quale linguaggio vuoi rimuovere? ");
				int id_linguaggio =scanner.nextInt();
				Linguaggio.rimuoviLinguaggioSviluppatore(conn, id_sviluppatore, id_linguaggio);
			}
				break;
			case 6:
			{
				Sviluppatore.readAllSviluppatori(conn);
				System.out.println("A quale sviluppatore vuoi aggiungere un progetto? ");
				int id_sviluppatore = scanner.nextInt();
				Progetto.readAllProgetti(conn);
				System.out.println("Quale progetto vuoi aggiungere? ");
				int id_progetto =scanner.nextInt();
				Progetto.inserisciProgettoSviluppatore(conn, id_sviluppatore, id_progetto);
			}
				break;
			case 7:
			{
				Sviluppatore.readAllSviluppatori(conn);
				System.out.println("Da quale sviluppatore vuoi rimuovere un progetto? ");
				int id_sviluppatore = scanner.nextInt();
				Progetto.readAllProgetti(conn);
				System.out.println("Quale progetto vuoi rimuovere? ");
				int id_progetto =scanner.nextInt();
				Progetto.rimuoviProgettoSviluppatore(conn, id_sviluppatore, id_progetto);
			}
				break;
			case 8:
			{
				Sviluppatore.readAllSviluppatori(conn);
				System.out.println("Di quuale sviluppatore vuoi cambiare il team? ");
				int id_sviluppatore = scanner.nextInt();
				Team.readAllTeams(conn);
				System.out.println("Scegliere il nuovo team");
				int it_team = scanner.nextInt();
				Sviluppatore.aggiornaTeam(conn, id_sviluppatore, it_team);
			}
				break;
			}
		} while (scelta != 0);
	}

	/*
	 * crudManager:  Metodo che gestisce le operazioni CRUD (Create, Read, Update, Delete) 
	 * relative ai Manager.
	 *
	 * @param scanner: Scanner per leggere l'input dell'utente.
	 * @param conn: Connessione al database.
	 *
	 * @throws SQLException
	 */
	public static void crudManager(Scanner scanner, Connection conn) throws SQLException{
		int scelta = 0;
		do {
			printCrud(Manager.CRUD_MANAGER);
			scelta = scanner.nextInt();
			scanner.nextLine();

			switch (scelta) {
			case 1:
				Manager.readAllManager(conn);
				break;
			case 2:
			{
				Manager.readAllManager(conn);
				System.out.println("Di quale manager vuoi cambiare il team gestito?");
				int id_manager = scanner.nextInt();
				Team.readAllTeams(conn);
				System.out.println("Inserire nuovo team da gestire");
				int id_team = scanner.nextInt();
				Team.updateManagerID(conn, id_team, id_manager);
			}
				break;
			case 3: 
				Manager.readAllManager(conn);
				System.out.println("Di quale manager vuoi cambiare il bonus?");
				int id_manager = scanner.nextInt();
				System.out.println("Inserire nuovo bonus");
				double bonus = scanner.nextDouble();
				Manager.aggiornaBonus(conn, bonus, id_manager);
				break;
			}
		} while (scelta != 0);
	}
	
	/*
	 * crudLinguaggio:  Metodo che gestisce le operazioni CRUD (Create, Read, Update, Delete) 
	 * relative ai Linguaggi.
	 *
	 * @param scanner: Scanner per leggere l'input dell'utente.
	 * @param conn: Connessione al database.
	 *
	 * @throws SQLException
	 */
	public static void crudLinguaggio(Scanner scanner, Connection conn) throws SQLException {
		int scelta = 0;
		do {
			printCrud(Linguaggio.CRUD_LINGUAGGIO);
			scelta = scanner.nextInt();
			scanner.nextLine();

			switch (scelta) {
			case 1:
				Linguaggio.readAllLinguaggi(conn);
				break;
			case 2:
			{
				System.out.println("Nome del nuovo linguaggio: ");
				String nome = scanner.nextLine();
				Linguaggio.insertLinguaggio(conn, nome);
			}
				break;
			case 3: 
			{
				Linguaggio.readAllLinguaggi(conn);
				System.out.println("Quale linguaggio e' da rimuovere? ");
				int id_linguaggio = scanner.nextInt();
				Linguaggio.deleteLinguaggio(conn, id_linguaggio);
			}
				break;
			}
		} while (scelta != 0);
	}
	
	/*
	 * crudTeam:  Metodo che gestisce le operazioni CRUD (Create, Read, Update, Delete) 
	 * relative ai Team.
	 *
	 * @param scanner: Scanner per leggere l'input dell'utente.
	 * @param conn: Connessione al database.
	 *
	 * @throws SQLException
	 */
	public static void crudTeam(Scanner scanner, Connection conn) throws SQLException {
		int scelta = 0;
		do {
			printCrud(Team.CRUD_TEAM);
			scelta = scanner.nextInt();
			scanner.nextLine();

			switch (scelta) {
			case 1:
				Team.readAllTeams(conn);
				break;
			case 2:
			{
				Team.readAllTeams(conn);
				System.out.println("Di quale team vuoi sapere gli sviluppatori? ");
				int id_team = scanner.nextInt();
				Team.readDipendentiTeam(conn, id_team);
			}
				break;
			case 3: 
			{
				System.out.println("Nome del nuovo team: ");
				String nome = scanner.nextLine();
				Team.insertTeam(conn, nome);
			}
				break;
			case 4:
			{
				Team.readAllTeams(conn);
				System.out.println("Quale team e' da rimuovere? ");
				int id_team = scanner.nextInt();
				Team.deleteTeam(conn, id_team);
			}
			}
		} while (scelta != 0);
	}
	
	/*
	 * crudProgetto:  Metodo che gestisce le operazioni CRUD (Create, Read, Update, Delete) 
	 * relative ai Progetti.
	 *
	 * @param scanner: Scanner per leggere l'input dell'utente.
	 * @param conn: Connessione al database.
	 *
	 * @throws SQLException
	 */
	public static void crudProgetto(Scanner scanner, Connection conn) throws SQLException {
		int scelta = 0;
		do {
			printCrud(Progetto.CRUD_PROGETTO);
			scelta = scanner.nextInt();
			scanner.nextLine();

			switch (scelta) {
			case 1:
				Progetto.readAllProgetti(conn);
				break;
			case 2:
			{
				System.out.println("Nome del nuovo progetto: ");
				String nome = scanner.nextLine();
				Progetto.insertProgetto(conn, nome);
			}
				break;
			case 3:
			{
				Progetto.readAllProgetti(conn);
				System.out.println("Quale progetto e' da eliminare? ");
				int id_progetto = scanner.nextInt();
				Progetto.deleteProgetto(conn, id_progetto);
			}
				break;
			}
		} while (scelta != 0);
	}
	
	/*
	 * printCrud: Stampa il menu CRUD
	 *
	 * @param args: Una serie di stringhe che rappresentano le voci del menu CRUD.
	 */
	public static void printCrud(String... args) {
		System.out.println("0) Uscire");
		for (String menu : args) {
			System.out.println(menu);
		}
	}

	/*
	 * createTables: Crea tutte le tabelle necessarie nel database.
	 *
	 * @param conn: Connessione al database.
	 */
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

	/*
	 * getDBManager: Crea un'istanza di DBManager basandosi sull'inserimento dell'utente.
	 * @param scanner: Scanner per la lettura dell'input dell'utente.
	 * @return DBManager: Ritorna un'istanza di `DBManager` se la connessione al database ha successo, altrimenti `null` se l'utente inserisce "EXIT".
	 */
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
		int mainScelta = 0;
		do {
			try {
				printCrud(CATEGORIE);
				mainScelta = scanner.nextInt();
				scanner.nextLine();

				switch (mainScelta) {
				case 1:
					crudDipendente(scanner, conn);
					break;
				case 2:
					crudSviluppatore(scanner, conn);
					break;
				case 3:
					crudManager(scanner, conn);
					break;
				case 4:
					crudLinguaggio(scanner, conn);
					break;
				case 5:
					crudTeam(scanner, conn);
					break;
				case 6:
					crudProgetto(scanner, conn);
					break;
				case 0:
					System.out.println("Sei uscito dal programma");
				}
			} catch (InputMismatchException e) {
				System.out.println("Errore: Inserire un input valido");
				scanner.nextLine();
			}
			System.out.println();
		} while (mainScelta != 0);

		scanner.close();
		db.closeConnection();
	}
}

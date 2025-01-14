package Main;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
	giorgia                                antonino
	1(+1 metodi)  8(sono 3 metodi)   8.8(bonus e teamGestito)   10   13   15   18    20
	controllo ruolo -> creare un record dipendente che ritorna PK -> createSviluppatore o createManager()
	
	
	public static void printMenu() {
		System.out.println();
	}
	
	public static void createTables(Connection conn) {
		Dipendente.createTableDipendente(conn);
		Sviluppatore.createTableSviluppatore(conn);
		Manager.createTableManager(conn);
		Linguaggio.createTableLinguaggio(conn);
		Linguaggio.createTableSviluppatoreLinguaggio(conn);
		Progetto.createTableProgetto(conn);
		Progetto.createTableSviluppatoreProgetto(conn);
		Team.createTableTeam(conn);
	}
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		DBManager db = new DBManager("Azienda");
		Connection conn = db.getConnection();
		createTables(conn);
		
		int scelta;
		do {
			
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
			case 99:
				System.out.println("Hai chiuso il sistema");
				break;
			}
			
		} while (true);
		/* C R U D
		 * 
		 *  TUTTI I DIPENDENTI (Sviluppatore - Manager)
		 * 1) Create Dipendente (Sviluppatore o Manager) // fai i metodi createSviluppatore e createManager  
		 * 2) - Read Tutti Dipendenti !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		 * 3) Read Dipendente by ID !!!!!!!!
		 * 4) UpdateDipendente -> Stipendio -> Connection connection, id , nuovo valore !!!!!!
		 * 5) Delete Dipendente by ID -> !!!!!!!!!!!!!!!!
		 * 
		 *  TUTTI GLI SVILUPPATORI
		 * 6) - Read Tutti Sviluppatori  !!!!!!!!!!!!!!!!!!!!!!
		 * 7) Read Sviluppatore by ID !!!!!!!!
		 * 8) Update Sviluppatore -> Cambiare i suoi linguaggiconosciuti e progettiAssegnati e team
		 * 
		 * TUTTI I MANAGER
		 * 6) - Read Tutti Manager  !!!!!!!!!!!!!!!!!!
		 * 6.1) 
		 * 7) Read Manager by ID !!!!!!!!
		 * 8.8) Update Manager -> Cambiare bonus e teamGestito -> IDMANAGER
		 * 
		 * 
		 * TUTTI I LINGUAGGI
		 * 10) Create Linguaggi
		 * 11) - Read Tutti Linguaggi !!!!!!!!!!!!!!!!!
		 * 12) ====
		 * 13) (Update LinguaggiConosciuti di uno sviluppatore -> create/eliminare record sviluppatore_linguaggio)
		 * 14) Delete Linguaggio !!!!!!!!!!!!!!!!!!!!!!
		 * 
		 * Tutti i TEAM
		 * 15) Create Team
		 * 16) - Read tutti i team !!!!!!!!!!!!!!!
		 * 17) ----
		 * 18) Team -> cambiare il suo manager (se e solo se IDMANAGER NULL)
		 * 19) Delete Team !!!!!!!!!!!!
		 * 
		 * TUTTI I PROGETTI
		 * 20) Create un progetto
		 * 21) - Read tutti i progetti !!!!!!!!!!!!!!!!!!!!!!!!!
		 * 22) ---
		 * 24) Delete Progetto !!!!!!!!!!!!!!!!!!!!!
		 * 
		 * 25) Calcola Uscite stipendi sum(stipendiBase + bonus) 
		 * 
		 */
	
		
	}
}

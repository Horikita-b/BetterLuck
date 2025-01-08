package Main;
import java.util.ArrayList;
public class Developers extends Employers {
	
	private ArrayList<String> linguaggiConosciuti;
	private ArrayList<Integer> progettiAssegnati;

	public Developers(int id, String nome, String cognome, double stipendioBase, ArrayList<String> linguaggiConosciuti, ArrayList<Integer> progettiAssegnati) {
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

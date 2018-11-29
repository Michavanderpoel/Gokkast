import java.util.ArrayList;
import java.util.List;

public class Gokkast {
	private static int STARTSALDO = 150;
	private static int muntinworp = 20;
	private static int standaardAantalSchijven = 2;
	private int saldo;
	private String gokkastName;
	private List<Schijf> schijven = new ArrayList<>();
	
	public Gokkast() {
		
		this.gokkastName = "The Bitcoin Bandit (tm)";
		
		this.saldo = STARTSALDO;
		
		for(int i = 0; i < standaardAantalSchijven; i++) { schijven.add(new Schijf()); }
	}
	
	public Gokkast(int aantalSchijven) {
		
		this();
		
		if(aantalSchijven < standaardAantalSchijven) { aantalSchijven = standaardAantalSchijven; }
		
		for(int i = 0; i < (aantalSchijven-standaardAantalSchijven); i++) { schijven.add(new Schijf()); }
	}
	
	public void setGokkastName (String gokkastName) { this.gokkastName = gokkastName; }
	
	public String getGokkastName() { return this.gokkastName; }
	
	public int getSaldo() { return this.saldo; }
	
	public List<Schijf> getSchijven() { return this.schijven; }

	
	public Schijf.SLOTS[] getSLots(Gokkast gokkast){
		
		Schijf.SLOTS[] slots = new Schijf.SLOTS[gokkast.getSchijven().size()];
		
		int schijfNummer = 0;
		
		for(Schijf schijf: gokkast.getSchijven()) {
			
			slots[schijfNummer] = schijf.getSlot();
			
			schijfNummer++;
		}

		return slots;
	}
	
	
	public void schijvenVrijgeven() {
		
		for(Schijf schijf: this.schijven) { schijf.setSchijfVasthouden(false); }
	}
	
	
	public void geefZwengel() {
		
		for(Schijf schijf: schijven) {
			
			geefZwengel(schijf);
		}
	}
	
	
	public void geefZwengel(Schijf schijf) {
		
		if(schijf.getSchijfVasthouden()) { 
			
			schijf.getSlot();
			
		} else { 
			
			schijf.draai(); 
		} 
	}

	public int calculateScore(Schijf.SLOTS[] slots) {
		this.saldo -= muntinworp;
		int winst = 0;
		int gelijkeSlots = 0;
		int i = 0;
		
		for(Schijf.SLOTS slot: slots) {

			for (int j = 0; j < slots.length; j++) {
								
				if(j != i && (slots[j] == slot || slots[j] == Schijf.SLOTS.BAR) ) {
					
					gelijkeSlots++; 
					
					winst = schijven.get(i).getSlotValue();
				}
			}

			i++;
		}

		winst = gelijkeSlots * winst;

		this.saldo += winst;
		
		return winst;
	}
}

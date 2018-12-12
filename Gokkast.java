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
		
		setGokkastName("The Bitcoin Bandit (tm)");
		
		setSaldo(STARTSALDO);
		
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
	
	private void setSaldo(int bedrag) { this.saldo = bedrag; }
	
	public List<Schijf> getSchijven() { return this.schijven; }

	
	public Schijf.SLOTS[] getSLots(Gokkast gokkast){
		
		Schijf.SLOTS[] slots = new Schijf.SLOTS[gokkast.getSchijven().size()];
		
		int schijfNummer = 0;
		
		for(Schijf schijf: gokkast.getSchijven()) {
			
			slots[schijfNummer] = schijf.getSlotName();
			
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
			
			schijf.getSlotName();
			
		} else { 
			
			schijf.draai(); 
		} 
	}

	public int calculateScore(Schijf.SLOTS[] slots) {
		setSaldo(getSaldo() - muntinworp);
		int winst = 0;
		int highestSlotValue = 0;
		int aantalGelijkeSlots = 0;
		int aantalBARSlots = 0;
		boolean isBAR = false;
		
		int i = 0;
		for(Schijf.SLOTS slot_i: slots) {
			
			if(slot_i == Schijf.SLOTS.BAR) { 
				
				isBAR = true; 
				aantalBARSlots++;
			
			} else { 
				
				isBAR = false; 
			}

			int slotValue = schijven.get(i).getSlotValue();

			if(slotValue > highestSlotValue && !isBAR) {
				
				highestSlotValue = slotValue;
			}

			int j = 0;
			for(Schijf.SLOTS slot_j: slots) {
								
				if(i != j) {
										
					if(slot_i == slot_j || isBAR || slot_j == Schijf.SLOTS.BAR) {
						
						if(slot_i == slot_j || isBAR ) {
								
							aantalGelijkeSlots++;
						}
						
						if(slotValue > highestSlotValue) {
							
							if (!isBAR) { 
								
								highestSlotValue = slotValue;
							}
						}
					}
				}
				
				j++;
			}

			i++;
		}
		

		// JACKPOT
		if(aantalGelijkeSlots >= slots.length) {
			
			aantalGelijkeSlots = slots.length;
			
			System.out.println("\n J A C K P O T ! \n");
			
			if(aantalBARSlots == slots.length) {
				
				highestSlotValue = schijven.get(0).getSlotValue(Schijf.SLOTS.BAR);
			}
			
			aantalGelijkeSlots *= 2;
		}

		if(aantalGelijkeSlots > 1) { 
			
			winst = highestSlotValue * aantalGelijkeSlots;
		}
		
		setSaldo(getSaldo() + winst);
		
		return winst;
	}
}

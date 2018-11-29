import java.util.Random;
import java.util.Scanner;

public class Start {

	public static void main(String[] args) {		
		Gokkast gokkast = new Gokkast(3);
		String gameAction = "";
		boolean firstZwengel = true;
		Scanner input = new Scanner(System.in);
		
		System.out.println(gokkast.getGokkastName()+"\n");
		
		while(!gameAction.equals("U") && gokkast.getSaldo() > 0) {

			// Geef alle schijven een draai (bij de start en na het geven van een "Enter");
			if(gameAction.isEmpty() || firstZwengel) {
				
				gokkast.geefZwengel();
			
			// Draai alleen de schijven die niet zijn vastgezet:
			} else {
				String[] schijvenVasthouden = gameAction.split(",");
				
				int schijfNummer = 0;

				// Check welke schijf gedraaid mag worden:
				for(Schijf schijf: gokkast.getSchijven()) {
					
					// Is het schijfnummer gelijk aan de invoer van de speler? dan niet draaien, anders wel:
					for(String schijfVasthouden: schijvenVasthouden) {
						
						try{
							if(Integer.parseInt(schijfVasthouden)==schijfNummer) { 
							
								schijf.setSchijfVasthouden();
							}
						} catch (Exception e){ }
					}
					
					schijfNummer++;
				}

				gokkast.geefZwengel();
			}

			// Toon de uitkomst van het draaien van de schijven:
			int schijfNummer = 0;
			
			for(Schijf schijf: gokkast.getSchijven()) {
				
				System.out.println(schijfNummer + ": " + schijf.getCurrentSlot() + " ("+schijf.getSlotValue()+" bitcoin)");
				
				schijfNummer++;
			}
			
			// Na de eerste draai mag de speler keizen welke schijven vastgezet moeten worden:
			if(firstZwengel) {
				
				System.out.println("\n Kies schijven om vast te houden [getal1,getal2,...] of geef Enter.");
				
			// Bepaal score, speel verder of stoppen.
			} else {
				
				System.out.println("\n Je mined: " + gokkast.calculateScore(gokkast.getSLots(gokkast)) + " bitcoins");
				
				System.out.println("\n Bitcoins in je wallet: " + gokkast.getSaldo());
				
				if(gokkast.getSaldo() <= 0) {
					
					if(new Random().nextInt(10) > 5) {
						System.out.println("\n *** all your bitcoin are belong to us ***");
					} else {
						System.out.println("\n *** your wallet has been hacked ***");
					}
					
				} else {
					
					System.out.println("\n Geef Enter of (U)itbetalen");
				}
				
				gokkast.schijvenVrijgeven();
			}

			// Lees de invoer uit van de speler (pas na het geven van een enter):
			gameAction = input.nextLine();
			
			// Iedere Zwengel is een nieuwe muntinworp nodig:
			firstZwengel = !firstZwengel;
		}
		
		input.close();
		
		if(gameAction.equals("U")) {
			
			System.out.println("\n Je hebt " + gokkast.getSaldo() + " bitcoins gemined!");
		}
	}
}

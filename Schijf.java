import java.util.EnumMap;
import java.util.Random;

public class Schijf {	
	private EnumMap<SLOTS, Integer> schijf = new EnumMap<SLOTS, Integer>(SLOTS.class);	
	private boolean schijfVasthouden = false;	
	private SLOTS slot;
	
	public static enum SLOTS {
		AARDBEI, BANAAN, SE7EN, MELOEN, BAR, KLOK, KERS;
	}
	
	public Schijf() {
		schijf.put(SLOTS.AARDBEI, 3);
		schijf.put(SLOTS.BANAAN, 4);
		schijf.put(SLOTS.MELOEN, 5);
		schijf.put(SLOTS.SE7EN, 7);
		schijf.put(SLOTS.KERS, 9);
		schijf.put(SLOTS.KLOK, 12);
		schijf.put(SLOTS.BAR, 15);
	}
		
	public SLOTS draai() {
		
		if(this.schijfVasthouden) { return this.slot; }
		
		setSlot(SLOTS.values()[new Random().nextInt(SLOTS.values().length)]);
		
		return this.slot;
	}
	
	public void setSlot(SLOTS slot) { this.slot = slot; }
	
	public SLOTS getSlot() { return this.slot; }
	
	public SLOTS getSlotName(int slotNummer) { return SLOTS.values()[slotNummer]; }
	
	public int getSlotValue(SLOTS slotName) { return schijf.get(slotName).intValue(); }
	
	public int getSlotValue() { return schijf.get(this.slot).intValue(); }
	
	public boolean getSchijfVasthouden() { return this.schijfVasthouden; }
	
	public void setSchijfVasthouden() { this.schijfVasthouden = true; }
	
	public void setSchijfVasthouden(boolean schijfVasthouden) { this.schijfVasthouden = schijfVasthouden; }
	
	public void setSchijfVasthouden(Schijf schijf) { schijf.schijfVasthouden = true; }
	
	public void setSchijfVasthouden(Schijf schijf, boolean schijfVasthouden) { schijf.schijfVasthouden = schijfVasthouden; }
	
	public boolean compareSlotTo(SLOTS slot) { return this.slot == slot; }
}
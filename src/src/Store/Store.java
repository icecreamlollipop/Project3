package Store;
import java.util.*;
import Roll.*;

public class Store{
	Inventory inventory;
	OrdersForDay orders;
	Map<RollType,Integer> numSold = new HashMap<RollType,Integer>();
	int totalNumSold = 0;
	int totalShortages = 0;
	double totalMoney = 0;
	public Store() {
		inventory = new Inventory();
		//orders = new OrdersForDay();
		RollType.set_map(numSold, 0);
	}
	
	public void runMonth() {	// run store for a month, i.e. 30 days
		for (int i = 1; i <= 30; i++) {
			System.out.println("\n\n Day " + i + " ====================================\n============================================\n");
			runDay();
		}
		monthSummary();
	}
	
	public void runDay() {	// run store for a day
		inventory.init();
		orders = new OrdersForDay();
		orders.init();
		orders.runDay(inventory);
		System.out.println("\n");
		orders.daySummary();
		inventory.print(); // print how much things are left in the inventory after the day is over
		updateMonthStats();
	}
	
	private void updateMonthStats() {
		for (RollType rt : RollType.values()) {
			numSold.put(rt, numSold.get(rt)+orders.numSold.get(rt));
		}
		totalNumSold += orders.totalSold;
		totalShortages += orders.shortageImpacts;
		totalMoney += orders.totalCost;
	}
	
	public void monthSummary() {
		System.out.println("\n======================================================\nMONTH STATS");
		System.out.println("Rolls sold: "+totalNumSold+" total");
		for (RollType rt : RollType.values()) {
			System.out.println(" > "+rt+": "+numSold.get(rt));
		}
		System.out.println("Total money: "+totalMoney);
		System.out.println("Total shortage impacts: "+totalShortages);
	}
	
	
}
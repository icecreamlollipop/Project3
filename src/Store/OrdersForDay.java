package Store;
import java.util.Random;

import Order.*;

import java.util.*;
import Roll.*;


public class OrdersForDay{
	int numCatering;
	int numBusiness;
	int numCasual;
	int numCustomers;
	ArrayList<Customer> orderArr = new ArrayList<Customer>();
	Map<RollType, Integer> numSold = new HashMap<RollType, Integer>();
	double totalCost = 0;
	int shortageImpacts = 0;
	int totalSold = 0;
	boolean shortageClosed = false;
	Map<CustomerType,Double> payments = new HashMap<CustomerType,Double>();
	Map<CustomerType,Integer> shortages = new HashMap<CustomerType,Integer>();
	static final Random RNG = new Random();
	
	public void init() {
		initCount();
		insert();
		randSort();
		RollType.set_map(numSold, 0);
		CustomerType.set_map(payments, 0);
		CustomerType.set_map(shortages, 0);
	}
	
	public void initCount() {
		Random r = new Random();
		numCasual = r.nextInt(11) + 1;
		numCatering = r.nextInt(2) + 1;
		numBusiness = r.nextInt(2) + 1;
		numCustomers = numCasual + numCatering + numBusiness;
	}
	
	public void insert() {
		orderArr.clear();
		for (int i = 0; i < numCasual; i++) {
			orderArr.add(CustomerType.factory_create(CustomerType.CASUAL));
		}
		for (int i = 0; i < numCatering; i++) {
			orderArr.add(CustomerType.factory_create(CustomerType.CATERING));
		}
		for (int i = 0; i < numBusiness; i++) {
			orderArr.add(CustomerType.factory_create(CustomerType.BUSINESS));
		}
	}
	
	public void randSort() {
		Collections.shuffle(orderArr);
	}
	public void increment(Map map, Object obj, int amount) {
		map.put(obj, (int)map.get(obj)+amount);
	}
	public double calculate_cost(List<Roll> rolls) {
		double ans = 0;
		for (Roll r : rolls) {
			ans += r.cost();
		}
		return ans;
	}
	public void runDay(Inventory inv) {
		
		for (int i=0;i<numCustomers;i++) {
			System.out.println();
			// get customer and print basic order info
			Customer orderer = orderArr.get(i);
			System.out.println("Order number: "+(i+1));
			System.out.println("Customer type: "+orderer.type());
			
			// get order
			Map<RollType,Integer> order = orderer.initialOrder();
			if (!inv.canFulfill(order)) { // there is a shortage
				order = orderer.shortageOrder(inv);
				increment(shortages,orderer.type(),1);
				shortageImpacts++;
			}
			
			// create list of rolls
			List<Roll> rolls = new ArrayList<Roll>();
			for (RollType rt : order.keySet()) {
				for (int j=0;j<order.get(rt);j++) {
					increment(numSold,rt,1);
					totalSold++;
					Roll r = RollType.factory_create(rt);
					// add decorators and print
					System.out.print(" > "+rt);
					int numSauces = RNG.nextInt(4);
					int numFillings = RNG.nextInt(2);
					int numToppings = RNG.nextInt(3);
					for (int x=0;x<numSauces;x++) {
						System.out.print(" (+sauce)");
						r = new Sauces(r);
					}
					for (int x=0;x<numFillings;x++) {
						System.out.print(" (+filling)");
						r = new Fillings(r);
					}
					for (int x=0;x<numToppings;x++) {
						System.out.print(" (+topping)");
						r = new Toppings(r);
					}
					System.out.println();
					// add the roll
					rolls.add(r);
					// check for outage
					increment(inv.numLeft, rt, -1);
					if (inv.numLeft.get(rt) == 0) {
						System.out.println(">>> Ran out of roll of type "+rt);
					}
				}
			}
			// update costs
			double cost = calculate_cost(rolls);
			System.out.println("Cost of order: "+cost);
			payments.put(orderer.type(), payments.get(orderer.type())+cost);
			totalCost += cost;
			// no rolls left
			if (inv.totalNumLeft() == 0) {
				System.out.println("Store closed because there's nothing left");
				shortageClosed = true;
				break;
			}
		}
	}
	public void daySummary() {
		System.out.println("DAY SUMMARY");
		System.out.println("Payments from customers: "+totalCost+" total");
		for (CustomerType ct : CustomerType.values()) {
			System.out.println(" > "+ct+": "+payments.get(ct));
		}
		System.out.println("Roll shortages: "+shortageImpacts+" total");
		for (CustomerType ct : CustomerType.values()) {
			System.out.println(" > "+ct+": "+shortages.get(ct));
		}
		System.out.println("Total orders: "+totalSold+" total");
		for (RollType rt : RollType.values()) {
			System.out.println(" > "+rt+": "+numSold.get(rt));
		}
		if (shortageClosed) System.out.println("Store closed due to shortage");
		else System.out.println("Store did not close due to shortage");
	}
}
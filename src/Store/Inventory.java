package Store;
import Roll.*;
import java.util.*;

public class Inventory{
	int numJelly;
	int numPastry;
	int numEgg;
	int numSpring;
	int numSausage;
	public Map<RollType,Integer> numLeft = new HashMap<RollType,Integer>();
	boolean inventoryOut;
	static final Random RNG = new Random();
	public static void set_map(Map<RollType,Integer> map,int num) {
		for (RollType rt : RollType.values()) {
			map.put(rt, num);
		}
	}
	public Inventory() {	// constructor
		inventoryOut = false;
		set_map(numLeft,0);
	}
	
	public void init() {	// initialize inventory and print contents
		refresh();
		print();
	}
	
	public void refresh() {
		for (RollType rt : RollType.values()) {
			if (numLeft.get(rt) == 0) numLeft.put(rt, 30);
		}
	}
	
	public void print() {
		for (RollType rt : RollType.values()) {
			System.out.println(rt+" rolls left: "+numLeft.get(rt));
		}
	}
	public boolean canFulfill(Map<RollType,Integer> order) {
		for (RollType rt : RollType.values()) {
			if (order.containsKey(rt) && order.get(rt) > numLeft.get(rt)) {
				return false;
			}
		}
		return true;
	}
	public int totalNumLeft() {
		int ans = 0;
		for (RollType rt : RollType.values()) {
			ans += numLeft.get(rt);
		}
		return ans;
	}
	public ArrayList<RollType> availableTypes() {
		ArrayList<RollType> ans = new ArrayList<RollType>();
		for (RollType rt : RollType.values()) {
			if (numLeft.get(rt)>0) ans.add(rt);
		}
		return ans;
	}
	public Map<RollType,Integer> randomSubset(int max) {
		Map<RollType,Integer> ans = new HashMap<RollType,Integer>();
		set_map(ans,0);
		int num_added = 0;
		while (num_added<max && totalNumLeft()>num_added) {
			RollType rt = RollType.random();
			if (numLeft.get(rt)>ans.get(rt)) {
				ans.put(rt,ans.get(rt)+1);
				num_added++;
			}
		}
		return ans;
	}
}
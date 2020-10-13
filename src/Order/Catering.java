package Order;

import java.util.*;

import Roll.*;
import Store.*;

public class Catering implements Order{
	
	int t;
	public CustomerType type() {return CustomerType.CATERING;}
	public Map<RollType,Integer> initialOrder() {
		Map<RollType,Integer> ans = new HashMap<RollType,Integer>();
		for (int i=0;i<3;i++) {
			RollType type;
			do {
				type = RollType.random();
			} while (ans.containsKey(type));
			ans.put(type, 5);
		}
		return ans;
	}
	public Map<RollType,Integer> shortageOrder(Inventory inventory) {
		return inventory.randomSubset(15);
	}
}
package Order;
import java.util.*;
import Roll.*;
import Store.*;

/*
 * This implements the OrderingStrategy class to provide a strategy for customers
 */
public class TwoOfEachOrNothing implements OrderingStrategy {
	public Map<RollType,Integer> initialOrder() {
		Map<RollType,Integer> ans = new HashMap<RollType,Integer>();
		RollType.set_map(ans, 2);
		return ans;
	}
	public Map<RollType,Integer> shortageOrder(Inventory inventory) {
		return new HashMap<RollType,Integer>();
	}
}
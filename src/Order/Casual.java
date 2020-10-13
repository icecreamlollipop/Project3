package Order;
import java.util.*;
import Roll.*;
import Store.*;

public class Casual implements Order{
	static final Random RNG = new Random();
	int numRolls;
	RollType rt;

	public CustomerType type() {return CustomerType.CASUAL;}
	public Map<RollType,Integer> initialOrder() {	// buy 1 to 3 rolls, type determined randomly
		
		Random r = new Random();
		
		numRolls = r.nextInt(2) + 1;
		rt = RollType.random();
		
		Map<RollType,Integer> ans = new HashMap<RollType,Integer>();
		ans.put(rt, numRolls);
		return ans;
	}
	
	public Map<RollType,Integer> shortageOrder(Inventory inventory) {
		List<RollType> avail = inventory.availableTypes();
		Map<RollType,Integer> ans = new HashMap<RollType,Integer>();
		if (avail.contains(rt)) {
			ans.put(rt, inventory.numLeft.get(rt));
			return ans;
		}
		Collections.shuffle(avail);
		rt = avail.get(0);
		if (inventory.numLeft.get(rt) < numRolls) numRolls = inventory.numLeft.get(rt);
		ans.put(rt, numRolls);
		return ans;
	}
}
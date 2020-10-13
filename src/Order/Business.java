package Order;
import java.util.*;
import Roll.*;
import Store.*;

public class Business implements Order{
	public CustomerType type() {return CustomerType.BUSINESS;}
	public Map<RollType,Integer> initialOrder() {
		Map<RollType,Integer> ans = new HashMap<RollType,Integer>();
		Inventory.set_map(ans, 2);
		return ans;
	}
	public Map<RollType,Integer> shortageOrder(Inventory inventory) {
		return new HashMap<RollType,Integer>();
	}
}
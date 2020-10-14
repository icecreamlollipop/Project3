package Order;

import java.util.*;

import Roll.*;
import Store.*;

/*
 * This uses the Strategy pattern to provide ordering strategies to customers
 */
public interface OrderingStrategy {
	public Map<RollType,Integer> initialOrder();
	public Map<RollType,Integer> shortageOrder(Inventory inventory);
}

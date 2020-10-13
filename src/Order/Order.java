package Order;
import java.util.*;
import Roll.*;
import Store.*;

public interface Order{
	// total cost
	// customer type
	public CustomerType type();
	public Map<RollType,Integer> initialOrder();
	public Map<RollType,Integer> shortageOrder(Inventory inventory);
}
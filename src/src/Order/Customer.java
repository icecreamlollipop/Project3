package Order;
import java.util.*;
import Roll.*;
import Store.*;

public class Customer{
	private CustomerType type;
	private OrderingStrategy strategy;
	public Customer(CustomerType type, OrderingStrategy strategy) {
		this.type = type;
		this.strategy = strategy;
	}
	public CustomerType type() {return this.type;}
	public Map<RollType,Integer> initialOrder() {return strategy.initialOrder();}
	public Map<RollType,Integer> shortageOrder(Inventory inventory) {return strategy.shortageOrder(inventory);}
}
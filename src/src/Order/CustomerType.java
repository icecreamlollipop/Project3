package Order;

import java.util.Map;

public enum CustomerType {
	BUSINESS, CASUAL, CATERING;
	/*
	 * A factory for creating customers
	 */
	public static Customer factory_create(CustomerType ct) {
		switch (ct) {
		case BUSINESS: return new Customer(CustomerType.BUSINESS, new TwoOfEachOrNothing());
		case CASUAL: return new Customer(CustomerType.CASUAL, new SomeOfOne());
		case CATERING: return new Customer(CustomerType.CATERING, new FiveOfThreeOrAny());
		default: return null;
		}
	}
	/*
	 * Utilities for creating a map involving customer types
	 */
	public static void set_map(Map<CustomerType,Integer> map,int num) {
		for (CustomerType rt : CustomerType.values()) {
			map.put(rt, num);
		}
	}
	public static void set_map(Map<CustomerType,Double> map,double num) {
		for (CustomerType rt : CustomerType.values()) {
			map.put(rt, num);
		}
	}
}

package Order;

import java.util.Map;

import Roll.RollType;

public enum CustomerType {
	BUSINESS, CASUAL, CATERING;
	public static Order factory_create(CustomerType ct) {
		switch (ct) {
		case BUSINESS: return new Business();
		case CASUAL: return new Casual();
		case CATERING: return new Catering();
		default: return null;
		}
	}
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

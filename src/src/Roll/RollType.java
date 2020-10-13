package Roll;
import java.util.*;

public enum RollType {
	EGG, JELLY, PASTRY, SAUSAGE, SPRING;
	public static RollType random() {
		Random r = new Random();
		return values()[r.nextInt(values().length)];
	}
	/*
	 * A factory for creating rolls
	 */
	public static Roll factory_create(RollType rt) {
		switch (rt) {
		case EGG: return new Egg();
		case JELLY: return new Jelly();
		case PASTRY: return new Pastry();
		case SAUSAGE: return new Sausage();
		case SPRING: return new Spring();
		default: return null;
		}
	}
	public static void set_map(Map<RollType,Integer> map,int num) {
		for (RollType rt : RollType.values()) {
			map.put(rt, num);
		}
	}
	public static void set_map(Map<RollType,Double> map,double num) {
		for (RollType rt : RollType.values()) {
			map.put(rt, num);
		}
	}
}

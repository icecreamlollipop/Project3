package Roll;

public class Toppings extends Extras{

	public Toppings(Roll roll) {
		super(roll);
	}
	
	public double cost() {
		double added;
		switch(type()) {
		case JELLY: added = 0.15; break;
		case EGG: added = 0.24; break;
		case SPRING: added = 0.33; break;
		case SAUSAGE: added = 0.42; break;
		case PASTRY: added = 0.51; break;
		default: added = 999;
		}
		return super.cost()+added;
	}
}
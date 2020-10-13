package Roll;

/*
 * A concrete decorator for Roll
 */
public class Sauces extends Extras{

	public Sauces(Roll roll) {
		super(roll);
	}
	
	public double cost() {
		double added;
		switch(type()) { // different cost depending on type
		case JELLY: added = 0.11; break;
		case EGG: added = 0.22; break;
		case SPRING: added = 0.33; break;
		case SAUSAGE: added = 0.44; break;
		case PASTRY: added = 0.55; break;
		default: added = 999;
		}
		return super.cost()+added;
	}
}
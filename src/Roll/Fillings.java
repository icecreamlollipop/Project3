package Roll;

public class Fillings extends Extras{

	public Fillings(Roll roll) {
		super(roll);
	}
	
	public double cost() {
		double added;
		switch(type()) {
		case JELLY: added = 0.1; break;
		case EGG: added = 0.2; break;
		case SPRING: added = 0.3; break;
		case SAUSAGE: added = 0.4; break;
		case PASTRY: added = 0.5; break;
		default: added = 999;
		}
		return super.cost()+added;
	}
}
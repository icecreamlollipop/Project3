package Roll;

public abstract class Extras implements Roll{
	
	protected Roll roll;
	
	public Extras(Roll roll) {
		super();
		this.roll = roll;
	}
	
	public RollType type() {
		return roll.type();
	}
	public double cost() {
		return roll.cost();
	}
}
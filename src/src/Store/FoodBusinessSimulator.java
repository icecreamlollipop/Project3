package Store;

public class FoodBusinessSimulator{
	
	public static void main(String args[]) {
		openBusiness();
		Store store = new Store();	// instantiate a store - may open more stores in future
		store.runMonth();	// run the store for 30 days
    }
	
	public static void openBusiness() {
		System.out.println("Start the business.=========================\n============================================");
	}
}


/* TODO
 * 1. How do I make constructors for Egg(), Jelly(), and so on? Basically how do I make constructors for all the things in the Roll folder?
 * 2. In the folder Roll, make it so that the additional cost of each extra is different for each type of roll. Right now each extra costs different, but is the same for all the rolls.
 * 3. In the folder Roll (maybe another folder, unsure), make it so that for each roll, a customer may get (at random) 0 to 3 extra sauces, 0 to 1 extra fillings, and 0 to 2 toppings. Right now Every roll gets 1 of each topping (I think?)
 * ---------
 * 4. In Catering.java, make sure this person gets 3 different types of roll, and 5 of each types
 * 5. In the initialOrder() functions in Catering.java, Casual.java, Business.java, are there more efficient ways to do this than a switch statement?
 * ---------
 * 6. Implement the shortageOrder() functions. We'd have to keep count of the counts in the Inventory, and update the counts, not sure how.
 * 7. Implement the whole "if everything sells out, close down the store" and "if one thing sells out, print some notice"
 * --------- 
 * 8. Update the counts in the Inventory, as well as increase production capacity when needed.
 * 9. in Store.java print things for daySummary() and monthSummary()
 * ---------
 * 10. I have a feeling that the command pattern isn't rlly correct (the way I wrote Order.java)
 */
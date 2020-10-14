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

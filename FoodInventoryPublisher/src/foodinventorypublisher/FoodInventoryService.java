package foodinventorypublisher;

public interface FoodInventoryService {
 
    boolean reduceStock(int foodCode, int quantity);
    void displayStock();
    void openInventoryMenu();
	void addStock();
}
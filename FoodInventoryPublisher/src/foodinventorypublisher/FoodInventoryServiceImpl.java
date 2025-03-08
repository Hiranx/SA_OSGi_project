package foodinventorypublisher;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FoodInventoryServiceImpl implements FoodInventoryService {
    private Map<Integer, Integer> stock = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public FoodInventoryServiceImpl() {
       
        stock.put(1, 50); 
        stock.put(2, 40); 
        stock.put(3, 20); 
        stock.put(4, 30); 
        stock.put(5, 25); 
    }

    @Override
    public void addStock() {
    	System.out.println("Enter Food or Beverage Code : ");
    	int foodCode = scanner.nextInt();
    	System.out.println("Enter the Quantity : ");
    	int quantity = scanner.nextInt();
        stock.put(foodCode, stock.getOrDefault(foodCode, 0) + quantity);
        System.out.println("Stock updated! Current stock of item " + foodCode + ": " + stock.get(foodCode));
    }

    @Override
    public boolean reduceStock(int foodCode, int quantity) {
        if (stock.containsKey(foodCode) && stock.get(foodCode) >= quantity) {
            stock.put(foodCode, stock.get(foodCode) - quantity);
            return true;
        } else {
            System.out.println("Insufficient stock for item " + foodCode);
            return false;
        }
    }

    @Override
    public void displayStock() {
        System.out.println("\n------ Current Stock ------");
        System.out.println("Food Code | Item Name       | Stock");
        System.out.println("-----------------------------------");

        for (Map.Entry<Integer, Integer> entry : stock.entrySet()) {
            String foodName = getFoodName(entry.getKey());
            System.out.printf("%-9d | %-15s | %-5d\n", entry.getKey(), foodName, entry.getValue());
        }
    }

    private String getFoodName(int foodCode) {
        switch (foodCode) {
            case 1: return "Rice and Curry";
            case 2: return "Fried Rice";
            case 3: return "Burgers";
            case 4: return "CocaCola";
            case 5: return "CreamSoda";
            default: return "Unknown";
        }
    }

 

	@Override
	 public void openInventoryMenu() {
        while (true) {
            System.out.println("\n*** INVENTORY SYSTEM ***");
            System.out.println("[1] Check Current Inventory");
            System.out.println("[2] Add Stock to Inventory");
            System.out.println("[3] Exit to Food Service");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayStock();
                    break;
                case 2:
                	addStock();
                    break;
                case 3:
                    System.out.println("Returning to Food and Beverage Service...");
                    return;
                default:
                    System.out.println("Invalid choice! Please enter 1, 2, or 3.");
            }
        }
    }
}

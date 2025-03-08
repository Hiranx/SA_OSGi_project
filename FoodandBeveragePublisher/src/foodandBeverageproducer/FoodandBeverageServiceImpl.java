package foodandBeverageproducer;

import java.util.Map;
import java.util.Scanner;
import foodinventorypublisher.FoodInventoryService;

public class FoodandBeverageServiceImpl implements IFoodandBeverageService {
    Scanner scan = new Scanner(System.in);
    String isContinue;
    FoodandBeverageModel food = new FoodandBeverageModel();
    private FoodInventoryService inventoryService;

    public FoodandBeverageServiceImpl(FoodInventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void addFoodsAndbeverages() {
        System.out.println("----- OUR MENU -----");
        System.out.println("[1] Rice and Curry : Rs.750.00");
        System.out.println("[2] Fried Rice : Rs.800.00");
        System.out.println("[3] Burgers : Rs.1750.00");
        System.out.println("[4] CocaCola : Rs.100.00");
        System.out.println("[5] CreamSoda : Rs.200.00");
        System.out.println();
        System.out.println("Add your Order - Enter Food Code");

        do {
            System.out.print("Enter Food number: ");
            int foodCode = scan.nextInt();
            System.out.print("Enter Food Quantity: ");
            int qty = scan.nextInt();

            if (foodCode >= 1 && foodCode <= 5) {  
                if (inventoryService.reduceStock(foodCode, qty)) {
                    food.addFood(foodCode, qty);
                    System.out.println("Order added successfully!");
                } else {
                    System.out.println("Order failed! Not enough stock.");
                }
            } else {
                System.out.println("Invalid food code! Please enter a number between 1 and 5.");
            }

            System.out.print("Do You Want to Add More Foods? (y/n): ");
            isContinue = scan.next();
        } while (isContinue.equalsIgnoreCase("y"));

        DisplayFoodsAndBeverages();
    }

    @Override
    public void DisplayFoodsAndBeverages() {
        System.out.println("\n----- Your Order Summary -----");

        if (food.getFoods().isEmpty()) {
            System.out.println("No items ordered.");
            return;
        }

        for (Map.Entry<Integer, Integer> entry : food.getFoods().entrySet()) {
            int foodCode = entry.getKey();
            int quantity = entry.getValue();
            String foodName = "";
            int price = 0;

            switch (foodCode) {
                case 1:
                    foodName = "Rice and Curry";
                    price = 750;
                    break;
                case 2:
                    foodName = "Fried Rice";
                    price = 800;
                    break;
                case 3:
                    foodName = "Burgers";
                    price = 1750;
                    break;
                case 4:
                    foodName = "CocaCola";
                    price = 100;
                    break;
                case 5:
                    foodName = "CreamSoda";
                    price = 200;
                    break;
                default:
                    foodName = "Unknown item";
            }

            System.out.println(foodName + " - " + quantity + " x Rs." + price + " = Rs." + (quantity * price));
        }

        calculateBill();
    }

    @Override
    public void calculateBill() {
        int totalBill = 0;

        for (Map.Entry<Integer, Integer> entry : food.getFoods().entrySet()) {
            int foodCode = entry.getKey();
            int quantity = entry.getValue();
            int price = 0;

            switch (foodCode) {
                case 1:
                    price = 750;
                    break;
                case 2:
                    price = 800;
                    break;
                case 3:
                    price = 1750;
                    break;
                case 4:
                    price = 100;
                    break;
                case 5:
                    price = 200;
                    break;
            }

            totalBill += price * quantity;
        }

        food.setTotalBill(totalBill);
        System.out.println("\nTotal Bill: Rs." + food.getTotalBill() + ".00");
    }

    @Override
    public void getById() {
        // Not implemented
    }
}

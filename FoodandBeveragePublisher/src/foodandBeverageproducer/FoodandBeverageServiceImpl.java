package foodandBeverageproducer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoodandBeverageServiceImpl implements IFoodandBeverageService {
	  Scanner scan = new Scanner(System.in);
	    String isContinue;
	    FoodandBeverageModel food = new FoodandBeverageModel();
	   

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
	            if (foodCode >= 1 && foodCode <= 5) {  // Validate input
	            	food.addFood(foodCode);
	               
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

	        for (int foodCode : food.getFoods()) {
	            switch (foodCode) {
	                case 1:
	                    System.out.println("Rice and Curry - Rs.750.00");
	                    break;
	                case 2:
	                    System.out.println("Fried Rice - Rs.800.00");
	                    break;
	                case 3:
	                    System.out.println("Burgers - Rs.1750.00");
	                    break;
	                case 4:
	                    System.out.println("CocaCola - Rs.100.00");
	                    break;
	                case 5:
	                    System.out.println("CreamSoda - Rs.200.00");
	                    break;
	                default:
	                    System.out.println("Unknown item: " + foodCode);
	            }
	        }
	        calculateBill();
	}

	@Override
	public void getById() {
		// TODO Auto-generated method stub
		
	}

	  @Override
	    public void calculateBill() {
	        int totalBill = 0;

	        for (int foodCode : food.getFoods()) {
	            switch (foodCode) {
	                case 1:
	                    totalBill += 750;
	                    break;
	                case 2:
	                    totalBill += 800;
	                    break;
	                case 3:
	                    totalBill += 1750;
	                    break;
	                case 4:
	                    totalBill += 100;
	                    break;
	                case 5:
	                    totalBill += 200;
	                    break;
	            }
	        }
	        food.setTotalBill(totalBill);
	        System.out.println("\nTotal Bill: Rs." + food.getTotalBill() + ".00");
	    }

}

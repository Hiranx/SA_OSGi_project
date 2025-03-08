package foodandBeverageconsumer;

import java.util.Scanner;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import foodandBeverageproducer.IFoodandBeverageService;
import foodinventorypublisher.FoodInventoryService;

public class Activator implements BundleActivator {

    private ServiceReference foodServiceReference;
    private ServiceReference inventoryServiceReference;

    public void start(BundleContext context) throws Exception {
        System.out.println("---Start Food and Beverage Consumer Service---");
        
        foodServiceReference = context.getServiceReference(IFoodandBeverageService.class.getName());
        if (foodServiceReference == null) {
            System.out.println("Error: Food and Beverage Service is not available.");
            return;
        }
        IFoodandBeverageService foodService = (IFoodandBeverageService) context.getService(foodServiceReference);

        inventoryServiceReference = context.getServiceReference(FoodInventoryService.class.getName());
        if (inventoryServiceReference == null) {
            System.out.println("Error: Food Inventory Service is not available.");
            return;
        }
        FoodInventoryService inventoryService = (FoodInventoryService) context.getService(inventoryServiceReference);
        displayMenu(foodService, inventoryService);
    }

    private void displayMenu(IFoodandBeverageService foodService, FoodInventoryService inventoryService) {
        Scanner scanner = new Scanner(System.in);
        int option;
        
        while (true) {
            System.out.println("\n----------  Food and Beverage Management System  ----------\n");
            System.out.println("[1] - Order Food and Beverages");
            System.out.println("[2] - Access Inventory System");
            System.out.println("[3] - Exit");
            System.out.println("\n--------------------------------------------------------------");
            System.out.print("\nChoose an option: ");
            
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (option) {
                case 1:
                    foodService.addFoodsAndbeverages();
                    break;
                case 2:
                   inventoryService.openInventoryMenu();
                    break;
                case 3:
                    System.out.println("Exiting Food and Beverage Consumer Service...");
                    return;
                default:
                    System.out.println("Invalid choice! Please enter 1, 2, or 3.");
            }
        }
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("---Stopping Food and Beverage Consumer Service---");
        if (foodServiceReference != null) {
            context.ungetService(foodServiceReference);
        }
        if (inventoryServiceReference != null) {
            context.ungetService(inventoryServiceReference);
        }
    }
}

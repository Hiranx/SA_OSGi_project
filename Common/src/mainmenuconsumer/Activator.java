package mainmenuconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import merchandiseconsumer.MerchandiseConsumerActivator;
import merchandiseservie.MerchandiseInterface;
import foodandBeverageconsumer.*;
import foodandBeverageproducer.IFoodandBeverageService;

import event.consumer.EventConsumer;
import event.producer.EventProviderInterface;
import sports.producer.SportsEventProviderInterface;


public class Activator implements BundleActivator {

    private static BundleContext context;
    private ServiceTracker<MerchandiseInterface, MerchandiseInterface> merchTracker;
    private ServiceReference<MerchandiseConsumerActivator> merchConsumerRef;
    private ServiceReference<IFoodandBeverageService> foodServiceReference;
    private ServiceTracker<EventProviderInterface, EventProviderInterface> eventTracker;
    private ServiceTracker<SportsEventProviderInterface, SportsEventProviderInterface> sportsEventTracker;


    static BundleContext getContext() {
        return context;
    }

    public void start(BundleContext bundleContext) throws Exception {
        Activator.context = bundleContext;
        
     // Initialize Event Service Tracker
        eventTracker = new ServiceTracker<>(bundleContext, EventProviderInterface.class, null);
        eventTracker.open();

        // Initialize Sports Event Service Tracker
        sportsEventTracker = new ServiceTracker<>(bundleContext, SportsEventProviderInterface.class, null);
        sportsEventTracker.open();

        // Use a ServiceTracker to wait for the MerchandiseInterface service
        merchTracker = new ServiceTracker<>(bundleContext, MerchandiseInterface.class, null);
        merchTracker.open();

        // Wait for the service to become available
        MerchandiseInterface merchService = merchTracker.waitForService(5000); // Wait for 5 seconds

        if (merchService == null) {
            System.err.println("MerchandiseInterface service not found. Please ensure the bundle is active.");
            return;
        }

        // Get the MerchandiseConsumerActivator service
        merchConsumerRef = bundleContext.getServiceReference(MerchandiseConsumerActivator.class);
        if (merchConsumerRef == null) {
            System.err.println("MerchandiseConsumerActivator service not found. Please ensure the bundle is active.");
            return;
        }
        MerchandiseConsumerActivator merchConsumer = bundleContext.getService(merchConsumerRef);

        System.out.println("Main menu Loading.....");

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n==============================================================");
            System.out.println("\tWelcome to Smart Event Venue Management System");
            int option = 0;

            System.out.println("================================================");
            System.out.println("\n\nPlease select an option!\n");
            System.out.println("1. Event Ticket Booking");
            System.out.println("2. Merchandise Management");
            System.out.println("3. Food and Beverages Managment");
            System.out.println("4. Another Function");
            System.out.println("5. Quit \n");

            System.out.print("Select an option :");
            option = sc.nextInt();

            switch (option) {
            case 1:
                // Call the Event Consumer
                EventProviderInterface eventService = eventTracker.getService();
                SportsEventProviderInterface sportsEventService = sportsEventTracker.getService();

                if (eventService == null && sportsEventService == null) {
                    System.err.println("Event services are not available. Please ensure the bundles are active.");
                } else {
                    EventConsumer eventConsumer = new EventConsumer(eventService, sportsEventService);
                    eventConsumer.startBooking();
                }
                break;
                case 2:
                    // Call the displayMenu method of MerchandiseConsumerActivator
                    if (merchConsumer != null) {
                        merchConsumer.displayMenu();
                    } else {
                        System.err.println("MerchandiseConsumer is not available.");
                    }
                    break;
                case 3:
                    System.out.println("Starting Food and Beverage Consumer Service...\n");
                    startFoodAndBeverageService(context);
                    break;

                case 4:
                    break;
                case 5:
                	sc.close();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            System.out.println("Thank you for using Event Merchandise Management System");
            
        }

        
    }
    private void startFoodAndBeverageService(BundleContext context) {
        try {
            // Retrieve the Food and Beverage Service
            foodServiceReference = context.getServiceReference(IFoodandBeverageService.class);
            if (foodServiceReference == null) {
                System.out.println("Error: Food and Beverage Service is not available.");
                return;
            }
            IFoodandBeverageService foodService = context.getService(foodServiceReference);

            // Retrieve the Food Inventory Service
            ServiceReference<foodinventorypublisher.FoodInventoryService> inventoryServiceReference =
                    context.getServiceReference(foodinventorypublisher.FoodInventoryService.class);
            if (inventoryServiceReference == null) {
                System.out.println("Error: Food Inventory Service is not available.");
                return;
            }
            foodinventorypublisher.FoodInventoryService inventoryService =
                    context.getService(inventoryServiceReference);

            // Create an instance of Food and Beverage Consumer Activator
            foodandBeverageconsumer.Activator foodConsumerActivator = new foodandBeverageconsumer.Activator();

            // Manually call displayMenu method with necessary services
            foodConsumerActivator.displayMenu(foodService, inventoryService);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void stop(BundleContext bundleContext) throws Exception {
        if (merchTracker != null) {
            merchTracker.close();
        }
        if (merchConsumerRef != null) {
            bundleContext.ungetService(merchConsumerRef);
        }
        if (eventTracker != null) {
            eventTracker.close();
        }
        if (sportsEventTracker != null) {
            sportsEventTracker.close();
        }
        Activator.context = null;
    }
}
package mainmenuconsumer;

import merchandiseconsumer.MerchandiseConsumerActivator;
import merchandiseservie.MerchandiseInterface;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import java.util.Scanner;

public class Activator implements BundleActivator {

    private static BundleContext context;
    private ServiceTracker<MerchandiseInterface, MerchandiseInterface> merchTracker;
    private ServiceReference<MerchandiseConsumerActivator> merchConsumerRef;

    static BundleContext getContext() {
        return context;
    }

    public void start(BundleContext bundleContext) throws Exception {
        Activator.context = bundleContext;

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
            System.out.println("1. Merchandise Management");
            System.out.println("2. Another Management");
            System.out.println("3. Another Function");
            System.out.println("4. Another Function");
            System.out.println("5. Quit \n");

            System.out.print("Select an option :");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    // Call the displayMenu method of MerchandiseConsumerActivator
                    if (merchConsumer != null) {
                        merchConsumer.displayMenu();
                    } else {
                        System.err.println("MerchandiseConsumer is not available.");
                    }
                    break;
                case 2:
                    // Call Discount Management
                    break;
                case 3:
                    // Call Another Function
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

    public void stop(BundleContext bundleContext) throws Exception {
        if (merchTracker != null) {
            merchTracker.close();
        }
        if (merchConsumerRef != null) {
            bundleContext.ungetService(merchConsumerRef);
        }
        Activator.context = null;
    }
}
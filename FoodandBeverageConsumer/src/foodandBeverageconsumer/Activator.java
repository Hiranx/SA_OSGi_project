package foodandBeverageconsumer;

import foodandBeverageproducer.IFoodandBeverageService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

    private ServiceReference<IFoodandBeverageService> serviceReference;
    private IFoodandBeverageService foodandbeverageService;

    public void start(BundleContext context) throws Exception {
        System.out.println("---Start Food and Beverage Consumer Service---");

        // Get the registered service from OSGi
        serviceReference = context.getServiceReference(IFoodandBeverageService.class);
        foodandbeverageService = context.getService(serviceReference);

        if (foodandbeverageService != null) {
            System.out.println("Successfully connected to Food and Beverage Service!");
            foodandbeverageService.addFoodsAndbeverages(); // Example call
        } else {
            System.out.println("Failed to retrieve Food and Beverage Service.");
        }
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("---Stopping Food and Beverage Consumer Service---");
        if (serviceReference != null) {
            context.ungetService(serviceReference);
        }
    }
}
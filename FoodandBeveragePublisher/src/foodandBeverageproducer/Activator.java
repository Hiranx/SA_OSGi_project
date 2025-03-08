package foodandBeverageproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import foodinventorypublisher.FoodInventoryService;

public class Activator implements BundleActivator {

	private ServiceRegistration serviceRegistration;


	 @Override
	 public void start(BundleContext context) throws Exception {
		    System.out.println("Starting Food and Beverage Producer Service...");
		    
		    ServiceReference<?> ref = context.getServiceReference(FoodInventoryService.class.getName());
		    FoodInventoryService inventoryService = (FoodInventoryService) context.getService(ref);

		    IFoodandBeverageService service = new FoodandBeverageServiceImpl(inventoryService);
		    
		    // 🔹 Registering the service
		    serviceRegistration = context.registerService(IFoodandBeverageService.class.getName(), service, null);
		    
		    System.out.println("Food and Beverage Service Registered Successfully.");
		}


	    @Override
	    public void stop(BundleContext context) throws Exception {
	        System.out.println("Stopping Food and Beverage Producer Service...");
	        
	        if (serviceRegistration != null) {
	            serviceRegistration.unregister();
	            System.out.println("Food and Beverage Service Unregistered.");
	        }
	    }

}

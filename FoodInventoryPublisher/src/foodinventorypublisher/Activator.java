package foodinventorypublisher;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
	 private ServiceRegistration<?> registration;
	 private Map<Integer, Integer> stock = new HashMap<>();
	    private Scanner sc = new Scanner(System.in);

	    @Override
	    public void start(BundleContext context) throws Exception {
	        FoodInventoryServiceImpl service = new FoodInventoryServiceImpl();
	        registration = context.registerService(FoodInventoryService.class.getName(), service, null);
	        System.out.println("Food Inventory Service Started");

	        // Start stock management menu
	        service.openInventoryMenu();
	    }
	   
	    

	    @Override
	    public void stop(BundleContext context) throws Exception {
	        registration.unregister();
	        System.out.println("Food Inventory Service Stopped");
	    }
}




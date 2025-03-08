package foodandBeverageproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration serviceRegistration;


	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Food and Beverage Publisher service started");
		IFoodandBeverageService foodandbeverage = new FoodandBeverageServiceImpl();
		serviceRegistration = bundleContext.registerService(IFoodandBeverageService.class.getName(), foodandbeverage, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Food and Beverage Publisher service stopped");
		serviceRegistration.unregister();
	}

}

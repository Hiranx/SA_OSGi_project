package locationpricingservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class LocationPricingProducerActivator implements BundleActivator {

	private ServiceRegistration<LocationPricingService> serviceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
    	LocationPricingService pricingService = new LocationPricingServiceImpl(); 
        serviceRegistration = context.registerService(LocationPricingService.class, pricingService, null);
        System.out.println("💲 Location Pricing Service Started.");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        serviceRegistration.unregister();
        System.out.println("❎ Location Pricing Service Stopped.");
    }
}

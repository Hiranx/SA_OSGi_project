package locationservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class LocationProducerActivator implements BundleActivator {
	
	private ServiceRegistration<LocationService> serviceRegistration;
	
	 @Override
	    public void start(BundleContext context) throws Exception {
	        LocationService locationService = new LocationServiceImpl();
	        serviceRegistration = context.registerService(LocationService.class, locationService, null);
	        System.out.println("Standard Location Service Started.");
	    }

	    @Override
	    public void stop(BundleContext context) throws Exception {
	        serviceRegistration.unregister();
	        System.out.println("Standard Location Service Stopped.");
	    }
}
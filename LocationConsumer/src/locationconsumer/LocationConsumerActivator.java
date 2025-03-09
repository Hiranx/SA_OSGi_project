package locationconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import com.mtit.service.LocationService;

public class LocationConsumerActivator implements BundleActivator {

	@Override
    public void start(BundleContext context) throws Exception {
        ServiceReference<LocationService> serviceReference = context.getServiceReference(LocationService.class);
        if (serviceReference != null) {
            LocationService locationService = context.getService(serviceReference);
            LocationConsumer consumer = new LocationConsumer(locationService);
            consumer.showAvailableLocations();
        } else {
            System.out.println("No Location Service found!");
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Location Consumer Stopped.");
    }
}
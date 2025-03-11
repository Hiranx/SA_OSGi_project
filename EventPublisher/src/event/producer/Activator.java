package event.producer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

    private ServiceRegistration serviceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        EventProviderInterface eventService = new EventProvider();
        serviceRegistration = context.registerService(EventProviderInterface.class.getName(), eventService, null);
        System.out.println("Event Producer Service Started");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        serviceRegistration.unregister();
        System.out.println("Event Producer Service Stopped");
    }
}

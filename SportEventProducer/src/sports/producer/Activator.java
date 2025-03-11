package sports.producer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

    private ServiceRegistration serviceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        SportsEventProviderInterface sportsEventService = new SportsEventProvider();
        serviceRegistration = context.registerService(SportsEventProviderInterface.class.getName(), sportsEventService, null);
        System.out.println("Sports Event Producer Service Started");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        serviceRegistration.unregister();
        System.out.println("Sports Event Producer Service Stopped");
    }
}


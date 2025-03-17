package merchandiseservie;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
    private ServiceRegistration<MerchandiseInterface> registration;

    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Merchandise Service Started");

        MerchandiseInterface merchService = new MerchandiseInterfaceImpl();
        registration = bundleContext.registerService(MerchandiseInterface.class, merchService, null);
    }

    public void stop(BundleContext bundleContext) throws Exception {
        if (registration != null) {
            registration.unregister();
        }
        System.out.println("Merchandise Service Stopped");
    }
}
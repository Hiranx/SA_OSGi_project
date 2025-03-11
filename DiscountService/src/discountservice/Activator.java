package discountservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
    private ServiceRegistration<DiscountInterface> registration;
    
    public void start(BundleContext context) throws Exception {
        DiscountProducerImpl discountProvider = new DiscountProducerImpl();
        registration = context.registerService(DiscountInterface.class, discountProvider, null);
        System.out.println("Discount Service Started.");
    }

    public void stop(BundleContext context) throws Exception {
        registration.unregister();
        System.out.println("Discount Service Stopped.");
    }
}

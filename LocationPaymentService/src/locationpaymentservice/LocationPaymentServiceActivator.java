package locationpaymentservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class LocationPaymentServiceActivator implements BundleActivator {

	private ServiceRegistration<LocationPaymentService> serviceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
    	LocationPaymentService paymentService = new LocationPaymentServiceImpl();
        serviceRegistration = context.registerService(LocationPaymentService.class, paymentService, null);
        System.out.println("💳 Payment Service Started.");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        serviceRegistration.unregister();
        System.out.println("❌ Payment Service Stopped.");
    }
}

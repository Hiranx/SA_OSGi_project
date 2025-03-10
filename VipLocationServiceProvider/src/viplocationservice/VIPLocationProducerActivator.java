package viplocationservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class VIPLocationProducerActivator implements BundleActivator {

	private ServiceRegistration<VIPLocationService> serviceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        VIPLocationService vipLocationService = new VIPLocationServiceImpl();
        serviceRegistration = context.registerService(VIPLocationService.class, vipLocationService, null);
        System.out.println("VIP Location Service Started.");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        serviceRegistration.unregister();
        System.out.println("VIP Location Service Stopped.");
    }
}
package merchandiseconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import discountservice.DiscountInterface;
import merchandiseservie.MerchandiseInterface;
import merchandiseservie.MerchandiseInterfaceImpl;

public class Activator implements BundleActivator {
    private ServiceReference<DiscountInterface> discountReference;
    private DiscountInterface discountService;
    private ServiceRegistration<MerchandiseInterface> publicServiceRegistration;

    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Merchandise Consumer Started");

        MerchandiseInterface merchService = new MerchandiseInterfaceImpl();
        publicServiceRegistration = bundleContext.registerService(MerchandiseInterface.class, merchService, null);
        System.out.println("Event Merchandise Store consumer service is open");

        
        discountReference = bundleContext.getServiceReference(DiscountInterface.class);
        if (discountReference != null) {
            discountService = bundleContext.getService(discountReference);
            System.out.println("Discount Service Found.");
        } else {
            System.out.println("No Discount Service Found.");
        }

        MerchandiseConsumer merchConsumer = new MerchandiseConsumer(merchService, discountService);
        merchConsumer.start();
    }

    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("consumer is stopped");

        if (publicServiceRegistration != null) {
            publicServiceRegistration.unregister();
        }
        if (discountReference != null) {
            bundleContext.ungetService(discountReference);
        }
    }
}

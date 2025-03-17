package merchandiseconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import discountservice.DiscountInterface;
import merchandiseservie.MerchandiseInterface;

public class MerchandiseConsumerActivator implements BundleActivator {
    private ServiceReference<DiscountInterface> discountReference;
    private DiscountInterface discountService;
    private ServiceRegistration<MerchandiseConsumerActivator> registration;
    private MerchandiseConsumer merchConsumer;

    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Merchandise Consumer Started");

        // Get the MerchandiseInterface service
        ServiceReference<MerchandiseInterface> merchReference = bundleContext.getServiceReference(MerchandiseInterface.class);
        if (merchReference == null) {
            System.err.println("MerchandiseInterface service not found.");
            return;
        }
        MerchandiseInterface merchService = bundleContext.getService(merchReference);

        // Get the DiscountInterface service
        discountReference = bundleContext.getServiceReference(DiscountInterface.class);
        if (discountReference != null) {
            discountService = bundleContext.getService(discountReference);
            System.out.println("Discount Service Found.");
        } else {
            System.out.println("No Discount Service Found.");
        }

        // Initialize the MerchandiseConsumer
        merchConsumer = new MerchandiseConsumer(merchService, discountService);

        // Register this activator as a service
        registration = bundleContext.registerService(MerchandiseConsumerActivator.class, this, null);
    }

    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("Merchandise Consumer Stopped");

        if (registration != null) {
            registration.unregister();
        }
        if (discountReference != null) {
            bundleContext.ungetService(discountReference);
        }
    }

    public void displayMenu() {
        if (merchConsumer != null) {
            merchConsumer.start();
        } else {
            System.err.println("MerchandiseConsumer is not initialized.");
        }
    }
}
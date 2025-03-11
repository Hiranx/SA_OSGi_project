package event.consumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import event.producer.EventProviderInterface;
import sports.producer.SportsEventProviderInterface;

public class Activator implements BundleActivator {

    private ServiceReference eventServiceReference;
    private ServiceReference sportsEventServiceReference;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Starting Event Consumer...");

        // Get event provider service reference
        eventServiceReference = context.getServiceReference(EventProviderInterface.class.getName());
        sportsEventServiceReference = context.getServiceReference(SportsEventProviderInterface.class.getName());

        EventProviderInterface eventService = null;
        SportsEventProviderInterface sportsEventService = null;

        if (eventServiceReference != null) {
            eventService = (EventProviderInterface) context.getService(eventServiceReference);
        }

        if (sportsEventServiceReference != null) {
            sportsEventService = (SportsEventProviderInterface) context.getService(sportsEventServiceReference);
        }

        if (eventService == null && sportsEventService == null) {
            System.out.println("Error: No event services found!");
            return;
        }

        // Start booking process
        EventConsumer bookingService = new EventConsumer(eventService, sportsEventService);
        bookingService.startBooking();

        System.out.println("Event Consumer Started Successfully");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Event Consumer Stopped");

        if (eventServiceReference != null) {
            context.ungetService(eventServiceReference);
        }
        if (sportsEventServiceReference != null) {
            context.ungetService(sportsEventServiceReference);
        }
    }
}

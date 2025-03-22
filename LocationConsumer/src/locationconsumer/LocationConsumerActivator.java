package locationconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import java.util.Scanner;

import locationservice.LocationService;
import viplocationservice.VIPLocationService;
import locationpricingservice.LocationPricingService;
import locationpaymentservice.LocationPaymentService;

public class LocationConsumerActivator implements BundleActivator {
    private Scanner scanner;
    //private LocationConsumer locationConsumer;

    @Override
    public void start(BundleContext context) throws Exception {
        scanner = new Scanner(System.in);

        // Get services
        ServiceReference<LocationService> locationServiceRef = context.getServiceReference(LocationService.class);
        ServiceReference<VIPLocationService> vipLocationServiceRef = context.getServiceReference(VIPLocationService.class);
        ServiceReference<LocationPricingService> pricingServiceRef = context.getServiceReference(LocationPricingService.class);
        ServiceReference<LocationPaymentService> paymentServiceRef = context.getServiceReference(LocationPaymentService.class);

        if (locationServiceRef == null || vipLocationServiceRef == null || pricingServiceRef == null || paymentServiceRef == null) {
            System.out.println("Required publisher services not found!");
            return;
        }

        LocationService locationService = context.getService(locationServiceRef);
        VIPLocationService vipLocationService = context.getService(vipLocationServiceRef);
        LocationPricingService pricingService = context.getService(pricingServiceRef);
        LocationPaymentService paymentService = context.getService(paymentServiceRef);

        LocationConsumer locationConsumer = new LocationConsumer(locationService, vipLocationService, pricingService, paymentService);
        
        locationConsumer.printTitle();
        // Show available locations
        locationConsumer.showAvailableLocations();

        // Show pricing details
        locationConsumer.showLocationPricing();

        // Process payments
        while (true) {
            System.out.println("\nEnter location number for payment (or type '0' to exit): ");
            int locationNumber;

            try {
                locationNumber = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            if (locationNumber == 0) {
                break;
            }

            System.out.println("Enter payment amount: ");
            double amount;

            try {
                amount = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount! Please enter a valid number.");
                continue;
            }

            locationConsumer.processPayment(locationNumber, amount);
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Location Consumer Stopped!!!");
        // Do not close the scanner to avoid closing System.in
    }
}
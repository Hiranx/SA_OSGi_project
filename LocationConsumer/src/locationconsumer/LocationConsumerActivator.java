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

    @Override
    public void start(BundleContext context) throws Exception {
        scanner = new Scanner(System.in);

        // Get standard locations
        ServiceReference<LocationService> serviceReference = context.getServiceReference(LocationService.class);
        if (serviceReference != null) {
            LocationService locationService = context.getService(serviceReference);
            System.out.println("Standard Locations:");
            for (String location : locationService.getAvailableLocations()) {
                System.out.println("- " + location);
            }
        } else {
            System.out.println("No Standard Location Service Found!");
        }

        // Get VIP locations
        ServiceReference<VIPLocationService> vipServiceReference = context.getServiceReference(VIPLocationService.class);
        if (vipServiceReference != null) {
            VIPLocationService vipLocationService = context.getService(vipServiceReference);
            System.out.println("\nVIP Locations:");
            for (String vipLocation : vipLocationService.getVIPLocations()) {
                System.out.println("- " + vipLocation);
            }
        } else {
            System.out.println("No VIP Location Service Found!");
        }

        // Get Location Pricing Service
        ServiceReference<LocationPricingService> pricingServiceReference = context.getServiceReference(LocationPricingService.class);
        if (pricingServiceReference != null) {
            LocationPricingService pricingService = context.getService(pricingServiceReference);
            System.out.println("\nLocation Pricing:");
            displayPricing(pricingService);
        } else {
            System.out.println("No Location Pricing Service Found!");
        }

        // Get Location Payment Service
        ServiceReference<LocationPaymentService> paymentServiceReference = context.getServiceReference(LocationPaymentService.class);
        if (paymentServiceReference != null) {
            LocationPaymentService paymentService = context.getService(paymentServiceReference);
            processUserPayments(paymentService);
        } else {
            System.out.println("No Location Payment Service Found!");
        }
    }

    private void displayPricing(LocationPricingService pricingService) {
        System.out.println("Hotel: Rs." + pricingService.getLocationPrice("Hotel"));
        System.out.println("Stadium: Rs." + pricingService.getLocationPrice("Stadium"));
        System.out.println("Conference Room: Rs." + pricingService.getLocationPrice("Conference Room"));
        System.out.println("Outdoor Arena: Rs." + pricingService.getLocationPrice("Outdoor Arena"));
        System.out.println("Restaurant: Rs." + pricingService.getLocationPrice("Restaurant"));

        System.out.println("\nVIP Location Pricing:");
        System.out.println("Lotus Tower: Rs." + pricingService.getLocationPrice("Lotus Tower"));
        System.out.println("VIP Lounge: Rs." + pricingService.getLocationPrice("VIP Lounge"));
        System.out.println("Exclusive Hall: Rs." + pricingService.getLocationPrice("Exclusive Hall"));
        System.out.println("Private Suite: Rs." + pricingService.getLocationPrice("Private Suite"));
    }

    private void processUserPayments(LocationPaymentService paymentService) {
        while (true) {
            System.out.println("\nEnter location name for payment (or type 'exit' to quit): ");
            String location = scanner.nextLine();

            if (location.equalsIgnoreCase("exit")) {
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

            boolean success = paymentService.processPayment(location, amount);
            if (success) {
                System.out.println("✅ Payment Successful!");
            } else {
                System.out.println("❌ Payment Failed!");
            }
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Location Consumer Stopped!!!");
        scanner.close();
    }
}

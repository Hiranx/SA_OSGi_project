package locationconsumer;

import locationservice.LocationService;
import viplocationservice.VIPLocationService;
import locationpricingservice.LocationPricingService;
import locationpaymentservice.LocationPaymentService;

import java.util.Scanner;

public class LocationConsumer {
    private LocationService locationService;
    private VIPLocationService vipLocationService;
    private LocationPricingService pricingService;
    private LocationPaymentService paymentService;

    public LocationConsumer(LocationService locationService, VIPLocationService vipLocationService,
                           LocationPricingService pricingService, LocationPaymentService paymentService) {
        this.locationService = locationService;
        this.vipLocationService = vipLocationService;
        this.pricingService = pricingService;
        this.paymentService = paymentService;
    }

    // Method to print the title with animation
    public void printTitle() throws InterruptedException {
        String[] title = {
            "  _                     _   _             ",
            " | |                   | | (_)            ",
            " | |     ___   ___ __ _| |_ _  ___  _ __  ",
            " | |    / _ \\ / __/ _` | __| |/ _ \\| '_ \\ ",
            " | |___| (_) | (_| (_| | |_| | (_) | | | |",
            " |______\\___/ \\___\\__,_|\\__|_|\\___/|_| |_|",
            "==========================================",
            "        LOCATION BOOKING SYSTEM           ",
            "=========================================="
        };

        for (String line : title) {
            System.out.println(line);
            Thread.sleep(100); // Creates a typing effect
        }
    }

    // Method to display a loading animation
    public void loadingAnimation() throws InterruptedException {
        String loading = "Processing";

        System.out.print(loading); // Print "Processing" once and keep it static

        for (int i = 0; i < 3; i++) { // Loop for 3 cycles
            for (int j = 0; j < 3; j++) {
                System.out.print("."); // Print a dot
                Thread.sleep(100); // Delay between dots
            }
        }
        System.out.println(); // Move to the next line after the animation
    }
    

    // Method to show available locations
    public void showAvailableLocations() {
        System.out.println("Standard Locations:");
        String[] standardLocations = locationService.getAvailableLocations();
        for (int i = 0; i < standardLocations.length; i++) {
            System.out.println((i + 1) + ". " + standardLocations[i]);
        }

        System.out.println("\nVIP Locations:");
        String[] vipLocations = vipLocationService.getVIPLocations();
        for (int i = 0; i < vipLocations.length; i++) {
            System.out.println((i + standardLocations.length + 1) + ". " + vipLocations[i]);
        }
    }

    // Method to show pricing for all locations
    public void showLocationPricing() {
        System.out.println("\nLocation Pricing Details:");

        // Standard Locations
        String[] standardLocations = locationService.getAvailableLocations();
        System.out.println("Standard Locations:");
        for (String location : standardLocations) {
            double price = pricingService.getLocationPrice(location);
            System.out.println("- " + location + ": Rs." + price);
        }

        // VIP Locations
        String[] vipLocations = vipLocationService.getVIPLocations();
        System.out.println("\nVIP Locations:");
        for (String location : vipLocations) {
            double price = pricingService.getLocationPrice(location);
            System.out.println("- " + location + ": Rs." + price);
        }
    }

    // Method to process payment for a selected location
    public void processPayment(int locationNumber, double amount) {
        try {
            // Display loading animation during payment processing
            loadingAnimation();

            String[] standardLocations = locationService.getAvailableLocations();
            String[] vipLocations = vipLocationService.getVIPLocations();
            String[] allLocations = new String[standardLocations.length + vipLocations.length];

            System.arraycopy(standardLocations, 0, allLocations, 0, standardLocations.length);
            System.arraycopy(vipLocations, 0, allLocations, standardLocations.length, vipLocations.length);

            if (locationNumber < 1 || locationNumber > allLocations.length) {
                System.out.println("Invalid location number!");
                return;
            }

            String selectedLocation = allLocations[locationNumber - 1];
            double price = pricingService.getLocationPrice(selectedLocation);

            System.out.println("\nSelected Location: " + selectedLocation);
            System.out.println("Price: Rs." + price);

            boolean success = paymentService.processPayment(selectedLocation, amount);
            if (success) {
                System.out.println("✅ Payment Successful!");
            } else {
                System.out.println("❌ Payment Failed: Insufficient Amount!");
            }
        } catch (InterruptedException e) {
            System.err.println("An error occurred during the animation: " + e.getMessage());
        }
    }

    // Method to display the full location management menu
    public void displayLocationMenu() {
        try {
            // Display the title with animation
            printTitle();

            // Show available locations
            showAvailableLocations();

            // Show pricing details
            showLocationPricing();

            // Process payment
            Scanner sc = new Scanner(System.in);
            System.out.println("\nEnter location number for payment (or type '0' to exit): ");
            int locationNumber = sc.nextInt();
            if (locationNumber != 0) {
                System.out.println("Enter payment amount: ");
                double amount = sc.nextDouble();
                processPayment(locationNumber, amount);
            }
        } catch (InterruptedException e) {
            System.err.println("An error occurred during the animation: " + e.getMessage());
        }
    }
}
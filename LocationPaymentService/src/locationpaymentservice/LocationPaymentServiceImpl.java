package locationpaymentservice;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import locationservice.LocationService;
import viplocationservice.VIPLocationService;

import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class LocationPaymentServiceImpl implements LocationPaymentService {
    private final Map<String, Double> pricingMap;
    private final BundleContext bundleContext;

    public LocationPaymentServiceImpl(BundleContext context) {
        this.bundleContext = context;
        pricingMap = new HashMap<>();
        
        // Standard Locations
        pricingMap.put("Hotel", 15000.0);
        pricingMap.put("Stadium", 22000.0);
        pricingMap.put("Conference Room", 8000.0);
        pricingMap.put("Outdoor Arena", 10000.0);
        pricingMap.put("Restaurant", 5000.0);

        // VIP Locations
        pricingMap.put("Lotus Tower", 550000.0);
        pricingMap.put("VIP Lounge", 35000.0);
        pricingMap.put("Exclusive Hall", 150000.0);
        pricingMap.put("Private Suite", 100000.0);
    }

    @Override
    public boolean processPayment(String location, double amount) {
        // ✅ First, check if the location exists in an active service
        if (!isLocationAvailable(location)) {
            System.out.println("⚠ Error: Attempted payment for an inactive or non-existent location: " + location);
            return false;
        }

        // ✅ Then, check pricing and process the payment
        double price = pricingMap.getOrDefault(location, -1.0);
        if (price == -1.0) {
            System.out.println("⚠ Location not found in pricing!");
            return false;
        } else if (amount >= price) {
            System.out.println("✅ Payment Successful for " + location + " - Paid: Rs." + amount);
            return true;
        } else {
            System.out.println("❌ Payment Failed: Insufficient Amount! (Required: Rs." + price + ", Provided: Rs." + amount + ")");
            return false;
        }
    }

    private boolean isLocationAvailable(String location) {
        ServiceReference<LocationService> serviceRef = bundleContext.getServiceReference(LocationService.class);
        ServiceReference<VIPLocationService> vipServiceRef = bundleContext.getServiceReference(VIPLocationService.class);

        
        List<String> standardLocations = serviceRef != null
                ? Arrays.asList(bundleContext.getService(serviceRef).getAvailableLocations())
                : List.of();  // Empty list if service is not available

        List<String> vipLocations = vipServiceRef != null
                ? Arrays.asList(bundleContext.getService(vipServiceRef).getVIPLocations())
                : List.of();

        return standardLocations.contains(location) || vipLocations.contains(location);
    }
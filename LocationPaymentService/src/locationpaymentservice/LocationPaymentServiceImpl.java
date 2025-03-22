package locationpaymentservice;

import java.util.HashMap;
import java.util.Map;

public class LocationPaymentServiceImpl implements LocationPaymentService {
    private final Map<String, Double> pricingMap;

    public LocationPaymentServiceImpl() {
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
        double price = pricingMap.getOrDefault(location, -1.0);
        if (price == -1.0) {
            System.out.println("⚠️ Location not found!");
            return false;
        } else if (amount >= price) {  // <-- Allow overpayment
            if (amount > price) {
                System.out.println("⚠️ Overpaid! Change: Rs." + (amount - price));
            }
            System.out.println("✅ Payment Successful for " + location + " - Paid: Rs." + amount);
            return true;
        } else {
            System.out.println("❌ Payment Failed: Insufficient Amount! " +
                    "(Required: Rs." + price + ", Provided: Rs." + amount + ")");
            return false;
        }
    }
}
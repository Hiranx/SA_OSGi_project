package locationpricingservice;

import java.util.HashMap;
import java.util.Map;

public class LocationPricingServiceImpl implements LocationPricingService{
	private final Map<String, Double> pricingMap;

    public LocationPricingServiceImpl() {
        pricingMap = new HashMap<>();
        pricingMap.put("Hotel", 15000.0);
        pricingMap.put("Stadium", 22000.0);
        pricingMap.put("Conference Room", 8000.0);
        pricingMap.put("Outdoor Arena", 10000.0);
        pricingMap.put("Restaurant", 5000.0);
        
        
        pricingMap.put("Lotus Tower", 550000.0);
        pricingMap.put("VIP Lounge", 35000.0);
        pricingMap.put("Exclusive Hall", 150000.0);
        pricingMap.put("Private Suite", 100000.0);
    }

    @Override
    public double getLocationPrice(String location) {
        return pricingMap.getOrDefault(location, 0.0);
    }
}


package discountservice;

import java.util.HashMap;
import java.util.Map;

public class DiscountProducerImpl implements DiscountInterface {
    private Map<String, Double> mapDiscount;
    
    public DiscountProducerImpl() {
        mapDiscount = new HashMap<>();
        mapDiscount.put("DevOps workshop 2025", 10.0);
        mapDiscount.put("Get Together SLIIT", 15.0);
        mapDiscount.put("Art Exhibition", 5.0);
    }

    @Override
    public double getDiscount(String eventName) {
        return mapDiscount.getOrDefault(eventName, 0.0);
    }
}

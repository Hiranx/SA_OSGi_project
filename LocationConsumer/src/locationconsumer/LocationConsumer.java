package locationconsumer;

import com.mtit.service.LocationService;

public class LocationConsumer {
    private LocationService locationService;

    public LocationConsumer(LocationService locationService) {
        this.locationService = locationService;
    }

    public void showAvailableLocations() {
        System.out.println("Available Locations:");
        for (String location : locationService.getAvailableLocations()) {
            System.out.println("- " + location);
        }
    }
}
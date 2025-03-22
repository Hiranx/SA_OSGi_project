package viplocationservice;

public class VIPLocationServiceImpl implements VIPLocationService {
    @Override
    public String[] getVIPLocations() {
        return new String[]{"Lotus Tower", "VIP Lounge", "Exclusive Hall", "Private Suite"};
    }
}
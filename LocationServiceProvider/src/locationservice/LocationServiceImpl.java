package locationservice;

public class LocationServiceImpl implements LocationService{

	@Override
	public String[] getAvailableLocations() {
		
		return new String[]{"Hotel", "Stadium", "Conference Room", "Outdoor Arena" , "Restaurant"};
	}

}

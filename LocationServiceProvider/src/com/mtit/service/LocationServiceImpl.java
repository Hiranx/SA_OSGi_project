package com.mtit.service;

public class LocationServiceImpl implements LocationService{

	@Override
	public String[] getAvailableLocations() {
		
		return new String[]{"Hall A", "Hall B", "Conference Room 1", "Outdoor Arena"};
	}

}

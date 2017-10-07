package com.abhishek.assignment.service;

import com.abhishek.assignment.model.GeoLocation;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class GoogleMapService {
	private static final int MIN_LATITUDE = -90;
	private static final int MAX_LATITUDE = 90;
	private static final int MIN_LONGITUDE = -180;
	private static final int MAX_LONGITUDE = 180;

	public GeoLocation getRandomGeoLocation() {
		return GeoLocation.builder().latitude(getNextLatitude()).longitude(getNextLongitude()).build();
	}

	private double getNextLongitude() {
		return ThreadLocalRandom.current().nextDouble(MIN_LONGITUDE, MAX_LONGITUDE);
	}

	private double getNextLatitude() {
		return ThreadLocalRandom.current().nextDouble(MIN_LATITUDE, MAX_LATITUDE);
	}
}

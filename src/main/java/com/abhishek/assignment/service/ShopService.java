package com.abhishek.assignment.service;

import com.abhishek.assignment.model.Shop;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class ShopService {
	@Getter(AccessLevel.PROTECTED)
	private static final Map<String, Shop> SHOPS = new ConcurrentHashMap<>();

	@Autowired
	private GoogleMapService googleMapService;

	public Shop updateShop(final Shop shop) {
		if (Objects.isNull(shop.getGeoLocation()))
			shop.setGeoLocation(googleMapService.getRandomGeoLocation());
		return SHOPS.put(shop.getShopName(), shop);
	}

	public Shop getNearestShop(final Double longitude, final Double latitude) {
		Shop shop = null;
		Double temp = Double.MAX_VALUE;
		for (Map.Entry<String, Shop> shopEntry : SHOPS.entrySet()) {
			Double distance = getShopDistance(shopEntry.getValue(), longitude, latitude);
			if (distance < temp) {
				shop = shopEntry.getValue();
				temp = distance;
			}
		}
		return shop;
	}

	private Double getShopDistance(Shop shop, final Double longitude, final Double latitude) {
		return Math.sqrt(Math.pow(shop.getGeoLocation().getLatitude() - latitude, 2)
				+ Math.pow(shop.getGeoLocation().getLongitude() - longitude, 2));
	}

}

package com.abhishek.assignment.rest;

import com.abhishek.assignment.model.Shop;
import com.abhishek.assignment.service.ShopService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(value = "/assignment/v1/shops")
public class ShopController {
	private Gson gson = new Gson();

	@Autowired
	private ShopService shopService;

	@PostMapping
	public ResponseEntity<?> addOrUpdateShop(@RequestBody String shop) {
		try {
			Shop shopUpdated = shopService.updateShop(gson.fromJson(shop, Shop.class));
			if (Objects.isNull(shopUpdated))
				return new ResponseEntity<>("Shop created.", CREATED);
			else
				return new ResponseEntity<>(shopUpdated, CREATED);
		} catch (Exception ex) {
			return new ResponseEntity<>("Failed to process request", INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	public ResponseEntity<?> getNearestShop(@RequestParam(value = "longitude") final Double longitude,
											@RequestParam(value = "latitude") final Double latitude) {
		try {
			Shop shop = shopService.getNearestShop(longitude, latitude);
			return Objects.isNull(shop) ? new ResponseEntity<>("No Shop found.", NOT_FOUND) :
					new ResponseEntity<>(shopService.getNearestShop(longitude, latitude), FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>("Failed to process request", INTERNAL_SERVER_ERROR);
		}
	}
}

package com.abhishek.assignment.service;

import com.abhishek.assignment.model.GeoLocation;
import com.abhishek.assignment.model.Shop;
import com.abhishek.assignment.model.ShopAddress;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShopServiceTest {
	@Mock
	private GoogleMapService googleMapService;

	@Spy
	@InjectMocks
	private ShopService testObj;

	@Before
	public void setUp() throws Exception {
		when(googleMapService.getRandomGeoLocation()).thenReturn(getMockedGeoLocation());
	}

	@After
	public void tearDown() throws Exception {
		ShopService.getSHOPS().clear();
	}

	@Test
	public void shouldAddShopWhenThereIsNoShopPresentInDataBase() throws Exception {
		assertThat(testObj.updateShop(getShop()), nullValue());
		verify(googleMapService, atMost(1)).getRandomGeoLocation();
	}

	@Test
	public void shouldReplaceShopWhenThereIsShopAlreadyPresentWithSameName() throws Exception {
		assertThat(testObj.updateShop(getShop()), nullValue());
		assertThat(testObj.updateShop(getShop()), allOf(is(instanceOf(Shop.class)),
				hasProperty("shopName", equalTo("TestOne")),
				hasProperty("shopAddress", allOf(is(instanceOf(ShopAddress.class)),
						hasProperty("number", equalTo("12344")),
						hasProperty("postCode", equalTo(412001)))),
				hasProperty("geoLocation", allOf(is(instanceOf(GeoLocation.class)),
						hasProperty("longitude", allOf(
								greaterThanOrEqualTo(-180D), lessThanOrEqualTo(180D))),
						hasProperty("latitude", allOf(
								greaterThanOrEqualTo(-90D), lessThanOrEqualTo(90D)))))));

		verify(googleMapService, atMost(2)).getRandomGeoLocation();
	}

	@Test
	public void getNearestShop() throws Exception {
	}

	private Shop getShop() {
		return Shop.builder().shopName("TestOne")
				.shopAddress(ShopAddress.builder().number("12344").postCode(412001).build())
				.build();
	}

	private GeoLocation getMockedGeoLocation() {
		return GeoLocation.builder().latitude(12D).longitude(45D).build();
	}
}
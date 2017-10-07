package com.abhishek.assignment.rest;

import com.abhishek.assignment.Application;
import com.abhishek.assignment.model.Shop;
import com.abhishek.assignment.model.ShopAddress;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
public class ShopControllerIntegrationTest {

	@LocalServerPort
	private int port;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void shouldCreateNewShopIfNotExists() throws Exception {
		given()
				.body(new Gson().toJson(getShop()))
				.contentType(ContentType.JSON)
				.when()
				.post(createURLWithPort("/assignment/v1/shops"))
				.then()
				.statusCode(201)
				.body(containsString("Shop created"));

	}

	@Test
	public void shouldReplaceExistingShopIfItAlreadyExists() throws Exception {
		given()
				.body(new Gson().toJson(getShop()))
				.contentType(ContentType.JSON)
				.when()
				.post(createURLWithPort("/assignment/v1/shops"))
				.then()
				.statusCode(201)
				.body("shopName", equalTo("TestOne"))
				.body("shopAddress.number", equalTo("12344"))
				.body("shopAddress.postCode", equalTo(412001))
				.body("geoLocation.longitude", notNullValue())
				.body("geoLocation.latitude", notNullValue());

	}

	@Test
	public void shouldNotReturnAnyShopIfDatabaseIsEmpty() throws Exception {
		given()
				.body(new Gson().toJson(getShop()))
				.contentType(ContentType.JSON)
				.queryParam("longitude", 0.0D)
				.queryParam("latitude", 0.0D)
				.when()
				.get(createURLWithPort("/assignment/v1/shops"))
				.then()
				.statusCode(302)
				.and()
				.body("shopName", equalTo("TestOne"))
				.body("shopAddress.number", equalTo("12344"))
				.body("shopAddress.postCode", equalTo(412001));

	}

	private Shop getShop() {
		return Shop.builder().shopName("TestOne")
				.shopAddress(ShopAddress.builder().number("12344").postCode(412001).build())
				.build();
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
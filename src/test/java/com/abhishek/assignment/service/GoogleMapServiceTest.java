package com.abhishek.assignment.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

public class GoogleMapServiceTest {
	private GoogleMapService testObj;

	@Before
	public void setUp() throws Exception {
		testObj = new GoogleMapService();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testForObjectNotNull() throws Exception {
		assertThat(testObj, notNullValue());
	}

	@Test
	public void shouldGetRandomLocation() throws Exception {
		assertThat(testObj.getRandomGeoLocation(), notNullValue());
		assertThat(testObj.getRandomGeoLocation().getLatitude(), allOf(
				greaterThanOrEqualTo(-90D), lessThanOrEqualTo(90D)));
		assertThat(testObj.getRandomGeoLocation().getLongitude(), allOf(
				greaterThanOrEqualTo(-180D), lessThanOrEqualTo(180D)));
	}

}
package com.abhishek.assignment;

import com.abhishek.assignment.rest.ShopControllerIntegrationTest;
import com.abhishek.assignment.service.GoogleMapServiceTest;
import com.abhishek.assignment.service.ShopServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({GoogleMapServiceTest.class, ShopServiceTest.class, ShopControllerIntegrationTest.class})
public class AllTests {
}

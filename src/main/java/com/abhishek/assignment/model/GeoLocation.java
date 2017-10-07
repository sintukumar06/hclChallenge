package com.abhishek.assignment.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeoLocation {
	private double longitude;
	private double latitude;
}

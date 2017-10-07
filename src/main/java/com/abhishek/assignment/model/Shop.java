package com.abhishek.assignment.model;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"shopName"})
public class Shop {
	private String shopName;
	private ShopAddress shopAddress;
	private GeoLocation geoLocation;
}

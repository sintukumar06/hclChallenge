package com.abhishek.assignment.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShopAddress {
	private String number;
	private int postCode;
}

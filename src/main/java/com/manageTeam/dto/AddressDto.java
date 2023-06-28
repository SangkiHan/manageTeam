package com.manageTeam.dto;

import com.manageTeam.entity.Address;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.ToString;

@ApiModel(value = "주소 Dto")
@Getter
@ToString
public class AddressDto {
	private String city;
	private String street;
	private String zipcode;
	
	public AddressDto(String city, String street, String zipcode) {
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
	
	public AddressDto(Address address) {
		this.city = address.getCity();
		this.street = address.getStreet();
		this.zipcode = address.getZipcode();
	}
}

package com.manageTeam.dto;

import com.manageTeam.entity.Address;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.ToString;

@ApiModel(value = "주소 Dto")
@Getter
@ToString
public class AddressDto {
	/**
	 * 도시
	 */
	private String city;
	/**
	 * 도로명
	 */
	private String street;
	/**
	 * 우편번호
	 */
	private String zipcode;
	/**
	 * Entity to Dto Constructor
	 */
	public AddressDto(String city, String street, String zipcode) {
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
	/**
	 * Entity to Dto Constructor
	 */
	public AddressDto(Address address) {
		this.city = address.getCity();
		this.street = address.getStreet();
		this.zipcode = address.getZipcode();
	}
}

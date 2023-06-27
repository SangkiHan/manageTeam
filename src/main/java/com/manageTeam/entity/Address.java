package com.manageTeam.entity;

import javax.persistence.Embeddable;

import com.manageTeam.dto.AddressDto;

import lombok.Getter;

/*
 * 주소
 * */
@Embeddable
@Getter
public class Address {
	
	private String city;
	private String street;
	private String zipcode;
	
	public Address() {}
	
	public Address(AddressDto address) {
		this.city = address.getCity();
		this.street = address.getStreet();
		this.zipcode = address.getZipcode();
	}
}

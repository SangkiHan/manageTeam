package com.manageTeam.global.entity;

import javax.persistence.Embeddable;

import com.manageTeam.global.dto.AddressDto;

import lombok.Getter;

/**
 * @description 주소 Embeddable 주소가 필요한 Entity에 추가하여 사용한다.
 * @author skhan
 */
@Embeddable
@Getter
public class Address {
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
	
	public Address() {}
	
	/**
	 * Dto to Entity Constructor
	 */
	public Address(AddressDto address) {
		this.city = address.getCity();
		this.street = address.getStreet();
		this.zipcode = address.getZipcode();
	}
}

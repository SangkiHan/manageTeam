package com.manageTeam.global.dto;

import com.manageTeam.global.entity.Address;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "주소 Dto")
@Getter
@Setter
@NoArgsConstructor
public class AddressDto {

	@ApiParam(value = "도시")
	private String city;

	@ApiParam(value = "도로명")
	private String street;

	@ApiParam(value = "우편번호")
	private String zipcode;

	@Builder
	private AddressDto(String city, String street, String zipcode) {
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}

	public Address toEntity(){
		return Address.builder()
			.city(city)
			.street(street)
			.zipcode(zipcode)
			.build();
	}
}

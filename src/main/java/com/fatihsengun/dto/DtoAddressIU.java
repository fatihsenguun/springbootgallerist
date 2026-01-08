package com.fatihsengun.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAddressIU {

	@NotEmpty(message = "City Cannot be Empty")
	private String city;
	
	@NotEmpty(message = "District Cannot be Empty")
	private String district;
	
	@NotEmpty(message = "Neighborhood Cannot be Empty")
	private String neighborhood;
	
	@NotEmpty(message = "Street Cannot be Empty")
	private String street;
}

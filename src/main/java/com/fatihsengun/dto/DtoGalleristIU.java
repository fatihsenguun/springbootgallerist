package com.fatihsengun.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DtoGalleristIU {

	@NotEmpty
	private String galleristName;

	@NotEmpty
	private DtoAddressIU address;

}

package com.fatihsengun.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoGallerist extends DtoBase {

	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;

	@NotEmpty
	private DtoAddress address;
}
